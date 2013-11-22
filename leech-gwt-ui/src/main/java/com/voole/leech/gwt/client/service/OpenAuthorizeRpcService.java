package com.voole.leech.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.leech.shared.entity.menu.MenuNode;
import com.voole.leech.shared.exception.SharedException;

@RemoteServiceRelativePath("rpc/open_authorize.rpc")
public interface OpenAuthorizeRpcService extends RemoteService {
	void logout();

	List<MenuNode> getUserMenus() throws SharedException;

	Boolean login(String email, String password);

	Boolean isLogin();
}
