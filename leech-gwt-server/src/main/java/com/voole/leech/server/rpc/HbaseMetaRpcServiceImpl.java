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
import com.voole.leech.gwt.client.service.HbaseMetaRpcService;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForHbaseMeta;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForHbaseQuery;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForLogModelMeta;
import com.voole.leech.service.HbaseMetaService;
import com.voole.leech.service.SimpleService;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

@Service
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForHbaseMeta.GROUP)
public class HbaseMetaRpcServiceImpl implements HbaseMetaRpcService {
	@Autowired
	private HbaseMetaService hbaseMetaService;
	@Autowired
	private SimpleService simpleService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.ADD_TABLE)
	public HbaseTable saveHbaseTable(HbaseTable hbaseTable)
			throws SharedException, ConstraintViolationException {
		return getService().saveHbaseTable(hbaseTable);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.DELETE_TABLE)
	public void deleteHbaseTable(Integer id) throws SharedException,
			ConstraintViolationException {
		getService().deleteHbaseTable(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.UPDATE_TABLE)
	public HbaseTable updateHbaseTable(HbaseTable hbaseTable)
			throws SharedException, ConstraintViolationException {
		return getService().updateHbaseTable(hbaseTable);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.QUERY_TABLE)
	public PagingLoadResult<HbaseTable> pagingHbaseTable(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getService().pagingHbaseTable(config);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.ADD_TABLE_VERSION)
	public HbaseTableVersion saveHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException {
		return getService().saveHbaseTableVersion(hbaseTableVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.DELETE_TABLE_VERSION)
	public void deleteHbaseTableVersion(Integer id) throws SharedException,
			ConstraintViolationException {
		getService().deleteHbaseTableVersion(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.UPDATE_TABLE_VERSION)
	public HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException {
		return getService().updateHbaseTableVersion(hbaseTableVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.QUERY_TABLE_VERSION)
	public PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getService().pagingHbaseTableVersion(config);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.ADD_TABLE_COLUMN)
	public HbaseTableColumn saveHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) throws SharedException,
			ConstraintViolationException {
		return getService().saveHbaseTableColumn(hbaseTableColumn);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.DELETE_TABLE_COLUMN)
	public void deleteHbaseTableColumn(Integer id) throws SharedException,
			ConstraintViolationException {
		getService().deleteHbaseTableColumn(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.UPDATE_TABLE_COLUMN)
	public HbaseTableColumn updateHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) throws SharedException,
			ConstraintViolationException {
		return getService().updateHbaseTableColumn(hbaseTableColumn);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.QUERY_TABLE_COLUMN)
	public PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getService().pagingHbaseTableColumn(config);
	}

	public HbaseMetaService getService() {
		return hbaseMetaService;
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.DELETE_TABLE)
	public void deleteHbaseTables(List<Integer> ids) {
		getService().delete(HbaseTable.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.DELETE_TABLE_VERSION)
	public void deleteHbaseTableVersions(List<Integer> ids) {
		getService().delete(HbaseTableVersion.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.DELETE_TABLE_COLUMN)
	public void deleteHbaseTableColumns(List<Integer> ids) {
		getService().delete(HbaseTableColumn.class, ids);
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.ADD_TABLE_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.UPDATE_TABLE_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.QUERY_TABLE_VERSION) })
	public List<HbaseTable> getHbaseTablesForCombox() {
		return getService().getHbaseTablesForCombox();
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.ADD_TABLE_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.UPDATE_TABLE_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseMeta.QUERY_TABLE_VERSION),
			@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForLogModelMeta.GROUP, value = AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForLogModelMeta.GROUP, value = AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForLogModelMeta.GROUP, value = AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public List<HbaseTableColumn> getColumnsByVersionId(Integer id) {
		return hbaseMetaService.getColumnsByVersionId(id);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForHbaseQuery.GROUP, value = AuthorizeAnnotationNamesForHbaseQuery.QUERY)
	public List<HbaseTableColumn> getTableAllColumns(String table,
			String[] versions) throws SharedException,
			ConstraintViolationException {
		return getService().getTableAllColumns(table, versions);
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForLogModelMeta.GROUP, value = AuthorizeAnnotationNamesForLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForLogModelMeta.GROUP, value = AuthorizeAnnotationNamesForLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForLogModelMeta.GROUP, value = AuthorizeAnnotationNamesForLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public List<HbaseTableVersion> getHbaseTableVersionsForCombox()
			throws SharedException, ConstraintViolationException {
		return simpleService.get(HbaseTableVersion.class);
	}

}
