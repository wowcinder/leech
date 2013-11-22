package com.voole.leech.gwt.client.common.combox;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.editor.CinderEditor;
import com.voole.leech.gwt.client.common.event.EditEvent;

public abstract class AddEnableComboBox<T> extends ComboBox<T> {
	private T addItem;
	protected T lastSelectItem = null;

	private final CinderEditor<T> editor;

	public AddEnableComboBox(ListStore<T> store,
			LabelProvider<? super T> labelProvider, CinderEditor<T> editor) {
		super(store, labelProvider);
		setEditable(false);
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);

		this.editor = editor;
		
		this.addSelectionHandler(new SelectionHandler<T>() {
			@Override
			public void onSelection(SelectionEvent<T> event) {
				T selectItem = event.getSelectedItem();
				if (isAddItem(selectItem)) {
					getEditor().fireEvent(getEditEvent());
				} else {
					setLastSelectItem(selectItem);
				}
			}
		});
	}

	protected abstract T createAddItem();

	protected abstract boolean isAddItem(T selectItem);

	protected abstract T newComboxInstance();

	protected EditEvent<T> getEditEvent() {
		return new EditEvent<T>(newComboxInstance(), getCreateItemCallback());
	}

	protected GwtCallBack<T> getCreateItemCallback() {
		return new GwtCallBack<T>() {
			@Override
			protected void _call(T t) {
				getStore().add(0, t);
				setValue(t,true);
			}

			@Override
			protected void _cancel() {
				super._cancel();
//				select(getLastSelectItem());
				setValue(getLastSelectItem(),true);
			}

			@Override
			protected void _fail() {
				super._fail();
//				select(getLastSelectItem());
				setValue(getLastSelectItem(),true);
			}
		};
	}

	protected T getAddItem() {
		if (addItem == null) {
			addItem = createAddItem();
		}
		return addItem;
	}

	public T getLastSelectItem() {
		return lastSelectItem;
	}

	public void setLastSelectItem(T lastSelectItem) {
		this.lastSelectItem = lastSelectItem;
	}

	public CinderEditor<T> getEditor() {
		return editor;
	}
}
