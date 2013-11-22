/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.menu.tree;

import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class MenuTree extends Tree<MenuNode, String> {
	public MenuTree(TreeStore<MenuNode> store) {
		super(store, new ValueProvider<MenuNode, String>() {

			@Override
			public String getValue(MenuNode object) {
				return object.getName();
			}

			@Override
			public void setValue(MenuNode object, String value) {

			}

			@Override
			public String getPath() {
				return null;
			}
		});

		setIconProvider(new IconProvider<MenuNode>() {
			@Override
			public ImageResource getIcon(MenuNode model) {
				ImageResource style = null;
				TreeStyle ts = getStyle();
				if (!isLeaf(model)) {
					if (isExpanded(model)) {
						style = ts.getNodeOpenIcon() != null ? ts
								.getNodeOpenIcon() : appearance.openNodeIcon();
					} else {
						style = ts.getNodeCloseIcon() != null ? ts
								.getNodeCloseIcon() : appearance
								.closeNodeIcon();
					}
				} else if (model instanceof MenuGroup) {
					style = ts.getNodeCloseIcon() != null ? ts
							.getNodeCloseIcon() : appearance.closeNodeIcon();
				} else {
					style = ts.getLeafIcon();
				}
				return style;
			}
		});
		initDND();
		setContextMenu(new GxtMenu(this));
	}

	private void initDND() {
		@SuppressWarnings("unused")
		TreeDragSource<MenuNode> source = new TreeDragSource<MenuNode>(this);
		TreeDropTarget<MenuNode> target = new MenuTreeDropTarget(this);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.BOTH);
	}

	public void initData(List<MenuNode> result) {
		store.clear();
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}

	}

	public void reset() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new RpcAsyncCallback<List<MenuNode>>() {

					@Override
					public void _onSuccess(List<MenuNode> t) {
						initData(t);
					}
				});
	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (parent == null) {
			store.add(menuNode);
		} else {
			store.add(parent, menuNode);
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
			for (MenuNode node : nodes) {
				initData(menuNode, node);
			}
		}
	}

}
