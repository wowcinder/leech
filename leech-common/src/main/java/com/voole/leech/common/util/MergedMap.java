/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuehuiHe
 * @date 2013年11月22日
 */
public class MergedMap extends HashMap<String, Object> {

	private static final long serialVersionUID = -8483031079745759268L;

	@SuppressWarnings("unchecked")
	public void setList(List<Object> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		for (Object o : list) {
			if (o instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) o;
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					put2(key, value);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void put2(String key, Object value) {
		if (this.containsKey(key) && this.get(key) instanceof Collection) {
			if (value instanceof Collection) {
				((Collection<Object>) this.get(key))
						.addAll((Collection<Object>) value);
			} else {
				((Collection<Object>) this.get(key)).add(value);
			}
		} else {
			put(key, value);
		}
	}
}
