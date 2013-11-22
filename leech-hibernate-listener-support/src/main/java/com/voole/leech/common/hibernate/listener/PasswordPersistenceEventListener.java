/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.hibernate.listener;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

import com.voole.leech.common.shared.entity.password.PasswordEncryptor;
import com.voole.leech.common.shared.entity.password.PasswordPersistence;

/**
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public class PasswordPersistenceEventListener extends
		AbstractHibernateEventListener implements PreInsertEventListener,
		PreUpdateEventListener {

	private static final long serialVersionUID = 1010463485417784456L;

	private PasswordEncryptor passwordEncryptor;

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		Object o = event.getEntity();
		if (o instanceof PasswordPersistence) {
			PasswordPersistence p = (PasswordPersistence) o;
			String password = null;
			if (p.getPassword() != null) {
				password = passwordEncryptor
						.getEncryptPassword(p.getPassword());
			} else {
				PasswordPersistence old = (PasswordPersistence) event
						.getPersister().getFactory().openSession()
						.get(p.getClass(), event.getId());
				password = old.getPassword();
			}
			p.setPassword(password);
			setValue(event.getState(), event.getPersister()
					.getEntityMetamodel().getPropertyNames(),
					p.getPasswordPropertyName(), password, p);
		}
		return false;
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object o = event.getEntity();
		if (o instanceof PasswordPersistence) {
			PasswordPersistence p = (PasswordPersistence) o;
			if (p != null && p.getPassword() != null) {
				String password = passwordEncryptor.getEncryptPassword(p
						.getPassword());
				p.setPassword(password);
				setValue(event.getState(), event.getPersister()
						.getEntityMetamodel().getPropertyNames(),
						p.getPasswordPropertyName(), password, p);
			}
		}
		return false;
	}

	public PasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}

}
