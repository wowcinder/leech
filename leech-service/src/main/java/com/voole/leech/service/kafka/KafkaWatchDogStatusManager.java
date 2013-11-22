/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service.kafka;

import java.util.Map;

import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaWatchDogStatusManager {

	public void login(Integer dogId);

	public void logoff(Integer dogId);

	public void tick(Integer dogId);

	public void checkAliveDog();

	public Map<Integer, KafkaProcessServerStatus> getWatchDogStauts();

	public Integer getWatchDogIdByIp(String ip);

	/**
	 * @param dogId
	 */
	public void restart(Integer dogId);

	/**
	 * @param dogId
	 */
	public void start(Integer dogId);

	/**
	 * @param dogId
	 */
	public void stop(Integer dogId);
}
