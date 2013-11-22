/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.common.combox;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class EnumComboBox<T extends Enum<T>> extends ComboBox<T> {

	public EnumComboBox(T... values) {
		super(new ListStore<T>(new ModelKeyProvider<T>() {
			@Override
			public String getKey(T item) {
				return item.name();
			}
		}), new LabelProvider<T>() {
			@Override
			public String getLabel(T item) {
				return item.toString();
			}
		});

		for (T value : values) {
			getStore().add(value);
		}

		setEditable(false);
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
	}

}
