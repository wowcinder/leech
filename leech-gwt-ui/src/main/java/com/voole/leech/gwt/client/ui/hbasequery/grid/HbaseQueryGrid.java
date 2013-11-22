/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery.grid;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.common.paging.EtlPagingLoader;
import com.voole.leech.gwt.client.ui.hbasequery.column.HbaseColumnConfig;
import com.voole.leech.gwt.client.ui.hbasequery.grid.GridHeaderReadyEvent.GridHeaderReadyHanlder;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.shared.paging.HbaseQueryPagingCondition;
import com.voole.leech.shared.paging.HbaseQueryPagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseQueryGrid extends CinderGrid<HbaseRecord<String>> implements
		GridHeaderReadyHanlder {

	public static class HbaseQueryGridConfigProvider extends
			GridConfigProvider<HbaseRecord<String>> {

		public HbaseQueryGridConfigProvider(ListStore<HbaseRecord<String>> store) {
			super(store);
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback) {

		}

		@Override
		protected void initColumnConfig() {
			RowNumberer<HbaseRecord<String>> rowNumberer = new RowNumberer<HbaseRecord<String>>(
					new IdentityValueProvider<HbaseRecord<String>>());
			rowNumberer.setResizable(true);
			rowNumberer.setFixed(false);
			columns.add(rowNumberer);
			columns.add(new ColumnConfig<HbaseRecord<String>, String>(
					new ValueProvider<HbaseRecord<String>, String>() {
						@Override
						public String getValue(HbaseRecord<String> object) {
							return object.getKey().toString();
						}

						@Override
						public void setValue(HbaseRecord<String> object,
								String value) {

						}

						@Override
						public String getPath() {
							return null;
						}
					}, 100, "key"));
		}

		public void refreshGridHeader(final HbaseQueryGrid grid) {
			grid.mask(DefaultMessages.getMessages().loadMask_msg());
			RpcServiceUtils.HbaseMetaRpcService.getTableAllColumns(
					grid.getTableName(), grid.getVersions(),
					new RpcAsyncCallback<List<HbaseTableColumn>>() {

						@Override
						public void _onSuccess(List<HbaseTableColumn> t) {
							for (HbaseTableColumn hbaseTableColumn : t) {
								columns.add(new HbaseColumnConfig<String>(
										hbaseTableColumn));
							}
							grid.getView().refresh(true);
							grid.fireEvent(new GridHeaderReadyEvent());
						}

						@Override
						public void post() {
							super.post();
							grid.unmask();
						}
					});
		}

	}

	private final String tableName;
	private final String[] versions;
	private final int cacheSize;

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public HbaseQueryGrid(String table, int cacheSize, String... versions) {
		super(new HbaseQueryGridConfigProvider(
				new ListStore<HbaseRecord<String>>(
						new ModelKeyProvider<HbaseRecord<String>>() {

							@Override
							public String getKey(HbaseRecord<String> item) {
								return item.getKey().toString();
							}
						}) {
				}), new GridConfig(false, false));
		this.tableName = table;
		this.versions = versions;
		this.cacheSize = cacheSize;
		addHandler(this, GridHeaderReadyEvent.TYPE);

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
			};
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

	void rpcLoad(EtlPagingLoadConfigBean loadConfig,
			AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback) {
		RpcServiceUtils.HbaseQueryRpcService.get(loadConfig, callback);
	}

	public HbaseQueryGrid(String table, String... versions) {
		this(table, 80, versions);
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				getConfigProvider().refreshGridHeader(HbaseQueryGrid.this);
			}
		});
	}

	@Override
	public HbaseQueryGridConfigProvider getConfigProvider() {
		return (HbaseQueryGridConfigProvider) super.getConfigProvider();
	}

	@Override
	public void onHeaderReady() {
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

	@SuppressWarnings("unchecked")
	@Override
	public EtlPagingLoader<HbaseRecord<String>> getLoader() {
		return (EtlPagingLoader<HbaseRecord<String>>) super.getLoader();
	}

}
