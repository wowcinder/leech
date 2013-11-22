/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author XuehuiHe
 * @date 2013年11月18日
 */
public class MergedList extends ArrayList<Object> {
	private static final long serialVersionUID = -2869017023694912638L;

	public MergedList() {
	}

	@SuppressWarnings("unchecked")
	public void setList(List<Object> list) {
		if (list == null) {
			return;
		}
		HashSet<Object> set = new HashSet<Object>();
		for (Object o : list) {
			if (o == null) {
				continue;
			}
			if (o instanceof List) {
				set.addAll((List<Object>) o);
			} else {
				set.add(o);
			}
		}
		this.addAll(set);
	}
}
