/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.hbasemeta.shared.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Entity
@Table(name = "hbase_table_column")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HbaseTableColumn extends EntityWithCreateTimeAndAutoIncreaseId
		implements Serializable {
	private static final long serialVersionUID = -7351279578902858388L;
	private String name;
	private String shortname;
	private HbaseTableColumnType type;
	private Integer pos;
	private HbaseTableVersion version;
	private String desc;

	public HbaseTableColumn() {
	}

	@Column(length = 40, nullable = false)
	@Length(min = 1, max = 40)
	@NotNull
	public String getName() {
		return name;
	}

	@Column(length = 20)
	@Length(min = 1, max = 40)
	public String getShortname() {
		return shortname;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "column_type", nullable = false, length = 20)
	public HbaseTableColumnType getType() {
		return type;
	}

	public Integer getPos() {
		return pos;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "version_id")
	public HbaseTableVersion getVersion() {
		return version;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setType(HbaseTableColumnType type) {
		this.type = type;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setVersion(HbaseTableVersion version) {
		this.version = version;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public enum HbaseTableColumnType {
		SHORT(Short.class), INT(Integer.class), LONG(Long.class), FLOAT(
				Float.class), DOUBLE(Double.class), STRING(String.class), CHAR(
				Character.class), BOOLEAN(Boolean.class), DATE(Date.class), TIME(
				Date.class), DATETIME(Date.class), TEXT(String.class);
		Class<?> clazz;

		HbaseTableColumnType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

	}
}
