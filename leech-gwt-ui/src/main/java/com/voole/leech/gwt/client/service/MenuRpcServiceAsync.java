
package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

import org.hibernate.validator.engine.ValidationSupport;

public interface MenuRpcServiceAsync {


    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void saveMenuGroup(MenuGroup arg0, AsyncCallback<MenuGroup> callback);

    public void updateMenuGroup(MenuGroup arg0, AsyncCallback<MenuGroup> callback);

    public void saveMenu(Menu arg0, AsyncCallback<Menu> callback);

    public void updateMenu(Menu arg0, AsyncCallback<Menu> callback);

    public void deleteMenuNode(Integer arg0, AsyncCallback<Void> callback);

    public void moveMenuNode(Integer arg0, Integer arg1, List<MenuNode> arg2, AsyncCallback<List<MenuNode>> callback);

}
