
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaTopicFixedModelVersionProperty
    extends PropertyAccess<KafkaTopicFixedModelVersion>
{


    public ValueProvider<KafkaTopicFixedModelVersion, LogModelVersion<?>> version();

    public ValueProvider<KafkaTopicFixedModelVersion, com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset> charset();

    public ValueProvider<KafkaTopicFixedModelVersion, List<KafkaWatchDogTopicSetting>> topicSettings();

    public ValueProvider<KafkaTopicFixedModelVersion, Boolean> isEnabled();

    public ValueProvider<KafkaTopicFixedModelVersion, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<KafkaTopicFixedModelVersion> key();

    public ValueProvider<KafkaTopicFixedModelVersion, Integer> id();

    public ValueProvider<KafkaTopicFixedModelVersion, Date> lastUpdateTimeStamp();

    public ValueProvider<KafkaTopicFixedModelVersion, Date> createTime();

    public ValueProvider<KafkaTopicFixedModelVersion, String> createTimePropertyName();

}
