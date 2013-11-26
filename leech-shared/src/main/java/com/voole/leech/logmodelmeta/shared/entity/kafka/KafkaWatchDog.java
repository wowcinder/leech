/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.logmodelmeta.shared.entity.kafka;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Entity
@Table(name = "kafka_watch_dog")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class KafkaWatchDog extends EntityWithCreateTimeAndAutoIncreaseId {
	private static final long serialVersionUID = -6405171995643782865L;
	private String ip;
	private String name;
	private Integer rmiPort;
	private KafkaProcessServerStatus status;
	private List<KafkaWatchDogTopicSetting> topicSettings;

	public KafkaWatchDog() {
		status = KafkaProcessServerStatus.STOPED;
		rmiPort = 1199;
	}

	@Column(nullable = false, length = 16, unique = true)
	@NotNull
	@Length(min = 1, max = 16)
	public String getIp() {
		return ip;
	}

	@Transient
	public KafkaProcessServerStatus getStatus() {
		return status;
	}

	@OneToMany(mappedBy = "server", cascade = CascadeType.REMOVE)
	public List<KafkaWatchDogTopicSetting> getTopicSettings() {
		return topicSettings;
	}

	@Column(length = 30, nullable = false)
	@NotNull
	@Length(min = 1, max = 30)
	public String getName() {
		return name;
	}

	@Column(nullable = false)
	public Integer getRmiPort() {
		return rmiPort;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRmiPort(Integer rmiPort) {
		this.rmiPort = rmiPort;
	}

	public void setTopicSettings(List<KafkaWatchDogTopicSetting> topicSettings) {
		this.topicSettings = topicSettings;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setStatus(KafkaProcessServerStatus status) {
		this.status = status;
	}

	public static enum KafkaProcessServerStatus {
		STARTING, RUNNING, STOPPING, STOPED, EXCEPTION;
	}
}
