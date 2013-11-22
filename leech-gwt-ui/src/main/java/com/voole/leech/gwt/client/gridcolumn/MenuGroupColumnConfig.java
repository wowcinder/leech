
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

public class MenuGroupColumnConfig {


    public static ColumnConfig<MenuGroup, List<MenuNode>> nodes() {
        ColumnConfig<MenuGroup, List<MenuNode>> nodes = new ColumnConfig<MenuGroup, List<MenuNode>>(PropertyUtils.MenuGroupProperty.nodes(), 200, "nodes");
        nodes.setSortable(false);
        nodes.setMenuDisabled(true);
        return nodes;
    }

    public static ColumnConfig<MenuGroup, MenuNode> prev() {
        ColumnConfig<MenuGroup, MenuNode> prev = new ColumnConfig<MenuGroup, MenuNode>(PropertyUtils.MenuGroupProperty.prev(), 200, "prev");
        prev.setSortable(false);
        prev.setMenuDisabled(true);
        return prev;
    }

    public static ColumnConfig<MenuGroup, String> name() {
        ColumnConfig<MenuGroup, String> name = new ColumnConfig<MenuGroup, String>(PropertyUtils.MenuGroupProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<MenuGroup, MenuGroup> parent() {
        ColumnConfig<MenuGroup, MenuGroup> parent = new ColumnConfig<MenuGroup, MenuGroup>(PropertyUtils.MenuGroupProperty.parent(), 200, "parent");
        parent.setSortable(false);
        parent.setMenuDisabled(true);
        return parent;
    }

    public static ColumnConfig<MenuGroup, Integer> id() {
        ColumnConfig<MenuGroup, Integer> id = new ColumnConfig<MenuGroup, Integer>(PropertyUtils.MenuGroupProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<MenuGroup, Date> lastUpdateTimeStamp() {
        ColumnConfig<MenuGroup, Date> lastUpdateTimeStamp = new ColumnConfig<MenuGroup, Date>(PropertyUtils.MenuGroupProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<MenuGroup, Date> createTime() {
        ColumnConfig<MenuGroup, Date> createTime = new ColumnConfig<MenuGroup, Date>(PropertyUtils.MenuGroupProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<MenuGroup, String> createTimePropertyName() {
        ColumnConfig<MenuGroup, String> createTimePropertyName = new ColumnConfig<MenuGroup, String>(PropertyUtils.MenuGroupProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
