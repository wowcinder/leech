package com.voole.leech.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.leech.gwt.client.event.CenterViewCloseEvent.Hanlder;

public class CenterViewCloseEvent extends GwtEvent<Hanlder> {
	public static final Type<Hanlder> TYPE = new Type<Hanlder>();

	public interface Hanlder extends EventHandler {
		void onCenterViewClose(String token);
	}

	private String token;
	
	public CenterViewCloseEvent(String token) {
		this.token = token;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Hanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Hanlder handler) {
		handler.onCenterViewClose(getToken());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
