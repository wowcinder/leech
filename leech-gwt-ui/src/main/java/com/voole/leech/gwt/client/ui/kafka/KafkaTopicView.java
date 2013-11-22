package com.voole.leech.gwt.client.ui.kafka;

import java.util.List;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.leech.gwt.client.common.GwtCallBack;
import com.voole.leech.gwt.client.common.event.EditEvent;
import com.voole.leech.gwt.client.common.grid.GridConfig;
import com.voole.leech.gwt.client.ui.AbstractCenterView;
import com.voole.leech.gwt.client.ui.kafka.editor.KafkaTopicEditor;
import com.voole.leech.gwt.client.ui.kafka.editor.KafkaTopicFixedModelVersionEditor;
import com.voole.leech.gwt.client.ui.kafka.grid.KafkaTopicGrid;
import com.voole.leech.gwt.client.util.RpcServiceUtils;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import com.voole.leech.shared.annotations.MenuToken;

@MenuToken(name = "TOPIC", token = "kafka_topic", group = "KAFKA")
public class KafkaTopicView extends AbstractCenterView<KafkaTopic> {
	private final KafkaTopicFixedModelVersionEditor kafkaTopicFixedModelVersionEditor;

	public KafkaTopicView() {
		super(new KafkaTopicGrid(new GridConfig()), null);
		kafkaTopicFixedModelVersionEditor = KafkaTopicEditor
				.createFixedModeVersionEditor();
	}

	@Override
	protected KafkaTopic newViewInstance() {
		return null;
	}

	@Override
	protected void initAddBtHandler() {
		getHeaderBar().getAddBt().setText("添加固定model_version");
		getHeaderBar().getAddBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				KafkaTopicFixedModelVersion t = new KafkaTopicFixedModelVersion();
				kafkaTopicFixedModelVersionEditor
						.fireEditEvent(new EditEvent<KafkaTopicFixedModelVersion>(
								t,
								new GwtCallBack<KafkaTopicFixedModelVersion>() {
									@Override
									protected void _call(
											KafkaTopicFixedModelVersion t) {
										getGrid().getStore().add(0, t);
									}
								}));
			}
		});
	}

	@Override
	protected void initUpdateHandler() {
		getGrid().addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				KafkaTopic u = getGrid().getStore().get(event.getRowIndex());
				if (u instanceof KafkaTopicFixedModelVersion) {
					kafkaTopicFixedModelVersionEditor
							.fireEditEvent(new EditEvent<KafkaTopicFixedModelVersion>(
									(KafkaTopicFixedModelVersion) u,
									new GwtCallBack<KafkaTopicFixedModelVersion>() {

										@Override
										protected void _call(
												KafkaTopicFixedModelVersion t) {
											getGrid().getStore().update(t);
										}
									}, true));
				}

			}
		});
	}

	@Override
	protected void delete(List<KafkaTopic> list) {
		RpcServiceUtils.KafkaRpcService.deleteKafkaTopics(
				getIds(list, new EntityToKey<KafkaTopic, Integer>() {
					@Override
					public Integer getId(KafkaTopic m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
