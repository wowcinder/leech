/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.ui.CenterView;
import com.voole.leech.gwt.client.ui.hbasequery.column.HbaseColumnConfig;
import com.voole.leech.gwt.client.ui.hbasequery.grid.HbaseQueryGrid2;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年9月2日
 */
@MenuToken(name = "hbase查询", token = "hbase_query", group = "hbase查询")
public class HbaseListView extends BorderLayoutContainer implements CenterView {
	private ListView<HbaseTable, String> hbaseTableList;
	private ListStore<HbaseTable> HbaseTableListStore;
	private TabPanel viewPanel;
	ContentPanel west;

	public HbaseListView() {
		initHbaseTableList();

		BorderLayoutData westData = new BorderLayoutData(200);
		westData.setMargins(new Margins(5, 0, 5, 5));
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setCollapseHidden(true);
		westData.setCollapseMini(true);

		west = new ContentPanel();
		west.setHeadingText("hbase表");
		west.setBodyBorder(true);
		west.add(hbaseTableList);
		setWestWidget(west, westData);

		MarginData centerData = new MarginData();
		centerData.setMargins(new Margins(5));

		viewPanel = new TabPanel();
		viewPanel.setBodyBorder(true);
		viewPanel.setTabScroll(true);
		viewPanel.setResizeTabs(true);
		viewPanel.setMinTabWidth(130);
		viewPanel.setCloseContextMenu(true);
		viewPanel.getElement().getStyle().setTextAlign(TextAlign.CENTER);

		setCenterWidget(viewPanel, centerData);
	}

	private void initHbaseTableList() {
		HbaseTableListStore = new ListStore<HbaseTable>(
				new ModelKeyProvider<HbaseTable>() {
					@Override
					public String getKey(HbaseTable item) {
						return item.getId() + "";
					}
				});
		hbaseTableList = new ListView<HbaseTable, String>(HbaseTableListStore,
				new ValueProvider<HbaseTable, String>() {

					@Override
					public String getValue(HbaseTable object) {
						return object.getName();
					}

					@Override
					public void setValue(HbaseTable object, String value) {
					}

					@Override
					public String getPath() {
						return null;
					}
				}) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						RpcServiceUtils.HbaseMetaRpcService
								.getHbaseTablesForCombox(new RpcAsyncCallback<List<HbaseTable>>() {

									@Override
									public void _onSuccess(List<HbaseTable> t) {
										HbaseTableListStore.addAll(t);
									}

								});
					}
				});
			}
		};

		hbaseTableList.getElement().getStyle().setTextAlign(TextAlign.LEFT);

		hbaseTableList.getSelectionModel().addSelectionHandler(
				new SelectionHandler<HbaseTable>() {

					@Override
					public void onSelection(SelectionEvent<HbaseTable> event) {
						showTable(event.getSelectedItem().getName());
					}
				});
	}

	private void showTable(final String tableName) {
		hbaseTableList.mask("加载meta...");
		RpcServiceUtils.HbaseMetaRpcService.getTableAllColumns(tableName, null,
				new RpcAsyncCallback<List<HbaseTableColumn>>() {

					@Override
					public void _onSuccess(List<HbaseTableColumn> t) {
						List<ColumnConfig<HbaseRecord<String>, ?>> columns = new ArrayList<ColumnConfig<HbaseRecord<String>, ?>>();
						RowNumberer<HbaseRecord<String>> rowNumberer = new RowNumberer<HbaseRecord<String>>(
								new IdentityValueProvider<HbaseRecord<String>>());
						rowNumberer.setResizable(true);
						rowNumberer.setFixed(false);
						columns.add(rowNumberer);
						columns.add(new ColumnConfig<HbaseRecord<String>, String>(
								new ValueProvider<HbaseRecord<String>, String>() {
									@Override
									public String getValue(
											HbaseRecord<String> object) {
										return object.getKey().toString();
									}

									@Override
									public void setValue(
											HbaseRecord<String> object,
											String value) {

									}

									@Override
									public String getPath() {
										return null;
									}
								}, 100, "key"));
						for (HbaseTableColumn hbaseTableColumn : t) {
							columns.add(new HbaseColumnConfig<String>(
									hbaseTableColumn));
						}
						HbaseQueryGrid2 tab = new HbaseQueryGrid2(columns,
								tableName);
						viewPanel.add(tab, new TabItemConfig(tableName, true));
						viewPanel.setActiveWidget(tab);
					}

					@Override
					public void post() {
						super.post();
						hbaseTableList.unmask();
					}
				});

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
