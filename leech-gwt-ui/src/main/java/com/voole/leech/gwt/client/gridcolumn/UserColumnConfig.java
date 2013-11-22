
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;

public class UserColumnConfig {


    public static ColumnConfig<User, String> email() {
        ColumnConfig<User, String> email = new ColumnConfig<User, String>(PropertyUtils.UserProperty.email(), 200, "email");
        email.setSortable(false);
        email.setMenuDisabled(true);
        return email;
    }

    public static ColumnConfig<User, UserGroup> userGroup() {
        ColumnConfig<User, UserGroup> userGroup = new ColumnConfig<User, UserGroup>(PropertyUtils.UserProperty.userGroup(), 200, "userGroup");
        userGroup.setSortable(false);
        userGroup.setMenuDisabled(true);
        return userGroup;
    }

    public static ColumnConfig<User, List<Authorize>> extraAuthorizes() {
        ColumnConfig<User, List<Authorize>> extraAuthorizes = new ColumnConfig<User, List<Authorize>>(PropertyUtils.UserProperty.extraAuthorizes(), 200, "extraAuthorizes");
        extraAuthorizes.setSortable(false);
        extraAuthorizes.setMenuDisabled(true);
        return extraAuthorizes;
    }

    public static ColumnConfig<User, Integer> id() {
        ColumnConfig<User, Integer> id = new ColumnConfig<User, Integer>(PropertyUtils.UserProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<User, Date> lastUpdateTimeStamp() {
        ColumnConfig<User, Date> lastUpdateTimeStamp = new ColumnConfig<User, Date>(PropertyUtils.UserProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<User, Date> createTime() {
        ColumnConfig<User, Date> createTime = new ColumnConfig<User, Date>(PropertyUtils.UserProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<User, String> createTimePropertyName() {
        ColumnConfig<User, String> createTimePropertyName = new ColumnConfig<User, String>(PropertyUtils.UserProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

    public static ColumnConfig<User, String> passwordPropertyName() {
        ColumnConfig<User, String> passwordPropertyName = new ColumnConfig<User, String>(PropertyUtils.UserProperty.passwordPropertyName(), 200, "passwordPropertyName");
        passwordPropertyName.setSortable(false);
        passwordPropertyName.setMenuDisabled(true);
        return passwordPropertyName;
    }

    public static ColumnConfig<User, String> password() {
        ColumnConfig<User, String> password = new ColumnConfig<User, String>(PropertyUtils.UserProperty.password(), 200, "password");
        password.setSortable(false);
        password.setMenuDisabled(true);
        return password;
    }

}
