package com.voole.leech.dao.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.leech.shared.annotations.MenuToken;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.menu.Menu;
import com.voole.leech.shared.entity.menu.MenuGroup;
import com.voole.leech.shared.entity.menu.MenuNode;

@Repository
public class MenuDaoImpl implements MenuDao {
	@Resource(name = "leechSf")
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuNode> getUserMenus(Integer uid) {
		Session s = getSession();
		Criteria c = s.createCriteria(MenuNode.class);
		List<MenuNode> list = c.list();

		MenuListGenerator generator = new MenuListGenerator(list);
		if (uid == 0) {
			return generator.getRootNodes();
		} else {
			Set<Integer> aIds = new HashSet<Integer>();
			List<Authorize> authorities = s.createCriteria(Authorize.class)
					.createAlias("users", "user")
					.add(Restrictions.eq("user.id", uid)).list();
			List<Authorize> authorities2 = s.createCriteria(Authorize.class)
					.createAlias("userGroups", "ug")
					.createAlias("ug.users", "user")
					.add(Restrictions.eq("user.id", uid)).list();
			authorities.addAll(authorities2);
			for (Authorize authority : authorities) {
				aIds.add(authority.getId());
			}
			return generator.getUserNodes(aIds);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends MenuNode> T save(MenuNode node) {
		Session s = getSession();
		MenuNode parent = node.getParent();
		MenuNode prev = node.getPrev();
		Criteria c = s.createCriteria(MenuNode.class);
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		if (prev == null) {
			c.add(Restrictions.isNull("prev"));
		} else {
			c.add(Restrictions.eq("prev", prev));
		}
		MenuNode oldPrev = (MenuNode) c.uniqueResult();

		s.save(node);
		if (oldPrev != null) {
			oldPrev.setPrev(node);
			s.merge(oldPrev);
		}
		return (T) node;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends MenuNode> T update(MenuNode node) {
		getSession().update(node);
		return (T) node;
	}

	@Override
	public void deleteMenuNode(Integer id) {
		Session s = getSession();
		MenuNode node = (MenuNode) s.get(MenuNode.class, id);
		MenuNode parent = node.getParent();
		Criteria c = s.createCriteria(MenuNode.class);
		c.add(Restrictions.eq("prev", node));
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		MenuNode next = (MenuNode) c.uniqueResult();
		s.delete(node);
		if (next != null) {
			next.setPrev(node.getPrev());
			s.update(next);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> nodes) {
		for (int i = nodes.size() - 1; i >= 0; i--) {
			MenuNode node = nodes.get(i);
			update(parentId, prevId, node);
		}
		
		Criteria c = getSession().createCriteria(MenuNode.class);
		return c.list();
	}

	private MenuNode update(Integer parentId, Integer prevId, MenuNode node) {
		Session s = getSession();
		node = (MenuNode) s.get(MenuNode.class, node.getId());
		MenuNode parent = node.getParent();
		Criteria c = s.createCriteria(MenuNode.class);
		c.add(Restrictions.eq("prev", node));
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		MenuNode next = (MenuNode) c.uniqueResult();
		if (next != null) {
			next.setPrev(node.getPrev());
			s.update(next);
		}

		parent = null;
		MenuNode prev = null;
		if (parentId != null) {
			parent = new MenuGroup();
			parent.setId(parentId);
		}
		if (prevId != null) {
			prev = new MenuNode();
			prev.setId(prevId);
		}
		c = s.createCriteria(MenuNode.class);
		if (parent == null) {
			c.add(Restrictions.isNull("parent"));
		} else {
			c.add(Restrictions.eq("parent", parent));
		}
		if (prev == null) {
			c.add(Restrictions.isNull("prev"));
		} else {
			c.add(Restrictions.eq("prev", prev));
		}
		MenuNode oldPrev = (MenuNode) c.uniqueResult();

		node.setParent((MenuGroup) parent);
		node.setPrev(prev);
		s.update(node);
		if (oldPrev != null) {
			oldPrev.setPrev(node);
			s.merge(oldPrev);
		}
		return node;
	}

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public void insertMenuConfig(List<MenuToken> tokens) {

		Session s = getSession();
		Map<String, List<MenuToken>> map = new HashMap<String, List<MenuToken>>();
		for (MenuToken menuToken : tokens) {
			String group = menuToken.group();
			if (!map.containsKey(group)) {
				map.put(group, new ArrayList<MenuToken>());
			}
			map.get(group).add(menuToken);
		}

		for (String group : map.keySet()) {
			MenuGroup mg = null;
			if (group.length() != 0) {
				mg = (MenuGroup) s.createCriteria(MenuGroup.class)
						.add(Restrictions.isNull("parent"))
						.add(Restrictions.eq("name", group)).uniqueResult();
				if (mg == null) {
					mg = new MenuGroup();
					mg.setName(group);
					save(mg);
				}
			}

			for (MenuToken token : map.get(group)) {
				Menu menu = (Menu) s.createCriteria(Menu.class)
						.add(Restrictions.eq("token", token.token()))
						.uniqueResult();
				if (menu == null) {
					menu = new Menu();
					menu.setName(token.name());
					menu.setParent(mg);
					menu.setToken(token.token());
					save(menu);
				}
			}
		}

	}
}
