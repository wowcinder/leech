/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.grid;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class GridConfig {
	private final boolean isMultiSelect;
	private final boolean isPaging;
	private int itemsPerPage = 50;

	public GridConfig() {
		this(true, true);
	}

	public GridConfig(boolean isMultiSelect, boolean isPaging) {
		this.isMultiSelect = isMultiSelect;
		this.isPaging = isPaging;
	}

	public boolean isMultiSelect() {
		return isMultiSelect;
	}

	public boolean isPaging() {
		return isPaging;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
}
