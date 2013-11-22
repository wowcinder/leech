package com.voole.leech.gwt.client.common;

public abstract class GwtCallBack<T> {

	public GwtCallBack() {
	}

	protected void post() {
	}

	protected abstract void _call(T t);

	public final void call(T t) {
		_call(t);
		post();
	}

	protected void _cancel() {
	}

	public final void cancel() {
		_cancel();
		post();
	}

	protected void _fail() {
	}

	public final void fail() {
		_fail();
		post();
	}

}
