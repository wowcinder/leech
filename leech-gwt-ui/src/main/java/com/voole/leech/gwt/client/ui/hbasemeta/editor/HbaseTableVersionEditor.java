/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasemeta.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
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
import com.voole.leech.gwt.client.ui.hbasemeta.combox.HbaseTableCombox;
import com.voole.leech.gwt.client.ui.hbasemeta.grid.HbaseTableColumnGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableVersionEditor extends CinderEditor<HbaseTableVersion> {

	public interface Driver extends
			SimpleBeanEditorDriver<HbaseTableVersion, HbaseTableVersionEditor> {

	}

	TextField version;
	HbaseTableCombox table;
	TextField desc;
	ListStoreEditor<HbaseTableColumn> columns;
	@Ignore
	HbaseTableColumnGrid tableColumnGrid;
	@Ignore
	HeaderBar headerBar;
	@Ignore
	HbaseTableColumnEditor columnEditor;

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public HbaseTableVersionEditor() {
		super(GWT.<Driver> create(Driver.class), "hbase_table_version");
	}

	@Override
	protected void _update(HbaseTableVersion t) {
		RpcServiceUtils.HbaseMetaRpcService.updateHbaseTableVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(HbaseTableVersion t) {
		RpcServiceUtils.HbaseMetaRpcService.saveHbaseTableVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		version = new TextField();
		table = new HbaseTableCombox();
		desc = new TextField();

		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(table, "table"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);

		initHbaseTableColumns();
		initHbaseTableColumnEditorHanlder();
	}

	protected void initHbaseTableColumns() {
		headerBar = new HeaderBar();
		tableColumnGrid = new HbaseTableColumnGrid(new GridConfig(true, false));
		tableColumnGrid.setHeight(300);
		columnEditor = new HbaseTableColumnEditor();
		columns = new ListStoreEditor<HbaseTableColumn>(
				tableColumnGrid.getStore());

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(headerBar, vd);
		vlc.add(tableColumnGrid, mainVd);

		FieldLabel columnFieldLabel = new FieldLabel(vlc, "columns");
		columnFieldLabel.setLabelAlign(LabelAlign.TOP);

		layoutContainer.add(columnFieldLabel, mainVd);
	}

	protected void initHbaseTableColumnEditorHanlder() {
		getHeaderBar().getAddBt().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				HbaseTableVersion version = getCurrEditEvent().getTarget();
				HbaseTableColumn tableColumn = new HbaseTableColumn();
				tableColumn.setVersion(version);

				getColumnEditor().fireEditEvent(
						new EditEvent<HbaseTableColumn>(tableColumn,
								new GwtCallBack<HbaseTableColumn>() {
									@Override
									protected void _call(HbaseTableColumn t) {
										getTableColumnGrid().getStore().add(t);
									}
								}));
			}
		});

		getHeaderBar().getDelBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getHeaderBar().getDelBt().disable();
				final List<HbaseTableColumn> list = getTableColumnGrid()
						.getSelectionModel().getSelectedItems();
				if (list.size() == 0) {
					getHeaderBar().getDelBt().enable();
					return;
				}
				if (getCurrEditEvent().getTarget().getId() == null) {
					deleteColumnsInView(list);
					getHeaderBar().getDelBt().enable();
				} else {
					List<Integer> ids = new ArrayList<Integer>();
					for (HbaseTableColumn hbaseTableColumn : list) {
						ids.add(hbaseTableColumn.getId());
					}
					RpcServiceUtils.HbaseMetaRpcService
							.deleteHbaseTableColumns(ids,
									new RpcAsyncCallback<Void>() {

										@Override
										public void _onSuccess(Void t) {
											deleteColumnsInView(list);
										}

										public void post() {
											getHeaderBar().getDelBt().enable();
										};
									});
				}
			}
		});

		getTableColumnGrid().addCellDoubleClickHandler(
				new CellDoubleClickHandler() {

					@Override
					public void onCellClick(CellDoubleClickEvent event) {
						HbaseTableColumn tableColumn = getTableColumnGrid()
								.getStore().get(event.getRowIndex());
						getColumnEditor().fireEditEvent(
								new EditEvent<HbaseTableColumn>(tableColumn,
										new GwtCallBack<HbaseTableColumn>() {
											@Override
											protected void _call(
													HbaseTableColumn t) {
												getTableColumnGrid().getStore()
														.update(t);
											}
										}, true));
					}
				});
	}

	@Override
	public void onEdit(final EditEvent<HbaseTableVersion> event) {
		if (event.getTarget().getColumns() == null) {
			event.getTarget().setColumns(new ArrayList<HbaseTableColumn>());
		}
		super.onEdit(event);
		if (event.isUpdate()) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					getTableColumnGrid().mask("加载中...");
					RpcServiceUtils.HbaseMetaRpcService.getColumnsByVersionId(
							event.getTarget().getId(),
							RpcAsyncCallback
									.dealWith(new GwtCallBack<List<HbaseTableColumn>>() {

										@Override
										protected void _call(
												List<HbaseTableColumn> t) {
											getTableColumnGrid().getStore()
													.replaceAll(t);
										}

										@Override
										protected void post() {
											super.post();
											getTableColumnGrid().unmask();
										}
									}));

				}
			});
		}
	}

	protected void deleteColumnsInView(List<HbaseTableColumn> list) {
		ListStore<HbaseTableColumn> store = getTableColumnGrid().getStore();
		for (HbaseTableColumn hbaseTableColumn : list) {
			store.remove(hbaseTableColumn);
		}
	}

	@Ignore
	public HbaseTableColumnGrid getTableColumnGrid() {
		return tableColumnGrid;
	}

	@Ignore
	public HeaderBar getHeaderBar() {
		return headerBar;
	}

	@Ignore
	public HbaseTableColumnEditor getColumnEditor() {
		return columnEditor;
	}

}
