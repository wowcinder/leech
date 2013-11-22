
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

public class MenuNodeColumnConfig {


    public static ColumnConfig<MenuNode, MenuNode> prev() {
        ColumnConfig<MenuNode, MenuNode> prev = new ColumnConfig<MenuNode, MenuNode>(PropertyUtils.MenuNodeProperty.prev(), 200, "prev");
        prev.setSortable(false);
        prev.setMenuDisabled(true);
        return prev;
    }

    public static ColumnConfig<MenuNode, String> name() {
        ColumnConfig<MenuNode, String> name = new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<MenuNode, MenuGroup> parent() {
        ColumnConfig<MenuNode, MenuGroup> parent = new ColumnConfig<MenuNode, MenuGroup>(PropertyUtils.MenuNodeProperty.parent(), 200, "parent");
        parent.setSortable(false);
        parent.setMenuDisabled(true);
        return parent;
    }

    public static ColumnConfig<MenuNode, Integer> id() {
        ColumnConfig<MenuNode, Integer> id = new ColumnConfig<MenuNode, Integer>(PropertyUtils.MenuNodeProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<MenuNode, Date> lastUpdateTimeStamp() {
        ColumnConfig<MenuNode, Date> lastUpdateTimeStamp = new ColumnConfig<MenuNode, Date>(PropertyUtils.MenuNodeProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<MenuNode, Date> createTime() {
        ColumnConfig<MenuNode, Date> createTime = new ColumnConfig<MenuNode, Date>(PropertyUtils.MenuNodeProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<MenuNode, String> createTimePropertyName() {
        ColumnConfig<MenuNode, String> createTimePropertyName = new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
