package com.voole.leech.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

@RemoteServiceRelativePath("rpc/hbase_meta.rpc")
public interface HbaseMetaRpcService extends RemoteService {
	HbaseTable saveHbaseTable(HbaseTable hbaseTable) throws SharedException,
			ConstraintViolationException;

	void deleteHbaseTable(Integer id) throws SharedException,
			ConstraintViolationException;

	HbaseTable updateHbaseTable(HbaseTable hbaseTable) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<HbaseTable> pagingHbaseTable(EtlPagingLoadConfigBean config)
			throws SharedException, ConstraintViolationException;

	HbaseTableVersion saveHbaseTableVersion(HbaseTableVersion hbaseTableVersion)
			throws SharedException, ConstraintViolationException;

	void deleteHbaseTableVersion(Integer id) throws SharedException,
			ConstraintViolationException;

	HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	HbaseTableColumn saveHbaseTableColumn(HbaseTableColumn hbaseTableColumn)
			throws SharedException, ConstraintViolationException;

	void deleteHbaseTableColumn(Integer id) throws SharedException,
			ConstraintViolationException;

	HbaseTableColumn updateHbaseTableColumn(HbaseTableColumn hbaseTableColumn)
			throws SharedException, ConstraintViolationException;

	PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	void deleteHbaseTables(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	void deleteHbaseTableVersions(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	void deleteHbaseTableColumns(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	public List<HbaseTable> getHbaseTablesForCombox() throws SharedException,
			ConstraintViolationException;

	public List<HbaseTableColumn> getColumnsByVersionId(Integer id)
			throws SharedException, ConstraintViolationException;

	public List<HbaseTableVersion> getHbaseTableVersionsForCombox()
			throws SharedException, ConstraintViolationException;

	public List<HbaseTableColumn> getTableAllColumns(String table,
			String[] versions) throws SharedException,
			ConstraintViolationException;

	ValidationSupport dummy();
}
