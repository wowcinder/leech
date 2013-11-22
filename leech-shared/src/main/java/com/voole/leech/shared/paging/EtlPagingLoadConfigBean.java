package com.voole.leech.shared.paging;

import com.sencha.gxt.data.shared.loader.PagingLoadConfigBean;

public class EtlPagingLoadConfigBean extends PagingLoadConfigBean {
	private static final long serialVersionUID = 8925370681075443720L;

	public EtlPagingLoadConfigBean() {
		this(0, 50);
	}

	public EtlPagingLoadConfigBean(int offset, int limit) {
		super(offset, limit);
	}

	private PagingCondition condition;

	public void setCondition(PagingCondition condition) {
		this.condition = condition;
	}

	public PagingCondition getCondition() {
		return condition;
	}
}
