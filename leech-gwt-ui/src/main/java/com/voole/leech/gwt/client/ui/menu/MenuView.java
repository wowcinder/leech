/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.menu;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.TextAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.ui.CenterView;
import com.voole.leech.gwt.client.ui.menu.tree.MenuTree;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.annotations.MenuToken;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
@MenuToken(name = "菜单管理", token = "menu_manager", group = "菜单管理")
public class MenuView extends HorizontalLayoutContainer implements CenterView {
	private MenuTree tree;
	private final TreeStore<MenuNode> store;

	public MenuView() {
		store = new TreeStore<MenuNode>(new ModelKeyProvider<MenuNode>() {
			@Override
			public String getKey(MenuNode item) {
				return item.getId() + "";
			}
		});
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				init();
			}
		});
	}

	private void init() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new RpcAsyncCallback<List<MenuNode>>() {
					@Override
					public void _onSuccess(List<MenuNode> t) {
						initData(t);
					}
				});
	}

	public void initData(List<MenuNode> result) {
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}
		tree = new MenuTree(store);
		tree.getElement().getStyle().setTextAlign(TextAlign.LEFT);
		tree.setWidth(300);

		add(tree, new HorizontalLayoutData(-1, 1, new Margins(10)));
		forceLayout();
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

	private CenterViewConfig centerViewConfig;

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}

}
