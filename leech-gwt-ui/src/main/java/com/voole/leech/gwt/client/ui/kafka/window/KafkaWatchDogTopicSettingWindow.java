/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.window;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.window.FixedWindow;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.kafka.editor.KafkaWatchDogTopicSettingEditor;
import com.voole.leech.gwt.client.ui.kafka.grid.KafkaTopicGrid;
import com.voole.leech.gwt.client.ui.kafka.grid.KafkaWatchDogTopicSettingGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaWatchDogTopicSettingWindow extends FixedWindow {

	public class MultiAddWindow extends FixedWindow {
		private final TextButton submitBt;
		private final KafkaTopicGrid topicsGrid;

		public MultiAddWindow() {
			setHeadingText("批量添加Kafka topic");

			submitBt = new TextButton("添加");
			addButton(submitBt);
			setButtonAlign(BoxLayoutPack.CENTER);

			topicsGrid = new KafkaTopicGrid(new GridConfig(true, false));
			topicsGrid.setHeight(450);
			setWidget(topicsGrid);

			submitBt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					List<KafkaTopic> list = topicsGrid.getSelectionModel()
							.getSelectedItems();
					if (list.size() == 0) {
						return;
					}
					List<Integer> topicIds = new ArrayList<Integer>();
					for (KafkaTopic kafkaTopic : list) {
						topicIds.add(kafkaTopic.getId());
					}
					topicsGrid.mask(DefaultMessages.getMessages()
							.loadMask_msg());
					RpcServiceUtils.KafkaRpcService
							.saveWatchDogTopicSettings(
									grid.getWatchDog().getId(),
									topicIds,
									new RpcAsyncCallback<List<KafkaWatchDogTopicSetting>>() {

										@Override
										public void _onSuccess(
												List<KafkaWatchDogTopicSetting> t) {
											grid.getStore().addAll(t);
										}

										@Override
										public void post() {
											super.post();
											topicsGrid.unmask();
											MultiAddWindow.this.hide();
											MultiAddWindow.this
													.removeFromParent();
										}
									});
				}
			});

		}

		@Override
		protected void onShow() {
			super.onShow();
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					topicsGrid.mask(DefaultMessages.getMessages()
							.loadMask_msg());
					RpcServiceUtils.KafkaRpcService.getRemainKafkaTopics(grid
							.getWatchDog().getId(),
							new RpcAsyncCallback<List<KafkaTopic>>() {
								@Override
								public void _onSuccess(List<KafkaTopic> t) {
									topicsGrid.getStore().clear();
									topicsGrid.getStore().addAll(t);
								}

								@Override
								public void post() {
									super.post();
									topicsGrid.unmask();
								}
							});
				}
			});
		}
	}

	public class KafkaWatchDogTopicSettingView extends
			AbstractCenterView<KafkaWatchDogTopicSetting> {
		private final TextButton addMultiBt;

		public KafkaWatchDogTopicSettingView(
				CinderGrid<KafkaWatchDogTopicSetting> grid) {
			super(grid, new KafkaWatchDogTopicSettingEditor());
			addMultiBt = new TextButton("批量添加");
			getHeaderBar().add(addMultiBt);

			addMultiBt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					MultiAddWindow multiAddWindow = new MultiAddWindow();
					multiAddWindow.show();
				}
			});

		}

		@Override
		protected KafkaWatchDogTopicSetting newViewInstance() {
			KafkaWatchDogTopicSetting setting = new KafkaWatchDogTopicSetting();
			setting.setServer(getGrid().getWatchDog());
			return setting;
		}

		@Override
		protected void delete(List<KafkaWatchDogTopicSetting> list) {
			RpcServiceUtils.KafkaRpcService
					.deleteKafkaWatchDogTopicSettings(
							getIds(list,
									new EntityToKey<KafkaWatchDogTopicSetting, Integer>() {

										@Override
										public Integer getId(
												KafkaWatchDogTopicSetting m) {
											return m.getId();
										}
									}), getAsyncCallback(list));
		}

		@Override
		public KafkaWatchDogTopicSettingGrid getGrid() {
			return (KafkaWatchDogTopicSettingGrid) super.getGrid();
		}

	}

	private final KafkaWatchDogTopicSettingGrid grid;
	private final KafkaWatchDogTopicSettingView view;

	public KafkaWatchDogTopicSettingWindow(KafkaWatchDog watchDog) {
		setHeadingText("topic_setting");
		grid = new KafkaWatchDogTopicSettingGrid(watchDog);
		view = new KafkaWatchDogTopicSettingView(grid);

		setWidget(view);
	}

	@Override
	protected void onShow() {
		super.onShow();
	}
}
