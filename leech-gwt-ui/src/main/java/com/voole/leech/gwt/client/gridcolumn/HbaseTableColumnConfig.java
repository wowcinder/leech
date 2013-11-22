
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableColumnConfig {


    public static ColumnConfig<HbaseTable, String> shortname() {
        ColumnConfig<HbaseTable, String> shortname = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.shortname(), 200, "shortname");
        shortname.setSortable(false);
        shortname.setMenuDisabled(true);
        return shortname;
    }

    public static ColumnConfig<HbaseTable, List<HbaseTableVersion>> versions() {
        ColumnConfig<HbaseTable, List<HbaseTableVersion>> versions = new ColumnConfig<HbaseTable, List<HbaseTableVersion>>(PropertyUtils.HbaseTableProperty.versions(), 200, "versions");
        versions.setSortable(false);
        versions.setMenuDisabled(true);
        return versions;
    }

    public static ColumnConfig<HbaseTable, String> name() {
        ColumnConfig<HbaseTable, String> name = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<HbaseTable, Integer> id() {
        ColumnConfig<HbaseTable, Integer> id = new ColumnConfig<HbaseTable, Integer>(PropertyUtils.HbaseTableProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<HbaseTable, String> desc() {
        ColumnConfig<HbaseTable, String> desc = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<HbaseTable, Date> lastUpdateTimeStamp() {
        ColumnConfig<HbaseTable, Date> lastUpdateTimeStamp = new ColumnConfig<HbaseTable, Date>(PropertyUtils.HbaseTableProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<HbaseTable, Date> createTime() {
        ColumnConfig<HbaseTable, Date> createTime = new ColumnConfig<HbaseTable, Date>(PropertyUtils.HbaseTableProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<HbaseTable, String> createTimePropertyName() {
        ColumnConfig<HbaseTable, String> createTimePropertyName = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
