/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.entity.filter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年10月22日
 */
@Entity
@Table(name = "hbase_filter_base")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class EtlFilterBase extends EtlFilter {
	private static final long serialVersionUID = 6184651889287879034L;
	private HbaseTableColumn column;
	private EtlFilterBaseOp operator;
	private Object Value;

	public EtlFilterBase() {
		operator = EtlFilterBaseOp.EQUAL;
	}

	@ManyToOne
	public HbaseTableColumn getColumn() {
		return column;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "operator", nullable = false, length = 20)
	@NotNull
	public EtlFilterBaseOp getOperator() {
		return operator;
	}

	@Transient
	public Object getValue() {
		return Value;
	}

	public void setColumn(HbaseTableColumn column) {
		this.column = column;
	}

	public void setOperator(EtlFilterBaseOp operator) {
		this.operator = operator;
	}

	public void setValue(Object value) {
		Value = value;
	}

	public static enum EtlFilterBaseOp {
		LESS("<"), LESS_OR_EQUAL("<="), EQUAL("="), NOT_EQUAL("<>"), GREATER(
				">"), GREATER_OR_EQUAL(">="), IS_NULL("is null"), IS_NOT_NULL(
				"is not null"), MATCH("match"), NOT_MATCH("not match"), CONTAINS(
				"contains"), NOT_CONTAINS("not contains");
		private final String opStr;

		private EtlFilterBaseOp(String opStr) {
			this.opStr = opStr;
		}

		public String getOpStr() {
			return opStr;
		}
	}

}
