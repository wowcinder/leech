/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.tree;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Rectangle;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;

/**
 * 
 * fixed:指定某些leaf可以append
 * 
 * @author XuehuiHe
 * @date 2013年9月23日
 */
public class FixedTreeDropTarget<M> extends TreeDropTarget<M> {

	public FixedTreeDropTarget(Tree<M, ?> tree) {
		super(tree);
	}

	protected void handleInsert(DndDragMoveEvent event, final TreeNode<M> item) {
		Element e = getWidget().getView().getElementContainer(item);

		int height = e.getOffsetHeight();
		int mid = height / 2;
		int top = e.getAbsoluteTop();
		mid += top;
		int y = event.getDragMoveEvent().getNativeEvent().getClientY();
		boolean before = y < mid;

		boolean leaf = getWidget().isLeaf(item.getModel());

		if ((!leaf || isAllowDropOnLeaf(item))
				&& (feedback == Feedback.BOTH || feedback == Feedback.APPEND)
				&& ((before && y > top + 4) || (!before && y < top + height - 4))) {
			handleAppend(event, item);
			return;
		}

		if ((!leaf)
				&& ((before && y > top + 4) || (!before && y < top + height - 4))) {
			clearStyle(item);
			return;
		}

		// clear any active append item
		if (activeItem != null && activeItem != item) {
			clearStyle(activeItem);
		}

		appendItem = null;

		status = before ? 0 : 1;

		if (activeItem != null) {
			clearStyle(activeItem);
		}

		activeItem = item;

		if (activeItem != null) {
			TreeStore<M> store = getWidget().getStore();

			int idx = -1;

			M p = store.getParent(activeItem.getModel());
			if (p != null) {
				idx = store.getChildren(p).indexOf(activeItem.getModel());
			} else {
				idx = store.getRootItems().indexOf(activeItem.getModel());
			}

			ImageResource status = resources.dropInsert();
			if (before && idx == 0) {
				status = resources.dropInsertAbove();
			} else if (idx > 1 && !before && p != null
					&& idx == store.getChildCount(p) - 1) {
				status = resources.dropInsertBelow();
			}

			event.getStatusProxy().setStatus(true, status);

			if (before) {
				showInsert(event, e, true);
			} else {
				showInsert(event, e, false);
			}
		}
	}

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
			handleAppend(event, item);
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

	protected boolean isAllowDropOnLeaf(TreeNode<M> item) {
		return isAllowDropOnLeaf() && isDropOnLeafEnabled(item.getModel());
	}

	protected boolean isDropOnLeafEnabled(M m) {
		return true;
	}

	private void showInsert(DndDragMoveEvent event, Element elem, boolean before) {
		Insert insert = Insert.get();

		insert.show(elem);
		Rectangle rect = elem.<XElement> cast().getBounds();

		int y = before ? rect.getY() - 2 : (rect.getY() + rect.getHeight() - 4);

		// dont call setBounds though component as it expects widget to be
		// attached
		insert.getElement().setBounds(rect.getX(), y, rect.getWidth(), 6);
	}
}
