/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Repository
public class SimpleDaoImpl implements SimpleDao {
	@Resource(name = "leechSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends Serializable, V> void delete(Class<V> clazz, List<K> ids) {
		for (K k : ids) {
			V v = (V) getSession().load(clazz, k);
			getSession().delete(v);
		}
	}

	@Override
	public <T> T save(T t) {
		getSession().persist(t);
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K extends Serializable, V> void delete(Class<V> clazz, K id) {
		V v = (V) getSession().load(clazz, id);
		getSession().delete(v);
	}

	@Override
	public <T> T update(T t) {
		getSession().update(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PagingLoadResult<T> paging(Class<T> clazz,
			EtlPagingLoadConfigBean config) {
		PagingLoadResultBean<T> pr = new PagingLoadResultBean<T>();
		pr.setOffset(config.getOffset());

		Criteria criteria = getSession().createCriteria(clazz);
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
	public <T> List<T> get(Class<T> clazz) {
		return getSession().createCriteria(clazz)
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();

	}

}
