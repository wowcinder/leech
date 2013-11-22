/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.dao.SimpleDao;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.util.CinderValidator;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Service
@Transactional
public class SimpleServiceImpl implements SimpleService {
	@Autowired
	private SimpleDao simpleDao;

	@Override
	public <K extends Serializable, V> void delete(Class<V> clazz, List<K> ids) {
		simpleDao.delete(clazz, ids);
	}

	@Override
	public <T> T save(T t) {
		CinderValidator.validate(t);
		simpleDao.save(t);
		return t;
	}

	@Override
	public <K extends Serializable, V> void delete(Class<V> clazz, K id) {
		simpleDao.delete(clazz, id);
	}

	@Override
	public <T> T update(T t) {
		CinderValidator.validate(t);
		simpleDao.update(t);
		return t;
	}

	@Override
	public <T> PagingLoadResult<T> paging(Class<T> clazz,
			EtlPagingLoadConfigBean config) {
		return simpleDao.paging(clazz, config);
	}

	@Override
	public <T> List<T> get(Class<T> clazz) {
		return simpleDao.get(clazz);
	}

}
