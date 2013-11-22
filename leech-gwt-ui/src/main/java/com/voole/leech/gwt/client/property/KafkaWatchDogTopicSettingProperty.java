
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaWatchDogTopicSettingProperty
    extends PropertyAccess<KafkaWatchDogTopicSetting>
{


    public ValueProvider<KafkaWatchDogTopicSetting, Boolean> isEnabled();

    public ValueProvider<KafkaWatchDogTopicSetting, KafkaWatchDog> server();

    public ValueProvider<KafkaWatchDogTopicSetting, KafkaTopic> topic();

    public ValueProvider<KafkaWatchDogTopicSetting, Integer> threadNum();

    public ValueProvider<KafkaWatchDogTopicSetting, KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus> status();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<KafkaWatchDogTopicSetting> key();

    public ValueProvider<KafkaWatchDogTopicSetting, Integer> id();

    public ValueProvider<KafkaWatchDogTopicSetting, Date> lastUpdateTimeStamp();

    public ValueProvider<KafkaWatchDogTopicSetting, Date> createTime();

    public ValueProvider<KafkaWatchDogTopicSetting, String> createTimePropertyName();

}
