/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class SimpleCenterView extends VerticalLayoutContainer implements
		CenterView {

	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	protected static final VerticalLayoutData mainVd = new VerticalLayoutData(
			1, 1);

	private CenterViewConfig centerViewConfig;

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}

}
