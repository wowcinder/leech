/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service.kafka.transaction;

import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;


/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaTransactionDao {
	public Integer queryWatchDogIdByIp(String ip);
	public KafkaWatchDog getDogById(Integer id);
}
