
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaWatchDogProperty
    extends PropertyAccess<KafkaWatchDog>
{


    public ValueProvider<KafkaWatchDog, List<KafkaWatchDogTopicSetting>> topicSettings();

    public ValueProvider<KafkaWatchDog, String> ip();

    public ValueProvider<KafkaWatchDog, Integer> rmiPort();

    public ValueProvider<KafkaWatchDog, String> name();

    public ValueProvider<KafkaWatchDog, KafkaWatchDog.KafkaProcessServerStatus> status();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<KafkaWatchDog> key();

    public ValueProvider<KafkaWatchDog, Integer> id();

    public ValueProvider<KafkaWatchDog, Date> lastUpdateTimeStamp();

    public ValueProvider<KafkaWatchDog, Date> createTime();

    public ValueProvider<KafkaWatchDog, String> createTimePropertyName();

}
