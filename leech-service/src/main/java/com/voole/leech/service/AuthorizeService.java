/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import com.voole.leech.bean.ScanedAccessAuthority;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.menu.MenuNode;

/**
 * 认证，授权服务
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public interface AuthorizeService {
	boolean verify(Class<?> targetClass, Method invokeMethod);

	boolean login(String email, String password);

	void logout();

	boolean isLogin();

	boolean isAdmin();

	Integer getUserId();

	List<MenuNode> getUserMenus();

	void deal(Set<ScanedAccessAuthority> list);

	/**
	 * @return
	 */
	List<Authorize> getAllocatenbeAuthorizes();
}
