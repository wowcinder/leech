package com.voole.leech.shared;

import java.io.Serializable;

public class Provider<T> implements Serializable {
	private static final long serialVersionUID = 8146548225336292003L;

	private T t;

	public Provider() {
	}

	public Provider(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return this.get() == null || this.get().equals(null);
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Provider) {
			Provider<?> that = (Provider<?>) obj;
			return this.equals(that.get());
		}
		if (this.get() != null) {
			return this.get().equals(obj);
		} else {
			return obj.equals(null);
		}
	}

	@Override
	public String toString() {
		if (this.equals(null)) {
			return null;
		}
		return this.get().toString();
	}

	@Override
	public int hashCode() {
		if (this.equals(null)) {
			return 0;
		}
		return this.get().hashCode();
	}
}
