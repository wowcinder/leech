/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.leech.gwt.client.common.paging.EtlPagingLoader;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public abstract class GridConfigProvider<M> implements RpcProxyLoad<M> {

	protected final List<ColumnConfig<M, ?>> columns;
	protected final CheckBoxSelectionModel<M> sm;
	protected final ListStore<M> store;

	public GridConfigProvider(ListStore<M> store) {
		columns = new ArrayList<ColumnConfig<M, ?>>();

		IdentityValueProvider<M> identity = new IdentityValueProvider<M>();
		sm = new CheckBoxSelectionModel<M>(identity);
		sm.setSelectionMode(SelectionMode.MULTI);
		this.store = store;
		initColumnConfig();
	}

	protected abstract void initColumnConfig();

	public List<ColumnConfig<M, ?>> getColumns(boolean isMultiSelect) {
		if (isMultiSelect) {
			columns.add(0, sm.getColumn());
		}
		return columns;
	}

	public void initSelectModel(Grid<M> grid, boolean isMultiSelect) {
		if (isMultiSelect) {
			grid.setSelectionModel(sm);
		}
	}

	public void initPaging(Grid<M> grid, GridConfig gridConfig) {
		if (gridConfig.isPaging()) {
			grid.setLoader(getPagingLoader(grid.getStore()));
		}
	}

	public RpcProxyLoad<M> getLoader() {
		return this;
	}

	public RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<M>> getProxy() {
		return new RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<M>>() {
			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<M>> callback) {
				getLoader().load(loadConfig, callback);
			}
		};
	}

	public EtlPagingLoader<M> getPagingLoader(ListStore<M> store) {
		EtlPagingLoader<M> loader = new EtlPagingLoader<M>(getProxy());
		loader.setRemoteSort(true);
		if (store != null) {
			loader.addLoadHandler(new LoadResultListStoreBinding<M>(store));

		}
		return loader;
	}

	public static class LoadResultListStoreBinding<M> implements
			LoadHandler<EtlPagingLoadConfigBean, PagingLoadResult<M>> {
		protected ListStore<M> store;

		public LoadResultListStoreBinding(ListStore<M> store) {
			this.store = store;
		}

		@Override
		public void onLoad(
				LoadEvent<EtlPagingLoadConfigBean, PagingLoadResult<M>> event) {
			ListLoadResult<M> loaded = event.getLoadResult();
			store.replaceAll(loaded.getData());
		}

	}

	public ListStore<M> getStore() {
		return store;
	}

}
