package com.voole.leech.gwt.client.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class EtlApp {

	private static final Logger log = Logger.getLogger(EtlApp.class.getName());

	public void run() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				Window.alert("Error: " + e.getMessage());
				log.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
		});
		RootPanel.get().add(new EtlView());
	}
}
