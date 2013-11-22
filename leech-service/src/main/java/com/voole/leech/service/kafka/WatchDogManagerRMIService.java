/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service.kafka;


/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface WatchDogManagerRMIService {

	/**
	 * @param clientIp
	 * @param rmiPort
	 * @return
	 */
	Integer login(String clientIp, Integer rmiPort);

	/**
	 * @param clientIp
	 */
	void logoff(String clientIp);

	/**
	 * @param clientIp
	 */
	void tick(String clientIp);


}
