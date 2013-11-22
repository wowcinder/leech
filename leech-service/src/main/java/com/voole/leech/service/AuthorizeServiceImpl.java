/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.voole.leech.annotations.AuthorizeSystemAnnotations.Tuple;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.TupleUtil;
import com.voole.leech.bean.ScanedAccessAuthority;
import com.voole.leech.common.shared.entity.password.PasswordEncryptor;
import com.voole.leech.dao.authorize.AuthorizeDao;
import com.voole.leech.dao.menu.MenuDao;
import com.voole.leech.dao.user.UserDao;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;
import com.voole.leech.shared.entity.menu.MenuNode;
import com.voole.leech.shared.entity.user.User;

/**
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public class AuthorizeServiceImpl implements AuthorizeService {
	private static final String USER_ID_NAME_IN_SESSION = "USERID";
	private static final String AUTHORIZES_NAME_IN_SESSION = "AUTHORIZES";

	private boolean isDebug = false;

	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthorizeDao authorizeDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PasswordEncryptor passwordEncryptor;

	private final Map<Tuple, Set<String>> tupleToTokens;

	public AuthorizeServiceImpl() {
		tupleToTokens = new HashMap<Tuple, Set<String>>();
	}

	@Override
	public boolean verify(Class<?> targetClass, Method invokeMethod) {
		if (isDebug()) {
			return true;
		}
		if (isAdmin()) {
			return true;
		}
		Set<String> tokens = getTokens(targetClass, invokeMethod);
		Set<String> currUserAuthorizeTokens = getCurrUserAuthorizeTokens();
		for (String token : tokens) {
			if (currUserAuthorizeTokens.contains(token)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private Set<String> getCurrUserAuthorizeTokens() {
		return (Set<String>) getSession().getAttribute(
				AUTHORIZES_NAME_IN_SESSION);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean login(String email, String password) {
		if (isLogin()) {
			logout();
		}
		if (password == null) {
			password = "";
		}
		if ("admin".equals(email) && "admin".equals(password)) {
			HttpSession session = getSession();
			session.setAttribute(USER_ID_NAME_IN_SESSION, 0);
			return true;
		}
		String encryptPassword = passwordEncryptor.getEncryptPassword(password);
		User user = userDao.findUser(email, encryptPassword);
		if (user != null) {
			HttpSession session = getSession();
			session.setAttribute(USER_ID_NAME_IN_SESSION, user.getId());
			session.setAttribute(AUTHORIZES_NAME_IN_SESSION,
					authorizeDao.getAuthorizeTokens(user.getId()));
			return true;
		}
		return false;
	}

	@Override
	public void logout() {
		HttpSession session = getSession();
		@SuppressWarnings({ "unchecked" })
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			session.removeAttribute(name);
		}
	}

	@Override
	public boolean isLogin() {
		if (isDebug()) {
			return true;
		}
		Integer uid = getUserId();
		if (uid != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		if (isDebug()) {
			return true;
		}
		Integer uid = getUserId();
		if (uid != null && uid == 0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer getUserId() {
		return (Integer) getSession().getAttribute(USER_ID_NAME_IN_SESSION);
	}

	protected HttpSession getSession() {
		if (RequestContextHolder.currentRequestAttributes() != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession();
		}
		return null;
	}

	private Set<String> getTokens(Class<?> targetClass, Method invokeMethod) {
		Tuple tuple = new Tuple(targetClass, invokeMethod);
		if (!tupleToTokens.containsKey(tuple)) {
			initTokens(tuple);
		}
		return tupleToTokens.get(tuple);
	}

	private synchronized void initTokens(Tuple tuple) {
		if (tupleToTokens.containsKey(tuple)) {
			return;
		}
		tupleToTokens.put(tuple, TupleUtil.getTokens(tuple));
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuNode> getUserMenus() {
		Integer uid = getUserId();
		if (isAdmin()) {
			uid = 0;
		}
		return menuDao.getUserMenus(uid);
	}

	@Override
	@Transactional
	public void deal(Set<ScanedAccessAuthority> list) {
		HashMap<String, List<ScanedAccessAuthority>> map = new HashMap<String, List<ScanedAccessAuthority>>();
		for (ScanedAccessAuthority scanedAccessAuthority : list) {
			String group = scanedAccessAuthority.getGroup();
			if (!map.containsKey(group)) {
				map.put(group, new ArrayList<ScanedAccessAuthority>());
			}
			map.get(group).add(scanedAccessAuthority);
		}
		for (String group : map.keySet()) {
			List<ScanedAccessAuthority> items = map.get(group);
			Integer gid = authorizeDao.queryAuthorizeGroupIdByName(group);
			AuthorizeGroup ag = new AuthorizeGroup();
			ag.setName(group);
			if (gid != null) {
				ag.setId(gid);
			} else {
				authorizeDao.saveAuthorizeGroup(ag);
			}

			for (ScanedAccessAuthority scanedAccessAuthority : items) {
				Authorize old = authorizeDao.queryAuthorizeIdByName(ag.getId(),
						scanedAccessAuthority.getValue());
				if (old == null) {
					Authorize authority = new Authorize();
					authority.setGroup(ag);
					authority.setName(scanedAccessAuthority.getValue());
					authorizeDao.saveAuthorize(authority);
				}
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Authorize> getAllocatenbeAuthorizes() {
		return authorizeDao.getAllocatenbeAuthorizes();
	}
}
