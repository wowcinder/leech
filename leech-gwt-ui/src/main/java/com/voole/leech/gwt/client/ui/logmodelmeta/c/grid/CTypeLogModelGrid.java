/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.logmodelmeta.c.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.CTypeLogModelColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class CTypeLogModelGrid extends CinderGrid<CTypeLogModel> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public CTypeLogModelGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<CTypeLogModel>(new ListStore<CTypeLogModel>(
				PropertyUtils.CTypeLogModelProperty.key())) {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<CTypeLogModel>> callback) {
				RpcServiceUtils.CTypeLogModelMetaRpcService.pagingLogModel(
						loadConfig, callback);
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<CTypeLogModel, String> name = CTypeLogModelColumnConfig
						.name();
				ColumnConfig<CTypeLogModel, String> desc = CTypeLogModelColumnConfig
						.desc();
				columns.add(name);
				columns.add(desc);
			}
		},  gridConfig);
	}

}
