package com.voole.leech.server.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.leech.gwt.client.service.OpenAuthorizeRpcService;
import com.voole.leech.server.rpc.open.OpenRpcService;
import com.voole.leech.service.AuthorizeService;
import com.voole.leech.shared.entity.menu.MenuNode;

@Service
public class OpenAuthorizeRpcServiceImpl implements OpenAuthorizeRpcService,
		OpenRpcService {
	@Autowired
	private AuthorizeService authorizeService;

	@Override
	public void logout() {
		authorizeService.logout();
	}

	@Override
	public List<MenuNode> getUserMenus() {
		return authorizeService.getUserMenus();
	}

	@Override
	public Boolean login(String email, String password) {
		return authorizeService.login(email, password);
	}

	@Override
	public Boolean isLogin() {
		return authorizeService.isLogin();
	}

}
