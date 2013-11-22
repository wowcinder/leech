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
import com.voole.leech.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.combox.JsonLogModelCombox;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class JsonLogModelVersionEditor extends
		CinderEditor<JsonLogModelVersion> {
	interface Driver
			extends
			SimpleBeanEditorDriver<JsonLogModelVersion, JsonLogModelVersionEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public JsonLogModelVersionEditor() {
		super(GWT.<Driver> create(Driver.class), "模型版本");
	}

	@Override
	protected void _update(JsonLogModelVersion t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.updateLogModelVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(JsonLogModelVersion t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.saveLogModelVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	JsonLogModelCombox model;
	TextField version;
	@Path("rootNode.hbaseTableVersion")
	HbaseTableVersionCombox hbaseTableVersion;
	TextArea desc;
	@Ignore
	JsonLogModelGroupColumn rootNode;

	@Override
	protected void _initView() {
		model = new JsonLogModelCombox();
		version = new TextField();
		hbaseTableVersion = new HbaseTableVersionCombox();
		desc = new TextArea();
		layoutContainer.add(new FieldLabel(model, "model"), vd);
		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableVersion,
				"hbaseTableVersion"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}
}
