/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasemeta.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.HbaseTableVersionColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableVersionGrid extends CinderGrid<HbaseTableVersion> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public HbaseTableVersionGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<HbaseTableVersion>(
				new ListStore<HbaseTableVersion>(
						PropertyUtils.HbaseTableVersionProperty.key())) {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseTableVersion>> callback) {
				RpcServiceUtils.HbaseMetaRpcService.pagingHbaseTableVersion(
						loadConfig, callback);
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<HbaseTableVersion, String> version = HbaseTableVersionColumnConfig
						.version();
				ColumnConfig<HbaseTableVersion, HbaseTable> table = HbaseTableVersionColumnConfig
						.table();
				table.setCell(new SimpleSafeHtmlRenderer<HbaseTable>() {
					@Override
					protected String _getLabel(HbaseTable c) {
						return c.getName();
					}
				}.getCell());
				ColumnConfig<HbaseTableVersion, String> desc = HbaseTableVersionColumnConfig
						.desc();
				columns.add(table);
				columns.add(version);
				columns.add(desc);
			}
		}, gridConfig);
	}
}
