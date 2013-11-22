
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableVersionColumnConfig {


    public static ColumnConfig<HbaseTableVersion, Integer> id() {
        ColumnConfig<HbaseTableVersion, Integer> id = new ColumnConfig<HbaseTableVersion, Integer>(PropertyUtils.HbaseTableVersionProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<HbaseTableVersion, HbaseTable> table() {
        ColumnConfig<HbaseTableVersion, HbaseTable> table = new ColumnConfig<HbaseTableVersion, HbaseTable>(PropertyUtils.HbaseTableVersionProperty.table(), 200, "table");
        table.setSortable(false);
        table.setMenuDisabled(true);
        return table;
    }

    public static ColumnConfig<HbaseTableVersion, String> desc() {
        ColumnConfig<HbaseTableVersion, String> desc = new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<HbaseTableVersion, String> version() {
        ColumnConfig<HbaseTableVersion, String> version = new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>> columns() {
        ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>> columns = new ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>>(PropertyUtils.HbaseTableVersionProperty.columns(), 200, "columns");
        columns.setSortable(false);
        columns.setMenuDisabled(true);
        return columns;
    }

    public static ColumnConfig<HbaseTableVersion, Date> lastUpdateTimeStamp() {
        ColumnConfig<HbaseTableVersion, Date> lastUpdateTimeStamp = new ColumnConfig<HbaseTableVersion, Date>(PropertyUtils.HbaseTableVersionProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<HbaseTableVersion, Date> createTime() {
        ColumnConfig<HbaseTableVersion, Date> createTime = new ColumnConfig<HbaseTableVersion, Date>(PropertyUtils.HbaseTableVersionProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<HbaseTableVersion, String> createTimePropertyName() {
        ColumnConfig<HbaseTableVersion, String> createTimePropertyName = new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
