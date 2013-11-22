package com.voole.leech.server.controller.rpc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.leech.util.BeanFinder;
import com.voole.leech.util.ClassScaner;

@Service
public class SpringMVCGwtRpcProxyUtil implements
		SpringMVCGwtRpcProxyUtilInterface {
	@Autowired
	private ServletContext context;

	private final ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy> serviceMap;
	private final Map<String, Class<?>> pathToClass;

	public SpringMVCGwtRpcProxyUtil() {
		serviceMap = new ConcurrentHashMap<Class<? extends RemoteService>, SpringMVCGwtRpcProxy>();
		pathToClass = new HashMap<String, Class<?>>();
		initPathToClass();
	}

	protected void initPathToClass() {
		try {
			ClassScaner scaner = new ClassScaner("xdata.etl.cinder.gwt.client.service");
			for (Class<?> clazz : scaner.getClazzes()) {
				if (clazz.isAnnotationPresent(RemoteServiceRelativePath.class)) {
					RemoteServiceRelativePath path = clazz
							.getAnnotation(RemoteServiceRelativePath.class);
					pathToClass.put(getPath(path.value()), clazz);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getPath(String rawPath) {
		return rawPath.substring(rawPath.lastIndexOf('/') + 1);
	}

	@Override
	public SpringMVCGwtRpcProxy getService(Class<? extends RemoteService> clazz) {
		if (!serviceMap.contains(clazz)) {
			createServiceProxy(clazz);
		}
		return serviceMap.get(clazz);
	}

	private synchronized void createServiceProxy(
			Class<? extends RemoteService> clazz) {
		if (serviceMap.contains(clazz)) {
			return;
		}
		serviceMap.put(clazz,
				new SpringMVCGwtRpcProxy(BeanFinder.getBean(clazz), context));
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpringMVCGwtRpcProxy getService(String rpcPath) {
		if (!pathToClass.containsKey(rpcPath)) {
			return null;
		}
		return getService((Class<? extends RemoteService>) pathToClass
				.get(rpcPath));

	}
}
