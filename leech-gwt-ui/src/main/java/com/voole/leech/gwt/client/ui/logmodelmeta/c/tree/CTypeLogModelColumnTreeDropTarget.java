package com.voole.leech.gwt.client.ui.logmodelmeta.c.tree;

import java.util.List;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.voole.leech.gwt.client.common.RpcAsyncCallback;
import com.voole.leech.gwt.client.ui.logmodelmeta.LogModelColumnTreeDropTarget;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

public class CTypeLogModelColumnTreeDropTarget extends
		LogModelColumnTreeDropTarget<CTypeLogModelColumn> {

	public CTypeLogModelColumnTreeDropTarget(CTypeLogModelColumnTree tree) {
		super(tree);
	}

	@Override
	protected void move(final CTypeLogModelColumn p,
			final List<TreeStore.TreeNode<CTypeLogModelColumn>> nodes,
			final int index, CTypeLogModelColumn prev, CTypeLogModelColumn item) {
		getWidget().mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.CTypeLogModelMetaRpcService.move(prev, p, item,
				new RpcAsyncCallback<CTypeLogModelColumn>() {
					@Override
					public void _onSuccess(CTypeLogModelColumn t) {
						TreeStore<CTypeLogModelColumn> store = getWidget()
								.getStore();
						update(p, nodes, index);
						store.update(t);
					}

					@Override
					public void post() {
						super.post();
						getWidget().unmask();
					}
				});
	}

	@Override
	protected boolean isDropOnLeafEnabled(CTypeLogModelColumn m) {
		return m instanceof CTypeLogModelGroupColumn;
	}

}
