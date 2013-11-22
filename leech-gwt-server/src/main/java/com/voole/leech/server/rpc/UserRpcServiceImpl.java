/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.rpc;

import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.ADD_USER;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.ADD_USER_GROUP;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.DELETE_USER;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.DELETE_USER_GROUP;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.GROUP;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.QUERY_USER;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.QUERY_USER_GROUP;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.UPDATE_USER;
import static com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForUser.UPDATE_USER_GROUP;

import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import com.voole.leech.gwt.client.service.UserRpcService;
import com.voole.leech.service.UserService;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.util.CinderValidator;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Service
@AuthorizeGroupAnnotation(GROUP)
public class UserRpcServiceImpl implements UserRpcService {
	@Autowired
	private UserService userService;

	@Override
	@AuthorizeAnnotation(ADD_USER)
	public User saveUser(User user, String password) {
		user.setPassword(password);
		CinderValidator.validate(user);
		return userService.saveUser(user);
	}

	@Override
	@AuthorizeAnnotation(UPDATE_USER)
	public User updateUser(User user, String password) {
		user.setPassword(password);
		CinderValidator.validate(user);
		return userService.updateUser(user);
	}

	@Override
	@AuthorizeAnnotation(ADD_USER_GROUP)
	public UserGroup saveUserGroup(UserGroup userGroup) {
		return userService.saveUserGroup(userGroup);
	}

	@Override
	@AuthorizeAnnotation(UPDATE_USER_GROUP)
	public UserGroup updateUserGroup(UserGroup userGroup) {
		return userService.updateUserGroup(userGroup);
	}

	@Override
	@AuthorizeAnnotation(UPDATE_USER)
	public List<UserGroup> getUserGroupListForCombox() {
		return userService.getUserGroupListForCombox();
	}

	@Override
	@AuthorizeAnnotation(QUERY_USER)
	public PagingLoadResult<User> pagingUser(EtlPagingLoadConfigBean config)
			throws SharedException {
		return userService.pagingUser(config);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotation(DELETE_USER)
	public void deleteUsers(List<Integer> ids) throws SharedException {
		userService.deleteUsers(ids);
	}

	@Override
	@AuthorizeAnnotation(DELETE_USER_GROUP)
	public void deleteUserGroups(List<Integer> ids) throws SharedException {
		userService.deleteUserGroups(ids);
	}

	@Override
	@AuthorizeAnnotation(QUERY_USER_GROUP)
	public PagingLoadResult<UserGroup> pagingUserGroup(
			EtlPagingLoadConfigBean config) throws SharedException {
		return userService.pagingUserGroup(config);
	}

	@Override
	@AuthorizeAnnotation(QUERY_USER)
	@AuthorizeAnnotations({ @AuthorizeAnnotation("修改用户") })
	public List<Authorize> getUserExtraAuthorizes(Integer uid) {
		return userService.getUserExtraAuthorizes(uid);
	}

	@Override
	@AuthorizeAnnotation(QUERY_USER_GROUP)
	@AuthorizeAnnotations({ @AuthorizeAnnotation("修改用户组") })
	public List<Authorize> getUserGroupAuthorizes(Integer uid)
			throws SharedException {
		return userService.getUserGroupAuthorizes(uid);
	}
}
