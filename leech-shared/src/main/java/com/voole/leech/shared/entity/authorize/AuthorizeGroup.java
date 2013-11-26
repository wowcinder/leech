/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.entity.authorize;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Table(name = "authorize_group")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)@Entity
public class AuthorizeGroup extends EntityWithCreateTimeAndAutoIncreaseId
		implements Serializable {

	private static final long serialVersionUID = -1351283572556439616L;

	private String name;
	private Integer displayOrder;
	private List<Authorize> authorizes;

	public AuthorizeGroup() {
	}

	@Column(length = 20, unique = true, nullable = false)
	@Length(min = 1, max = 20)
	@NotNull
	public String getName() {
		return name;
	}

	@Column(name = "display_order")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	public List<Authorize> getAuthorizes() {
		return authorizes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void setAuthorizes(List<Authorize> authorizes) {
		this.authorizes = authorizes;
	}

}
