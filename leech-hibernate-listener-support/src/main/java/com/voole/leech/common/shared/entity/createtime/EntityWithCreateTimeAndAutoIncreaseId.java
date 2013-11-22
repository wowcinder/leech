/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.shared.entity.createtime;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;

/**
 * @author XuehuiHe
 * @date 2013年11月22日
 */
@MappedSuperclass
@AccessType("property")
public class EntityWithCreateTimeAndAutoIncreaseId extends
		EntityWithCreateTimeImpl implements Serializable{
	private static final long serialVersionUID = 9082011710653727337L;
	private Integer id;

	public EntityWithCreateTimeAndAutoIncreaseId() {
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
