package com.voole.leech.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;
import com.voole.leech.shared.exception.SharedException;

@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuRpcService extends RemoteService {

	public MenuGroup saveMenuGroup(MenuGroup menuGroup) throws SharedException,
			ConstraintViolationException;

	public MenuGroup updateMenuGroup(MenuGroup menuGroup)
			throws SharedException, ConstraintViolationException;

	public Menu saveMenu(Menu menu) throws SharedException,
			ConstraintViolationException;

	public Menu updateMenu(Menu menu) throws SharedException,
			ConstraintViolationException;

	public void deleteMenuNode(Integer id) throws SharedException,
			ConstraintViolationException;

	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes) throws SharedException,
			ConstraintViolationException;

	ValidationSupport dummy();
}
