
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableColumnColumnConfig {


    public static ColumnConfig<HbaseTableColumn, String> shortname() {
        ColumnConfig<HbaseTableColumn, String> shortname = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.shortname(), 200, "shortname");
        shortname.setSortable(false);
        shortname.setMenuDisabled(true);
        return shortname;
    }

    public static ColumnConfig<HbaseTableColumn, String> name() {
        ColumnConfig<HbaseTableColumn, String> name = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<HbaseTableColumn, Integer> id() {
        ColumnConfig<HbaseTableColumn, Integer> id = new ColumnConfig<HbaseTableColumn, Integer>(PropertyUtils.HbaseTableColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<HbaseTableColumn, HbaseTableColumn.HbaseTableColumnType> type() {
        ColumnConfig<HbaseTableColumn, HbaseTableColumn.HbaseTableColumnType> type = new ColumnConfig<HbaseTableColumn, HbaseTableColumn.HbaseTableColumnType>(PropertyUtils.HbaseTableColumnProperty.type(), 200, "type");
        type.setSortable(false);
        type.setMenuDisabled(true);
        return type;
    }

    public static ColumnConfig<HbaseTableColumn, String> desc() {
        ColumnConfig<HbaseTableColumn, String> desc = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<HbaseTableColumn, HbaseTableVersion> version() {
        ColumnConfig<HbaseTableColumn, HbaseTableVersion> version = new ColumnConfig<HbaseTableColumn, HbaseTableVersion>(PropertyUtils.HbaseTableColumnProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<HbaseTableColumn, Integer> pos() {
        ColumnConfig<HbaseTableColumn, Integer> pos = new ColumnConfig<HbaseTableColumn, Integer>(PropertyUtils.HbaseTableColumnProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<HbaseTableColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<HbaseTableColumn, Date> lastUpdateTimeStamp = new ColumnConfig<HbaseTableColumn, Date>(PropertyUtils.HbaseTableColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<HbaseTableColumn, Date> createTime() {
        ColumnConfig<HbaseTableColumn, Date> createTime = new ColumnConfig<HbaseTableColumn, Date>(PropertyUtils.HbaseTableColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<HbaseTableColumn, String> createTimePropertyName() {
        ColumnConfig<HbaseTableColumn, String> createTimePropertyName = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
