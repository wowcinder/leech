/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery.grid;

import com.google.gwt.user.client.Event;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;
import com.voole.leech.shared.paging.HbaseQueryPagingCondition;
import com.voole.leech.shared.paging.HbaseQueryPagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年9月2日
 */
public abstract class HbaseGridView<M> extends GridView<M> {
	private boolean isLoadingNext = false;
	private boolean isGetAllData = false;
	private final GwtCallBack<PagingLoadResult<M>> doLoadNextCallBack;

	public HbaseGridView() {
		super();
		doLoadNextCallBack = new GwtCallBack<PagingLoadResult<M>>() {
			@Override
			protected void _call(PagingLoadResult<M> t) {
				HbaseQueryPagingLoadResultBean<M> pr = (HbaseQueryPagingLoadResultBean<M>) t;
				grid.getStore().addAll(t.getData());
				refreshIsGetAllData(pr);
			}

			@Override
			public void post() {
				super.post();
				grid.unmask();
				setLoadingNext(false);
			}
		};
	}

	private void refreshIsGetAllData(HbaseQueryPagingLoadResultBean<M> t) {
		EtlPagingLoadConfigBean config = ((EtlPagingLoadConfigBean) grid
				.getLoader().getLastLoadConfig());
		setGetAllData(config.getOffset() + config.getLimit() >= t
				.getTotalLength());
		((HbaseQueryPagingCondition) config.getCondition()).setLastRow(t
				.getLastRow());
	}

	@Override
	protected void syncScroll() {
		super.syncScroll();
		if (!isGetAllData() && isArriveBottom()) {
			doLoadNext();
		}

	}

	protected void handleComponentEvent(Event event) {
		super.handleComponentEvent(event);
	}

	private boolean isArriveBottom() {
		XElement scrElement = getScroller();
		return scrElement.getScrollHeight() - scrElement.getScrollTop()
				- scrElement.getClientHeight() * 1.1 < 0;
	}

	protected void doLoadNext() {
		if (isLoadingNext() && !isGetAllData()) {
			return;
		}
		grid.mask(DefaultMessages.getMessages().loadMask_msg());
		setLoadingNext(true);
		_doLoadNext();
	}

	protected abstract void _doLoadNext();

	public boolean isLoadingNext() {
		return isLoadingNext;
	}

	public void setLoadingNext(boolean isLoadingNext) {
		this.isLoadingNext = isLoadingNext;
	}

	public GwtCallBack<PagingLoadResult<M>> getDoLoadNextCallBack() {
		return doLoadNextCallBack;
	}

	public boolean isGetAllData() {
		return isGetAllData;
	}

	public void setGetAllData(boolean isGetAllData) {
		this.isGetAllData = isGetAllData;
	}

}
