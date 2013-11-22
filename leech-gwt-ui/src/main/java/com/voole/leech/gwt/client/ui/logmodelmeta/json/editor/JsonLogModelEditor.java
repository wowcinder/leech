/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.logmodelmeta.json.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class JsonLogModelEditor extends CinderEditor<JsonLogModel> {
	interface Driver extends
			SimpleBeanEditorDriver<JsonLogModel, JsonLogModelEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public JsonLogModelEditor() {
		super(GWT.<Driver> create(Driver.class), "LogModel");
	}

	@Override
	protected void _update(JsonLogModel t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.updateLogModel(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(JsonLogModel t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.saveLogModel(t,
				getSaveOrUpdateAsyncCallback());

	}

	TextField name;
	TextArea desc;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);

	}
}
