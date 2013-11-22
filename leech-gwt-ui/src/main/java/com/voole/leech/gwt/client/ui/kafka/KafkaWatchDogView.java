/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.kafka.editor.KafkaWatchDogEditor;
import com.voole.leech.gwt.client.ui.kafka.grid.KafkaWatchDogGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import com.voole.leech.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@MenuToken(name = "WatchDog", token = "watch_dog", group = "KAFKA")
public class KafkaWatchDogView extends AbstractCenterView<KafkaWatchDog> {
	Timer timer;

	/**
	 * @param grid
	 * @param editor
	 */
	public KafkaWatchDogView() {
		super(new KafkaWatchDogGrid(new GridConfig()), new KafkaWatchDogEditor());
		/*
		timer = new Timer() {
			@Override
			public void run() {
				getGrid().getLoader().load();
			}
		};
		timer.scheduleRepeating(10000);
		final TextButton stopRefresh = new TextButton("停止刷新");
		getHeaderBar().add(stopRefresh);

		stopRefresh.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (stopRefresh.getText().equals("停止刷新")) {
					timer.cancel();
					stopRefresh.setText("自动刷新");
				}else{
					timer.run();
					timer.scheduleRepeating(10000);
					stopRefresh.setText("停止刷新");
				}
			}
		});*/
	}

	@Override
	protected void onDetach() {
		super.onDetach();
//		timer.cancel();
	}

	@Override
	protected KafkaWatchDog newViewInstance() {
		KafkaWatchDog dog = new KafkaWatchDog();
		dog.setTopicSettings(new ArrayList<KafkaWatchDogTopicSetting>());
		return dog;
	}

	@Override
	protected void delete(List<KafkaWatchDog> list) {
		RpcServiceUtils.KafkaRpcService.deleteKafkaWatchDogs(
				getIds(list, new EntityToKey<KafkaWatchDog, Integer>() {

					@Override
					public Integer getId(KafkaWatchDog m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
