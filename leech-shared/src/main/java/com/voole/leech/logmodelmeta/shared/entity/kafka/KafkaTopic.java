/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.logmodelmeta.shared.entity.kafka;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.leech.common.shared.entity.createtime.EntityWithCreateTimeAndAutoIncreaseId;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Entity
@Table(name = "kafka_topic")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)@Inheritance(strategy = InheritanceType.JOINED)
public class KafkaTopic extends EntityWithCreateTimeAndAutoIncreaseId {
	private static final long serialVersionUID = -7632761348362494932L;
	private String name;

	private List<KafkaWatchDogTopicSetting> topicSettings;
	private KafkaTopicCharset charset;
	private Boolean isEnabled;

	public KafkaTopic() {
		isEnabled = false;
		charset = KafkaTopicCharset.UTF8;
	}

	@Column(length = 100, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE)
	public List<KafkaWatchDogTopicSetting> getTopicSettings() {
		return topicSettings;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	@NotNull
	public KafkaTopicCharset getCharset() {
		return charset;
	}

	@Column(nullable = false, columnDefinition = "boolean")
	@NotNull
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setCharset(KafkaTopicCharset charset) {
		this.charset = charset;
	}

	public void setTopicSettings(List<KafkaWatchDogTopicSetting> topicSettings) {
		this.topicSettings = topicSettings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static enum KafkaTopicCharset {
		GBK("GBK"), UTF8("UTF-8"), ISO88591("ISO-8859-1");
		private final String charset;

		private KafkaTopicCharset(String charset) {
			this.charset = charset;
		}

		public String getCharset() {
			return charset;
		}

	}
}
