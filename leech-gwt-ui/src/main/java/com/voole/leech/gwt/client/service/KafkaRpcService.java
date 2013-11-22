/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@RemoteServiceRelativePath("rpc/kafka.rpc")
public interface KafkaRpcService extends RemoteService {
	List<LogModelVersion<?>> getLogModelVersions() throws SharedException,
			ConstraintViolationException;

	List<KafkaTopic> getTopics() throws SharedException,
			ConstraintViolationException;

	KafkaTopic saveKafkaTopic(KafkaTopic topic) throws SharedException,
			ConstraintViolationException;

	KafkaTopic updateKafkaTopic(KafkaTopic topic) throws SharedException,
			ConstraintViolationException;

	void deleteKafkaTopics(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<KafkaTopic> pagingKafkaTopic(EtlPagingLoadConfigBean config)
			throws SharedException, ConstraintViolationException;

	KafkaWatchDog saveKafkaWatchDog(KafkaWatchDog dog) throws SharedException,
			ConstraintViolationException;

	KafkaWatchDog updateKafkaWatchDog(KafkaWatchDog dog)
			throws SharedException, ConstraintViolationException;

	void deleteKafkaWatchDogs(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDog(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	KafkaWatchDogTopicSetting saveKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException;

	KafkaWatchDogTopicSetting updateKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException;

	void deleteKafkaWatchDogTopicSettings(List<Integer> ids)
			throws SharedException, ConstraintViolationException;

	List<KafkaWatchDogTopicSetting> getKafkaWatchDogTopicSettings(
			Integer watchDogId) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDogStatus(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	List<KafkaTopic> getRemainKafkaTopics(Integer dogId);

	List<KafkaWatchDogTopicSetting> saveWatchDogTopicSettings(Integer dogId,
			List<Integer> topicIds);

	void restart(Integer dogId);

	void start(Integer dogId);

	void stop(Integer dogId);

	ValidationSupport dummy();
}
