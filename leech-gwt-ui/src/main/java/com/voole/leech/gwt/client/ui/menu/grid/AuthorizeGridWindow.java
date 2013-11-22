/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.menu.grid;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.window.FixedWindow;
import com.voole.leech.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class AuthorizeGridWindow extends FixedWindow {
	public AuthorizeGridWindow(GwtCallBack<Authorize> callback) {
		final AuthorizeGrid grid = new AuthorizeGrid(callback);
		setWidget(grid);
		setHeadingHtml("双击选择:");
		setHeight(450);
		setModal(true);

		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				grid.getCallback().call(
						grid.getStore().get(event.getRowIndex()));
				AuthorizeGridWindow.this.hide();
			}
		});
	}
}
