/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasemeta;

import java.util.ArrayList;
import java.util.List;

import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.hbasemeta.editor.HbaseTableVersionEditor;
import com.voole.leech.gwt.client.ui.hbasemeta.grid.HbaseTableVersionGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表版本管理", token = "hbase_table_version", group = "hbase_meta管理")
public class HbaseTableVersionView extends
		AbstractCenterView<HbaseTableVersion> {

	/**
	 * @param grid
	 * @param editor
	 */
	public HbaseTableVersionView() {
		super(new HbaseTableVersionGrid(new GridConfig()),
				new HbaseTableVersionEditor());
	}

	@Override
	protected HbaseTableVersion newViewInstance() {
		HbaseTableVersion v = new HbaseTableVersion();
		v.setColumns(new ArrayList<HbaseTableColumn>());
		return v;
	}

	@Override
	protected void delete(List<HbaseTableVersion> list) {
		List<Integer> ids = new ArrayList<Integer>();
		for (HbaseTableVersion hbaseTableVersion : list) {
			ids.add(hbaseTableVersion.getId());
		}
		RpcServiceUtils.HbaseMetaRpcService.deleteHbaseTableVersions(ids,
				getAsyncCallback(list));
	}
}
