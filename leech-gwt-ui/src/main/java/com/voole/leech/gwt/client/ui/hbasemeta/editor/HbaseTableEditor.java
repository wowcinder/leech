package com.voole.leech.gwt.client.ui.hbasemeta.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;

public class HbaseTableEditor extends CinderEditor<HbaseTable> {

	public interface Driver extends
			SimpleBeanEditorDriver<HbaseTable, HbaseTableEditor> {

	}

	TextField name;
	TextField shortname;
	TextArea desc;

	public HbaseTableEditor() {
		super(GWT.<Driver> create(Driver.class), "hbase_table");
	}

	@Override
	protected void _update(HbaseTable t) {
		RpcServiceUtils.HbaseMetaRpcService.updateHbaseTable(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(HbaseTable t) {
		RpcServiceUtils.HbaseMetaRpcService.saveHbaseTable(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		shortname = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(shortname, "shortname"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}

}
