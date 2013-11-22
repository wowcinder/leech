package com.voole.leech.gwt.client.ui.user.window;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.ui.user.window.ShowAuthorizesSelectorWindowEvent.ShowAuthorizesSelectorWindowEventHandler;
import com.voole.leech.shared.entity.authorize.Authorize;

public class ShowAuthorizesSelectorWindowEvent extends
		GwtEvent<ShowAuthorizesSelectorWindowEventHandler> {
	public static final Type<ShowAuthorizesSelectorWindowEventHandler> TYPE = new Type<ShowAuthorizesSelectorWindowEventHandler>();

	public interface ShowAuthorizesSelectorWindowEventHandler extends
			EventHandler {
		void dealEvent(ShowAuthorizesSelectorWindowEvent event);
	}

	private GwtCallBack<List<Authorize>> callback;

	public ShowAuthorizesSelectorWindowEvent(
			GwtCallBack<List<Authorize>> callback) {
		this.callback = callback;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowAuthorizesSelectorWindowEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowAuthorizesSelectorWindowEventHandler handler) {
		handler.dealEvent(this);
	}

	public GwtCallBack<List<Authorize>> getCallback() {
		return callback;
	}
}
