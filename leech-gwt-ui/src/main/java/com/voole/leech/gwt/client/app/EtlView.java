package com.voole.leech.gwt.client.app;

import com.google.gwt.user.client.Window;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.state.client.BorderLayoutStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.voole.leech.gwt.client.event.CenterViewCloseEvent;
import com.voole.leech.gwt.client.event.MenuClickEvent;
import com.voole.leech.gwt.client.ui.CenterContainer;
import com.voole.leech.gwt.client.ui.MenuView;

public class EtlView extends BorderLayoutContainer implements
		MenuClickEvent.Hanlder, CenterViewCloseEvent.Hanlder {

	private CenterContainer centerContainer;
	private MenuView menuView;

	public EtlView() {
		centerContainer = new CenterContainer(this);
		menuView = new MenuView(this);

		monitorWindowResize = true;
		Window.enableScrolling(false);
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());

		setStateful(true);
		setStateId("explorerLayout");

		BorderLayoutStateHandler state = new BorderLayoutStateHandler(this);
		state.loadState();

		addHandler(this, MenuClickEvent.TYPE);
		addHandler(this, CenterViewCloseEvent.TYPE);

		init();
	}

	public void init() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-theme'></div><div id=demo-title>ETL管理系统</div>");

		HtmlLayoutContainer northPanel = new HtmlLayoutContainer(sb.toString());
		northPanel.setStateful(false);
		northPanel.setId("demo-header");
		northPanel.addStyleName("x-small-editor");

		BorderLayoutData northData = new BorderLayoutData(35);
		setNorthWidget(northPanel, northData);

		BorderLayoutData westData = new BorderLayoutData(200);
		westData.setMargins(new Margins(5, 0, 5, 5));
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setCollapseHidden(true);
		westData.setCollapseMini(true);

		ContentPanel west = new ContentPanel();
		west.setHeadingText("菜单");
		west.setBodyBorder(true);
		west.add(menuView.asWidget());

		setWestWidget(west, westData);

		MarginData centerData = new MarginData();
		centerData.setMargins(new Margins(5));

		setCenterWidget(centerContainer.asWidget(), centerData);
	}

	@Override
	public void onMenuClick(String token) {
		centerContainer.showCenter(token);
	}

	@Override
	public void onCenterViewClose(String token) {
		menuView.showMenu(token);
	}

	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}

}
