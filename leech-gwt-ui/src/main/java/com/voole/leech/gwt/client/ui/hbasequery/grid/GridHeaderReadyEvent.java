/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery.grid;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.leech.gwt.client.ui.hbasequery.grid.GridHeaderReadyEvent.GridHeaderReadyHanlder;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class GridHeaderReadyEvent extends GwtEvent<GridHeaderReadyHanlder> {
	public static final Type<GridHeaderReadyHanlder> TYPE = new Type<GridHeaderReadyHanlder>();

	public interface GridHeaderReadyHanlder extends EventHandler {
		public void onHeaderReady();
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GridHeaderReadyHanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GridHeaderReadyHanlder handler) {
		handler.onHeaderReady();
	}
}
