package com.voole.leech.gwt.client.common.paging;

import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.shared.paging.PagingCondition;

public class EtlPagingLoader<T> extends
		PagingLoader<EtlPagingLoadConfigBean, PagingLoadResult<T>> {
	private PagingCondition condition;

	public void setCondition(PagingCondition condition) {
		this.condition = condition;
	}

	public PagingCondition getCondition() {
		return condition;
	}

	public EtlPagingLoader(
			DataProxy<EtlPagingLoadConfigBean, PagingLoadResult<T>> proxy) {
		super(proxy);
	}

	public boolean load(EtlPagingLoadConfigBean loadConfig) {
		loadConfig.setCondition(getCondition());
		return super.load(loadConfig);
	}

	protected EtlPagingLoadConfigBean newLoadConfig() {
		return new EtlPagingLoadConfigBean();
	}
}
