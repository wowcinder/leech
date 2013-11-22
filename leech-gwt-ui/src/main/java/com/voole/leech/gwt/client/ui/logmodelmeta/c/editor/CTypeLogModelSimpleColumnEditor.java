package com.voole.leech.gwt.client.ui.logmodelmeta.c.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.ui.hbasemeta.combox.HbaseTableColumnCombox;
import com.voole.leech.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;

public class CTypeLogModelSimpleColumnEditor extends
		CinderEditor<CTypeLogModelSimpleColumn> {
	interface Driver
			extends
			SimpleBeanEditorDriver<CTypeLogModelSimpleColumn, CTypeLogModelSimpleColumnEditor> {

	}

	public CTypeLogModelSimpleColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "simple_column");
	}

	@Override
	protected void _update(CTypeLogModelSimpleColumn t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.updateLogModelSimpleColumn(
				t, getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(CTypeLogModelSimpleColumn t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.saveLogModelSimpleColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	HbaseTableColumnCombox hbaseTableColumn;
	TextField name;

	@Override
	protected void _initView() {
		name = new TextField();
		hbaseTableColumn = new HbaseTableColumnCombox();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableColumn, "hbase_column"),
				vd);
	}

	@Override
	public void onEdit(EditEvent<CTypeLogModelSimpleColumn> event) {
		hbaseTableColumn.fireRefreshCombox(new HbaseTableColumnComboxAddEvent(
				event.getTarget().getGroupColumn().getHbaseTableVersion()));
		super.onEdit(event);
	}

}
