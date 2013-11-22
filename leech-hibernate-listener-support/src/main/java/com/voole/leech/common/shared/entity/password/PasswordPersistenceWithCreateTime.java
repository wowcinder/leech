/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.shared.entity.password;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTime;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MappedSuperclass
public class PasswordPersistenceWithCreateTime extends PasswordPersistenceImpl
		implements Serializable, EntityWithCreateTime {
	private static final long serialVersionUID = 3750991196000295395L;
	private Date createTime;
	private Integer id;

	public PasswordPersistenceWithCreateTime() {
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	@AccessType("property")
	@Column(name = "create_time", nullable = false, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	@Transient
	public String getCreateTimePropertyName() {
		return "createTime";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
