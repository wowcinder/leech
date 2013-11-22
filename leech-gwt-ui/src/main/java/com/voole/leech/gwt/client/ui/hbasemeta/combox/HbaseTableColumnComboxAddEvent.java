package com.voole.leech.gwt.client.ui.hbasemeta.combox;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.leech.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent.HbaseTableColumnComboxAddHanlder;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableColumnComboxAddEvent extends
		GwtEvent<HbaseTableColumnComboxAddHanlder> {
	public static final Type<HbaseTableColumnComboxAddHanlder> TYPE = new Type<HbaseTableColumnComboxAddHanlder>();

	public interface HbaseTableColumnComboxAddHanlder extends EventHandler {
		void onAdd(HbaseTableColumnComboxAddEvent hbaseTableColumnComboxAddEvent);
	}

	private final HbaseTableVersion version;

	public HbaseTableColumnComboxAddEvent(HbaseTableVersion version) {
		this.version = version;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<HbaseTableColumnComboxAddHanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(HbaseTableColumnComboxAddHanlder handler) {
		handler.onAdd(this);
	}

	public HbaseTableVersion getVersion() {
		return version;
	}

}
