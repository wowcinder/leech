/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.bean;

import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class ScanedAccessAuthority {
	private String group;
	private String value;

	public ScanedAccessAuthority() {
	}

	public ScanedAccessAuthority(String group, String value) {
		this.group = group;
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static ScanedAccessAuthority get(
			AuthorizeAnnotation authorizeAnnotation, String agName) {
		String realAgName = authorizeAnnotation.group();
		if (realAgName == null || realAgName.length() == 0) {
			realAgName = agName;
		}
		if (realAgName == null || realAgName.length() == 0
				|| authorizeAnnotation.value() == null
				|| authorizeAnnotation.value().length() == 0) {
			return null;
		}
		return new ScanedAccessAuthority(realAgName,
				authorizeAnnotation.value());
	}

	@Override
	public int hashCode() {
		return getGroup().hashCode() + 3 * getValue().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanedAccessAuthority)) {
			return false;
		}
		ScanedAccessAuthority that = (ScanedAccessAuthority) obj;
		return that.getGroup().equals(this.getGroup())
				&& that.getValue().equals(this.getValue());
	}

}
