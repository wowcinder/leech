package com.voole.leech.gwt.client.ui.logmodelmeta.json.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.common.grid.CinderGrid;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.common.grid.GridConfigProvider;
import com.voole.leech.gwt.client.gridcolumn.JsonLogModelVersionColumnConfig;
import com.voole.leech.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelMappingEditor;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

public class JsonLogModelVersionGrid extends CinderGrid<JsonLogModelVersion> {
	public static class JsonLogModelVersionGridProvider extends
			GridConfigProvider<JsonLogModelVersion> {
		private final JsonLogModelMappingEditor mappingEditor;

		public JsonLogModelVersionGridProvider() {
			super(new ListStore<JsonLogModelVersion>(
					PropertyUtils.JsonLogModelVersionProperty.key()));
			mappingEditor = new JsonLogModelMappingEditor();
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<JsonLogModelVersion>> callback) {
			RpcServiceUtils.JsonLogModelMetaRpcService.pagingLogModelVersion(
					loadConfig, callback);
		}

		@Override
		protected void initColumnConfig() {

			ColumnConfig<JsonLogModelVersion, JsonLogModel> model = JsonLogModelVersionColumnConfig
					.model();
			model.setCell(new SimpleSafeHtmlRenderer<JsonLogModel>() {

				@Override
				protected String _getLabel(JsonLogModel c) {
					return c.getName();
				}
			}.getCell());
			ColumnConfig<JsonLogModelVersion, String> version = JsonLogModelVersionColumnConfig
					.version();
			ColumnConfig<JsonLogModelVersion, JsonLogModelGroupColumn> hbaseTable = JsonLogModelVersionColumnConfig
					.rootNode();
			hbaseTable
					.setCell(new SimpleSafeHtmlRenderer<JsonLogModelGroupColumn>() {
						@Override
						protected String _getLabel(JsonLogModelGroupColumn c) {
							if (c.getHbaseTableVersion() != null) {
								return c.getHbaseTableVersion().getTable()
										.getName();
							}
							return null;
						}
					}.getCell());
			hbaseTable.setHeader("hbaseTable");
			ColumnConfig<JsonLogModelVersion, JsonLogModelGroupColumn> hbaseTableVersion = JsonLogModelVersionColumnConfig
					.rootNode();
			hbaseTableVersion
					.setCell(new SimpleSafeHtmlRenderer<JsonLogModelGroupColumn>() {
						@Override
						protected String _getLabel(JsonLogModelGroupColumn c) {
							if (c.getHbaseTableVersion() != null) {
								return c.getHbaseTableVersion().getVersion();
							}
							return null;
						}
					}.getCell());
			hbaseTableVersion.setHeader("hbaseTableVersion");
			ColumnConfig<JsonLogModelVersion, String> desc = JsonLogModelVersionColumnConfig
					.desc();

			ColumnConfig<JsonLogModelVersion, String> bt = new ColumnConfig<JsonLogModelVersion, String>(
					new ValueProvider<JsonLogModelVersion, String>() {

						@Override
						public String getValue(JsonLogModelVersion object) {
							return "修改mapping";
						}

						@Override
						public void setValue(JsonLogModelVersion object,
								String v) {

						}

						@Override
						public String getPath() {
							return null;
						}
					});
			TextButtonCell tbCell = new TextButtonCell();
			bt.setCell(tbCell);
			tbCell.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					mappingEditor
							.fireEditEvent(new EditEvent<JsonLogModelVersion>(
									getStore().get(
											event.getContext().getIndex()),
									new GwtCallBack<JsonLogModelVersion>() {
										@Override
										protected void _call(
												JsonLogModelVersion t) {

										}
									}, true));
				}
			});
			columns.add(model);
			columns.add(version);
			columns.add(hbaseTable);
			columns.add(hbaseTableVersion);
			columns.add(desc);
			columns.add(bt);

		}
	}

	public JsonLogModelVersionGrid(GridConfig gridConfig) {
		super(new JsonLogModelVersionGridProvider(),

		gridConfig);
	}

}
