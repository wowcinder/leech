/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.hbasemeta.shared.entity.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Entity
@Table(name = "hbase_table_version")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HbaseTableVersion extends EntityWithCreateTimeAndAutoIncreaseId
		implements Serializable {
	private static final long serialVersionUID = 1172877225725071936L;
	private String version = "";
	private String desc;
	private HbaseTable table;
	private List<HbaseTableColumn> columns;

	public HbaseTableVersion() {
	}

	@Column(length = 20, nullable = false)
	@NotNull
	@Length(min = 0, max = 20)
	public String getVersion() {
		return version;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "table_id")
	@NotNull
	public HbaseTable getTable() {
		return table;
	}

	@OneToMany(mappedBy = "version", cascade = { CascadeType.REMOVE,
			CascadeType.PERSIST })
	public List<HbaseTableColumn> getColumns() {
		return columns;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setTable(HbaseTable table) {
		this.table = table;
	}

	public void setColumns(List<HbaseTableColumn> columns) {
		this.columns = columns;
	}

}
