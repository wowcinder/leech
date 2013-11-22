package com.voole.leech.gwt.client.ui.user.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.AuthorizeColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

public class AuthorizeGrid extends CinderGrid<Authorize> {

	public AuthorizeGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<Authorize>(new ListStore<Authorize>(
				PropertyUtils.AuthorizeProperty.key())) {
			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<Authorize>> callback) {

				// TODO
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<Authorize, AuthorizeGroup> group = AuthorizeColumnConfig
						.group();
				group.setCell(new SimpleSafeHtmlRenderer<AuthorizeGroup>() {
					@Override
					protected String _getLabel(AuthorizeGroup c) {
						return c.getName();
					}
				}.getCell());
				columns.add(group);
				ColumnConfig<Authorize, String> name = AuthorizeColumnConfig
						.name();
				columns.add(name);
			}
		}, gridConfig);
	}
}
