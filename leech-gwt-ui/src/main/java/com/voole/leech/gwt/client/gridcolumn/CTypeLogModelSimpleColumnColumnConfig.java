
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;

public class CTypeLogModelSimpleColumnColumnConfig {


    public static ColumnConfig<CTypeLogModelSimpleColumn, HbaseTableColumn> hbaseTableColumn() {
        ColumnConfig<CTypeLogModelSimpleColumn, HbaseTableColumn> hbaseTableColumn = new ColumnConfig<CTypeLogModelSimpleColumn, HbaseTableColumn>(PropertyUtils.CTypeLogModelSimpleColumnProperty.hbaseTableColumn(), 200, "hbaseTableColumn");
        hbaseTableColumn.setSortable(false);
        hbaseTableColumn.setMenuDisabled(true);
        return hbaseTableColumn;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, CTypeLogModelGroupColumn> groupColumn() {
        ColumnConfig<CTypeLogModelSimpleColumn, CTypeLogModelGroupColumn> groupColumn = new ColumnConfig<CTypeLogModelSimpleColumn, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelSimpleColumnProperty.groupColumn(), 200, "groupColumn");
        groupColumn.setSortable(false);
        groupColumn.setMenuDisabled(true);
        return groupColumn;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, String> name() {
        ColumnConfig<CTypeLogModelSimpleColumn, String> name = new ColumnConfig<CTypeLogModelSimpleColumn, String>(PropertyUtils.CTypeLogModelSimpleColumnProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, Integer> pos() {
        ColumnConfig<CTypeLogModelSimpleColumn, Integer> pos = new ColumnConfig<CTypeLogModelSimpleColumn, Integer>(PropertyUtils.CTypeLogModelSimpleColumnProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, Integer> id() {
        ColumnConfig<CTypeLogModelSimpleColumn, Integer> id = new ColumnConfig<CTypeLogModelSimpleColumn, Integer>(PropertyUtils.CTypeLogModelSimpleColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<CTypeLogModelSimpleColumn, Date> lastUpdateTimeStamp = new ColumnConfig<CTypeLogModelSimpleColumn, Date>(PropertyUtils.CTypeLogModelSimpleColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, Date> createTime() {
        ColumnConfig<CTypeLogModelSimpleColumn, Date> createTime = new ColumnConfig<CTypeLogModelSimpleColumn, Date>(PropertyUtils.CTypeLogModelSimpleColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<CTypeLogModelSimpleColumn, String> createTimePropertyName() {
        ColumnConfig<CTypeLogModelSimpleColumn, String> createTimePropertyName = new ColumnConfig<CTypeLogModelSimpleColumn, String>(PropertyUtils.CTypeLogModelSimpleColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
