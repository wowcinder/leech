/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.exception;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public class NoLoginException extends SharedException {
	private static final long serialVersionUID = 6818572874922105982L;

	public NoLoginException() {
		super();
	}

	public NoLoginException(String message) {
		super(message);
	}

	public NoLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoLoginException(Throwable cause) {
		super(cause);
	}
}
