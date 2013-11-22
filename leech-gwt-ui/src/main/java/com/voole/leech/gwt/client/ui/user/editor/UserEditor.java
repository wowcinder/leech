/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.user.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.HeaderBar;
import com.voole.leech.gwt.client.ui.user.combox.UserGroupCombox;
import com.voole.leech.gwt.client.ui.user.grid.AuthorizeGrid;
import com.voole.leech.gwt.client.ui.user.window.AuthorizesSelectorWindow;
import com.voole.leech.gwt.client.ui.user.window.ShowAuthorizesSelectorWindowEvent;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class UserEditor extends CinderEditor<User> {
	public interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {

	}

	@Ignore
	AuthorizesSelectorWindow authorizesSelectorWindow;

	TextField email;

	TextField password;

	UserGroupCombox userGroup;
	@Ignore
	AuthorizeGrid extraAuthorizesGrid;
	@Ignore
	HeaderBar headerBar;

	ListStoreEditor<Authorize> extraAuthorizes;

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public UserEditor() {
		super(GWT.<Driver> create(Driver.class), "用户");
	}

	@Override
	protected void _update(User t) {
		RpcServiceUtils.UserRpcService.updateUser(t, t.getPassword(),
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(User t) {
		RpcServiceUtils.UserRpcService.saveUser(t, t.getPassword(),
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		email = new TextField();
		layoutContainer.add(new FieldLabel(email, "email"), vd);
		password = new TextField();
		layoutContainer.add(new FieldLabel(password, "password"), vd);
		userGroup = new UserGroupCombox();
		layoutContainer.add(new FieldLabel(userGroup, "group"), vd);

		initExtraAuthorizesGrid();
	}

	protected void initExtraAuthorizesGrid() {
		authorizesSelectorWindow = new AuthorizesSelectorWindow();

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		extraAuthorizesGrid = new AuthorizeGrid(new GridConfig(true, false));
		extraAuthorizesGrid.setHeight(300);
		headerBar = new HeaderBar();
		vlc.add(headerBar, vd);
		vlc.add(extraAuthorizesGrid, mainVd);

		FieldLabel extraAuthorizesFieldLabel = new FieldLabel(vlc,
				"extraAuthorizes");
		extraAuthorizesFieldLabel.setLabelAlign(LabelAlign.TOP);

		layoutContainer.add(extraAuthorizesFieldLabel, mainVd);

		extraAuthorizes = new ListStoreEditor<Authorize>(
				extraAuthorizesGrid.getStore());

		getHeaderBar().getAddBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getAuthorizesSelectorWindow().fireShowEvent(
						new ShowAuthorizesSelectorWindowEvent(
								new GwtCallBack<List<Authorize>>() {
									@Override
									protected void _call(List<Authorize> t) {
										add(t);
									}
								}));
			}
		});

		getHeaderBar().getDelBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getHeaderBar().getDelBt().disable();
				List<Authorize> list = getExtraAuthorizesGrid()
						.getSelectionModel().getSelectedItems();
				for (Authorize authorize : list) {
					getExtraAuthorizesGrid().getStore().remove(authorize);
				}
				getHeaderBar().getDelBt().enable();
			}
		});

	}

	@Override
	public void onEdit(final EditEvent<User> event) {
		if(event.getTarget().getExtraAuthorizes() == null){
			event.getTarget().setExtraAuthorizes(new ArrayList<Authorize>());
		}
		
		super.onEdit(event);
		if (event.isUpdate()) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					getExtraAuthorizesGrid().mask("加载中...");
					RpcServiceUtils.UserRpcService.getUserExtraAuthorizes(event
							.getTarget().getId(), RpcAsyncCallback
							.dealWith(new GwtCallBack<List<Authorize>>() {
								@Override
								protected void _call(List<Authorize> t) {
									getExtraAuthorizesGrid().getStore()
											.replaceAll(t);
								}

								@Override
								protected void post() {
									super.post();
									getExtraAuthorizesGrid().unmask();
								}
							}));
				}
			});
		}
	}

	protected void add(List<Authorize> list) {
		ListStore<Authorize> store = getExtraAuthorizesGrid().getStore();
		for (Authorize authorize : list) {
			if (store.indexOf(authorize) == -1) {
				store.add(authorize);
			}
		}
	}

	public AuthorizeGrid getExtraAuthorizesGrid() {
		return extraAuthorizesGrid;
	}

	public HeaderBar getHeaderBar() {
		return headerBar;
	}

	public AuthorizesSelectorWindow getAuthorizesSelectorWindow() {
		return authorizesSelectorWindow;
	}
}
