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
import com.voole.leech.gwt.client.gridcolumn.HbaseTableColumnColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn.HbaseTableColumnType;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableColumnGrid extends CinderGrid<HbaseTableColumn> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public HbaseTableColumnGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<HbaseTableColumn>(
				new ListStore<HbaseTableColumn>(
						PropertyUtils.HbaseTableColumnProperty.key())) {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseTableColumn>> callback) {

			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<HbaseTableColumn, String> name = HbaseTableColumnColumnConfig
						.name();
				ColumnConfig<HbaseTableColumn, String> shortname = HbaseTableColumnColumnConfig
						.shortname();
				ColumnConfig<HbaseTableColumn, HbaseTableColumnType> type = HbaseTableColumnColumnConfig
						.type();
				type.setCell(new SimpleSafeHtmlRenderer<HbaseTableColumnType>() {

					@Override
					protected String _getLabel(HbaseTableColumnType c) {
						return c.name();
					}
				}.getCell());
				ColumnConfig<HbaseTableColumn, String> desc = HbaseTableColumnColumnConfig
						.desc();
				columns.add(name);
				columns.add(shortname);
				columns.add(type);
				columns.add(desc);
			}
		}, gridConfig);
	}

}
