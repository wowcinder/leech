/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.controller.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public interface SpringMVCGwtRpcProxyUtilInterface {
	public SpringMVCGwtRpcProxy getService(Class<? extends RemoteService> clazz);

	public SpringMVCGwtRpcProxy getService(String rpcPath);
}
