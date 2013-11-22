
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;

public class JsonLogModelGroupColumnColumnConfig {


    public static ColumnConfig<JsonLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<JsonLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<JsonLogModelGroupColumn, HbaseTableVersion>(PropertyUtils.JsonLogModelGroupColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        hbaseTableVersion.setSortable(false);
        hbaseTableVersion.setMenuDisabled(true);
        return hbaseTableVersion;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, List<JsonLogModelColumn>> columns() {
        ColumnConfig<JsonLogModelGroupColumn, List<JsonLogModelColumn>> columns = new ColumnConfig<JsonLogModelGroupColumn, List<JsonLogModelColumn>>(PropertyUtils.JsonLogModelGroupColumnProperty.columns(), 200, "columns");
        columns.setSortable(false);
        columns.setMenuDisabled(true);
        return columns;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, JsonLogModelGroupColumn> groupColumn() {
        ColumnConfig<JsonLogModelGroupColumn, JsonLogModelGroupColumn> groupColumn = new ColumnConfig<JsonLogModelGroupColumn, JsonLogModelGroupColumn>(PropertyUtils.JsonLogModelGroupColumnProperty.groupColumn(), 200, "groupColumn");
        groupColumn.setSortable(false);
        groupColumn.setMenuDisabled(true);
        return groupColumn;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, String> path() {
        ColumnConfig<JsonLogModelGroupColumn, String> path = new ColumnConfig<JsonLogModelGroupColumn, String>(PropertyUtils.JsonLogModelGroupColumnProperty.path(), 200, "path");
        path.setSortable(false);
        path.setMenuDisabled(true);
        return path;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, Integer> id() {
        ColumnConfig<JsonLogModelGroupColumn, Integer> id = new ColumnConfig<JsonLogModelGroupColumn, Integer>(PropertyUtils.JsonLogModelGroupColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<JsonLogModelGroupColumn, Date> lastUpdateTimeStamp = new ColumnConfig<JsonLogModelGroupColumn, Date>(PropertyUtils.JsonLogModelGroupColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, Date> createTime() {
        ColumnConfig<JsonLogModelGroupColumn, Date> createTime = new ColumnConfig<JsonLogModelGroupColumn, Date>(PropertyUtils.JsonLogModelGroupColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<JsonLogModelGroupColumn, String> createTimePropertyName() {
        ColumnConfig<JsonLogModelGroupColumn, String> createTimePropertyName = new ColumnConfig<JsonLogModelGroupColumn, String>(PropertyUtils.JsonLogModelGroupColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
