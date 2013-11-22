/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery.column;

import java.io.Serializable;
import java.util.HashMap;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseRecordValueProvider implements
		ValueProvider<HbaseRecord<?>, Object>, Serializable {

	private static final long serialVersionUID = -7347689191496715303L;
	private String column;

	public HbaseRecordValueProvider(String column) {
		this.column = column;
	}

	@Override
	public Object getValue(HbaseRecord<?> record) {
		if (record.getData() != null
				&& record.getData().get(getColumn()) != null) {
			return record.getData().get(getColumn());
		}
		return null;
	}

	@Override
	public void setValue(HbaseRecord<?> record, Object value) {
		if (record.getData() == null) {
			record.setData(new HashMap<String, Object>());
		}
		record.getData().put(getColumn(), value);
	}

	@Override
	public String getPath() {
		return getColumn();
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
