/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.logmodelmeta;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.voole.leech.gwt.client.common.tree.FixedTreeDropTarget;

/**
 * 
 * 只能一个一个的移动
 * 
 * @author XuehuiHe
 * @date 2013年9月23日
 */
public class LogModelColumnTreeDropTarget<M> extends FixedTreeDropTarget<M> {

	public LogModelColumnTreeDropTarget(Tree<M, ?> tree) {
		super(tree);
		tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	protected void appendModel(final M p, List<?> items, final int index) {
		if (items.size() == 0)
			return;
		@SuppressWarnings("unchecked")
		final List<TreeStore.TreeNode<M>> nodes = (List<TreeStore.TreeNode<M>>) items;
		final List<M> menuNodes = new ArrayList<M>();
		for (TreeStore.TreeNode<M> treeNode : nodes) {
			menuNodes.add(treeNode.getData());
		}
		M prev = null;
		if (index > 0) {
			if (p == null) {
				prev = getWidget().getStore().getChild(index - 1);
			} else {
				prev = getWidget().getStore().getChildren(p).get(index - 1);
			}
		}
		move(p, nodes, index, prev, menuNodes.get(0));
	}

	protected void move(final M p, List<TreeStore.TreeNode<M>> nodes,
			final int index, M prev, M item) {

	}

	protected void update(M p, List<TreeStore.TreeNode<M>> nodes, int index) {
		if (p == null) {
			getWidget().getStore().addSubTree(index, nodes);
		} else {
			getWidget().getStore().addSubTree(p, index, nodes);
		}
	}

	/**
	 * node不能移动到根节点平行级, node不能append到自己的parent
	 */
	@Override
	protected void showFeedback(DndDragMoveEvent event) {
		// TODO this might not get the right element
		final TreeNode<M> item = getWidget().findNode(
				event.getDragMoveEvent().getNativeEvent().getEventTarget()
						.<Element> cast());

		if (item == null && activeItem != null) {
			clearStyle(activeItem);
		}

		if (item != null
				&& event.getDropTarget().getWidget() == event.getDragSource()
						.getWidget()) {
			@SuppressWarnings("unchecked")
			Tree<M, ?> source = (Tree<M, ?>) event.getDragSource().getWidget();
			List<M> list = source.getSelectionModel().getSelection();
			M overModel = item.getModel();

			for (int i = 0; i < list.size(); i++) {
				M sel = list.get(i);
				M selParent = getWidget().getStore().getParent(sel);
				if (selParent != null && selParent == overModel) {// fixed
					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
					return;
				}
				if (overModel == sel) {
					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
					return;
				}
				List<M> children = getWidget().getStore().getAllChildren(sel);
				if (children.contains(item.getModel())) {
					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
					return;
				}
			}
		}

		boolean append = feedback == Feedback.APPEND
				|| feedback == Feedback.BOTH;
		boolean insert = feedback == Feedback.INSERT
				|| feedback == Feedback.BOTH;

		if (item == null) {
			// fixed
		} else if (insert) {
			handleInsert(event, item);
		} else if ((!getWidget().isLeaf(item.getModel()) || isAllowDropOnLeaf(item))
				&& append) {
			handleAppend(event, item);
		} else {
			if (activeItem != null) {
				clearStyle(activeItem);
			}
			status = -1;
			activeItem = null;
			appendItem = null;

			Insert.get().hide();
			event.getStatusProxy().setStatus(false);
		}
	}

}
