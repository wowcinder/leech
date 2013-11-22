package com.voole.leech.dao.user;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.user.User;
import com.voole.leech.shared.entity.user.UserGroup;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

@Repository
public class UserDaoImpl implements UserDao {
	@Resource(name = "leechSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public User findUser(String email, String encryptPassword) {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", encryptPassword))
				.uniqueResult();
	}

	@Override
	public void save(User user) {
		getSession().save(user);
	}

	@Override
	public User saveUser(User user) {
		getSession().save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		getSession().update(user);
		return user;
	}

	@Override
	public UserGroup saveUserGroup(UserGroup userGroup) {
		getSession().save(userGroup);
		return userGroup;
	}

	@Override
	public UserGroup updateUserGroup(UserGroup userGroup) {
		getSession().update(userGroup);
		return userGroup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> getUserGroupListForCombox() {
		return getSession().createCriteria(UserGroup.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<User> pagingUser(EtlPagingLoadConfigBean config)
			throws SharedException {
		PagingLoadResultBean<User> pr = new PagingLoadResultBean<User>();
		pr.setOffset(config.getOffset());

		Criteria criteria = getSession().createCriteria(User.class);

		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setTotalLength((int) rowCount);

		criteria.setProjection(null);
		criteria.setFirstResult(config.getOffset());
		criteria.setMaxResults(config.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		pr.setData(criteria.list());

		return pr;
	}

	@Override
	public void deleteUsers(List<Integer> ids) throws SharedException {
		for (Integer id : ids) {
			User u = (User) getSession().load(User.class, id);
			getSession().delete(u);
		}
	}

	@Override
	public void deleteUserGroups(List<Integer> ids) throws SharedException {
		for (Integer id : ids) {
			UserGroup u = (UserGroup) getSession().load(UserGroup.class, id);
			getSession().delete(u);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<UserGroup> pagingUserGroup(
			EtlPagingLoadConfigBean config) throws SharedException {
		PagingLoadResultBean<UserGroup> pr = new PagingLoadResultBean<UserGroup>();
		pr.setOffset(config.getOffset());

		Criteria criteria = getSession().createCriteria(UserGroup.class);

		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setTotalLength((int) rowCount);

		criteria.setProjection(null);
		criteria.setFirstResult(config.getOffset());
		criteria.setMaxResults(config.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		pr.setData(criteria.list());

		return pr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authorize> getUserExtraAuthorizes(Integer uid)
			throws SharedException {
		return getSession().createCriteria(Authorize.class)
				.createAlias("users", "user")
				.add(Restrictions.eq("user.id", uid)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authorize> getUserGroupAuthorizes(Integer gid)
			throws SharedException {
		return getSession().createCriteria(Authorize.class)
				.createAlias("userGroups", "userGroup")
				.add(Restrictions.eq("userGroup.id", gid)).list();
	}

}
