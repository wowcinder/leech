/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import com.voole.leech.dao.kafka.KafkaDao;
import com.voole.leech.gwt.client.service.KafkaRpcService;
import com.voole.leech.logmodelmeta.shared.entity.LogModelVersion;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaTopic;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForKafka;
import com.voole.leech.service.SimpleService;
import com.voole.leech.service.kafka.KafkaWatchDogStatusManager;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service
@AuthorizeGroupAnnotation(value = AuthorizeAnnotationNamesForKafka.GROUP)
public class KafkaRpcServiceImpl implements KafkaRpcService {
	@Autowired
	private SimpleService simpleService;
	@Autowired
	private KafkaWatchDogStatusManager kafkaStatusManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@AuthorizeAnnotations(value = {
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC) })
	public List<LogModelVersion<?>> getLogModelVersions()
			throws SharedException, ConstraintViolationException {
		return (List) simpleService.get(LogModelVersion.class);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC)
	public KafkaTopic saveKafkaTopic(KafkaTopic topic) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(topic);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC)
	public KafkaTopic updateKafkaTopic(KafkaTopic topic)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(topic);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.DELETE_TOPIC)
	public void deleteKafkaTopics(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(KafkaTopic.class, ids);

	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC)
	public PagingLoadResult<KafkaTopic> pagingKafkaTopic(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(KafkaTopic.class, config);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_WATCH_DOG)
	public KafkaWatchDog saveKafkaWatchDog(KafkaWatchDog dog)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(dog);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_WATCH_DOG)
	public KafkaWatchDog updateKafkaWatchDog(KafkaWatchDog dog)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(dog);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.DELETE_WATCH_DOG)
	public void deleteKafkaWatchDogs(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(KafkaWatchDog.class, ids);

	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_WATCH_DOG)
	public PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDog(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(KafkaWatchDog.class, config);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC_SETTING)
	public KafkaWatchDogTopicSetting saveKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(setting);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC_SETTING)
	public KafkaWatchDogTopicSetting updateKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException {
		return simpleService.update(setting);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.DELETE_TOPIC_SETTING)
	public void deleteKafkaWatchDogTopicSettings(List<Integer> ids)
			throws SharedException, ConstraintViolationException {
		simpleService.delete(KafkaWatchDogTopicSetting.class, ids);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC_SETTING)
	public List<KafkaWatchDogTopicSetting> getKafkaWatchDogTopicSettings(
			Integer watchDogId) throws SharedException,
			ConstraintViolationException {
		List<KafkaWatchDogTopicSetting> list = simpleService
				.get(KafkaWatchDogTopicSetting.class);
		List<KafkaWatchDogTopicSetting> result = new ArrayList<KafkaWatchDogTopicSetting>();
		for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : list) {
			if (kafkaWatchDogTopicSetting.getServer().getId()
					.equals(watchDogId)) {
				result.add(kafkaWatchDogTopicSetting);
			}
		}
		return result;
	}

	@Override
	@AuthorizeAnnotations(value = {
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC_SETTING),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC_SETTING),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC_SETTING) })
	public List<KafkaTopic> getTopics() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(KafkaTopic.class);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_WATCH_DOG_STATUS)
	public PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDogStatus(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {

		PagingLoadResult<KafkaWatchDog> pr = pagingKafkaWatchDog(config);
		if (pr.getData() != null && pr.getData().size() > 0) {
			Map<Integer, KafkaProcessServerStatus> watchDogStauts = kafkaStatusManager
					.getWatchDogStauts();
			for (KafkaWatchDog dog : pr.getData()) {
				if (watchDogStauts.containsKey(dog.getId())) {
					dog.setStatus(watchDogStauts.get(dog.getId()));
				}
			}
		}
		return pr;
	}

	@Autowired
	private KafkaDao kafkaDao;

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC_SETTING)
	@Transactional(readOnly = true)
	public List<KafkaTopic> getRemainKafkaTopics(Integer dogId) {
		return kafkaDao.getRemainKafkaTopics(dogId);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC_SETTING)
	public List<KafkaWatchDogTopicSetting> saveWatchDogTopicSettings(
			Integer dogId, List<Integer> topicIds) {
		List<KafkaWatchDogTopicSetting> list = new ArrayList<KafkaWatchDogTopicSetting>();
		KafkaWatchDog dog = new KafkaWatchDog();
		dog.setId(dogId);
		for (Integer topicId : topicIds) {
			KafkaTopic topic = new KafkaTopic();
			topic.setId(topicId);

			KafkaWatchDogTopicSetting setting = new KafkaWatchDogTopicSetting();
			setting.setServer(dog);
			setting.setTopic(topic);
			setting.setThreadNum(1);
			simpleService.save(setting);
			list.add(setting);
		}
		return list;
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.OP_WATCH_DOG)
	public void restart(Integer dogId) {
		kafkaStatusManager.restart(dogId);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.OP_WATCH_DOG)
	public void start(Integer dogId) {
		kafkaStatusManager.start(dogId);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.OP_WATCH_DOG)
	public void stop(Integer dogId) {
		kafkaStatusManager.stop(dogId);
	}

}
