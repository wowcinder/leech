package com.voole.leech.server.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.leech.server.controller.rpc.SpringMVCGwtRpcProxyUtilInterface;

@Controller
@RequestMapping("etl/rpc")
public class GwtRpcController {
	@Autowired
	private SpringMVCGwtRpcProxyUtilInterface proxyUtil;

	public GwtRpcController() {
	}

	@RequestMapping("{rpcPath:[^\\.]+\\.rpc}")
	public void rpc(@PathVariable String rpcPath, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SerializationException {
		if (proxyUtil.getService(rpcPath) != null) {
			proxyUtil.getService(rpcPath).processPost(request, response);
		}

	}
}
