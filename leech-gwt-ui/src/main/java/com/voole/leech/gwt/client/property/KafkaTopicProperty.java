
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaTopicProperty
    extends PropertyAccess<KafkaTopic>
{


    public ValueProvider<KafkaTopic, KafkaTopic.KafkaTopicCharset> charset();

    public ValueProvider<KafkaTopic, List<KafkaWatchDogTopicSetting>> topicSettings();

    public ValueProvider<KafkaTopic, Boolean> isEnabled();

    public ValueProvider<KafkaTopic, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<KafkaTopic> key();

    public ValueProvider<KafkaTopic, Integer> id();

    public ValueProvider<KafkaTopic, Date> lastUpdateTimeStamp();

    public ValueProvider<KafkaTopic, Date> createTime();

    public ValueProvider<KafkaTopic, String> createTimePropertyName();

}
