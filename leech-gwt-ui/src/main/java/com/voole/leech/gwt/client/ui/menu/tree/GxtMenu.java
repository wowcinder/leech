/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.menu.tree;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.ui.menu.editor.MenuEditor;
import com.voole.leech.gwt.client.ui.menu.editor.MenuGroupEditor;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class GxtMenu extends com.sencha.gxt.widget.core.client.menu.Menu {

	private MenuItem insertMenu;
	private MenuItem insertMenuGroup;
	private MenuItem addMenu;
	private MenuItem addMenuGroup;
	private MenuItem delete;
	private MenuItem modify;

	private final MenuTree tree;

	private final MenuEditor menuEditor;
	private final MenuGroupEditor menuGroupEditor;

	public GxtMenu(MenuTree tree2) {
		this.tree = tree2;
		menuEditor = new MenuEditor();
		menuGroupEditor = new MenuGroupEditor();

		insertMenu = new MenuItem("插入菜单");
		insertMenuGroup = new MenuItem("插入菜单组");
		addMenu = new MenuItem("添加菜单");
		addMenuGroup = new MenuItem("添加菜单组");
		delete = new MenuItem("删除");
		modify = new MenuItem("修改 ");

		add(insertMenu);
		add(insertMenuGroup);
		add(new SeparatorMenuItem());
		add(addMenu);
		add(addMenuGroup);
		add(new SeparatorMenuItem());
		add(modify);
		add(delete);

		initInsertMenu();
		initInsertMenuGroup();
		initAddMenu();
		initAddMenuGroup();
		initDelete();
		initModify();

		this.addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				MenuNode selectItem = tree.getSelectionModel()
						.getSelectedItem();
				if (selectItem == null) {
					GxtMenu.this.disable();
				}
				if (selectItem instanceof Menu) {
					addMenu.disable();
					addMenuGroup.disable();
				}
			}
		});

		this.addHideHandler(new HideHandler() {

			@Override
			public void onHide(HideEvent event) {
				GxtMenu.this.enable();
				addMenu.enable();
				addMenuGroup.enable();
			}
		});
	}

	private void initModify() {
		modify.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode node = tree.getSelectionModel()
						.getSelectedItem();
				if (node instanceof Menu) {
					menuEditor.fireEditEvent(new EditEvent<Menu>((Menu) node,
							new GwtCallBack<Menu>() {
								@Override
								protected void _call(Menu t) {
									tree.getStore().update(t);
								}
							}, true));
				} else {
					menuGroupEditor.fireEditEvent(new EditEvent<MenuGroup>(
							(MenuGroup) node, new GwtCallBack<MenuGroup>() {

								@Override
								protected void _call(MenuGroup t) {
									tree.getStore().update(t);
								}
							}, true));
				}
			}
		});
	}

	protected void initDelete() {
		delete.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode node = tree.getSelectionModel()
						.getSelectedItem();
				RpcServiceUtils.MenuRpcService.deleteMenuNode(node.getId(),
						new RpcAsyncCallback<Void>() {
							@Override
							public void _onSuccess(Void t) {
								tree.getStore().remove(node);
							}

							@Override
							public void _onFailure(Throwable caught) {
								super._onFailure(caught);
								tree.reset();
							}
						});
			}
		});
	}

	protected void initAddMenuGroup() {
		addMenuGroup.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuGroup selectItem = (MenuGroup) tree
						.getSelectionModel().getSelectedItem();
				MenuGroup mg = new MenuGroup();
				mg.setParent(selectItem);
				MenuNode prev = tree.getStore().getLastChild(selectItem);
				mg.setPrev(prev);

				menuGroupEditor.fireEditEvent(new EditEvent<MenuGroup>(mg,
						new GwtCallBack<MenuGroup>() {

							@Override
							protected void _call(MenuGroup t) {
								tree.getStore().add(selectItem, t);
							}
						}));
			}
		});
	}

	protected void initAddMenu() {
		addMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuGroup selectItem = (MenuGroup) tree
						.getSelectionModel().getSelectedItem();

				Menu menu = new Menu();
				menu.setParent(selectItem);
				MenuNode prev = tree.getStore().getLastChild(selectItem);
				menu.setPrev(prev);

				menuEditor.fireEditEvent(new EditEvent<Menu>(menu,
						new GwtCallBack<Menu>() {
							@Override
							protected void _call(Menu t) {
								tree.getStore().add(selectItem, t);
							}
						}));
			}
		});
	}

	protected void initInsertMenuGroup() {
		insertMenuGroup.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode selectItem = tree.getSelectionModel()
						.getSelectedItem();
				MenuGroup mg = new MenuGroup();
				mg.setParent(selectItem.getParent());
				mg.setPrev(selectItem);

				menuGroupEditor.fireEditEvent(new EditEvent<MenuGroup>(mg,
						new GwtCallBack<MenuGroup>() {

							@Override
							protected void _call(MenuGroup t) {
								MenuGroup parent = null;
								if (selectItem.getParent() != null) {
									parent = (MenuGroup) tree.getStore()
											.findModel(selectItem.getParent());
								}
								int index = tree.getStore().indexOf(selectItem) + 1;
								if (parent == null) {
									tree.getStore().insert(index, t);
								} else {
									tree.getStore().insert(parent, index, t);
								}
							}
						}));
			}
		});
	}

	protected void initInsertMenu() {
		insertMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode selectItem = tree.getSelectionModel()
						.getSelectedItem();
				Menu menu = new Menu();
				menu.setParent(selectItem.getParent());
				menu.setPrev(selectItem);
				menuEditor.fireEvent(new EditEvent<Menu>(menu,
						new GwtCallBack<Menu>() {
							@Override
							protected void _call(Menu t) {
								MenuGroup parent = (MenuGroup) tree.getStore()
										.findModel(selectItem.getParent());
								int index = tree.getStore().indexOf(selectItem) + 1;
								if (parent == null) {
									tree.getStore().insert(index, t);
								} else {
									tree.getStore().insert(parent, index, t);
								}
							}
						}));
			}
		});
	}

	public MenuItem getInsertMenu() {
		return insertMenu;
	}

	public void setInsertMenu(MenuItem insertMenu) {
		this.insertMenu = insertMenu;
	}

	public MenuItem getInsertMenuGroup() {
		return insertMenuGroup;
	}

	public void setInsertMenuGroup(MenuItem insertMenuGroup) {
		this.insertMenuGroup = insertMenuGroup;
	}

	public MenuItem getAddMenu() {
		return addMenu;
	}

	public void setAddMenu(MenuItem addMenu) {
		this.addMenu = addMenu;
	}

	public MenuItem getAddMenuGroup() {
		return addMenuGroup;
	}

	public void setAddMenuGroup(MenuItem addMenuGroup) {
		this.addMenuGroup = addMenuGroup;
	}

	public MenuItem getDelete() {
		return delete;
	}

	public void setDelete(MenuItem delete) {
		this.delete = delete;
	}

}
