
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public class KafkaTopicFixedModelVersionColumnConfig {


    public static ColumnConfig<KafkaTopicFixedModelVersion, LogModelVersion<?>> version() {
        ColumnConfig<KafkaTopicFixedModelVersion, LogModelVersion<?>> version = new ColumnConfig<KafkaTopicFixedModelVersion, LogModelVersion<?>>(PropertyUtils.KafkaTopicFixedModelVersionProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset> charset() {
        ColumnConfig<KafkaTopicFixedModelVersion, com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset> charset = new ColumnConfig<KafkaTopicFixedModelVersion, com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset>(PropertyUtils.KafkaTopicFixedModelVersionProperty.charset(), 200, "charset");
        charset.setSortable(false);
        charset.setMenuDisabled(true);
        return charset;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, List<KafkaWatchDogTopicSetting>> topicSettings() {
        ColumnConfig<KafkaTopicFixedModelVersion, List<KafkaWatchDogTopicSetting>> topicSettings = new ColumnConfig<KafkaTopicFixedModelVersion, List<KafkaWatchDogTopicSetting>>(PropertyUtils.KafkaTopicFixedModelVersionProperty.topicSettings(), 200, "topicSettings");
        topicSettings.setSortable(false);
        topicSettings.setMenuDisabled(true);
        return topicSettings;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, Boolean> isEnabled() {
        ColumnConfig<KafkaTopicFixedModelVersion, Boolean> isEnabled = new ColumnConfig<KafkaTopicFixedModelVersion, Boolean>(PropertyUtils.KafkaTopicFixedModelVersionProperty.isEnabled(), 200, "isEnabled");
        isEnabled.setSortable(false);
        isEnabled.setMenuDisabled(true);
        return isEnabled;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, String> name() {
        ColumnConfig<KafkaTopicFixedModelVersion, String> name = new ColumnConfig<KafkaTopicFixedModelVersion, String>(PropertyUtils.KafkaTopicFixedModelVersionProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, Integer> id() {
        ColumnConfig<KafkaTopicFixedModelVersion, Integer> id = new ColumnConfig<KafkaTopicFixedModelVersion, Integer>(PropertyUtils.KafkaTopicFixedModelVersionProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, Date> lastUpdateTimeStamp() {
        ColumnConfig<KafkaTopicFixedModelVersion, Date> lastUpdateTimeStamp = new ColumnConfig<KafkaTopicFixedModelVersion, Date>(PropertyUtils.KafkaTopicFixedModelVersionProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, Date> createTime() {
        ColumnConfig<KafkaTopicFixedModelVersion, Date> createTime = new ColumnConfig<KafkaTopicFixedModelVersion, Date>(PropertyUtils.KafkaTopicFixedModelVersionProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<KafkaTopicFixedModelVersion, String> createTimePropertyName() {
        ColumnConfig<KafkaTopicFixedModelVersion, String> createTimePropertyName = new ColumnConfig<KafkaTopicFixedModelVersion, String>(PropertyUtils.KafkaTopicFixedModelVersionProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
