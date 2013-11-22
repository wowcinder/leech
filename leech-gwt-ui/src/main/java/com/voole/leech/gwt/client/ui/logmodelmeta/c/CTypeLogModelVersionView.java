package com.voole.leech.gwt.client.ui.logmodelmeta.c;

import java.util.List;

import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelVersionEditor;
import com.voole.leech.gwt.client.ui.logmodelmeta.c.grid.CTypeLogModelVersionGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.shared.annotations.MenuToken;

@MenuToken(name = "模型版本", token = "log_model_version_c", group = "CType日志模型")
public class CTypeLogModelVersionView extends
		AbstractCenterView<CTypeLogModelVersion> {

	public CTypeLogModelVersionView() {
		super(new CTypeLogModelVersionGrid(new GridConfig()),
				new CTypeLogModelVersionEditor());
	}

	@Override
	protected CTypeLogModelVersion newViewInstance() {
		return new CTypeLogModelVersion();
	}

	@Override
	protected void delete(List<CTypeLogModelVersion> list) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.deleteLogModelVersions(
				getIds(list, new EntityToKey<CTypeLogModelVersion, Integer>() {

					@Override
					public Integer getId(CTypeLogModelVersion m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
