
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;

public interface CTypeLogModelSimpleColumnProperty
    extends PropertyAccess<CTypeLogModelSimpleColumn>
{


    public ValueProvider<CTypeLogModelSimpleColumn, HbaseTableColumn> hbaseTableColumn();

    public ValueProvider<CTypeLogModelSimpleColumn, CTypeLogModelGroupColumn> groupColumn();

    public ValueProvider<CTypeLogModelSimpleColumn, String> name();

    public ValueProvider<CTypeLogModelSimpleColumn, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelSimpleColumn> key();

    public ValueProvider<CTypeLogModelSimpleColumn, Integer> id();

    public ValueProvider<CTypeLogModelSimpleColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<CTypeLogModelSimpleColumn, Date> createTime();

    public ValueProvider<CTypeLogModelSimpleColumn, String> createTimePropertyName();

}
