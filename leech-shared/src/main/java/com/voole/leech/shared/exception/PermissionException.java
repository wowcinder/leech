/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared.exception;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public class PermissionException extends SharedException {
	private static final long serialVersionUID = -4779019512147089489L;

	public PermissionException() {
		super();
	}

	public PermissionException(String message) {
		super(message);
	}

	public PermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionException(Throwable cause) {
		super(cause);
	}
}
