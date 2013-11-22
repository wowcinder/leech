/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.window;

import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.voole.leech.gwt.client.common.GwtCallBack;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class FixedWindow extends Window {

	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	protected static final VerticalLayoutData mainVd = new VerticalLayoutData(
			1, 1);

	private GwtCallBack<SelectEvent> cancelBack;

	private HandlerRegistration showHandlerHr;

	public FixedWindow() {
		showHandlerHr = this.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				showHandlerHr.removeHandler();
				FixedWindow.this.setMinHeight(FixedWindow.this.getElement()
						.getHeight(true));
			}
		});
		setModal(true);
	}

	public FixedWindow(GwtCallBack<SelectEvent> cancelBack) {
		this.cancelBack = cancelBack;
	}

	@Override
	protected void initTools() {
		super.initTools();
		if (isClosable()) {
			closeBtn.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					if (cancelBack != null) {
						cancelBack.call(event);
					}
				}
			});
		}
	}

	public GwtCallBack<SelectEvent> getCancelBack() {
		return cancelBack;
	}

	public void setCancelBack(GwtCallBack<SelectEvent> cancelBack) {
		this.cancelBack = cancelBack;
	}

}
