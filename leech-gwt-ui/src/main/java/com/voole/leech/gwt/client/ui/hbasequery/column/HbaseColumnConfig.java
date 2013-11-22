/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.ui.hbasequery.column;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseColumnConfig<K extends Serializable> extends
		ColumnConfig<HbaseRecord<K>, Object> {
	private HbaseTableColumn column;

	public HbaseColumnConfig(HbaseTableColumn column) {
		super(new HbaseRecordValueProvider(column.getName()), 100, column
				.getShortname());
		this.column = column;
		this.setSortable(false);
		this.setMenuDisabled(true);
		initValueProvider();
	}

	private void initValueProvider() {
		DateTimeFormat df = null;
		switch (getColumn().getType()) {
		case DATE:
			df = DateTimeFormat.getFormat("yyyy-MM-dd");
			break;
		case TIME:
			df = DateTimeFormat.getFormat("HH:mm:ss");
			break;
		case DATETIME:
			df = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
			break;
		default:
			break;
		}
		if (df != null) {
			setCell(new SimpleSafeHtmlCell<Object>(new DateSafeHtmlRenderer(df)));
		} else {
			setCell(new SimpleSafeHtmlCell<Object>(
					new AbstractSafeHtmlRenderer<Object>() {

						@Override
						public SafeHtml render(Object object) {
							if (object != null) {
								return SafeHtmlUtils.fromString(object
										.toString());
							}
							return null;
						}
					}));
		}
	}

	public class DateSafeHtmlRenderer extends AbstractSafeHtmlRenderer<Object> {

		private DateTimeFormat df;

		public DateSafeHtmlRenderer(DateTimeFormat df) {
			this.df = df;
		}

		@Override
		public SafeHtml render(Object object) {
			Date d = (Date) object;
			if (d != null) {
				return SafeHtmlUtils.fromString(df.format(d));
			}
			return null;
		}
	}

	public HbaseTableColumn getColumn() {
		return column;
	}

	public void setColumn(HbaseTableColumn column) {
		this.column = column;
	}

}
