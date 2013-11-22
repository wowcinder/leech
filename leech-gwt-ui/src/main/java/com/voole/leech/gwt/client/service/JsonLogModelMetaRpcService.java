/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@RemoteServiceRelativePath("rpc/log_model_json.rpc")
public interface JsonLogModelMetaRpcService extends RemoteService {
	JsonLogModel saveLogModel(JsonLogModel logModel) throws SharedException,
			ConstraintViolationException;

	JsonLogModel updateLogModel(JsonLogModel logModel)
			throws SharedException, ConstraintViolationException;

	void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<JsonLogModel> pagingLogModel(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	List<JsonLogModel> getLogModels() throws SharedException,
			ConstraintViolationException;

	ValidationSupport dummy();

	JsonLogModelVersion saveLogModelVersion(
			JsonLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException;

	JsonLogModelVersion updateLogModelVersion(
			JsonLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException;

	void deleteLogModelVersions(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<JsonLogModelVersion> pagingLogModelVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	List<JsonLogModelVersion> getLogModelVersions() throws SharedException,
			ConstraintViolationException;

	JsonLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;

	JsonLogModelSimpleColumn saveLogModelSimpleColumn(
			JsonLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException;

	JsonLogModelSimpleColumn updateLogModelSimpleColumn(
			JsonLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException;

	JsonLogModelGroupColumn saveLogModelGroupColumn(
			JsonLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException;

	JsonLogModelGroupColumn updateLogModelGroupColumn(
			JsonLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException;

	void deleteLogModelColumn(Integer id) throws SharedException,
			ConstraintViolationException;

}
