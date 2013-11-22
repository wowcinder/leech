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
import com.voole.leech.gwt.client.service.CTypeLogModelMetaRpcService;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForLogModelMeta;
import com.voole.leech.service.CTypeLogModelMetaService;
import com.voole.leech.service.SimpleService;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Service
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForLogModelMeta.GROUP)
public class CTypeLogModelMetaRpcServiceImpl implements
		CTypeLogModelMetaRpcService {
	@Autowired
	private SimpleService simpleService;
	@Autowired
	private CTypeLogModelMetaService cTypeLogModelMetaService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL)
	public CTypeLogModel saveLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(logModel);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL)
	public CTypeLogModel updateLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(logModel);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.DELETE_LOG_MODEL)
	public void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(CTypeLogModel.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL)
	public PagingLoadResult<CTypeLogModel> pagingLogModel(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(CTypeLogModel.class, config);
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
	public List<CTypeLogModel> getLogModels() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(CTypeLogModel.class);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION)
	public CTypeLogModelVersion saveLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.updateLogModelVersion(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.DELETE_LOG_MODEL_VERSION)
	public void deleteLogModelVersions(List<Integer> ids)
			throws SharedException, ConstraintViolationException {
		simpleService.delete(CTypeLogModelVersion.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION)
	public PagingLoadResult<CTypeLogModelVersion> pagingLogModelVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(CTypeLogModelVersion.class, config);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION)
	public List<CTypeLogModelVersion> getLogModelVersions()
			throws SharedException, ConstraintViolationException {
		return simpleService.get(CTypeLogModelVersion.class);
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaService.getLogModelVersionRootNode(versionId);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION)
	public CTypeLogModelSimpleColumn saveLogModelSimpleColumn(
			CTypeLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.saveLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelSimpleColumn updateLogModelSimpleColumn(
			CTypeLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.updateLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION)
	public CTypeLogModelGroupColumn saveLogModelGroupColumn(
			CTypeLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.saveLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelGroupColumn updateLogModelGroupColumn(
			CTypeLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.updateLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.DELETE_LOG_MODEL_VERSION)
	public void deleteLogModelColumn(Integer id) throws SharedException,
			ConstraintViolationException {
		cTypeLogModelMetaService.deleteLogModelColumn(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaService.move(prev, parent, curr);
	}

}
