
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;

public interface AuthorizeGroupProperty
    extends PropertyAccess<AuthorizeGroup>
{


    public ValueProvider<AuthorizeGroup, Integer> displayOrder();

    public ValueProvider<AuthorizeGroup, List<Authorize>> authorizes();

    public ValueProvider<AuthorizeGroup, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<AuthorizeGroup> key();

    public ValueProvider<AuthorizeGroup, Integer> id();

    public ValueProvider<AuthorizeGroup, Date> lastUpdateTimeStamp();

    public ValueProvider<AuthorizeGroup, Date> createTime();

    public ValueProvider<AuthorizeGroup, String> createTimePropertyName();

}
