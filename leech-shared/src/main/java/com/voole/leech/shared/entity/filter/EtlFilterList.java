/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.entity.filter;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author XuehuiHe
 * @date 2013年10月22日
 */
@Entity
@Table(name = "hbase_filter_list")
public class EtlFilterList extends EtlFilter {
	private static final long serialVersionUID = 1723117033826601200L;

	public static enum EtlFilterListOp {
		MUST_PASS_ALL, MUST_PASS_ONE
	}

	private EtlFilterListOp operator;
	private List<EtlFilter> filters;
	private UserFilterConfig filterConfig;

	public EtlFilterList() {
		operator = EtlFilterListOp.MUST_PASS_ALL;
	}

	@Enumerated(value = EnumType.STRING)
	@NotNull
	@Column(name = "op", length = 15, nullable = false)
	public EtlFilterListOp getOperator() {
		return operator;
	}

	@OneToMany(mappedBy = "list")
	public List<EtlFilter> getFilters() {
		return filters;
	}

	@OneToOne(mappedBy = "filter")
	public UserFilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(UserFilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void setOperator(EtlFilterListOp operator) {
		this.operator = operator;
	}

	public void setFilters(List<EtlFilter> filters) {
		this.filters = filters;
	}

}
