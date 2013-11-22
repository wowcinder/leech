/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.shared.entity.password;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

/**
 * 没有实现Serializable，GWT将不会序列化该类，密码不会传到前台
 * 
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MappedSuperclass
public class PasswordPersistenceImpl implements PasswordPersistence {
	private String password;

	@Column(length = 32)
	@Length(min = 5, max = 32)
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@Transient
	public String getPasswordPropertyName() {
		return "password";
	}

}
