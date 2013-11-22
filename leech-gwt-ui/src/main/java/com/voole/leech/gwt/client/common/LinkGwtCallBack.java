package com.voole.leech.gwt.client.common;

public abstract class LinkGwtCallBack<T, N> extends GwtCallBack<T> {
	private GwtCallBack<N> swapper;

	public LinkGwtCallBack(GwtCallBack<N> swapper) {
		this.swapper = swapper;
	}

	protected void post() {
		getSwapper().post();
	}

	protected void _cancel() {
		getSwapper()._cancel();
	}

	protected void _swapperCall(N n) {
		getSwapper()._call(n);
	}

	protected void _fail() {
		getSwapper()._fail();
	}

	public GwtCallBack<N> getSwapper() {
		return swapper;
	}
}
