/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.hibernate.listener;

import java.util.Date;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTime;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
public class EntityWithCreateTimeListener extends AbstractHibernateEventListener
		implements PreInsertEventListener {

	private static final long serialVersionUID = 612228253431346177L;

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object o = event.getEntity();
		if (o instanceof EntityWithCreateTime) {
			EntityWithCreateTime entity = (EntityWithCreateTime) o;
			Date now = new Date();
			entity.setCreateTime(now);
			setValue(event.getState(), event.getPersister()
					.getEntityMetamodel().getPropertyNames(),
					entity.getCreateTimePropertyName(), now, entity);
		}
		return false;
	}

}
