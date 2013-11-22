
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

public class MenuColumnConfig {


    public static ColumnConfig<Menu, String> token() {
        ColumnConfig<Menu, String> token = new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.token(), 200, "token");
        token.setSortable(false);
        token.setMenuDisabled(true);
        return token;
    }

    public static ColumnConfig<Menu, Authorize> requireAuthorize() {
        ColumnConfig<Menu, Authorize> requireAuthorize = new ColumnConfig<Menu, Authorize>(PropertyUtils.MenuProperty.requireAuthorize(), 200, "requireAuthorize");
        requireAuthorize.setSortable(false);
        requireAuthorize.setMenuDisabled(true);
        return requireAuthorize;
    }

    public static ColumnConfig<Menu, MenuNode> prev() {
        ColumnConfig<Menu, MenuNode> prev = new ColumnConfig<Menu, MenuNode>(PropertyUtils.MenuProperty.prev(), 200, "prev");
        prev.setSortable(false);
        prev.setMenuDisabled(true);
        return prev;
    }

    public static ColumnConfig<Menu, String> name() {
        ColumnConfig<Menu, String> name = new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<Menu, MenuGroup> parent() {
        ColumnConfig<Menu, MenuGroup> parent = new ColumnConfig<Menu, MenuGroup>(PropertyUtils.MenuProperty.parent(), 200, "parent");
        parent.setSortable(false);
        parent.setMenuDisabled(true);
        return parent;
    }

    public static ColumnConfig<Menu, Integer> id() {
        ColumnConfig<Menu, Integer> id = new ColumnConfig<Menu, Integer>(PropertyUtils.MenuProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<Menu, Date> lastUpdateTimeStamp() {
        ColumnConfig<Menu, Date> lastUpdateTimeStamp = new ColumnConfig<Menu, Date>(PropertyUtils.MenuProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<Menu, Date> createTime() {
        ColumnConfig<Menu, Date> createTime = new ColumnConfig<Menu, Date>(PropertyUtils.MenuProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<Menu, String> createTimePropertyName() {
        ColumnConfig<Menu, String> createTimePropertyName = new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
