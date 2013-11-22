
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;

public interface UserProperty
    extends PropertyAccess<User>
{


    public ValueProvider<User, String> email();

    public ValueProvider<User, UserGroup> userGroup();

    public ValueProvider<User, List<Authorize>> extraAuthorizes();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<User> key();

    public ValueProvider<User, Integer> id();

    public ValueProvider<User, Date> lastUpdateTimeStamp();

    public ValueProvider<User, Date> createTime();

    public ValueProvider<User, String> createTimePropertyName();

    public ValueProvider<User, String> passwordPropertyName();

    public ValueProvider<User, String> password();

}
