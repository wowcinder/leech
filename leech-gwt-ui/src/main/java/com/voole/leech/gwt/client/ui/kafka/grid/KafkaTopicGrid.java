/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.KafkaTopicColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class KafkaTopicGrid extends CinderGrid<KafkaTopic> {

	public static class KafkaTopicGridConfigProvider extends
			GridConfigProvider<KafkaTopic> {

		/**
		 * @param store
		 */
		public KafkaTopicGridConfigProvider() {
			super(new ListStore<KafkaTopic>(
					PropertyUtils.KafkaTopicProperty.key()));
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<KafkaTopic>> callback) {
			RpcServiceUtils.KafkaRpcService.pagingKafkaTopic(loadConfig,
					callback);
		}

		@Override
		protected void initColumnConfig() {
			ColumnConfig<KafkaTopic, String> name = (ColumnConfig<KafkaTopic, String>) KafkaTopicColumnConfig
					.name();
			ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicCharset> charset = (ColumnConfig<KafkaTopic, KafkaTopicCharset>) KafkaTopicColumnConfig
					.charset();
			charset.setCell(new SimpleSafeHtmlRenderer<KafkaTopicCharset>() {
				@Override
				protected String _getLabel(KafkaTopicCharset c) {
					return c.getCharset();
				}
			}.getCell());
			ColumnConfig<KafkaTopic, Boolean> isEnabled = KafkaTopicColumnConfig
					.isEnabled();
			columns.add(name);
			columns.add(charset);
			columns.add(isEnabled);

		}

	}

	/**
	 * @param configProvider
	 * @param gridConfig
	 */
	public KafkaTopicGrid(GridConfig gridConfig) {
		super(new KafkaTopicGridConfigProvider(), gridConfig);
	}

}
