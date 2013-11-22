
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public interface HbaseTableColumnProperty
    extends PropertyAccess<HbaseTableColumn>
{


    public ValueProvider<HbaseTableColumn, String> shortname();

    public ValueProvider<HbaseTableColumn, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<HbaseTableColumn> key();

    public ValueProvider<HbaseTableColumn, Integer> id();

    public ValueProvider<HbaseTableColumn, HbaseTableColumn.HbaseTableColumnType> type();

    public ValueProvider<HbaseTableColumn, String> desc();

    public ValueProvider<HbaseTableColumn, HbaseTableVersion> version();

    public ValueProvider<HbaseTableColumn, Integer> pos();

    public ValueProvider<HbaseTableColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<HbaseTableColumn, Date> createTime();

    public ValueProvider<HbaseTableColumn, String> createTimePropertyName();

}
