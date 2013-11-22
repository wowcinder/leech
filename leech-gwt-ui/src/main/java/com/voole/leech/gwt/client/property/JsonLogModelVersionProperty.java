
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;

public interface JsonLogModelVersionProperty
    extends PropertyAccess<JsonLogModelVersion>
{


    public ValueProvider<JsonLogModelVersion, JsonLogModelGroupColumn> rootNode();

    public ValueProvider<JsonLogModelVersion, List<KafkaTopicFixedModelVersion>> topics();

    public ValueProvider<JsonLogModelVersion, String> desc();

    public ValueProvider<JsonLogModelVersion, String> version();

    public ValueProvider<JsonLogModelVersion, JsonLogModel> model();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModelVersion> key();

    public ValueProvider<JsonLogModelVersion, Integer> id();

    public ValueProvider<JsonLogModelVersion, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModelVersion, Date> createTime();

    public ValueProvider<JsonLogModelVersion, String> createTimePropertyName();

}
