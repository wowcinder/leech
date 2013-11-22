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
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@RemoteServiceRelativePath("rpc/log_model_c.rpc")
public interface CTypeLogModelMetaRpcService extends RemoteService {
	CTypeLogModel saveLogModel(CTypeLogModel logModel) throws SharedException,
			ConstraintViolationException;

	CTypeLogModel updateLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException;

	void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<CTypeLogModel> pagingLogModel(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	List<CTypeLogModel> getLogModels() throws SharedException,
			ConstraintViolationException;

	ValidationSupport dummy();

	CTypeLogModelVersion saveLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException;

	CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException;

	void deleteLogModelVersions(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<CTypeLogModelVersion> pagingLogModelVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	List<CTypeLogModelVersion> getLogModelVersions() throws SharedException,
			ConstraintViolationException;

	CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;

	CTypeLogModelSimpleColumn saveLogModelSimpleColumn(
			CTypeLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException;

	CTypeLogModelSimpleColumn updateLogModelSimpleColumn(
			CTypeLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException;

	CTypeLogModelGroupColumn saveLogModelGroupColumn(
			CTypeLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException;

	CTypeLogModelGroupColumn updateLogModelGroupColumn(
			CTypeLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException;

	void deleteLogModelColumn(Integer id) throws SharedException,
			ConstraintViolationException;

	CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr)
			throws SharedException, ConstraintViolationException;
}
