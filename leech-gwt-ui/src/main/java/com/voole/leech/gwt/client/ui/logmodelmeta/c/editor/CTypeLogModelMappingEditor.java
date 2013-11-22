/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.logmodelmeta.c.editor;

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
import com.voole.leech.gwt.client.ui.logmodelmeta.c.tree.CTypeLogModelColumnTree;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月18日
 */
public class CTypeLogModelMappingEditor extends
		CinderEditor<CTypeLogModelVersion> {
	interface Driver
			extends
			SimpleBeanEditorDriver<CTypeLogModelVersion, CTypeLogModelMappingEditor> {

	}

	public CTypeLogModelMappingEditor() {
		super(GWT.<Driver> create(Driver.class), "logModel_mapping");
		getSaveOrUpdateBt().removeFromParent();
		getCancelBt().removeFromParent();
	}

	@Override
	protected void _update(CTypeLogModelVersion t) {

	}

	@Override
	protected void _add(CTypeLogModelVersion t) {

	}

	CTypeLogModelColumnTree columnsTree;
	TreeStore<CTypeLogModelColumn> treeStore;
	@Ignore
	CTypeLogModelGroupColumn rootNode;

	@Override
	protected void _initView() {
		treeStore = new TreeStore<CTypeLogModelColumn>(
				PropertyUtils.CTypeLogModelColumnProperty.key());
		columnsTree = new CTypeLogModelColumnTree(treeStore);
		columnsTree.setHeight(300);

		FieldLabel columnsTreeFieldLabel = new FieldLabel(columnsTree, "字段");
		columnsTreeFieldLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(columnsTreeFieldLabel, mainVd);
	}

	@Override
	public void onEdit(EditEvent<CTypeLogModelVersion> event) {
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
		final EditEvent<CTypeLogModelVersion> event = getCurrEditEvent();
		columnsTree.mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.CTypeLogModelMetaRpcService.getLogModelVersionRootNode(
				event.getTarget().getId(),
				new RpcAsyncCallback<CTypeLogModelGroupColumn>() {

					@Override
					public void _onSuccess(CTypeLogModelGroupColumn t) {
						initRootNode(t);
					}

					@Override
					public void post() {
						super.post();
						columnsTree.unmask();
					}
				});
	}

	protected void initRootNode(CTypeLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
		treeStore.add(rootNode);
		if (rootNode.getColumns() == null) {
			rootNode.setColumns(new ArrayList<CTypeLogModelColumn>());
		}
		for (CTypeLogModelColumn childNode : rootNode.getColumns()) {
			initRootNode(rootNode, childNode);
		}
	}

	protected void initRootNode(CTypeLogModelGroupColumn parentNode,
			CTypeLogModelColumn childNode) {
		treeStore.add(parentNode, childNode);
		if (childNode instanceof CTypeLogModelGroupColumn) {
			if (((CTypeLogModelGroupColumn) childNode).getColumns() == null) {
				((CTypeLogModelGroupColumn) childNode)
						.setColumns(new ArrayList<CTypeLogModelColumn>());
			}
			for (CTypeLogModelColumn childNode2 : ((CTypeLogModelGroupColumn) childNode)
					.getColumns()) {
				initRootNode(((CTypeLogModelGroupColumn) childNode), childNode2);
			}
		}
	}

}
