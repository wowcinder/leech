/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.SpinnerField;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.ui.kafka.combox.KafkaTopicCombox;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaWatchDogTopicSettingEditor extends
		CinderEditor<KafkaWatchDogTopicSetting> {
	interface Dirver
			extends
			SimpleBeanEditorDriver<KafkaWatchDogTopicSetting, KafkaWatchDogTopicSettingEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public KafkaWatchDogTopicSettingEditor() {
		super(GWT.<Dirver> create(Dirver.class), "topic_setting");
	}

	@Override
	protected void _update(KafkaWatchDogTopicSetting t) {
		RpcServiceUtils.KafkaRpcService.updateKafkaWatchDogTopicSetting(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(KafkaWatchDogTopicSetting t) {
		RpcServiceUtils.KafkaRpcService.saveKafkaWatchDogTopicSetting(t,
				getSaveOrUpdateAsyncCallback());
	}

	SpinnerField<Integer> threadNum;
	KafkaTopicCombox topic;

	@Override
	protected void _initView() {
		threadNum = new SpinnerField<Integer>(new IntegerPropertyEditor());
		threadNum.setMinValue(1);
		threadNum.setMaxValue(20);
		threadNum.setIncrement(1);
		threadNum.setAutoValidate(true);

		layoutContainer.add(new FieldLabel(threadNum, "threadNum"), vd);

		topic = new KafkaTopicCombox();
		layoutContainer.add(new FieldLabel(topic, "topic"), vd);
	}

	@Override
	public void onEdit(EditEvent<KafkaWatchDogTopicSetting> event) {
		super.onEdit(event);
		if (event.isUpdate()) {
			topic.disable();
		} else {
			topic.enable();
		}
	}

}
