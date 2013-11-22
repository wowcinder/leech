package com.voole.leech.service;

import java.util.List;

import com.voole.leech.shared.annotations.MenuToken;
import com.voole.leech.shared.entity.menu.MenuNode;

public interface MenuService {

	<T extends MenuNode> T save(MenuNode node);

	<T extends MenuNode> T update(MenuNode node);

	void deleteMenuNode(Integer id);

	List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes);

	void insertMenuConfig(List<MenuToken> tokens);
}
