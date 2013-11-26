package com.voole.leech.logmodelmeta.shared.entity.json;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;

@Entity
@Table(name = "log_model_version_json")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class JsonLogModelVersion extends LogModelVersion<JsonLogModel> {
	private static final long serialVersionUID = 2719663842833442034L;
	private JsonLogModelGroupColumn rootNode;

	public JsonLogModelVersion(Integer vid, Integer rootId) {
		this();
		setId(vid);
		rootNode.setId(rootId);
	}

	public JsonLogModelVersion() {
		rootNode = new JsonLogModelGroupColumn();
		rootNode.setPath("root");
	}

	@NotNull
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "root_node_id", nullable = false)
	public JsonLogModelGroupColumn getRootNode() {
		return rootNode;
	}

	public void setRootNode(JsonLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
	}

}
