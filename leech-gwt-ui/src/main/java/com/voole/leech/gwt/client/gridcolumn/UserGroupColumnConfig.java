
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;

public class UserGroupColumnConfig {


    public static ColumnConfig<UserGroup, List<User>> users() {
        ColumnConfig<UserGroup, List<User>> users = new ColumnConfig<UserGroup, List<User>>(PropertyUtils.UserGroupProperty.users(), 200, "users");
        users.setSortable(false);
        users.setMenuDisabled(true);
        return users;
    }

    public static ColumnConfig<UserGroup, List<Authorize>> authorizes() {
        ColumnConfig<UserGroup, List<Authorize>> authorizes = new ColumnConfig<UserGroup, List<Authorize>>(PropertyUtils.UserGroupProperty.authorizes(), 200, "authorizes");
        authorizes.setSortable(false);
        authorizes.setMenuDisabled(true);
        return authorizes;
    }

    public static ColumnConfig<UserGroup, String> name() {
        ColumnConfig<UserGroup, String> name = new ColumnConfig<UserGroup, String>(PropertyUtils.UserGroupProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<UserGroup, Integer> id() {
        ColumnConfig<UserGroup, Integer> id = new ColumnConfig<UserGroup, Integer>(PropertyUtils.UserGroupProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<UserGroup, Date> lastUpdateTimeStamp() {
        ColumnConfig<UserGroup, Date> lastUpdateTimeStamp = new ColumnConfig<UserGroup, Date>(PropertyUtils.UserGroupProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<UserGroup, Date> createTime() {
        ColumnConfig<UserGroup, Date> createTime = new ColumnConfig<UserGroup, Date>(PropertyUtils.UserGroupProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<UserGroup, String> createTimePropertyName() {
        ColumnConfig<UserGroup, String> createTimePropertyName = new ColumnConfig<UserGroup, String>(PropertyUtils.UserGroupProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
