package com.voole.leech.gwt.client.ui.hbasemeta.combox;

import java.util.List;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.common.combox.AddEnableComboBox;
import com.voole.leech.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent.HbaseTableColumnComboxAddHanlder;
import com.voole.leech.gwt.client.ui.hbasemeta.editor.HbaseTableColumnEditor;
import com.voole.leech.gwt.client.util.PropertyUtils;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;

public class HbaseTableColumnCombox extends AddEnableComboBox<HbaseTableColumn>
		implements HbaseTableColumnComboxAddHanlder {
	private HbaseTableColumnComboxAddEvent currEvent;

	public HbaseTableColumnCombox() {
		super(new ListStore<HbaseTableColumn>(
				PropertyUtils.HbaseTableColumnProperty.key()),
				new LabelProvider<HbaseTableColumn>() {
					@Override
					public String getLabel(HbaseTableColumn item) {
						return item.getName();
					}
				}, new HbaseTableColumnEditor());

		addHandler(this, HbaseTableColumnComboxAddEvent.TYPE);
	}

	@Override
	protected HbaseTableColumn createAddItem() {
		HbaseTableColumn column = new HbaseTableColumn();
		column.setId(-1);
		column.setName("添加...");
		return column;
	}

	@Override
	public boolean isAddItem(HbaseTableColumn selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected HbaseTableColumn newComboxInstance() {
		HbaseTableColumn column = new HbaseTableColumn();
		column.setVersion(getCurrEvent().getVersion());
		return column;
	}

	@Override
	public void onAdd(
			HbaseTableColumnComboxAddEvent hbaseTableColumnComboxAddEvent) {
		if (!isChangeVersion(hbaseTableColumnComboxAddEvent)) {
			return;

		}
		clear();
		setCurrEvent(hbaseTableColumnComboxAddEvent);
		initCombox();
	}

	private boolean isChangeVersion(HbaseTableColumnComboxAddEvent e) {
		if (getCurrEvent() == null) {
			return true;
		}
		if (getCurrEvent().getVersion() == null) {
			if (e.getVersion() == null) {
				return false;
			} else {
				return true;
			}
		} else {
			if (e.getVersion() == null) {
				return true;
			} else {
				return !e.getVersion().equals(
						getCurrEvent().getVersion().getId());
			}
		}
	}

	private void initCombox() {
		if (getCurrEvent().getVersion() == null) {
			return;
		}
		mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.HbaseMetaRpcService.getColumnsByVersionId(
				getCurrEvent().getVersion().getId(),
				new RpcAsyncCallback<List<HbaseTableColumn>>() {
					@Override
					public void _onSuccess(List<HbaseTableColumn> t) {
						getStore().clear();
						getStore().addAll(t);
						getStore().add(getAddItem());
					}

					@Override
					public void post() {
						super.post();
						unmask();
					}
				});
	}

	public HbaseTableColumnComboxAddEvent getCurrEvent() {
		return currEvent;
	}

	public void setCurrEvent(HbaseTableColumnComboxAddEvent currEvent) {
		this.currEvent = currEvent;
	}

	public void fireRefreshCombox(HbaseTableColumnComboxAddEvent event) {
		fireEvent(event);
	}

}
