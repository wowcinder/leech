
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;

public class JsonLogModelColumnConfig {


    public static ColumnConfig<JsonLogModel, List<JsonLogModelVersion>> versions() {
        ColumnConfig<JsonLogModel, List<JsonLogModelVersion>> versions = new ColumnConfig<JsonLogModel, List<JsonLogModelVersion>>(PropertyUtils.JsonLogModelProperty.versions(), 200, "versions");
        versions.setSortable(false);
        versions.setMenuDisabled(true);
        return versions;
    }

    public static ColumnConfig<JsonLogModel, String> name() {
        ColumnConfig<JsonLogModel, String> name = new ColumnConfig<JsonLogModel, String>(PropertyUtils.JsonLogModelProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<JsonLogModel, String> desc() {
        ColumnConfig<JsonLogModel, String> desc = new ColumnConfig<JsonLogModel, String>(PropertyUtils.JsonLogModelProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<JsonLogModel, Integer> id() {
        ColumnConfig<JsonLogModel, Integer> id = new ColumnConfig<JsonLogModel, Integer>(PropertyUtils.JsonLogModelProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<JsonLogModel, Date> lastUpdateTimeStamp() {
        ColumnConfig<JsonLogModel, Date> lastUpdateTimeStamp = new ColumnConfig<JsonLogModel, Date>(PropertyUtils.JsonLogModelProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<JsonLogModel, Date> createTime() {
        ColumnConfig<JsonLogModel, Date> createTime = new ColumnConfig<JsonLogModel, Date>(PropertyUtils.JsonLogModelProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<JsonLogModel, String> createTimePropertyName() {
        ColumnConfig<JsonLogModel, String> createTimePropertyName = new ColumnConfig<JsonLogModel, String>(PropertyUtils.JsonLogModelProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
