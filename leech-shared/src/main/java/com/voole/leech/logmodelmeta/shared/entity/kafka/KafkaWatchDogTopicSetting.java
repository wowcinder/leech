/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.logmodelmeta.shared.entity.kafka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Entity
@Table(name = "kafka_watch_dog_topic_setting", uniqueConstraints = @UniqueConstraint(columnNames = {
		"server_id", "topic_id" }))
public class KafkaWatchDogTopicSetting extends
		EntityWithCreateTimeAndAutoIncreaseId {
	private static final long serialVersionUID = 467001406891512377L;
	private KafkaWatchDog server;
	private KafkaTopic topic;
	private Integer threadNum;
	private KafkaWatchDogTopicSettingStatus status;
	private Boolean isEnabled;

	public KafkaWatchDogTopicSetting() {
		threadNum = 1;
		status = KafkaWatchDogTopicSettingStatus.STOPED;
		// isEnabled = false;
		isEnabled = true;
	}

	@ManyToOne(optional = false)
	public KafkaWatchDog getServer() {
		return server;
	}

	@ManyToOne(optional = false)
	@JoinColumn(updatable = false)
	public KafkaTopic getTopic() {
		return topic;
	}

	@Column(nullable = false, name = "thread_num")
	public Integer getThreadNum() {
		return threadNum;
	}

	@Transient
	public KafkaWatchDogTopicSettingStatus getStatus() {
		return status;
	}

	@Column(nullable = false, columnDefinition = "boolean")
	@NotNull
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setStatus(KafkaWatchDogTopicSettingStatus status) {
		this.status = status;
	}

	public void setServer(KafkaWatchDog server) {
		this.server = server;
	}

	public void setTopic(KafkaTopic topic) {
		this.topic = topic;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof KafkaWatchDogTopicSetting) {
			KafkaWatchDogTopicSetting that = (KafkaWatchDogTopicSetting) obj;
			return this.getServer().getId().equals(that.getServer().getId())
					&& this.getTopic().getId().equals(that.getTopic().getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getServer().getId().hashCode() + 3
				* getTopic().getId().hashCode();
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	public static enum KafkaWatchDogTopicSettingStatus {
		STARTING, RUNNING, STOPPING, STOPED, EXCEPTION;
	}

}
