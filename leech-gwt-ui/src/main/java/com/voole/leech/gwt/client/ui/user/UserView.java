/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.common.grid.HeaderBar;
import com.voole.leech.gwt.client.ui.SimpleCenterView;
import com.voole.leech.gwt.client.ui.user.editor.UserEditor;
import com.voole.leech.gwt.client.ui.user.grid.UserGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.annotations.MenuToken;
import com.voole.leech.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MenuToken(name = "用户管理", token = "user_manager", group = "用户管理")
public class UserView extends SimpleCenterView {

	private final HeaderBar headerBar;
	private final UserGrid grid;
	private final UserEditor editor;

	public UserView() {
		grid = new UserGrid();
		headerBar = new HeaderBar();
		editor = new UserEditor();
		add(headerBar, vd);
		add(grid, mainVd);
		add(grid.getPagingToolBar(), vd);

		headerBar.getAddBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				editor.fireEditEvent(new EditEvent<User>(new User(),
						new GwtCallBack<User>() {
							@Override
							protected void _call(User t) {
								getGrid().getStore().add(0, t);
							}
						}));
			}
		});

		headerBar.getDelBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				headerBar.getDelBt().disable();
				final List<User> list = getGrid().getSelectionModel()
						.getSelectedItems();
				if (list.size() == 0) {
					headerBar.getDelBt().enable();
				} else {
					List<Integer> ids = new ArrayList<Integer>();
					for (User user : list) {
						ids.add(user.getId());
					}
					RpcServiceUtils.UserRpcService.deleteUserGroups(ids,
							RpcAsyncCallback.dealWith(new GwtCallBack<Void>() {
								@Override
								protected void _call(Void t) {
									for (User user : list) {
										getGrid().getStore().remove(user);
									}
								}

								@Override
								protected void post() {
									super.post();
									headerBar.getDelBt().enable();
								}
							}));
				}
			}
		});

		getGrid().addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				User u = getGrid().getStore().get(event.getRowIndex());
				getEditor().fireEditEvent(
						new EditEvent<User>(u, new GwtCallBack<User>() {

							@Override
							protected void _call(User t) {
								getGrid().getStore().update(t);
							}
						}, true));
			}
		});
	}

	public HeaderBar getHeaderBar() {
		return headerBar;
	}

	public UserGrid getGrid() {
		return grid;
	}

	public UserEditor getEditor() {
		return editor;
	}

}
