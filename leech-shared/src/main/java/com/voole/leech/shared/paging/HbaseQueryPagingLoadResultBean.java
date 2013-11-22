/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.paging;

import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年9月2日
 */
public class HbaseQueryPagingLoadResultBean<D> extends PagingLoadResultBean<D> {
	private static final long serialVersionUID = 3497544585600561642L;
	private String lastRow;

	public HbaseQueryPagingLoadResultBean() {
	}

	public HbaseQueryPagingLoadResultBean(String lastRow) {
		this.lastRow = lastRow;
	}

	public String getLastRow() {
		return lastRow;
	}

	public void setLastRow(String lastRow) {
		this.lastRow = lastRow;
	}

}
