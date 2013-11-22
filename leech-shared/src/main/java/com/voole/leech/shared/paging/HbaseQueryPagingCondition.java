/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.paging;

/**
 * @author XuehuiHe
 * @date 2013年8月30日
 */
public class HbaseQueryPagingCondition implements PagingCondition {
	private static final long serialVersionUID = 3565779462040183251L;
	private String tableName;
	private String[] versions;
	private String lastRow;

	public HbaseQueryPagingCondition() {
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getVersions() {
		return versions;
	}

	public void setVersions(String[] versions) {
		this.versions = versions;
	}

	public String getLastRow() {
		return lastRow;
	}

	public void setLastRow(String lastRow) {
		this.lastRow = lastRow;
	}

}
