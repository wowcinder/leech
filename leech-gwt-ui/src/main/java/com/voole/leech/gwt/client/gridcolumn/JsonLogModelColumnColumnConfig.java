
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;

public class JsonLogModelColumnColumnConfig {


    public static ColumnConfig<JsonLogModelColumn, JsonLogModelGroupColumn> groupColumn() {
        ColumnConfig<JsonLogModelColumn, JsonLogModelGroupColumn> groupColumn = new ColumnConfig<JsonLogModelColumn, JsonLogModelGroupColumn>(PropertyUtils.JsonLogModelColumnProperty.groupColumn(), 200, "groupColumn");
        groupColumn.setSortable(false);
        groupColumn.setMenuDisabled(true);
        return groupColumn;
    }

    public static ColumnConfig<JsonLogModelColumn, String> path() {
        ColumnConfig<JsonLogModelColumn, String> path = new ColumnConfig<JsonLogModelColumn, String>(PropertyUtils.JsonLogModelColumnProperty.path(), 200, "path");
        path.setSortable(false);
        path.setMenuDisabled(true);
        return path;
    }

    public static ColumnConfig<JsonLogModelColumn, Integer> id() {
        ColumnConfig<JsonLogModelColumn, Integer> id = new ColumnConfig<JsonLogModelColumn, Integer>(PropertyUtils.JsonLogModelColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<JsonLogModelColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<JsonLogModelColumn, Date> lastUpdateTimeStamp = new ColumnConfig<JsonLogModelColumn, Date>(PropertyUtils.JsonLogModelColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<JsonLogModelColumn, Date> createTime() {
        ColumnConfig<JsonLogModelColumn, Date> createTime = new ColumnConfig<JsonLogModelColumn, Date>(PropertyUtils.JsonLogModelColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<JsonLogModelColumn, String> createTimePropertyName() {
        ColumnConfig<JsonLogModelColumn, String> createTimePropertyName = new ColumnConfig<JsonLogModelColumn, String>(PropertyUtils.JsonLogModelColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
