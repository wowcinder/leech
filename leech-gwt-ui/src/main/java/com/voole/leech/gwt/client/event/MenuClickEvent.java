package com.voole.leech.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.leech.gwt.client.event.MenuClickEvent.Hanlder;

public class MenuClickEvent extends GwtEvent<Hanlder> {

	public static final Type<Hanlder> TYPE = new Type<Hanlder>();

	public interface Hanlder extends EventHandler {
		void onMenuClick(String token);
	}

	private String token;

	public MenuClickEvent(String token) {
		this.token = token;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Hanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Hanlder handler) {
		handler.onMenuClick(getToken());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
