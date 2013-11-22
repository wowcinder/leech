
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

public class CTypeLogModelColumnColumnConfig {


    public static ColumnConfig<CTypeLogModelColumn, CTypeLogModelGroupColumn> groupColumn() {
        ColumnConfig<CTypeLogModelColumn, CTypeLogModelGroupColumn> groupColumn = new ColumnConfig<CTypeLogModelColumn, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelColumnProperty.groupColumn(), 200, "groupColumn");
        groupColumn.setSortable(false);
        groupColumn.setMenuDisabled(true);
        return groupColumn;
    }

    public static ColumnConfig<CTypeLogModelColumn, String> name() {
        ColumnConfig<CTypeLogModelColumn, String> name = new ColumnConfig<CTypeLogModelColumn, String>(PropertyUtils.CTypeLogModelColumnProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<CTypeLogModelColumn, Integer> pos() {
        ColumnConfig<CTypeLogModelColumn, Integer> pos = new ColumnConfig<CTypeLogModelColumn, Integer>(PropertyUtils.CTypeLogModelColumnProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<CTypeLogModelColumn, Integer> id() {
        ColumnConfig<CTypeLogModelColumn, Integer> id = new ColumnConfig<CTypeLogModelColumn, Integer>(PropertyUtils.CTypeLogModelColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<CTypeLogModelColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<CTypeLogModelColumn, Date> lastUpdateTimeStamp = new ColumnConfig<CTypeLogModelColumn, Date>(PropertyUtils.CTypeLogModelColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<CTypeLogModelColumn, Date> createTime() {
        ColumnConfig<CTypeLogModelColumn, Date> createTime = new ColumnConfig<CTypeLogModelColumn, Date>(PropertyUtils.CTypeLogModelColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<CTypeLogModelColumn, String> createTimePropertyName() {
        ColumnConfig<CTypeLogModelColumn, String> createTimePropertyName = new ColumnConfig<CTypeLogModelColumn, String>(PropertyUtils.CTypeLogModelColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
