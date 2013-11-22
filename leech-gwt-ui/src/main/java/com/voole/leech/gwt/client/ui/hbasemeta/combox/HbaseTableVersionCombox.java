package com.voole.leech.gwt.client.ui.hbasemeta.combox;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.combox.AddEnableComboBox;
import com.voole.leech.gwt.client.ui.hbasemeta.editor.HbaseTableVersionEditor;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableVersionCombox extends
		AddEnableComboBox<HbaseTableVersion> {

	public HbaseTableVersionCombox() {
		super(new ListStore<HbaseTableVersion>(
				PropertyUtils.HbaseTableVersionProperty.key()),
				new LabelProvider<HbaseTableVersion>() {
					@Override
					public String getLabel(HbaseTableVersion item) {
						if (item.getTable() == null) {
							return item.getVersion();
						}
						return item.getTable().getName() + "#"
								+ item.getVersion();
					}
				}, new HbaseTableVersionEditor());
		setForceSelection(false);
		setEditable(true);
	}

	@Override
	protected HbaseTableVersion createAddItem() {
		HbaseTableVersion version = new HbaseTableVersion();
		version.setId(-1);
		version.setVersion("添加...");
		return version;
	}

	@Override
	public boolean isAddItem(HbaseTableVersion selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected HbaseTableVersion newComboxInstance() {
		return new HbaseTableVersion();
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.HbaseMetaRpcService
						.getHbaseTableVersionsForCombox(new RpcAsyncCallback<List<HbaseTableVersion>>() {
							@Override
							public void _onSuccess(List<HbaseTableVersion> t) {
								getStore().clear();
								getStore().addAll(t);
								getStore().add(getAddItem());
							}
						});
			}
		});
	}
}
