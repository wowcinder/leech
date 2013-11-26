package com.voole.leech.logmodelmeta.shared.entity.json;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;

@Entity
@Table(name = "log_model_simple_column_json")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class JsonLogModelSimpleColumn extends JsonLogModelColumn {

	private static final long serialVersionUID = -5597434068603255025L;
	private HbaseTableColumn hbaseTableColumn;

	@OneToOne
	@JoinColumn(name = "hbaseTableColumn_id")
	public HbaseTableColumn getHbaseTableColumn() {
		return hbaseTableColumn;
	}

	public void setHbaseTableColumn(HbaseTableColumn hbaseTableColumn) {
		this.hbaseTableColumn = hbaseTableColumn;
	}

}
