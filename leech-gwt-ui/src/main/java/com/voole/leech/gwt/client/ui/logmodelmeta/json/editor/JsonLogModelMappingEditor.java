/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.logmodelmeta.json.editor;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.tree.JsonLogModelColumnTree;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月18日
 */
public class JsonLogModelMappingEditor extends
		CinderEditor<JsonLogModelVersion> {
	interface Driver
			extends
			SimpleBeanEditorDriver<JsonLogModelVersion, JsonLogModelMappingEditor> {

	}

	public JsonLogModelMappingEditor() {
		super(GWT.<Driver> create(Driver.class), "logModel_mapping");
		getSaveOrUpdateBt().removeFromParent();
		getCancelBt().removeFromParent();
	}

	@Override
	protected void _update(JsonLogModelVersion t) {

	}

	@Override
	protected void _add(JsonLogModelVersion t) {

	}

	JsonLogModelColumnTree columnsTree;
	TreeStore<JsonLogModelColumn> treeStore;
	@Ignore
	JsonLogModelGroupColumn rootNode;

	@Override
	protected void _initView() {
		treeStore = new TreeStore<JsonLogModelColumn>(
				PropertyUtils.JsonLogModelColumnProperty.key());
		columnsTree = new JsonLogModelColumnTree(treeStore);
		columnsTree.setHeight(300);

		FieldLabel columnsTreeFieldLabel = new FieldLabel(columnsTree, "字段");
		columnsTreeFieldLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(columnsTreeFieldLabel, mainVd);
	}

	@Override
	public void onEdit(EditEvent<JsonLogModelVersion> event) {
		super.onEdit(event);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				initTree();
			}
		});
	}

	protected void initTree() {
		treeStore.clear();
		final EditEvent<JsonLogModelVersion> event = getCurrEditEvent();
		columnsTree.mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.JsonLogModelMetaRpcService.getLogModelVersionRootNode(
				event.getTarget().getId(),
				new RpcAsyncCallback<JsonLogModelGroupColumn>() {

					@Override
					public void _onSuccess(JsonLogModelGroupColumn t) {
						initRootNode(t);
					}

					@Override
					public void post() {
						super.post();
						columnsTree.unmask();
					}
				});
	}

	protected void initRootNode(JsonLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
		treeStore.add(rootNode);
		if (rootNode.getColumns() == null) {
			rootNode.setColumns(new ArrayList<JsonLogModelColumn>());
		}
		for (JsonLogModelColumn childNode : rootNode.getColumns()) {
			initRootNode(rootNode, childNode);
		}
	}

	protected void initRootNode(JsonLogModelGroupColumn parentNode,
			JsonLogModelColumn childNode) {
		treeStore.add(parentNode, childNode);
		if (childNode instanceof JsonLogModelGroupColumn) {
			if (((JsonLogModelGroupColumn) childNode).getColumns() == null) {
				((JsonLogModelGroupColumn) childNode)
						.setColumns(new ArrayList<JsonLogModelColumn>());
			}
			for (JsonLogModelColumn childNode2 : ((JsonLogModelGroupColumn) childNode)
					.getColumns()) {
				initRootNode(((JsonLogModelGroupColumn) childNode), childNode2);
			}
		}
	}

}
