package com.voole.leech.dao.authorize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.leech.annotations.AuthorizeSystemAnnotations.TupleUtil;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.authorize.AuthorizeGroup;
import com.voole.leech.shared.exception.SharedException;

@Repository
public class AuthorizeDaoImpl implements AuthorizeDao {
	@Resource(name = "leechSf")
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getAuthorizeTokens(Integer uid) {
		Set<String> tokens = new HashSet<String>();

		List<Authorize> list = (List<Authorize>) getSession()
				.createCriteria(Authorize.class).createAlias("users", "user")
				.add(Restrictions.eq("user.id", uid)).list();
		List<Authorize> list2 = (List<Authorize>) getSession()
				.createCriteria(Authorize.class)
				.createAlias("userGroups", "userGroup")
				.createAlias("userGroup.users", "user")
				.add(Restrictions.eq("user.id", uid)).list();

		for (Authorize authorize : list) {
			tokens.add(authorize.getToken());
		}
		for (Authorize authorize : list2) {
			tokens.add(authorize.getToken());
		}
		return tokens;
	}

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public Integer queryAuthorizeGroupIdByName(String group) {
		Session s = getSession();
		AuthorizeGroup ag = (AuthorizeGroup) s
				.createCriteria(AuthorizeGroup.class)
				.add(Restrictions.eq("name", group)).uniqueResult();
		if (ag != null) {
			return ag.getId();
		}
		return null;
	}

	@Override
	public AuthorizeGroup saveAuthorizeGroup(AuthorizeGroup ag) {
		getSession().save(ag);
		return ag;
	}

	@Override
	public Authorize queryAuthorizeIdByName(Integer agId, String name) {
		Session s = getSession();
		Authorize a = (Authorize) s.createCriteria(Authorize.class)
				.add(Restrictions.eq("name", name)).createAlias("group", "ag")
				.add(Restrictions.eq("ag.id", agId)).uniqueResult();
		if (a != null) {
			return a;
		}
		return null;
	}

	@Override
	public Authorize saveAuthorize(Authorize authority) {
		refreshToken(authority);
		getSession().save(authority);
		return authority;
	}

	private void refreshToken(Authorize v) {
		Session sesion = getSession();
		if (v.getGroup() == null || v.getGroup().getId() == null) {
			throw new SharedException("AuthorityGroup can't be null");
		}
		v.setGroup((AuthorizeGroup) sesion.load(AuthorizeGroup.class, v
				.getGroup().getId()));
		v.setToken(TupleUtil.getToken(v.getGroup().getName(), v.getName()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authorize> getAllocatenbeAuthorizes() {
		return getSession().createCriteria(Authorize.class)
				.createAlias("group", "group").addOrder(Order.asc("group.id"))
				.addOrder(Order.asc("id")).list();
	}

}
