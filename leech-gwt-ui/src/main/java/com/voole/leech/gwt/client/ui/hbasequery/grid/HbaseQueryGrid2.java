/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery.grid;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.paging.EtlPagingLoader;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.shared.paging.HbaseQueryPagingCondition;
import com.voole.leech.shared.paging.HbaseQueryPagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseQueryGrid2 extends Grid<HbaseRecord<String>> {
	private final String tableName;
	private final String[] versions;
	private final int cacheSize;

	public HbaseQueryGrid2(List<ColumnConfig<HbaseRecord<String>, ?>> columns,
			String table, String... versions) {
		this(columns, table, 80, versions);
	}

	public HbaseQueryGrid2(List<ColumnConfig<HbaseRecord<String>, ?>> columns,
			String table, int cacheSize, String... versions) {
		super(new ListStore<HbaseRecord<String>>(
				new ModelKeyProvider<HbaseRecord<String>>() {

					@Override
					public String getKey(HbaseRecord<String> item) {
						return item.getKey().toString();
					}
				}) {
		}, new ColumnModel<HbaseRecord<String>>(columns));
		setLoadMask(true);
		this.tableName = table;
		this.versions = versions;
		this.cacheSize = cacheSize;

		RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<HbaseRecord<String>>> proxy = new RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<HbaseRecord<String>>>() {
			@Override
			public void load(
					EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback) {
				rpcLoad(loadConfig, callback);
			}
		};
		EtlPagingLoader<HbaseRecord<String>> loader = new EtlPagingLoader<HbaseRecord<String>>(
				proxy) {
			protected void onLoadSuccess(
					EtlPagingLoadConfigBean loadConfig,
					com.sencha.gxt.data.shared.loader.PagingLoadResult<HbaseRecord<String>> result) {
				super.onLoadSuccess(loadConfig, result);
				EtlPagingLoadConfigBean config = getLoader()
						.getLastLoadConfig();
				HbaseQueryPagingLoadResultBean<HbaseRecord<String>> pr = (HbaseQueryPagingLoadResultBean<HbaseRecord<String>>) result;
				((HbaseQueryPagingCondition) config.getCondition())
						.setLastRow(pr.getLastRow());
				if (pr.getData() == null || pr.getData().size() == 0) {
					((HbaseGridView<HbaseRecord<String>>) getView())
							.setGetAllData(true);
				}
			};

			@Override
			protected void onLoadFailure(EtlPagingLoadConfigBean loadConfig,
					Throwable t) {
				super.onLoadFailure(loadConfig, t);
				((HbaseGridView<HbaseRecord<String>>) getView())
						.setGetAllData(true);
			}
		};
		loader.setRemoteSort(true);
		HbaseQueryPagingCondition condition = new HbaseQueryPagingCondition();
		condition.setTableName(tableName);
		loader.setCondition(condition);

		setLoader(loader);

		loader.addLoadHandler(new LoadResultListStoreBinding<EtlPagingLoadConfigBean, HbaseRecord<String>, PagingLoadResult<HbaseRecord<String>>>(
				getStore()));
		GridView<HbaseRecord<String>> gridView = new HbaseGridView<HbaseRecord<String>>() {

			@Override
			protected void _doLoadNext() {
				EtlPagingLoadConfigBean config = (EtlPagingLoadConfigBean) getLoader()
						.getLastLoadConfig();
				config.setOffset(config.getOffset() + config.getLimit());
				rpcLoad(config,
						new RpcAsyncCallback<PagingLoadResult<HbaseRecord<String>>>() {
							@Override
							public void _onSuccess(
									PagingLoadResult<HbaseRecord<String>> t) {
								getDoLoadNextCallBack().call(t);
							}
						});
			}
		};
		setView(gridView);

	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				getLoader().load(0, getCacheSize());
			}
		});
	}

	public String getTableName() {
		return tableName;
	}

	public String[] getVersions() {
		return versions;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	void rpcLoad(EtlPagingLoadConfigBean loadConfig,
			AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback) {
		RpcServiceUtils.HbaseQueryRpcService.get(loadConfig, callback);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EtlPagingLoader<HbaseRecord<String>> getLoader() {
		return (EtlPagingLoader<HbaseRecord<String>>) super.getLoader();
	}
}
