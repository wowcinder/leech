/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.entity.filter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年10月22日
 */
@Entity
@Table(name = "user_filter_config", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"user_id", "table_id" }) })
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class UserFilterConfig extends EntityWithCreateTimeAndAutoIncreaseId {
	private static final long serialVersionUID = 4156608013660607122L;
	private User user;
	private EtlFilterList filter;
	private HbaseTable table;

	public UserFilterConfig() {
	}

	@ManyToOne(optional = false)
	@NotNull
	public User getUser() {
		return user;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fid")
	public EtlFilterList getFilter() {
		return filter;
	}

	@ManyToOne(optional = false)
	@NotNull
	public HbaseTable getTable() {
		return table;
	}

	public void setTable(HbaseTable table) {
		this.table = table;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFilter(EtlFilterList filter) {
		this.filter = filter;
	}

}
