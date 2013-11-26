package com.voole.leech.logmodelmeta.shared.entity.c;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.voole.leech.logmodelmeta.shared.entity.LogModel;

@Entity
@Table(name = "log_model_c")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)public class CTypeLogModel extends LogModel<CTypeLogModelVersion> {
	private static final long serialVersionUID = 5087339932449259266L;

}
