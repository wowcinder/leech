/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.grid;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class HeaderBar extends ToolBar {
	private final TextButton addBt;
	private final TextButton delBt;

	public HeaderBar() {
		addBt = new TextButton("添加");
		delBt = new TextButton("删除");
		add(addBt);
		add(delBt);
	}

	public TextButton getAddBt() {
		return addBt;
	}

	public TextButton getDelBt() {
		return delBt;
	}

}
