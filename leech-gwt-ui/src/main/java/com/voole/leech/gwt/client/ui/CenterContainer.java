/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.info.Info;
import com.voole.leech.gwt.client.app.CenterViewFinder;
import com.voole.leech.gwt.client.app.EtlView;
import com.voole.leech.gwt.client.event.CenterViewCloseEvent;
import com.voole.leech.gwt.client.ui.CenterView.CenterViewConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class CenterContainer extends TabPanel {

	private EtlView etlView;

	private static CenterViewFinder viewFinder = GWT
			.create(CenterViewFinder.class);

	public CenterContainer(EtlView etlView) {
		this.etlView = etlView;
		setBodyBorder(true);
		setTabScroll(true);
		setResizeTabs(true);
		setMinTabWidth(130);
		setCloseContextMenu(true);
		getElement().getStyle().setTextAlign(TextAlign.CENTER);
	}

	protected void close(Widget item) {
		super.close(item);
		afterClosedSubPanel();
	}

	public void showCenter(String token) {
		CenterView exists = findExistsTabItem(token);
		if (exists != null) {
			setActiveWidget(exists);
			return;
		}
		CenterView newView = create(token);
		if (newView == null) {
			Info.display("WARN", "没有找到页面!");
			return;
		}
		CenterViewConfig centerViewConfig = newView.getCenterViewConfig();
		TabItemConfig config = new TabItemConfig(centerViewConfig.getLabel(),
				centerViewConfig.isCloseAble());
		add(newView, config);
		setActiveWidget(newView);
	}

	protected CenterView create(String token) {
		return viewFinder.findCenterView(token);
	}

	public void afterClosedSubPanel() {
		CenterView active = (CenterView) getActiveWidget();
		if (active != null) {
			etlView.fireEvent(new CenterViewCloseEvent(active
					.getCenterViewConfig().getToken()));

		} else {
			etlView.fireEvent(new CenterViewCloseEvent(null));
		}

	}

	protected CenterView findExistsTabItem(String token) {
		for (int i = 0; i < getWidgetCount(); i++) {
			CenterView item = (CenterView) getWidget(i);
			if (token.equals(item.getCenterViewConfig().getToken())) {
				return item;
			}
		}
		return null;
	}

}
