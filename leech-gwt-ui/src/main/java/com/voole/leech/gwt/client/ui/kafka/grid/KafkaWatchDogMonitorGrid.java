/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.KafkaWatchDogColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public class KafkaWatchDogMonitorGrid extends CinderGrid<KafkaWatchDog> {

	public static class KafkaWatchDogGridConfigProvider extends
			GridConfigProvider<KafkaWatchDog> {

		/**
		 * @param store
		 */
		public KafkaWatchDogGridConfigProvider() {
			super(new ListStore<KafkaWatchDog>(
					PropertyUtils.KafkaWatchDogProperty.key()));
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<KafkaWatchDog>> callback) {
			RpcServiceUtils.KafkaRpcService.pagingKafkaWatchDogStatus(
					loadConfig, callback);
		}

		@Override
		protected void initColumnConfig() {
			ColumnConfig<KafkaWatchDog, String> name = KafkaWatchDogColumnConfig
					.name();
			ColumnConfig<KafkaWatchDog, KafkaProcessServerStatus> status = KafkaWatchDogColumnConfig
					.status();
			status.setCell(new SimpleSafeHtmlRenderer<KafkaProcessServerStatus>() {

				@Override
				protected String _getLabel(KafkaProcessServerStatus c) {
					return c.name();
				}
			}.getCell());

			ColumnConfig<KafkaWatchDog, String> stop = createColumn("stop");
			TextButtonCell stopBt = new TextButtonCell();
			stop.setCell(stopBt);

			ColumnConfig<KafkaWatchDog, String> start = createColumn("start");
			TextButtonCell startBt = new TextButtonCell();
			start.setCell(startBt);

			ColumnConfig<KafkaWatchDog, String> restart = createColumn("restart");
			TextButtonCell restartBt = new TextButtonCell();
			restart.setCell(restartBt);

			startBt.addSelectHandler(new SelectHandler() {

				@Override
				public void onSelect(SelectEvent event) {
					KafkaWatchDog dog = getStore().get(
							event.getContext().getIndex());
					RpcServiceUtils.KafkaRpcService.start(dog.getId(),
							new RpcAsyncCallback<Void>() {
								@Override
								public void _onSuccess(Void t) {

								}
							});

				}
			});

			stopBt.addSelectHandler(new SelectHandler() {

				@Override
				public void onSelect(SelectEvent event) {
					KafkaWatchDog dog = getStore().get(
							event.getContext().getIndex());
					RpcServiceUtils.KafkaRpcService.stop(dog.getId(),
							new RpcAsyncCallback<Void>() {
								@Override
								public void _onSuccess(Void t) {

								}
							});

				}
			});

			restartBt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					KafkaWatchDog dog = getStore().get(
							event.getContext().getIndex());
					RpcServiceUtils.KafkaRpcService.restart(dog.getId(),
							new RpcAsyncCallback<Void>() {
								@Override
								public void _onSuccess(Void t) {

								}
							});
				}
			});

			columns.add(name);
			columns.add(status);
			columns.add(stop);
			columns.add(start);
			columns.add(restart);
		}

		private ColumnConfig<KafkaWatchDog, String> createColumn(
				final String name) {
			return new ColumnConfig<KafkaWatchDog, String>(
					new ValueProvider<KafkaWatchDog, String>() {

						@Override
						public String getValue(KafkaWatchDog object) {
							return name;
						}

						@Override
						public void setValue(KafkaWatchDog object, String value) {

						}

						@Override
						public String getPath() {
							return null;
						}
					});
		}

	}

	public KafkaWatchDogMonitorGrid() {
		this(new GridConfig(false, true));
	}

	public KafkaWatchDogMonitorGrid(GridConfig gridConfig) {
		super(new KafkaWatchDogGridConfigProvider(), gridConfig);
	}

}
