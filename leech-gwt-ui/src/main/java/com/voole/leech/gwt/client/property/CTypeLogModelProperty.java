
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModel;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

public interface CTypeLogModelProperty
    extends PropertyAccess<CTypeLogModel>
{


    public ValueProvider<CTypeLogModel, List<CTypeLogModelVersion>> versions();

    public ValueProvider<CTypeLogModel, String> name();

    public ValueProvider<CTypeLogModel, String> desc();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModel> key();

    public ValueProvider<CTypeLogModel, Integer> id();

    public ValueProvider<CTypeLogModel, Date> lastUpdateTimeStamp();

    public ValueProvider<CTypeLogModel, Date> createTime();

    public ValueProvider<CTypeLogModel, String> createTimePropertyName();

}
