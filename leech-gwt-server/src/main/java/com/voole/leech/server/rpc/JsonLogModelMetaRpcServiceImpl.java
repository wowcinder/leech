/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.rpc;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import com.voole.leech.gwt.client.service.JsonLogModelMetaRpcService;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForLogModelMeta;
import com.voole.leech.service.JsonLogModelMetaService;
import com.voole.leech.service.SimpleService;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Service
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForLogModelMeta.GROUP)
public class JsonLogModelMetaRpcServiceImpl
		implements JsonLogModelMetaRpcService {
	@Autowired
	private SimpleService simpleService;
	@Autowired
	private JsonLogModelMetaService logModelMetaService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL)
	public JsonLogModel saveLogModel(JsonLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(logModel);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL)
	public JsonLogModel updateLogModel(JsonLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(logModel);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.DELETE_LOG_MODEL)
	public void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(JsonLogModel.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL)
	public PagingLoadResult<JsonLogModel> pagingLogModel(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(JsonLogModel.class, config);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public List<JsonLogModel> getLogModels() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(JsonLogModel.class);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION)
	public JsonLogModelVersion saveLogModelVersion(
			JsonLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public JsonLogModelVersion updateLogModelVersion(
			JsonLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return logModelMetaService.updateLogModelVersion(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.DELETE_LOG_MODEL_VERSION)
	public void deleteLogModelVersions(List<Integer> ids)
			throws SharedException, ConstraintViolationException {
		simpleService.delete(JsonLogModelVersion.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION)
	public PagingLoadResult<JsonLogModelVersion> pagingLogModelVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(JsonLogModelVersion.class, config);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION)
	public List<JsonLogModelVersion> getLogModelVersions()
			throws SharedException, ConstraintViolationException {
		return simpleService.get(JsonLogModelVersion.class);
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public JsonLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return logModelMetaService.getLogModelVersionRootNode(versionId);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION)
	public JsonLogModelSimpleColumn saveLogModelSimpleColumn(
			JsonLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException {
		return logModelMetaService.saveLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public JsonLogModelSimpleColumn updateLogModelSimpleColumn(
			JsonLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException {
		return logModelMetaService.updateLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION)
	public JsonLogModelGroupColumn saveLogModelGroupColumn(
			JsonLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException {
		return logModelMetaService.saveLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public JsonLogModelGroupColumn updateLogModelGroupColumn(
			JsonLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException {
		return logModelMetaService.updateLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.DELETE_LOG_MODEL_VERSION)
	public void deleteLogModelColumn(Integer id) throws SharedException,
			ConstraintViolationException {
		logModelMetaService.deleteLogModelColumn(id);
	}

}
