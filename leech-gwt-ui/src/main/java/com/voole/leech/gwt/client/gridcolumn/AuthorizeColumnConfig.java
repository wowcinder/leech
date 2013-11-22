
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.Set;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;

public class AuthorizeColumnConfig {


    public static ColumnConfig<Authorize, String> token() {
        ColumnConfig<Authorize, String> token = new ColumnConfig<Authorize, String>(PropertyUtils.AuthorizeProperty.token(), 200, "token");
        token.setSortable(false);
        token.setMenuDisabled(true);
        return token;
    }

    public static ColumnConfig<Authorize, Integer> displayOrder() {
        ColumnConfig<Authorize, Integer> displayOrder = new ColumnConfig<Authorize, Integer>(PropertyUtils.AuthorizeProperty.displayOrder(), 200, "displayOrder");
        displayOrder.setSortable(false);
        displayOrder.setMenuDisabled(true);
        return displayOrder;
    }

    public static ColumnConfig<Authorize, Set<Menu>> menus() {
        ColumnConfig<Authorize, Set<Menu>> menus = new ColumnConfig<Authorize, Set<Menu>>(PropertyUtils.AuthorizeProperty.menus(), 200, "menus");
        menus.setSortable(false);
        menus.setMenuDisabled(true);
        return menus;
    }

    public static ColumnConfig<Authorize, Set<User>> users() {
        ColumnConfig<Authorize, Set<User>> users = new ColumnConfig<Authorize, Set<User>>(PropertyUtils.AuthorizeProperty.users(), 200, "users");
        users.setSortable(false);
        users.setMenuDisabled(true);
        return users;
    }

    public static ColumnConfig<Authorize, Set<UserGroup>> userGroups() {
        ColumnConfig<Authorize, Set<UserGroup>> userGroups = new ColumnConfig<Authorize, Set<UserGroup>>(PropertyUtils.AuthorizeProperty.userGroups(), 200, "userGroups");
        userGroups.setSortable(false);
        userGroups.setMenuDisabled(true);
        return userGroups;
    }

    public static ColumnConfig<Authorize, String> name() {
        ColumnConfig<Authorize, String> name = new ColumnConfig<Authorize, String>(PropertyUtils.AuthorizeProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<Authorize, Integer> id() {
        ColumnConfig<Authorize, Integer> id = new ColumnConfig<Authorize, Integer>(PropertyUtils.AuthorizeProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<Authorize, AuthorizeGroup> group() {
        ColumnConfig<Authorize, AuthorizeGroup> group = new ColumnConfig<Authorize, AuthorizeGroup>(PropertyUtils.AuthorizeProperty.group(), 200, "group");
        group.setSortable(false);
        group.setMenuDisabled(true);
        return group;
    }

    public static ColumnConfig<Authorize, Date> lastUpdateTimeStamp() {
        ColumnConfig<Authorize, Date> lastUpdateTimeStamp = new ColumnConfig<Authorize, Date>(PropertyUtils.AuthorizeProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<Authorize, Date> createTime() {
        ColumnConfig<Authorize, Date> createTime = new ColumnConfig<Authorize, Date>(PropertyUtils.AuthorizeProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<Authorize, String> createTimePropertyName() {
        ColumnConfig<Authorize, String> createTimePropertyName = new ColumnConfig<Authorize, String>(PropertyUtils.AuthorizeProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
