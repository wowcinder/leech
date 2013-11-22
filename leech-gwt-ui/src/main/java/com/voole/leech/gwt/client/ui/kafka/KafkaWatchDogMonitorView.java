/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka;

import com.voole.leech.gwt.client.ui.SimpleCenterView;
import com.voole.leech.gwt.client.ui.kafka.grid.KafkaWatchDogMonitorGrid;
import com.voole.leech.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@MenuToken(name = "WatchDog_MONITOR", token = "watch_dog_monitor", group = "KAFKA")
public class KafkaWatchDogMonitorView extends SimpleCenterView {
	private final KafkaWatchDogMonitorGrid grid;

	public KafkaWatchDogMonitorView() {
		this.grid = new KafkaWatchDogMonitorGrid();
		add(this.grid, mainVd);
	}
}
