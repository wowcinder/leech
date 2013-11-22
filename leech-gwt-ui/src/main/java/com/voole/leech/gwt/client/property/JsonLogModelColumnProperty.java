
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;

public interface JsonLogModelColumnProperty
    extends PropertyAccess<JsonLogModelColumn>
{


    public ValueProvider<JsonLogModelColumn, JsonLogModelGroupColumn> groupColumn();

    public ValueProvider<JsonLogModelColumn, String> path();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModelColumn> key();

    public ValueProvider<JsonLogModelColumn, Integer> id();

    public ValueProvider<JsonLogModelColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModelColumn, Date> createTime();

    public ValueProvider<JsonLogModelColumn, String> createTimePropertyName();

}
