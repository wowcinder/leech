
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

public class CTypeLogModelColumnConfig {


    public static ColumnConfig<CTypeLogModel, List<CTypeLogModelVersion>> versions() {
        ColumnConfig<CTypeLogModel, List<CTypeLogModelVersion>> versions = new ColumnConfig<CTypeLogModel, List<CTypeLogModelVersion>>(PropertyUtils.CTypeLogModelProperty.versions(), 200, "versions");
        versions.setSortable(false);
        versions.setMenuDisabled(true);
        return versions;
    }

    public static ColumnConfig<CTypeLogModel, String> name() {
        ColumnConfig<CTypeLogModel, String> name = new ColumnConfig<CTypeLogModel, String>(PropertyUtils.CTypeLogModelProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<CTypeLogModel, String> desc() {
        ColumnConfig<CTypeLogModel, String> desc = new ColumnConfig<CTypeLogModel, String>(PropertyUtils.CTypeLogModelProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<CTypeLogModel, Integer> id() {
        ColumnConfig<CTypeLogModel, Integer> id = new ColumnConfig<CTypeLogModel, Integer>(PropertyUtils.CTypeLogModelProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<CTypeLogModel, Date> lastUpdateTimeStamp() {
        ColumnConfig<CTypeLogModel, Date> lastUpdateTimeStamp = new ColumnConfig<CTypeLogModel, Date>(PropertyUtils.CTypeLogModelProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<CTypeLogModel, Date> createTime() {
        ColumnConfig<CTypeLogModel, Date> createTime = new ColumnConfig<CTypeLogModel, Date>(PropertyUtils.CTypeLogModelProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<CTypeLogModel, String> createTimePropertyName() {
        ColumnConfig<CTypeLogModel, String> createTimePropertyName = new ColumnConfig<CTypeLogModel, String>(PropertyUtils.CTypeLogModelProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
