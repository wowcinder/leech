
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;

public interface JsonLogModelSimpleColumnProperty
    extends PropertyAccess<JsonLogModelSimpleColumn>
{


    public ValueProvider<JsonLogModelSimpleColumn, HbaseTableColumn> hbaseTableColumn();

    public ValueProvider<JsonLogModelSimpleColumn, JsonLogModelGroupColumn> groupColumn();

    public ValueProvider<JsonLogModelSimpleColumn, String> path();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModelSimpleColumn> key();

    public ValueProvider<JsonLogModelSimpleColumn, Integer> id();

    public ValueProvider<JsonLogModelSimpleColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModelSimpleColumn, Date> createTime();

    public ValueProvider<JsonLogModelSimpleColumn, String> createTimePropertyName();

}
