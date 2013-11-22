package com.voole.leech.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

public interface HbaseMetaService {
	HbaseTable saveHbaseTable(HbaseTable hbaseTable);

	void deleteHbaseTable(Integer id);

	HbaseTable updateHbaseTable(HbaseTable hbaseTable);

	PagingLoadResult<HbaseTable> pagingHbaseTable(EtlPagingLoadConfigBean config)
			throws SharedException;

	HbaseTableVersion saveHbaseTableVersion(HbaseTableVersion hbaseTableVersion);

	void deleteHbaseTableVersion(Integer id);

	HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion);

	PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException;

	HbaseTableColumn saveHbaseTableColumn(HbaseTableColumn hbaseTableColumn);

	void deleteHbaseTableColumn(Integer id);

	HbaseTableColumn updateHbaseTableColumn(HbaseTableColumn hbaseTableColumn);

	PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException;

	<T> void delete(Class<T> clazz, List<Integer> ids);

	public List<HbaseTable> getHbaseTablesForCombox();

	/**
	 * @param id
	 * @return
	 */
	List<HbaseTableColumn> getColumnsByVersionId(Integer id);

	public List<HbaseTableColumn> getTableAllColumns(String table,
			String[] versions) throws SharedException,
			ConstraintViolationException;
	
	public Map<String, Set<HbaseTableColumn>> getMetaForQuery(String tableName,
			String[] versions);
}
