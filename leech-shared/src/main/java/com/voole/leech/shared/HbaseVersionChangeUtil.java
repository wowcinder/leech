/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.shared;

import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月18日
 */
public class HbaseVersionChangeUtil {
	public static boolean isChange(HbaseTableVersion old,
			HbaseTableVersion _new) {
		if (old == null) {
			if (_new == null) {
				return false;
			} else {
				return true;
			}
		} else {
			if (_new == null) {
				return true;
			} else {
				return !old.getId().equals(_new.getId());
			}
		}
	}
}
