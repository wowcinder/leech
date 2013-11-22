/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui;

import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.voole.leech.gwt.client.app.EtlView;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.event.MenuClickEvent;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class MenuView extends Tree<MenuNode, MenuNode> {

	private EtlView etlView;

	public MenuView(EtlView etlView) {
		super(new TreeStore<MenuNode>(PropertyUtils.MenuNodeProperty.key()),
				new ValueProvider<MenuNode, MenuNode>() {

					@Override
					public MenuNode getValue(MenuNode object) {
						return object;
					}

					@Override
					public void setValue(MenuNode object, MenuNode value) {
						object = value;
					}

					@Override
					public String getPath() {
						return "name";
					}
				});

		this.etlView = etlView;
		SimpleSafeHtmlCell<MenuNode> cell = new SimpleSafeHtmlCell<MenuNode>(
				new SafeHtmlRenderer<MenuNode>() {

					@Override
					public SafeHtml render(MenuNode object) {
						return SimpleSafeHtmlRenderer.getInstance().render(
								object.getName());
					}

					@Override
					public void render(MenuNode object, SafeHtmlBuilder builder) {
						SimpleSafeHtmlRenderer.getInstance().render(
								object.getName(), builder);

					}
				}, "click") {
			@Override
			public void onBrowserEvent(
					com.google.gwt.cell.client.Cell.Context context,
					Element parent, MenuNode value, NativeEvent event,
					ValueUpdater<MenuNode> valueUpdater) {
				super.onBrowserEvent(context, parent, value, event,
						valueUpdater);
				if ("click".equals(event.getType())) {
					if (value instanceof Menu) {
						getEtlView().fireEvent(
								new MenuClickEvent(((Menu) value).getToken()));
					} else if (value instanceof LogoutMenu) {
						RpcServiceUtils.OpenAuthorizeRpcService
								.logout(new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										Window.Location.reload();
									}

									@Override
									public void onFailure(Throwable caught) {

									}
								});
					}
				}
			}

		};
		setCell(cell);
		setWidth(300);
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

	public void init() {
		getData();
	}

	protected void initData(List<MenuNode> result) {
		store.clear();
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}
		store.add(new LogoutMenu());
	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
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

	public void showMenu(String token) {
		MenuNode mn = getStore().findModelWithKey(token);
		if (mn != null) {
			MenuNode parent = getStore().getParent(mn);
			if (parent != null) {
				setExpanded(parent, true);
			}
			scrollIntoView(mn);
			getSelectionModel().select(mn, true);
		} else {
			getSelectionModel().deselectAll();
		}

	}

	private void getData() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new RpcAsyncCallback<List<MenuNode>>() {
					@Override
					public void _onSuccess(List<MenuNode> t) {
						initData(t);
					}
				});
	}

	public class LogoutMenu extends MenuNode {
		private static final long serialVersionUID = 6258185941078205551L;

		public LogoutMenu() {
			setName("登出");
			setId(-1);
		}
	}

	public EtlView getEtlView() {
		return etlView;
	}

	public void setEtlView(EtlView etlView) {
		this.etlView = etlView;
	}

}
