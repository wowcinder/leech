package com.voole.leech.gwt.client.ui.logmodelmeta.c.tree;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.voole.leech.gwt.client.ui.logmodelmeta.HbaseTableVersionChangeEvent;
import com.voole.leech.gwt.client.ui.logmodelmeta.HbaseTableVersionChangeEvent.HbaseTableVersionChangeHanlder;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import com.voole.leech.shared.HbaseVersionChangeUtil;

public class CTypeLogModelColumnTree extends Tree<CTypeLogModelColumn, String>
		implements HbaseTableVersionChangeHanlder<CTypeLogModelGroupColumn> {

	public CTypeLogModelColumnTree(TreeStore<CTypeLogModelColumn> store) {
		super(store, new ValueProvider<CTypeLogModelColumn, String>() {

			@Override
			public String getValue(CTypeLogModelColumn column) {
				String prefix = "";
				if (column instanceof CTypeLogModelGroupColumn) {
					prefix = "group:";
				}
				return prefix + column.getName();
			}

			@Override
			public void setValue(CTypeLogModelColumn object, String value) {
			}

			@Override
			public String getPath() {
				return null;
			}
		});
		setIconProvider(new IconProvider<CTypeLogModelColumn>() {
			@Override
			public ImageResource getIcon(CTypeLogModelColumn model) {
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
				} else if (model instanceof CTypeLogModelGroupColumn) {
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
		setContextMenu(new CTypeColumnTreeMenu(this));

		addHandler(this, HbaseTableVersionChangeEvent.TYPE);
	}

	private void initDND() {
		new TreeDragSource<CTypeLogModelColumn>(this);
		TreeDropTarget<CTypeLogModelColumn> target = new CTypeLogModelColumnTreeDropTarget(
				this);
		target.setAllowSelfAsSource(true);
		target.setAllowDropOnLeaf(true);
		target.setFeedback(Feedback.BOTH);
	}

	@Override
	public void onCheckVersionChange(
			HbaseTableVersionChangeEvent<CTypeLogModelGroupColumn> event) {
		CTypeLogModelGroupColumn owner = event.getColumn();
		HbaseTableVersion version = owner.getHbaseTableVersion();
		for (CTypeLogModelColumn column : getStore().getChildren(owner)) {
			if (column instanceof CTypeLogModelSimpleColumn) {
				CTypeLogModelSimpleColumn simpleColumn = (CTypeLogModelSimpleColumn) column;
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
