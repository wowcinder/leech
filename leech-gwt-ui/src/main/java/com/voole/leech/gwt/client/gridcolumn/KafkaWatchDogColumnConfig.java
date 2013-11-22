
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public class KafkaWatchDogColumnConfig {


    public static ColumnConfig<KafkaWatchDog, List<KafkaWatchDogTopicSetting>> topicSettings() {
        ColumnConfig<KafkaWatchDog, List<KafkaWatchDogTopicSetting>> topicSettings = new ColumnConfig<KafkaWatchDog, List<KafkaWatchDogTopicSetting>>(PropertyUtils.KafkaWatchDogProperty.topicSettings(), 200, "topicSettings");
        topicSettings.setSortable(false);
        topicSettings.setMenuDisabled(true);
        return topicSettings;
    }

    public static ColumnConfig<KafkaWatchDog, String> ip() {
        ColumnConfig<KafkaWatchDog, String> ip = new ColumnConfig<KafkaWatchDog, String>(PropertyUtils.KafkaWatchDogProperty.ip(), 200, "ip");
        ip.setSortable(false);
        ip.setMenuDisabled(true);
        return ip;
    }

    public static ColumnConfig<KafkaWatchDog, Integer> rmiPort() {
        ColumnConfig<KafkaWatchDog, Integer> rmiPort = new ColumnConfig<KafkaWatchDog, Integer>(PropertyUtils.KafkaWatchDogProperty.rmiPort(), 200, "rmiPort");
        rmiPort.setSortable(false);
        rmiPort.setMenuDisabled(true);
        return rmiPort;
    }

    public static ColumnConfig<KafkaWatchDog, String> name() {
        ColumnConfig<KafkaWatchDog, String> name = new ColumnConfig<KafkaWatchDog, String>(PropertyUtils.KafkaWatchDogProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<KafkaWatchDog, KafkaWatchDog.KafkaProcessServerStatus> status() {
        ColumnConfig<KafkaWatchDog, KafkaWatchDog.KafkaProcessServerStatus> status = new ColumnConfig<KafkaWatchDog, KafkaWatchDog.KafkaProcessServerStatus>(PropertyUtils.KafkaWatchDogProperty.status(), 200, "status");
        status.setSortable(false);
        status.setMenuDisabled(true);
        return status;
    }

    public static ColumnConfig<KafkaWatchDog, Integer> id() {
        ColumnConfig<KafkaWatchDog, Integer> id = new ColumnConfig<KafkaWatchDog, Integer>(PropertyUtils.KafkaWatchDogProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<KafkaWatchDog, Date> lastUpdateTimeStamp() {
        ColumnConfig<KafkaWatchDog, Date> lastUpdateTimeStamp = new ColumnConfig<KafkaWatchDog, Date>(PropertyUtils.KafkaWatchDogProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<KafkaWatchDog, Date> createTime() {
        ColumnConfig<KafkaWatchDog, Date> createTime = new ColumnConfig<KafkaWatchDog, Date>(PropertyUtils.KafkaWatchDogProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<KafkaWatchDog, String> createTimePropertyName() {
        ColumnConfig<KafkaWatchDog, String> createTimePropertyName = new ColumnConfig<KafkaWatchDog, String>(PropertyUtils.KafkaWatchDogProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
