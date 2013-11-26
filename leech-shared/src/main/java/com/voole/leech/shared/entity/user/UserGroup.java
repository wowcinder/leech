/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.entity.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;
import com.voole.leech.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
@Entity
@Table(name = "user_group")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class UserGroup extends EntityWithCreateTimeAndAutoIncreaseId implements
		Serializable {
	private static final long serialVersionUID = 6538171186264110989L;
	private String name;
	private List<User> users;
	private List<Authorize> authorizes;

	public UserGroup() {
	}

	@Column(length = 20, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 20)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "userGroup", cascade = CascadeType.REMOVE)
	public List<User> getUsers() {
		return users;
	}

	@ManyToMany
	@JoinTable(name = "user_group_to_authorize", joinColumns = { @JoinColumn(name = "gid") }, inverseJoinColumns = { @JoinColumn(name = "aid") })
	public List<Authorize> getAuthorizes() {
		return authorizes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setAuthorizes(List<Authorize> authorizes) {
		this.authorizes = authorizes;
	}

}
