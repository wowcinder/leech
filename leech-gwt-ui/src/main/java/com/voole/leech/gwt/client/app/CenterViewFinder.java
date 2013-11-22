package com.voole.leech.gwt.client.app;

import com.voole.leech.gwt.client.ui.CenterView;

public abstract class CenterViewFinder {

	protected CenterViewFinder() {
	}

	public abstract CenterView findCenterView(String token);
}
