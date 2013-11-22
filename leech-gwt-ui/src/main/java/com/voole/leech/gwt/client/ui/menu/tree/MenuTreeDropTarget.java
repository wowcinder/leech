/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.menu.tree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Rectangle;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class MenuTreeDropTarget extends TreeDropTarget<MenuNode> {

	public MenuTreeDropTarget(MenuTree tree) {
		super(tree);
	}

	protected void appendModel(final MenuNode p, List<?> items, final int index) {
		if (items.size() == 0)
			return;
		@SuppressWarnings("unchecked")
		final List<TreeStore.TreeNode<MenuNode>> nodes = (List<TreeStore.TreeNode<MenuNode>>) items;
		final List<MenuNode> menuNodes = new ArrayList<MenuNode>();
		for (TreeStore.TreeNode<MenuNode> treeNode : nodes) {
			menuNodes.add(treeNode.getData());
		}
		MenuNode prev = null;
		if (index > 0) {
			if (p == null) {
				prev = getWidget().getStore().getChild(index - 1);
			} else {
				prev = getWidget().getStore().getChildren(p).get(index - 1);
			}
		}

		Integer parentId = null;
		Integer prevId = null;
		if (p != null) {
			parentId = p.getId();
		}
		if (prev != null) {
			prevId = prev.getId();
		}
		RpcServiceUtils.MenuRpcService.moveMenuNode(parentId, prevId, menuNodes, new RpcAsyncCallback<List<MenuNode>>() {
			@Override
			public void _onSuccess(List<MenuNode> t) {
				TreeStore<MenuNode> store = getWidget().getStore();
				update(p, nodes, index);
				for (MenuNode menuNode : t) {
					store.update(menuNode);
				}
			}

			public void _onFailure(Throwable caught) {
				super._onFailure(caught);
				((MenuTree) getWidget()).reset();
			}
		});
	}

	protected void update(MenuNode p, List<TreeStore.TreeNode<MenuNode>> nodes,
			int index) {
		if (p == null) {
			getWidget().getStore().addSubTree(index, nodes);
		} else {
			getWidget().getStore().addSubTree(p, index, nodes);
		}
	}

	protected void handleAppendDrop(DndDropEvent event, TreeNode<MenuNode> item) {
		// TODO not MenuNode, but TreeStore.TreeNode<MenuNode>
		List<?> models = (List<?>) event.getData();
		if (models.size() > 0) {
			MenuNode p = null;
			if (item != null) {
				p = item.getModel();
				appendModel(p, models,
						getWidget().getStore().getChildCount(item.getModel()));
			} else {
				appendModel(p, models, getWidget().getStore().getRootItems()
						.size());
			}

		}
	}

	protected void handleInsertDrop(DndDropEvent event,
			TreeNode<MenuNode> item, int index) {
		List<?> droppedItems = (List<?>) event.getData();
		if (droppedItems.size() > 0) {
			int idx = getWidget().getStore().indexOf(item.getModel());
			idx = status == 0 ? idx : idx + 1;
			MenuNode p = getWidget().getStore().getParent(item.getModel());
			appendModel(p, droppedItems, idx);
		}
	}

	protected void handleInsert(DndDragMoveEvent event,
			final TreeNode<MenuNode> item) {
		Element e = getWidget().getView().getElementContainer(item);

		int height = e.getOffsetHeight();
		int mid = height / 2;
		int top = e.getAbsoluteTop();
		mid += top;
		int y = event.getDragMoveEvent().getNativeEvent().getClientY();
		boolean before = y < mid;

		boolean leaf = getWidget().isLeaf(item.getModel());

		if (myAllowDropOnLeaf(item)
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
			TreeStore<MenuNode> store = getWidget().getStore();

			int idx = -1;

			MenuNode p = store.getParent(activeItem.getModel());
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

	protected void showFeedback(DndDragMoveEvent event) {
		// TODO this might not get the right element
		final TreeNode<MenuNode> item = getWidget().findNode(
				event.getDragMoveEvent().getNativeEvent().getEventTarget()
						.<Element> cast());

		if (item == null && activeItem != null) {
			clearStyle(activeItem);
		}

		if (item != null
				&& event.getDropTarget().getWidget() == event.getDragSource()
						.getWidget()) {
			@SuppressWarnings("unchecked")
			Tree<MenuNode, ?> source = (Tree<MenuNode, ?>) event
					.getDragSource().getWidget();
			List<MenuNode> list = source.getSelectionModel().getSelection();
			MenuNode overModel = item.getModel();
			for (int i = 0; i < list.size(); i++) {
				MenuNode sel = list.get(i);
				if (overModel == sel) {
					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
					return;
				}
				List<MenuNode> children = getWidget().getStore()
						.getAllChildren(sel);
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
		} else if ((myAllowDropOnLeaf(item)) && append) {
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

	protected boolean myAllowDropOnLeaf(TreeNode<MenuNode> item) {
		return !getWidget().isLeaf(item.getModel())
				|| (item.getModel() instanceof MenuGroup);
	}

	private void showInsert(DndDragMoveEvent event, Element elem, boolean before) {
		Insert insert = Insert.get();

		insert.show(elem);
		Rectangle rect = elem.<XElement> cast().getBounds();

		int y = before ? rect.getY() - 2 : (rect.getY() + rect.getHeight() - 4);

		insert.getElement().setBounds(rect.getX(), y, rect.getWidth(), 6);
	}
}
