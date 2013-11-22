
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

public class CTypeLogModelGroupColumnColumnConfig {


    public static ColumnConfig<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<CTypeLogModelGroupColumn, HbaseTableVersion>(PropertyUtils.CTypeLogModelGroupColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        hbaseTableVersion.setSortable(false);
        hbaseTableVersion.setMenuDisabled(true);
        return hbaseTableVersion;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns() {
        ColumnConfig<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns = new ColumnConfig<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>>(PropertyUtils.CTypeLogModelGroupColumnProperty.columns(), 200, "columns");
        columns.setSortable(false);
        columns.setMenuDisabled(true);
        return columns;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> groupColumn() {
        ColumnConfig<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> groupColumn = new ColumnConfig<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelGroupColumnProperty.groupColumn(), 200, "groupColumn");
        groupColumn.setSortable(false);
        groupColumn.setMenuDisabled(true);
        return groupColumn;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, String> name() {
        ColumnConfig<CTypeLogModelGroupColumn, String> name = new ColumnConfig<CTypeLogModelGroupColumn, String>(PropertyUtils.CTypeLogModelGroupColumnProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, Integer> pos() {
        ColumnConfig<CTypeLogModelGroupColumn, Integer> pos = new ColumnConfig<CTypeLogModelGroupColumn, Integer>(PropertyUtils.CTypeLogModelGroupColumnProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, Integer> id() {
        ColumnConfig<CTypeLogModelGroupColumn, Integer> id = new ColumnConfig<CTypeLogModelGroupColumn, Integer>(PropertyUtils.CTypeLogModelGroupColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<CTypeLogModelGroupColumn, Date> lastUpdateTimeStamp = new ColumnConfig<CTypeLogModelGroupColumn, Date>(PropertyUtils.CTypeLogModelGroupColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, Date> createTime() {
        ColumnConfig<CTypeLogModelGroupColumn, Date> createTime = new ColumnConfig<CTypeLogModelGroupColumn, Date>(PropertyUtils.CTypeLogModelGroupColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, String> createTimePropertyName() {
        ColumnConfig<CTypeLogModelGroupColumn, String> createTimePropertyName = new ColumnConfig<CTypeLogModelGroupColumn, String>(PropertyUtils.CTypeLogModelGroupColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
