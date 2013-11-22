/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.kafka.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.voole.leech.gwt.client.ui.kafka.combox.LogModelVersionCombox;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaTopicFixedModelVersionEditor extends
		KafkaTopicEditor<KafkaTopicFixedModelVersion> {
	interface Driver
			extends
			SimpleBeanEditorDriver<KafkaTopicFixedModelVersion, KafkaTopicFixedModelVersionEditor> {

	}

	public KafkaTopicFixedModelVersionEditor() {
		super(GWT.<Driver> create(Driver.class));
	}

	LogModelVersionCombox version;

	@Override
	protected void _initView() {
		super._initView();
		version = new LogModelVersionCombox();
		layoutContainer.add(new FieldLabel(version, "version"), vd);
	}

}
