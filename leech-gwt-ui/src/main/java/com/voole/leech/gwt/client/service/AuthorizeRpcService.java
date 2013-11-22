/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@RemoteServiceRelativePath("rpc/authorize.rpc")
public interface AuthorizeRpcService extends RemoteService {
	List<Authorize> getAllocatenbeAuthorizes() throws SharedException;
}
