/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.shared.entity.createtime;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
@MappedSuperclass
@AccessType("property")
public class EntityWithCreateTimeImpl implements EntityWithCreateTime,
		Serializable {
	private static final long serialVersionUID = 8015695606291655333L;
	private Date createTime;

	public EntityWithCreateTimeImpl() {
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

}
