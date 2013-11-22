/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.dao.user.UserDao;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	@Transactional
	public UserGroup saveUserGroup(UserGroup userGroup) {
		return userDao.saveUserGroup(userGroup);
	}

	@Override
	@Transactional
	public UserGroup updateUserGroup(UserGroup userGroup) {
		return userDao.updateUserGroup(userGroup);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserGroup> getUserGroupListForCombox() {
		return userDao.getUserGroupListForCombox();
	}

	@Override
	@Transactional(readOnly = true)
	public PagingLoadResult<User> pagingUser(EtlPagingLoadConfigBean config)
			throws SharedException {
		return userDao.pagingUser(config);
	}

	@Override
	@Transactional
	public void deleteUsers(List<Integer> ids) throws SharedException {
		userDao.deleteUsers(ids);
	}

	@Override
	@Transactional
	public void deleteUserGroups(List<Integer> ids) throws SharedException {
		userDao.deleteUserGroups(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public PagingLoadResult<UserGroup> pagingUserGroup(
			EtlPagingLoadConfigBean config) throws SharedException {
		return userDao.pagingUserGroup(config);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Authorize> getUserExtraAuthorizes(Integer uid)
			throws SharedException {
		return userDao.getUserExtraAuthorizes(uid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Authorize> getUserGroupAuthorizes(Integer uid)
			throws SharedException {
		return userDao.getUserGroupAuthorizes(uid);
	}

}
