
package com.voole.leech.gwt.client.property;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

public interface MenuNodeProperty
    extends PropertyAccess<MenuNode>
{


    public ValueProvider<MenuNode, MenuNode> prev();

    public ValueProvider<MenuNode, String> name();

    public ValueProvider<MenuNode, MenuGroup> parent();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<MenuNode> key();

    public ValueProvider<MenuNode, Integer> id();

    public ValueProvider<MenuNode, Date> lastUpdateTimeStamp();

    public ValueProvider<MenuNode, Date> createTime();

    public ValueProvider<MenuNode, String> createTimePropertyName();

}
