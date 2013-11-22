
package com.voole.leech.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;

public class AuthorizeGroupColumnConfig {


    public static ColumnConfig<AuthorizeGroup, Integer> displayOrder() {
        ColumnConfig<AuthorizeGroup, Integer> displayOrder = new ColumnConfig<AuthorizeGroup, Integer>(PropertyUtils.AuthorizeGroupProperty.displayOrder(), 200, "displayOrder");
        displayOrder.setSortable(false);
        displayOrder.setMenuDisabled(true);
        return displayOrder;
    }

    public static ColumnConfig<AuthorizeGroup, List<Authorize>> authorizes() {
        ColumnConfig<AuthorizeGroup, List<Authorize>> authorizes = new ColumnConfig<AuthorizeGroup, List<Authorize>>(PropertyUtils.AuthorizeGroupProperty.authorizes(), 200, "authorizes");
        authorizes.setSortable(false);
        authorizes.setMenuDisabled(true);
        return authorizes;
    }

    public static ColumnConfig<AuthorizeGroup, String> name() {
        ColumnConfig<AuthorizeGroup, String> name = new ColumnConfig<AuthorizeGroup, String>(PropertyUtils.AuthorizeGroupProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<AuthorizeGroup, Integer> id() {
        ColumnConfig<AuthorizeGroup, Integer> id = new ColumnConfig<AuthorizeGroup, Integer>(PropertyUtils.AuthorizeGroupProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<AuthorizeGroup, Date> lastUpdateTimeStamp() {
        ColumnConfig<AuthorizeGroup, Date> lastUpdateTimeStamp = new ColumnConfig<AuthorizeGroup, Date>(PropertyUtils.AuthorizeGroupProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<AuthorizeGroup, Date> createTime() {
        ColumnConfig<AuthorizeGroup, Date> createTime = new ColumnConfig<AuthorizeGroup, Date>(PropertyUtils.AuthorizeGroupProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<AuthorizeGroup, String> createTimePropertyName() {
        ColumnConfig<AuthorizeGroup, String> createTimePropertyName = new ColumnConfig<AuthorizeGroup, String>(PropertyUtils.AuthorizeGroupProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
