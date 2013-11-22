
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.Set;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;

public interface AuthorizeProperty
    extends PropertyAccess<Authorize>
{


    public ValueProvider<Authorize, String> token();

    public ValueProvider<Authorize, Integer> displayOrder();

    public ValueProvider<Authorize, Set<Menu>> menus();

    public ValueProvider<Authorize, Set<User>> users();

    public ValueProvider<Authorize, Set<UserGroup>> userGroups();

    public ValueProvider<Authorize, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Authorize> key();

    public ValueProvider<Authorize, Integer> id();

    public ValueProvider<Authorize, AuthorizeGroup> group();

    public ValueProvider<Authorize, Date> lastUpdateTimeStamp();

    public ValueProvider<Authorize, Date> createTime();

    public ValueProvider<Authorize, String> createTimePropertyName();

}
