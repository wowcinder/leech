/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.util.List;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public interface UserService {
	User saveUser(User user);

	User updateUser(User user);

	UserGroup saveUserGroup(UserGroup userGroup);

	UserGroup updateUserGroup(UserGroup userGroup);

	List<UserGroup> getUserGroupListForCombox();

	PagingLoadResult<User> pagingUser(EtlPagingLoadConfigBean config)
			throws SharedException;

	void deleteUsers(List<Integer> ids) throws SharedException;

	void deleteUserGroups(List<Integer> ids) throws SharedException;

	PagingLoadResult<UserGroup> pagingUserGroup(EtlPagingLoadConfigBean config)
			throws SharedException;
	
	List<Authorize> getUserExtraAuthorizes(Integer uid) throws SharedException;
	
	List<Authorize> getUserGroupAuthorizes(Integer uid) throws SharedException;
}
