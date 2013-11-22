package com.voole.leech.shared.exception;

public class SharedException extends RuntimeException {
	private static final long serialVersionUID = 5620888413810109716L;

	public SharedException() {
		super();
	}

	public SharedException(String message) {
		super(message);
	}

	public SharedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SharedException(Throwable cause) {
		super(cause);
	}

}
