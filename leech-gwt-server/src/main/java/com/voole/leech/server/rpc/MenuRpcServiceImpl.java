package com.voole.leech.server.rpc;

import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import com.voole.leech.gwt.client.service.MenuRpcService;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForMenu;
import com.voole.leech.service.MenuService;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;
import com.voole.leech.util.CinderValidator;

@Service
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForMenu.GROUP)
public class MenuRpcServiceImpl implements MenuRpcService {
	@Autowired
	private MenuService menuService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public MenuGroup saveMenuGroup(MenuGroup menuGroup) {
		CinderValidator.validate(menuGroup);
		return menuService.save(menuGroup);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public MenuGroup updateMenuGroup(MenuGroup menuGroup) {
		CinderValidator.validate(menuGroup);
		return menuService.update(menuGroup);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public Menu saveMenu(Menu menu) {
		CinderValidator.validate(menu);
		return menuService.save(menu);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public Menu updateMenu(Menu menu) {
		CinderValidator.validate(menu);
		return menuService.update(menu);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public void deleteMenuNode(Integer id) {
		menuService.deleteMenuNode(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes) {
		return menuService.moveMenuNode(parentId, prevId, menuNodes);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

}
