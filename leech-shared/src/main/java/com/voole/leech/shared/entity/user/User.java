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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.password.PasswordPersistence;
import com.voole.leech.common.shared.entity.password.PasswordPersistenceWithCreateTime;
import com.voole.leech.shared.entity.authorize.Authorize;
import com.voole.leech.shared.entity.filter.UserFilterConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
@Entity
@Table(name = "user")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class User extends PasswordPersistenceWithCreateTime implements
		Serializable, PasswordPersistence {
	private static final long serialVersionUID = -1801720788002068921L;

	private String email;
	private UserGroup userGroup;
	private List<Authorize> extraAuthorizes;
	private List<UserFilterConfig> filters;

	public User() {
	}

	@Column(length = 50, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 50)
	public String getEmail() {
		return email;
	}

	@ManyToOne
	@JoinColumn(name = "gid")
	public UserGroup getUserGroup() {
		return userGroup;
	}

	@ManyToMany
	@JoinTable(name = "user_to_extra_authorize", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = { @JoinColumn(name = "aid") })
	public List<Authorize> getExtraAuthorizes() {
		return extraAuthorizes;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	public List<UserFilterConfig> getFilters() {
		return filters;
	}

	public void setFilters(List<UserFilterConfig> filters) {
		this.filters = filters;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setExtraAuthorizes(List<Authorize> extraAuthorizes) {
		this.extraAuthorizes = extraAuthorizes;
	}

}
