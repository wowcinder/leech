package com.voole.leech.logmodelmeta.shared.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

@Entity
@Table(name = "log_model")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LogModel<Version extends LogModelVersion<?>> extends
		EntityWithCreateTimeAndAutoIncreaseId {
	private static final long serialVersionUID = 5087339932449259266L;
	private String name;
	private String desc;
	private List<Version> versions;

	@NotNull
	@Length(min = 1, max = 100)
	@Column(nullable = false, unique = true, length = 100)
	public String getName() {
		return name;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@OneToMany(mappedBy = "model", targetEntity = LogModelVersion.class)
	public List<Version> getVersions() {
		return versions;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
}
