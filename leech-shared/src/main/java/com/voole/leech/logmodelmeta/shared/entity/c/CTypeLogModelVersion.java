package com.voole.leech.logmodelmeta.shared.entity.c;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;

@Entity
@Table(name = "log_model_version_c")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class CTypeLogModelVersion extends LogModelVersion<CTypeLogModel> {
	private static final long serialVersionUID = 2719663842833442034L;
	private CTypeLogModelGroupColumn rootNode;

	public CTypeLogModelVersion(Integer vid, Integer rootId) {
		this();
		setId(vid);
		rootNode.setId(rootId);
	}

	public CTypeLogModelVersion() {
		rootNode = new CTypeLogModelGroupColumn();
		rootNode.setPos(0);
		rootNode.setName("root");
	}

	@NotNull
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "root_node_id", nullable = false)
	public CTypeLogModelGroupColumn getRootNode() {
		return rootNode;
	}

	public void setRootNode(CTypeLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
	}

}
