package com.voole.leech.server.controller.rpc;

import static com.google.gwt.user.client.rpc.RpcRequestBuilder.STRONG_NAME_HEADER;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;

public class SpringMVCGwtRpcProxy {

	private final RemoteService delegate;
	private final SpringMVCGwtRpcSerializationPolicyProvider provider;
	private final ServletContext servletContext;

	public SpringMVCGwtRpcProxy(RemoteService service,
			ServletContext servletContext) {
		this.delegate = service;
		this.servletContext = servletContext;
		this.provider = new SpringMVCGwtRpcSerializationPolicyProvider();
	}

	public final void processPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		// Read the request fully.
		//
		String requestPayload = readContent(request);

		// Let subclasses see the serialized request.
		//
		onBeforeRequestDeserialized(requestPayload);

		// Invoke the core dispatching logic, which returns the serialized
		// result.
		//
		String responsePayload = processCall(requestPayload, request);

		// Let subclasses see the serialized response.
		//
		onAfterResponseSerialized(responsePayload);

		// Write the response.
		//
		writeResponse(request, response, responsePayload);
	}

	private void writeResponse(HttpServletRequest request,
			HttpServletResponse response, String responsePayload)
			throws IOException {
		boolean gzipEncode = RPCServletUtils.acceptsGzipEncoding(request)
				&& shouldCompressResponse(request, response, responsePayload);

		RPCServletUtils.writeResponse(servletContext, response,
				responsePayload, gzipEncode);
	}

	protected boolean shouldCompressResponse(HttpServletRequest request,
			HttpServletResponse response, String responsePayload) {
		return RPCServletUtils
				.exceedsUncompressedContentLengthLimit(responsePayload);
	}

	public String processCall(String payload, HttpServletRequest request)
			throws SerializationException {
		// First, check for possible XSRF situation
		checkPermutationStrongName(request);

		try {
			RPCRequest rpcRequest = RPC.decodeRequest(payload,
					delegate.getClass(), provider);
			onAfterRequestDeserialized(rpcRequest);
			return RPC.invokeAndEncodeResponse(delegate,
					rpcRequest.getMethod(), rpcRequest.getParameters(),
					rpcRequest.getSerializationPolicy(), rpcRequest.getFlags());
		} catch (IncompatibleRemoteServiceException ex) {
			servletContext
					.log("An IncompatibleRemoteServiceException was thrown while processing this call.",
							ex);
			return RPC.encodeResponseForFailure(null, ex);
		} catch (RpcTokenException tokenException) {
			servletContext
					.log("An RpcTokenException was thrown while processing this call.",
							tokenException);
			return RPC.encodeResponseForFailure(null, tokenException);
		}
	}

	protected void checkPermutationStrongName(HttpServletRequest request)
			throws SecurityException {
		if (getPermutationStrongName(request) == null) {
			throw new SecurityException(
					"Blocked request without GWT permutation header (XSRF attack?)");
		}
	}

	protected final String getPermutationStrongName(HttpServletRequest request) {
		return request.getHeader(STRONG_NAME_HEADER);
	}

	protected String readContent(HttpServletRequest request)
			throws ServletException, IOException {
		return RPCServletUtils.readContentAsGwtRpc(request);
	}

	protected void onAfterResponseSerialized(String serializedResponse) {
	}

	protected void onBeforeRequestDeserialized(String serializedRequest) {
	}

	protected void onAfterRequestDeserialized(RPCRequest rpcRequest) {
	}

	class SpringMVCGwtRpcSerializationPolicyProvider implements
			SerializationPolicyProvider {

		private final Map<String, SerializationPolicy> serializationPolicyCache;

		public SpringMVCGwtRpcSerializationPolicyProvider() {
			serializationPolicyCache = new HashMap<String, SerializationPolicy>();
		}

		private SerializationPolicy loadSerializationPolicy(
				String moduleBaseURL, String strongName) {
			String contextPath = servletContext.getContextPath();

			String modulePath = null;
			if (moduleBaseURL != null) {
				try {
					modulePath = new URL(moduleBaseURL).getPath();
				} catch (MalformedURLException ex) {
					// log the information, we will default
					servletContext.log("Malformed moduleBaseURL: "
							+ moduleBaseURL, ex);
				}
			}

			SerializationPolicy serializationPolicy = null;

			/*
			 * Check that the module path must be in the same web app as the
			 * servlet itself. If you need to implement a scheme different than
			 * this, override this method.
			 */
			if (modulePath == null || !modulePath.startsWith(contextPath)) {
				String message = "ERROR: The module path requested, "
						+ modulePath
						+ ", is not in the same web application as this servlet, "
						+ contextPath
						+ ".  Your module may not be properly configured or your client and server code maybe out of date.";
				servletContext.log(message);
			} else {
				// Strip off the context path from the module base URL. It
				// should be
				// a
				// strict prefix.
				String contextRelativePath = modulePath.substring(contextPath
						.length());

				String serializationPolicyFilePath = SerializationPolicyLoader
						.getSerializationPolicyFileName(contextRelativePath
								+ strongName);

				// Open the RPC resource file and read its contents.
				InputStream is = servletContext
						.getResourceAsStream(serializationPolicyFilePath);
				try {
					if (is != null) {
						try {
							serializationPolicy = SerializationPolicyLoader
									.loadFromStream(is, null);
						} catch (ParseException e) {
							servletContext
									.log("ERROR: Failed to parse the policy file '"
											+ serializationPolicyFilePath + "'",
											e);
						} catch (IOException e) {
							servletContext
									.log("ERROR: Could not read the policy file '"
											+ serializationPolicyFilePath + "'",
											e);
						}
					} else {
						String message = "ERROR: The serialization policy file '"
								+ serializationPolicyFilePath
								+ "' was not found; did you forget to include it in this deployment?";
						servletContext.log(message);
					}
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							// Ignore this error
						}
					}
				}
			}

			return serializationPolicy;
		}

		@Override
		public SerializationPolicy getSerializationPolicy(String moduleBaseURL,
				String strongName) {
			SerializationPolicy serializationPolicy = getCachedSerializationPolicy(
					moduleBaseURL, strongName);
			if (serializationPolicy != null) {
				return serializationPolicy;
			}

			serializationPolicy = doGetSerializationPolicy(moduleBaseURL,
					strongName);

			if (serializationPolicy == null) {
				// Failed to get the requested serialization policy; use the
				// default
				servletContext
						.log("WARNING: Failed to get the SerializationPolicy '"
								+ strongName
								+ "' for module '"
								+ moduleBaseURL
								+ "'; a legacy, 1.3.3 compatible, serialization policy will be used.  You may experience SerializationExceptions as a result.");
				serializationPolicy = RPC.getDefaultSerializationPolicy();
			}

			// This could cache null or an actual instance. Either way we will
			// not
			// attempt to lookup the policy again.
			putCachedSerializationPolicy(moduleBaseURL, strongName,
					serializationPolicy);

			return serializationPolicy;
		}

		protected SerializationPolicy doGetSerializationPolicy(
				String moduleBaseURL, String strongName) {
			return loadSerializationPolicy(moduleBaseURL, strongName);
		}

		private SerializationPolicy getCachedSerializationPolicy(
				String moduleBaseURL, String strongName) {
			synchronized (serializationPolicyCache) {
				return serializationPolicyCache.get(moduleBaseURL + strongName);
			}
		}

		private void putCachedSerializationPolicy(String moduleBaseURL,
				String strongName, SerializationPolicy serializationPolicy) {
			synchronized (serializationPolicyCache) {
				serializationPolicyCache.put(moduleBaseURL + strongName,
						serializationPolicy);
			}
		}
	}
}
