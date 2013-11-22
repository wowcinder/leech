package com.voole.leech.gwt.client.ui.logmodelmeta.c.tree;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelGroupColumnEditor;
import com.voole.leech.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelSimpleColumnEditor;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;

public class CTypeColumnTreeMenu extends Menu {
	private final CTypeLogModelColumnTree tree;
	private final CTypeLogModelSimpleColumnEditor simpleEditor;
	private final CTypeLogModelGroupColumnEditor groupEditor;

	private final MenuItem addSimpleColumn;
	private final MenuItem addGroupColumn;
	private final MenuItem delete;
	private final MenuItem modify;

	public CTypeColumnTreeMenu(final CTypeLogModelColumnTree owner) {
		this.tree = owner;
		simpleEditor = new CTypeLogModelSimpleColumnEditor();
		groupEditor = new CTypeLogModelGroupColumnEditor(owner);

		addSimpleColumn = new MenuItem("添加字段");
		addGroupColumn = new MenuItem("添加字段组");
		delete = new MenuItem("删除");
		modify = new MenuItem("修改 ");

		add(addSimpleColumn);
		add(addGroupColumn);
		add(new SeparatorMenuItem());
		add(modify);
		add(delete);

		initAddSimpleColumn();
		initAddGroupColumn();
		initModify();
		initDelete();

		this.addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				CTypeLogModelColumn selectItem = owner.getSelectionModel()
						.getSelectedItem();
				if (selectItem == null) {
					CTypeColumnTreeMenu.this.disable();
				}
				if (selectItem instanceof CTypeLogModelSimpleColumn) {
					addSimpleColumn.disable();
					addGroupColumn.disable();
				}
				if (selectItem.getGroupColumn() == null) {
					delete.disable();
					modify.disable();
				}
			}
		});

		this.addHideHandler(new HideHandler() {

			@Override
			public void onHide(HideEvent event) {
				CTypeColumnTreeMenu.this.enable();
				addSimpleColumn.enable();
				addGroupColumn.enable();
				delete.enable();
				modify.enable();
			}
		});
	}

	protected void initDelete() {
		delete.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final CTypeLogModelColumn column = tree.getSelectionModel()
						.getSelectedItem();
				RpcServiceUtils.CTypeLogModelMetaRpcService
						.deleteLogModelColumn(column.getId(),
								new RpcAsyncCallback<Void>() {
									@Override
									public void _onSuccess(Void t) {
										tree.getStore().remove(column);
									}
								});
			}
		});
	}

	protected void initModify() {
		modify.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				CTypeLogModelColumn column = tree.getSelectionModel()
						.getSelectedItem();
				if (column instanceof CTypeLogModelSimpleColumn) {
					simpleEditor
							.fireEditEvent(new EditEvent<CTypeLogModelSimpleColumn>(
									(CTypeLogModelSimpleColumn) column,
									new GwtCallBack<CTypeLogModelSimpleColumn>() {
										@Override
										protected void _call(
												CTypeLogModelSimpleColumn t) {
											tree.getStore().update(t);
										}
									}, true));
				} else {
					groupEditor
							.fireEditEvent(new EditEvent<CTypeLogModelGroupColumn>(
									(CTypeLogModelGroupColumn) column,
									new GwtCallBack<CTypeLogModelGroupColumn>() {
										@Override
										protected void _call(
												CTypeLogModelGroupColumn t) {
											tree.getStore().update(t);
										}
									}, true));
				}
			}
		});
	}

	protected void initAddSimpleColumn() {
		addSimpleColumn.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final CTypeLogModelGroupColumn selectItem = getGroupColumn();
				if (selectItem == null) {
					return;
				}

				CTypeLogModelSimpleColumn column = new CTypeLogModelSimpleColumn();
				column.setGroupColumn(selectItem);
				simpleEditor
						.fireEditEvent(new EditEvent<CTypeLogModelSimpleColumn>(
								column,
								new GwtCallBack<CTypeLogModelSimpleColumn>() {
									@Override
									protected void _call(
											CTypeLogModelSimpleColumn t) {
										tree.getStore().add(selectItem, t);
									}
								}));
			}
		});
	}

	protected CTypeLogModelGroupColumn getGroupColumn() {
		final CTypeLogModelGroupColumn selectItem = (CTypeLogModelGroupColumn) tree
				.getSelectionModel().getSelectedItem();
		return selectItem;
	}

	protected void initAddGroupColumn() {
		addGroupColumn.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final CTypeLogModelGroupColumn selectItem = getGroupColumn();
				if (selectItem == null) {
					return;
				}
				CTypeLogModelGroupColumn column = new CTypeLogModelGroupColumn();
				column.setGroupColumn(selectItem);
				groupEditor
						.fireEditEvent(new EditEvent<CTypeLogModelGroupColumn>(
								column,
								new GwtCallBack<CTypeLogModelGroupColumn>() {
									@Override
									protected void _call(
											CTypeLogModelGroupColumn t) {
										tree.getStore().add(selectItem, t);
									}
								}));
			}
		});
	}

}
