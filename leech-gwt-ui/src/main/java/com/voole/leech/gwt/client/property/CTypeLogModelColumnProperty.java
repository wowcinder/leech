
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

public interface CTypeLogModelColumnProperty
    extends PropertyAccess<CTypeLogModelColumn>
{


    public ValueProvider<CTypeLogModelColumn, CTypeLogModelGroupColumn> groupColumn();

    public ValueProvider<CTypeLogModelColumn, String> name();

    public ValueProvider<CTypeLogModelColumn, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelColumn> key();

    public ValueProvider<CTypeLogModelColumn, Integer> id();

    public ValueProvider<CTypeLogModelColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<CTypeLogModelColumn, Date> createTime();

    public ValueProvider<CTypeLogModelColumn, String> createTimePropertyName();

}
