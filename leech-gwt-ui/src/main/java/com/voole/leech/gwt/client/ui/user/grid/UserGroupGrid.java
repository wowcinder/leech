package com.voole.leech.gwt.client.ui.user.grid;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.UserGroupColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.user.UserGroup;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

public class UserGroupGrid extends CinderGrid<UserGroup> {
	public static final DateTimeFormat DF = DateTimeFormat
			.getFormat("yyyy-MM-dd HH:mm:ss");

	public UserGroupGrid() {
		super(new GridConfigProvider<UserGroup>(new ListStore<UserGroup>(
				PropertyUtils.UserGroupProperty.key())) {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<UserGroup>> callback) {
				RpcServiceUtils.UserRpcService.pagingUserGroup(loadConfig,
						callback);
			}

			@Override
			protected void initColumnConfig() {

				ColumnConfig<UserGroup, String> name = UserGroupColumnConfig
						.name();
				ColumnConfig<UserGroup, Date> createTime = UserGroupColumnConfig
						.createTime();
				createTime.setCell(new SimpleSafeHtmlRenderer<Date>() {
					@Override
					protected String _getLabel(Date c) {
						return DF.format(c);
					}
				}.getCell());
				columns.add(name);
				columns.add(createTime);

			}
		});
	}

}
