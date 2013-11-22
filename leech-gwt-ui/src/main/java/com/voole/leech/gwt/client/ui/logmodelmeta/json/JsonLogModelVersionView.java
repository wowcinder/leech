package com.voole.leech.gwt.client.ui.logmodelmeta.json;

import java.util.List;

import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelVersionEditor;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.grid.JsonLogModelVersionGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.shared.annotations.MenuToken;

@MenuToken(name = "模型版本", token = "log_model_version_json", group = "Json日志模型")
public class JsonLogModelVersionView extends
		AbstractCenterView<JsonLogModelVersion> {

	public JsonLogModelVersionView() {
		super(new JsonLogModelVersionGrid(new GridConfig()),
				new JsonLogModelVersionEditor());
	}

	@Override
	protected JsonLogModelVersion newViewInstance() {
		return new JsonLogModelVersion();
	}

	@Override
	protected void delete(List<JsonLogModelVersion> list) {
		RpcServiceUtils.JsonLogModelMetaRpcService.deleteLogModelVersions(
				getIds(list, new EntityToKey<JsonLogModelVersion, Integer>() {

					@Override
					public Integer getId(JsonLogModelVersion m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
