
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModel;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;

public interface JsonLogModelProperty
    extends PropertyAccess<JsonLogModel>
{


    public ValueProvider<JsonLogModel, List<JsonLogModelVersion>> versions();

    public ValueProvider<JsonLogModel, String> name();

    public ValueProvider<JsonLogModel, String> desc();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModel> key();

    public ValueProvider<JsonLogModel, Integer> id();

    public ValueProvider<JsonLogModel, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModel, Date> createTime();

    public ValueProvider<JsonLogModel, String> createTimePropertyName();

}
