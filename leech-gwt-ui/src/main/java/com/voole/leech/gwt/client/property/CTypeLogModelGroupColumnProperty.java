
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

public interface CTypeLogModelGroupColumnProperty
    extends PropertyAccess<CTypeLogModelGroupColumn>
{


    public ValueProvider<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns();

    public ValueProvider<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> groupColumn();

    public ValueProvider<CTypeLogModelGroupColumn, String> name();

    public ValueProvider<CTypeLogModelGroupColumn, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelGroupColumn> key();

    public ValueProvider<CTypeLogModelGroupColumn, Integer> id();

    public ValueProvider<CTypeLogModelGroupColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<CTypeLogModelGroupColumn, Date> createTime();

    public ValueProvider<CTypeLogModelGroupColumn, String> createTimePropertyName();

}
