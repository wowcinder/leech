/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.leech.dao.kafka.KafkaDao;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Service
public class WatchDogManagerRMIServiceImpl implements WatchDogManagerRMIService {
	@Autowired
	private KafkaDao kafkaDao;
	@Autowired
	private KafkaWatchDogStatusManager kafkaWatchDogStatusManager;

	public WatchDogManagerRMIServiceImpl() {
	}

	@Override
	@Transactional
	public Integer login(String clientIp, Integer rmiPort) {
		Integer dogId = getWatchDogIdByIp(clientIp);
		kafkaWatchDogStatusManager.login(dogId);
		kafkaDao.updateRmiPort(dogId, rmiPort);
		return dogId;
	}

	@Override
	public void logoff(String clientIp) {
		Integer dogId = getWatchDogIdByIp(clientIp);
		kafkaWatchDogStatusManager.logoff(dogId);
	}

	@Override
	public void tick(String clientIp) {
		Integer dogId = getWatchDogIdByIp(clientIp);
		kafkaWatchDogStatusManager.tick(dogId);
	}


	protected Integer getWatchDogIdByIp(String ip) {
		return kafkaWatchDogStatusManager.getWatchDogIdByIp(ip);
	}

}
