/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.entity.filter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年10月22日
 */
@Entity
@Table(name = "hbase_filter")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)@Inheritance(strategy = InheritanceType.JOINED)
public class EtlFilter extends EntityWithCreateTimeAndAutoIncreaseId {
	private static final long serialVersionUID = 1L;
	private EtlFilterList list;

	public EtlFilter() {
	}

	@ManyToOne
	public EtlFilterList getList() {
		return list;
	}

	public void setList(EtlFilterList list) {
		this.list = list;
	}

}
