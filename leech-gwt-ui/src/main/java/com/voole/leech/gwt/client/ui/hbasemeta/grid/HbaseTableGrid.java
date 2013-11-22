package com.voole.leech.gwt.client.ui.hbasemeta.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.HbaseTableColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

public class HbaseTableGrid extends CinderGrid<HbaseTable> {

	public HbaseTableGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<HbaseTable>(new ListStore<HbaseTable>(
				PropertyUtils.HbaseTableProperty.key())) {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseTable>> callback) {
				RpcServiceUtils.HbaseMetaRpcService.pagingHbaseTable(
						loadConfig, callback);
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<HbaseTable, String> name = HbaseTableColumnConfig
						.name();
				ColumnConfig<HbaseTable, String> shortname = HbaseTableColumnConfig
						.shortname();
				ColumnConfig<HbaseTable, String> desc = HbaseTableColumnConfig
						.desc();

				columns.add(name);
				columns.add(shortname);
				columns.add(desc);

			}
		}, gridConfig);
	}

}
