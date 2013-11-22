/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.cell;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public abstract class SimpleSafeHtmlRenderer<C> extends
		AbstractSafeHtmlRenderer<C> {

	@Override
	public SafeHtml render(C c) {
		String label = getLabel(c);
		if (label != null) {
			return SafeHtmlUtils.fromString(label);
		}
		return null;
	}

	public String getLabel(C c) {
		if (c == null) {
			return null;
		}
		return _getLabel(c);
	}

	protected abstract String _getLabel(C c);

	public SimpleSafeHtmlCell<C> getCell() {
		return new SimpleSafeHtmlCell<C>(this);
	}
}
