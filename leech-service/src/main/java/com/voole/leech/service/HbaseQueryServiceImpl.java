/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn.HbaseTableColumnType;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.shared.paging.HbaseQueryPagingCondition;
import com.voole.leech.shared.paging.HbaseQueryPagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Service
public class HbaseQueryServiceImpl implements HbaseQueryService {

	private static final int TOTAL_ITEMS = 5000;

	public static final Configuration config = HBaseConfiguration.create();
	@Autowired
	private HbaseMetaService hbaseMetaService;

	@Override
	public PagingLoadResult<HbaseRecord<String>> get(
			EtlPagingLoadConfigBean config) throws SharedException {
		HbaseQueryPagingLoadResultBean<HbaseRecord<String>> pr = new HbaseQueryPagingLoadResultBean<HbaseRecord<String>>();

		pr.setOffset(config.getOffset());
		pr.setTotalLength(TOTAL_ITEMS);

		HbaseQueryPagingCondition condition = (HbaseQueryPagingCondition) config
				.getCondition();
		queryData(condition, pr, config);
		return pr;
	}

	protected Set<String> getAllColumns(
			Map<String, Set<HbaseTableColumn>> versionToColumnMeta) {
		Set<String> result = new HashSet<String>();
		for (String version : versionToColumnMeta.keySet()) {
			Set<HbaseTableColumn> columns = versionToColumnMeta.get(version);
			for (HbaseTableColumn hbaseTableColumn : columns) {
				if (!result.contains(hbaseTableColumn.getName())) {
					result.add(hbaseTableColumn.getName());
				}
			}
		}
		return result;
	}

	protected HbaseRecord<String> getRecord(
			Map<String, Set<HbaseTableColumn>> versionToColumnMeta,
			Result result) {
		HbaseRecord<String> record = new HbaseRecord<String>();
		String currVersion = Bytes.toString(result.getValue(Bytes.toBytes("d"),
				Bytes.toBytes("version")));
		if (currVersion == null) {
			currVersion = "0.0";
		}
		Set<HbaseTableColumn> columnMeta = versionToColumnMeta.get(currVersion);
		record.setKey(Bytes.toString(result.getRow()));

		for (HbaseTableColumn hbaseTableColumn : columnMeta) {
			byte[] value = result.getValue(Bytes.toBytes("d"),
					Bytes.toBytes(hbaseTableColumn.getName()));

			record.getData().put(hbaseTableColumn.getName(),
					getValue(value, hbaseTableColumn.getType()));
		}
		return record;
	}

	protected Object getValue(byte[] b, HbaseTableColumnType type) {
		if (b == null) {
			return null;
		}
		Class<?> clazz = type.getClazz();
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return Bytes.toBoolean(b);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return Bytes.toLong(b);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return Bytes.toShort(b);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return Bytes.toDouble(b);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return Bytes.toFloat(b);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return Bytes.toInt(b);
		} else if (clazz.equals(String.class)) {
			return Bytes.toString(b);
		} else if (clazz.equals(BigDecimal.class)) {
			return Bytes.toBigDecimal(b);
		} else if (clazz.equals(Date.class)) {
			Date d = new Date();
			d.setTime(Bytes.toLong(b));
			return d;
		} else if (clazz.equals(BigInteger.class)) {
			return new BigInteger(b);
		}
		return null;
	}

	public static class VersionComparatorHelper {
		private List<Filter> filters;
		private String[] versions;
		private boolean isContainsEmptyVersion = false;

		public VersionComparatorHelper(String[] versions) {
			this.versions = versions;
			filters = new ArrayList<Filter>();
			init();
		}

		private void init() {
			for (String version : versions) {
				if ("0.0".equals(version)) {
					isContainsEmptyVersion = true;
					break;
				}
			}
			for (String version : versions) {
				if (!"0.0".equals(version)) {
					SingleColumnValueFilter f = new SingleColumnValueFilter(
							Bytes.toBytes("d"), Bytes.toBytes("version"),
							CompareOp.EQUAL, Bytes.toBytes(version));
					f.setFilterIfMissing(!isContainsEmptyVersion);
					filters.add(f);
				}
			}
		}

		public FilterList getFilters() {
			FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ONE,
					filters);
			return list;
		}
	}

	/**
	 * @param condition
	 * @param pr
	 */
	private void queryData(HbaseQueryPagingCondition condition,
			HbaseQueryPagingLoadResultBean<HbaseRecord<String>> pr,
			EtlPagingLoadConfigBean loadConfig) {
		int limit = loadConfig.getLimit();
		String tableName = condition.getTableName();
		String[] versions = condition.getVersions();

		Map<String, Set<HbaseTableColumn>> versionToColumnMeta = hbaseMetaService
				.getMetaForQuery(tableName, versions);
		Set<String> allColumns = getAllColumns(versionToColumnMeta);
		List<HbaseRecord<String>> list = new ArrayList<HbaseRecord<String>>();
		HTable table = null;
		try {
			table = new HTable(config, tableName);

			Scan scan = new Scan();
			scan.setCaching(limit);
			scan.setBatch(allColumns.size());
			for (String column : allColumns) {
				scan.addColumn(Bytes.toBytes("d"), Bytes.toBytes(column));
			}
			if (versions != null && versions.length > 0) {
				VersionComparatorHelper helper = new VersionComparatorHelper(
						versions);
				scan.setFilter(helper.getFilters());
			}
			if (condition.getLastRow() != null) {
				scan.setStartRow(Bytes.toBytes(condition.getLastRow() + "a"));
			}

			ResultScanner rs = table.getScanner(scan);

			int i = 1;
			for (Result result : rs) {
				HbaseRecord<String> record = getRecord(versionToColumnMeta,
						result);
				if (record != null) {
					list.add(record);
				}
				if (i >= limit) {
					break;
				}
				i++;
			}
			if (i < limit) {
				pr.setTotalLength(loadConfig.getOffset() + i - 1);
			}
		} catch (IOException e) {
			throw new SharedException("hbase query error", e);
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (list.size() > 0) {
			pr.setLastRow(list.get(list.size() - 1).getKey());
		}
		pr.setData(list);
	}
}
