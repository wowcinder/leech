
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public interface HbaseTableProperty
    extends PropertyAccess<HbaseTable>
{


    public ValueProvider<HbaseTable, String> shortname();

    public ValueProvider<HbaseTable, List<HbaseTableVersion>> versions();

    public ValueProvider<HbaseTable, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<HbaseTable> key();

    public ValueProvider<HbaseTable, Integer> id();

    public ValueProvider<HbaseTable, String> desc();

    public ValueProvider<HbaseTable, Date> lastUpdateTimeStamp();

    public ValueProvider<HbaseTable, Date> createTime();

    public ValueProvider<HbaseTable, String> createTimePropertyName();

}
