/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.util;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class TestSuper<Version extends Number,Model extends Number> {
	private Version version;

	public TestSuper() {
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
}
