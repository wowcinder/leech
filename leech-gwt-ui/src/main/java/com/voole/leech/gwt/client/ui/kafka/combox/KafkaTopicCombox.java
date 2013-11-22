/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.combox;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaTopicCombox extends ComboBox<KafkaTopic> {

	/**
	 * @param store
	 * @param labelProvider
	 */
	public KafkaTopicCombox() {
		super(
				new ListStore<KafkaTopic>(
						PropertyUtils.KafkaTopicProperty.key()),
				new LabelProvider<KafkaTopic>() {

					@Override
					public String getLabel(KafkaTopic item) {
						return item.getName();
					}
				});
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.KafkaRpcService
						.getTopics(new RpcAsyncCallback<List<KafkaTopic>>() {

							@Override
							public void _onSuccess(List<KafkaTopic> t) {
								getStore().clear();
								getStore().addAll(t);
							}
						});
			}
		});
	}
}
