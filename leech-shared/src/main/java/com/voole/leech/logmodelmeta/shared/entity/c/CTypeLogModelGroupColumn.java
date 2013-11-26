package com.voole.leech.logmodelmeta.shared.entity.c;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

@Entity
@Table(name = "log_model_group_column_c")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class CTypeLogModelGroupColumn extends CTypeLogModelColumn {
	private static final long serialVersionUID = 1424230533361555956L;
	private List<CTypeLogModelColumn> columns;
	private HbaseTableVersion hbaseTableVersion;

	@OneToMany(mappedBy = "groupColumn", cascade = { CascadeType.REMOVE })
	public List<CTypeLogModelColumn> getColumns() {
		return columns;
	}

	@OneToOne
	@JoinColumn(name = "hbaseTableVersion_id")
	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	public void setHbaseTableVersion(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

	public void setColumns(List<CTypeLogModelColumn> columns) {
		this.columns = columns;
	}

}
