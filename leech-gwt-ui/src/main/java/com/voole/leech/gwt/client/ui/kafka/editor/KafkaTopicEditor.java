/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.editor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.leech.gwt.client.common.combox.EnumComboBox;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class KafkaTopicEditor<T extends KafkaTopic> extends CinderEditor<T> {

	public static KafkaTopicFixedModelVersionEditor createFixedModeVersionEditor() {
		return new KafkaTopicFixedModelVersionEditor();
	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public KafkaTopicEditor(
			SimpleBeanEditorDriver<T, ? extends CinderEditor<T>> driver) {
		super(driver, "kafka主题");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void _update(KafkaTopic t) {
		RpcServiceUtils.KafkaRpcService.updateKafkaTopic(t,
				(AsyncCallback<KafkaTopic>) getSaveOrUpdateAsyncCallback());
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void _add(KafkaTopic t) {
		RpcServiceUtils.KafkaRpcService.saveKafkaTopic(t,
				(AsyncCallback<KafkaTopic>) getSaveOrUpdateAsyncCallback());
	}

	TextField name;
	EnumComboBox<KafkaTopicCharset> charset;

	@Override
	protected void _initView() {
		name = new TextField();
		charset = new EnumComboBox<KafkaTopic.KafkaTopicCharset>(
				KafkaTopicCharset.values());

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(charset, "charset"), vd);
	}

}
