/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.logmodelmeta.json;

import java.util.List;

import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelEditor;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.grid.JsonLogModelGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@MenuToken(name = "模型", token = "log_model_json", group = "Json日志模型")
public class JsonLogModelView extends AbstractCenterView<JsonLogModel> {

	/**
	 * @param grid
	 * @param editor
	 */
	public JsonLogModelView() {
		super(new JsonLogModelGrid(new GridConfig()), new JsonLogModelEditor());
	}

	@Override
	protected JsonLogModel newViewInstance() {
		return new JsonLogModel();
	}

	@Override
	protected void delete(List<JsonLogModel> list) {
		RpcServiceUtils.JsonLogModelMetaRpcService.deleteLogModels(
				getIds(list, new EntityToKey<JsonLogModel, Integer>() {
					@Override
					public Integer getId(JsonLogModel m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
