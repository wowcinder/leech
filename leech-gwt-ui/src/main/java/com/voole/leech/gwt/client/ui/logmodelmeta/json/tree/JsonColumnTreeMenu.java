package com.voole.leech.gwt.client.ui.logmodelmeta.json.tree;

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
import com.voole.leech.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelGroupColumnEditor;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelSimpleColumnEditor;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;

public class JsonColumnTreeMenu extends Menu {
	private final JsonLogModelColumnTree tree;
	private final JsonLogModelSimpleColumnEditor simpleEditor;
	private final JsonLogModelGroupColumnEditor groupEditor;

	private final MenuItem addSimpleColumn;
	private final MenuItem addGroupColumn;
	private final MenuItem delete;
	private final MenuItem modify;

	public JsonColumnTreeMenu(final JsonLogModelColumnTree owner) {
		this.tree = owner;
		simpleEditor = new JsonLogModelSimpleColumnEditor();
		groupEditor = new JsonLogModelGroupColumnEditor(owner);

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
				JsonLogModelColumn selectItem = owner.getSelectionModel()
						.getSelectedItem();
				if (selectItem == null) {
					JsonColumnTreeMenu.this.disable();
				}
				if (selectItem instanceof JsonLogModelSimpleColumn) {
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
				JsonColumnTreeMenu.this.enable();
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
				final JsonLogModelColumn column = tree.getSelectionModel()
						.getSelectedItem();
				RpcServiceUtils.JsonLogModelMetaRpcService
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
				JsonLogModelColumn column = tree.getSelectionModel()
						.getSelectedItem();
				if (column instanceof JsonLogModelSimpleColumn) {
					simpleEditor
							.fireEditEvent(new EditEvent<JsonLogModelSimpleColumn>(
									(JsonLogModelSimpleColumn) column,
									new GwtCallBack<JsonLogModelSimpleColumn>() {
										@Override
										protected void _call(
												JsonLogModelSimpleColumn t) {
											tree.getStore().update(t);
										}
									}, true));
				} else {
					groupEditor
							.fireEditEvent(new EditEvent<JsonLogModelGroupColumn>(
									(JsonLogModelGroupColumn) column,
									new GwtCallBack<JsonLogModelGroupColumn>() {
										@Override
										protected void _call(
												JsonLogModelGroupColumn t) {
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
				final JsonLogModelGroupColumn selectItem = getGroupColumn();
				if (selectItem == null) {
					return;
				}

				JsonLogModelSimpleColumn column = new JsonLogModelSimpleColumn();
				column.setGroupColumn(selectItem);
				simpleEditor
						.fireEditEvent(new EditEvent<JsonLogModelSimpleColumn>(
								column,
								new GwtCallBack<JsonLogModelSimpleColumn>() {
									@Override
									protected void _call(
											JsonLogModelSimpleColumn t) {
										tree.getStore().add(selectItem, t);
									}
								}));
			}
		});
	}

	protected JsonLogModelGroupColumn getGroupColumn() {
		final JsonLogModelGroupColumn selectItem = (JsonLogModelGroupColumn) tree
				.getSelectionModel().getSelectedItem();
		return selectItem;
	}

	protected void initAddGroupColumn() {
		addGroupColumn.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final JsonLogModelGroupColumn selectItem = getGroupColumn();
				if (selectItem == null) {
					return;
				}
				JsonLogModelGroupColumn column = new JsonLogModelGroupColumn();
				column.setGroupColumn(selectItem);
				groupEditor
						.fireEditEvent(new EditEvent<JsonLogModelGroupColumn>(
								column,
								new GwtCallBack<JsonLogModelGroupColumn>() {
									@Override
									protected void _call(
											JsonLogModelGroupColumn t) {
										tree.getStore().add(selectItem, t);
									}
								}));
			}
		});
	}

}
