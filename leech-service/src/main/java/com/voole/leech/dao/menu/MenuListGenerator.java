package com.voole.leech.dao.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

public class MenuListGenerator {
	private Map<MenuPosition, MenuNode> positionToNode;

	public MenuListGenerator(List<MenuNode> list) {
		positionToNode = new HashMap<MenuPosition, MenuNode>();
		for (MenuNode menuNode : list) {
			positionToNode
					.put(MenuPosition.getSelfPosition(menuNode), menuNode);
		}
	}

	public List<MenuNode> getUserNodes(Set<Integer> aIds) {
		List<MenuNode> nodes = new ArrayList<MenuNode>();
		nodes.addAll(getRootNodes());
		clear(nodes, aIds);
		return nodes;
	}

	private void clear(List<MenuNode> nodes, Set<Integer> aIds) {
		if (nodes == null || nodes.size() == 0) {
			return;
		}
		List<MenuNode> needDeleteMenus = new ArrayList<MenuNode>();
		for (MenuNode menuNode : nodes) {
			if (menuNode instanceof Menu) {
				if (((Menu) menuNode).getRequireAuthorize() == null) {
					needDeleteMenus.add(menuNode);
				} else {
					Integer aId = ((Menu) menuNode).getRequireAuthorize()
							.getId();
					if (!aIds.contains(aId)) {
						needDeleteMenus.add(menuNode);
					}
				}
			} else if (menuNode instanceof MenuGroup) {
				clear(((MenuGroup) menuNode).getNodes(), aIds);
			}
		}
		for (MenuNode menuNode : needDeleteMenus) {
			nodes.remove(menuNode);
		}
	}

	public List<MenuNode> getRootNodes() {
		return getNodes(null);
	}

	public List<MenuNode> getNodes(MenuGroup parent) {
		List<MenuNode> list = new ArrayList<MenuNode>();
		MenuPosition firstPosition = MenuPosition.getFirstPosition(parent);
		MenuNode nextNode = positionToNode.get(firstPosition);
		if (nextNode == null) {
			return null;
		}
		while (nextNode != null) {
			list.add(nextNode);
			if (nextNode instanceof MenuGroup) {
				List<MenuNode> nodes = getNodes((MenuGroup) nextNode);
				((MenuGroup) nextNode).setNodes(nodes);
			}
			MenuPosition nextPosition = MenuPosition.getNextPosition(nextNode);
			nextNode = positionToNode.get(nextPosition);
		}

		return list;
	}

	public static class MenuPosition {
		private Integer parent;
		private Integer prev;

		public MenuPosition() {
		}

		public MenuPosition(MenuGroup parent2, MenuNode prev2) {
			if (parent2 == null) {
				this.parent = 0;
			} else {
				this.parent = parent2.getId();
			}

			if (prev2 == null) {
				this.prev = 0;
			} else {
				this.prev = prev2.getId();
			}
		}

		public Integer getParent() {
			return parent;
		}

		public void setParent(Integer parent) {
			this.parent = parent;
		}

		public Integer getPrev() {
			return prev;
		}

		public void setPrev(Integer prev) {
			this.prev = prev;
		}

		public static MenuPosition getFirstPosition(MenuGroup node) {
			return new MenuPosition(node, null);
		}

		public static MenuPosition getFirstPosition() {
			return new MenuPosition(null, null);
		}

		public static MenuPosition getSelfPosition(MenuNode node) {
			return new MenuPosition(node.getParent(), node.getPrev());
		}

		public static MenuPosition getNextPosition(MenuNode node) {
			return new MenuPosition(node.getParent(), node);
		}

		@Override
		public int hashCode() {
			return getParent().hashCode() + 3 * getPrev().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof MenuPosition) {
				MenuPosition that = (MenuPosition) obj;
				return this.getParent().equals(that.getParent())
						&& this.getPrev().equals(that.getPrev());
			}
			return false;
		}
	}
}
