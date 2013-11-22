
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;

public class CTypeLogModelVersionColumnConfig {


    public static ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> rootNode() {
        ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> rootNode = new ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelVersionProperty.rootNode(), 200, "rootNode");
        rootNode.setSortable(false);
        rootNode.setMenuDisabled(true);
        return rootNode;
    }

    public static ColumnConfig<CTypeLogModelVersion, List<KafkaTopicFixedModelVersion>> topics() {
        ColumnConfig<CTypeLogModelVersion, List<KafkaTopicFixedModelVersion>> topics = new ColumnConfig<CTypeLogModelVersion, List<KafkaTopicFixedModelVersion>>(PropertyUtils.CTypeLogModelVersionProperty.topics(), 200, "topics");
        topics.setSortable(false);
        topics.setMenuDisabled(true);
        return topics;
    }

    public static ColumnConfig<CTypeLogModelVersion, String> desc() {
        ColumnConfig<CTypeLogModelVersion, String> desc = new ColumnConfig<CTypeLogModelVersion, String>(PropertyUtils.CTypeLogModelVersionProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<CTypeLogModelVersion, String> version() {
        ColumnConfig<CTypeLogModelVersion, String> version = new ColumnConfig<CTypeLogModelVersion, String>(PropertyUtils.CTypeLogModelVersionProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<CTypeLogModelVersion, CTypeLogModel> model() {
        ColumnConfig<CTypeLogModelVersion, CTypeLogModel> model = new ColumnConfig<CTypeLogModelVersion, CTypeLogModel>(PropertyUtils.CTypeLogModelVersionProperty.model(), 200, "model");
        model.setSortable(false);
        model.setMenuDisabled(true);
        return model;
    }

    public static ColumnConfig<CTypeLogModelVersion, Integer> id() {
        ColumnConfig<CTypeLogModelVersion, Integer> id = new ColumnConfig<CTypeLogModelVersion, Integer>(PropertyUtils.CTypeLogModelVersionProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<CTypeLogModelVersion, Date> lastUpdateTimeStamp() {
        ColumnConfig<CTypeLogModelVersion, Date> lastUpdateTimeStamp = new ColumnConfig<CTypeLogModelVersion, Date>(PropertyUtils.CTypeLogModelVersionProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<CTypeLogModelVersion, Date> createTime() {
        ColumnConfig<CTypeLogModelVersion, Date> createTime = new ColumnConfig<CTypeLogModelVersion, Date>(PropertyUtils.CTypeLogModelVersionProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<CTypeLogModelVersion, String> createTimePropertyName() {
        ColumnConfig<CTypeLogModelVersion, String> createTimePropertyName = new ColumnConfig<CTypeLogModelVersion, String>(PropertyUtils.CTypeLogModelVersionProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
