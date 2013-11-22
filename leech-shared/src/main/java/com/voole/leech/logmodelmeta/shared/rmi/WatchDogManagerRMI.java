/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.logmodelmeta.shared.rmi;


/**
 * 服务端接收的RMI
 * 
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public interface WatchDogManagerRMI {
	/**
	 * 注册
	 * 
	 * @param rmiPort
	 * @return WatchDog id
	 */
	public Integer login(Integer rmiPort);

	/**
	 * 注销
	 */
	public void logoff();

	/**
	 * 发送心跳
	 */
	public void tick();

}
