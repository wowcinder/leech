
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;

public class JsonLogModelVersionColumnConfig {


    public static ColumnConfig<JsonLogModelVersion, JsonLogModelGroupColumn> rootNode() {
        ColumnConfig<JsonLogModelVersion, JsonLogModelGroupColumn> rootNode = new ColumnConfig<JsonLogModelVersion, JsonLogModelGroupColumn>(PropertyUtils.JsonLogModelVersionProperty.rootNode(), 200, "rootNode");
        rootNode.setSortable(false);
        rootNode.setMenuDisabled(true);
        return rootNode;
    }

    public static ColumnConfig<JsonLogModelVersion, List<KafkaTopicFixedModelVersion>> topics() {
        ColumnConfig<JsonLogModelVersion, List<KafkaTopicFixedModelVersion>> topics = new ColumnConfig<JsonLogModelVersion, List<KafkaTopicFixedModelVersion>>(PropertyUtils.JsonLogModelVersionProperty.topics(), 200, "topics");
        topics.setSortable(false);
        topics.setMenuDisabled(true);
        return topics;
    }

    public static ColumnConfig<JsonLogModelVersion, String> desc() {
        ColumnConfig<JsonLogModelVersion, String> desc = new ColumnConfig<JsonLogModelVersion, String>(PropertyUtils.JsonLogModelVersionProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<JsonLogModelVersion, String> version() {
        ColumnConfig<JsonLogModelVersion, String> version = new ColumnConfig<JsonLogModelVersion, String>(PropertyUtils.JsonLogModelVersionProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<JsonLogModelVersion, JsonLogModel> model() {
        ColumnConfig<JsonLogModelVersion, JsonLogModel> model = new ColumnConfig<JsonLogModelVersion, JsonLogModel>(PropertyUtils.JsonLogModelVersionProperty.model(), 200, "model");
        model.setSortable(false);
        model.setMenuDisabled(true);
        return model;
    }

    public static ColumnConfig<JsonLogModelVersion, Integer> id() {
        ColumnConfig<JsonLogModelVersion, Integer> id = new ColumnConfig<JsonLogModelVersion, Integer>(PropertyUtils.JsonLogModelVersionProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<JsonLogModelVersion, Date> lastUpdateTimeStamp() {
        ColumnConfig<JsonLogModelVersion, Date> lastUpdateTimeStamp = new ColumnConfig<JsonLogModelVersion, Date>(PropertyUtils.JsonLogModelVersionProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<JsonLogModelVersion, Date> createTime() {
        ColumnConfig<JsonLogModelVersion, Date> createTime = new ColumnConfig<JsonLogModelVersion, Date>(PropertyUtils.JsonLogModelVersionProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<JsonLogModelVersion, String> createTimePropertyName() {
        ColumnConfig<JsonLogModelVersion, String> createTimePropertyName = new ColumnConfig<JsonLogModelVersion, String>(PropertyUtils.JsonLogModelVersionProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
