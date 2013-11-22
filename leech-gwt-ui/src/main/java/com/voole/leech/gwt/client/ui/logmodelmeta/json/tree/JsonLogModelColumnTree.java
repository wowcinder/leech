package com.voole.leech.gwt.client.ui.logmodelmeta.json.tree;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.voole.leech.gwt.client.ui.logmodelmeta.HbaseTableVersionChangeEvent;
import com.voole.leech.gwt.client.ui.logmodelmeta.HbaseTableVersionChangeEvent.HbaseTableVersionChangeHanlder;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;
import com.voole.leech.shared.HbaseVersionChangeUtil;

public class JsonLogModelColumnTree extends Tree<JsonLogModelColumn, String>
		implements HbaseTableVersionChangeHanlder<JsonLogModelGroupColumn> {

	public JsonLogModelColumnTree(TreeStore<JsonLogModelColumn> store) {
		super(store, new ValueProvider<JsonLogModelColumn, String>() {

			@Override
			public String getValue(JsonLogModelColumn column) {
				String prefix = "";
				if (column instanceof JsonLogModelGroupColumn) {
					prefix = "group:";
				}
				return prefix + column.getPath();
			}

			@Override
			public void setValue(JsonLogModelColumn object, String value) {
			}

			@Override
			public String getPath() {
				return null;
			}
		});
		setIconProvider(new IconProvider<JsonLogModelColumn>() {
			@Override
			public ImageResource getIcon(JsonLogModelColumn model) {
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
				} else if (model instanceof JsonLogModelGroupColumn) {
					style = ts.getNodeCloseIcon() != null ? ts
							.getNodeCloseIcon() : appearance.closeNodeIcon();
				} else {
					style = ts.getLeafIcon();
				}
				return style;
			}
		});
		initDND();
		getElement().getStyle().setTextAlign(TextAlign.LEFT);
		setWidth(300);
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setContextMenu(new JsonColumnTreeMenu(this));

		addHandler(this, HbaseTableVersionChangeEvent.TYPE);
	}

	private void initDND() {
		// new TreeDragSource<JsonLogModelColumn>(this);
		// TreeDropTarget<JsonLogModelColumn> target = new
		// JsonLogModelColumnTreeDropTarget(
		// this);
		// target.setAllowSelfAsSource(true);
		// target.setAllowDropOnLeaf(true);
		// target.setFeedback(Feedback.BOTH);
	}

	@Override
	public void onCheckVersionChange(
			HbaseTableVersionChangeEvent<JsonLogModelGroupColumn> event) {
		JsonLogModelGroupColumn owner = event.getColumn();
		HbaseTableVersion version = owner.getHbaseTableVersion();
		for (JsonLogModelColumn column : getStore().getChildren(owner)) {
			if (column instanceof JsonLogModelSimpleColumn) {
				JsonLogModelSimpleColumn simpleColumn = (JsonLogModelSimpleColumn) column;
				if (simpleColumn.getHbaseTableColumn() == null) {
					continue;
				}
				HbaseTableVersion oldVersion = simpleColumn
						.getHbaseTableColumn().getVersion();
				if (HbaseVersionChangeUtil.isChange(oldVersion, version)) {
					simpleColumn.setHbaseTableColumn(null);
				}
			}
		}
	}

}
