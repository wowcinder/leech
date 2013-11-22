package com.voole.leech.gwt.client.ui.logmodelmeta;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.leech.gwt.client.ui.logmodelmeta.HbaseTableVersionChangeEvent.HbaseTableVersionChangeHanlder;

public class HbaseTableVersionChangeEvent<T> extends
		GwtEvent<HbaseTableVersionChangeHanlder<T>> {
	public static Type<HbaseTableVersionChangeHanlder<?>> TYPE = new Type<HbaseTableVersionChangeHanlder<?>>();

	public interface HbaseTableVersionChangeHanlder<T> extends EventHandler {
		public void onCheckVersionChange(HbaseTableVersionChangeEvent<T> event);

	}

	private final T column;

	public HbaseTableVersionChangeEvent(T column) {
		this.column = column;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<HbaseTableVersionChangeHanlder<T>> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(HbaseTableVersionChangeHanlder<T> handler) {
		handler.onCheckVersionChange(this);
	}

	public T getColumn() {
		return column;
	}

}
