/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.gwt.client.service.AuthorizeRpcService;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForMenu;
import com.voole.leech.service.AuthorizeService;
import com.voole.leech.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Service
public class AuthorizeRpcServiceImpl implements AuthorizeRpcService {
	@Autowired
	private AuthorizeService authorizeService;

	@Override
	@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForMenu.GROUP, value = AuthorizeAnnotationNamesForMenu.ALL)
	public List<Authorize> getAllocatenbeAuthorizes() {
		return authorizeService.getAllocatenbeAuthorizes();
	}

}
