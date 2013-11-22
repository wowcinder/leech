/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.user.combox;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.combox.AddEnableComboBox;
import com.voole.leech.gwt.client.ui.user.editor.UserGroupEditor;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class UserGroupCombox extends AddEnableComboBox<UserGroup> {

	/**
	 * @param store
	 * @param labelProvider
	 * @param editor
	 */
	public UserGroupCombox() {
		super(new ListStore<UserGroup>(PropertyUtils.UserGroupProperty.key()),
				new LabelProvider<UserGroup>() {

					@Override
					public String getLabel(UserGroup item) {
						return item.getName();
					}
				}, new UserGroupEditor());
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.UserRpcService
						.getUserGroupListForCombox(new RpcAsyncCallback<List<UserGroup>>() {

							@Override
							public void _onSuccess(List<UserGroup> t) {
								getStore().clear();
								getStore().addAll(t);
								getStore().add(getAddItem());
							}
						});
			}
		});

	}

	@Override
	protected UserGroup createAddItem() {
		UserGroup ug = new UserGroup();
		ug.setId(-1);
		ug.setName("添加...");
		return ug;
	}

	@Override
	protected boolean isAddItem(UserGroup selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected UserGroup newComboxInstance() {
		return new UserGroup();
	}

}
