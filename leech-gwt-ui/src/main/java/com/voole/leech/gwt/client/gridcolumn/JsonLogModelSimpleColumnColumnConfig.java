
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;

public class JsonLogModelSimpleColumnColumnConfig {


    public static ColumnConfig<JsonLogModelSimpleColumn, HbaseTableColumn> hbaseTableColumn() {
        ColumnConfig<JsonLogModelSimpleColumn, HbaseTableColumn> hbaseTableColumn = new ColumnConfig<JsonLogModelSimpleColumn, HbaseTableColumn>(PropertyUtils.JsonLogModelSimpleColumnProperty.hbaseTableColumn(), 200, "hbaseTableColumn");
        hbaseTableColumn.setSortable(false);
        hbaseTableColumn.setMenuDisabled(true);
        return hbaseTableColumn;
    }

    public static ColumnConfig<JsonLogModelSimpleColumn, JsonLogModelGroupColumn> groupColumn() {
        ColumnConfig<JsonLogModelSimpleColumn, JsonLogModelGroupColumn> groupColumn = new ColumnConfig<JsonLogModelSimpleColumn, JsonLogModelGroupColumn>(PropertyUtils.JsonLogModelSimpleColumnProperty.groupColumn(), 200, "groupColumn");
        groupColumn.setSortable(false);
        groupColumn.setMenuDisabled(true);
        return groupColumn;
    }

    public static ColumnConfig<JsonLogModelSimpleColumn, String> path() {
        ColumnConfig<JsonLogModelSimpleColumn, String> path = new ColumnConfig<JsonLogModelSimpleColumn, String>(PropertyUtils.JsonLogModelSimpleColumnProperty.path(), 200, "path");
        path.setSortable(false);
        path.setMenuDisabled(true);
        return path;
    }

    public static ColumnConfig<JsonLogModelSimpleColumn, Integer> id() {
        ColumnConfig<JsonLogModelSimpleColumn, Integer> id = new ColumnConfig<JsonLogModelSimpleColumn, Integer>(PropertyUtils.JsonLogModelSimpleColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<JsonLogModelSimpleColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<JsonLogModelSimpleColumn, Date> lastUpdateTimeStamp = new ColumnConfig<JsonLogModelSimpleColumn, Date>(PropertyUtils.JsonLogModelSimpleColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<JsonLogModelSimpleColumn, Date> createTime() {
        ColumnConfig<JsonLogModelSimpleColumn, Date> createTime = new ColumnConfig<JsonLogModelSimpleColumn, Date>(PropertyUtils.JsonLogModelSimpleColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<JsonLogModelSimpleColumn, String> createTimePropertyName() {
        ColumnConfig<JsonLogModelSimpleColumn, String> createTimePropertyName = new ColumnConfig<JsonLogModelSimpleColumn, String>(PropertyUtils.JsonLogModelSimpleColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
