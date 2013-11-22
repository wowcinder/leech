
package com.voole.leech.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

public interface MenuGroupProperty
    extends PropertyAccess<MenuGroup>
{


    public ValueProvider<MenuGroup, List<MenuNode>> nodes();

    public ValueProvider<MenuGroup, MenuNode> prev();

    public ValueProvider<MenuGroup, String> name();

    public ValueProvider<MenuGroup, MenuGroup> parent();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<MenuGroup> key();

    public ValueProvider<MenuGroup, Integer> id();

    public ValueProvider<MenuGroup, Date> lastUpdateTimeStamp();

    public ValueProvider<MenuGroup, Date> createTime();

    public ValueProvider<MenuGroup, String> createTimePropertyName();

}
