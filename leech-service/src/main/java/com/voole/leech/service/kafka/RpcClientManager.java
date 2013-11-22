/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Service;

import com.voole.leech.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import com.voole.leech.logmodelmeta.shared.rmi.WatchDogRMI;
import com.voole.leech.service.kafka.transaction.KafkaTransactionDao;

/**
 * @author XuehuiHe
 * @date 2013年10月17日
 */
@Service
public class RpcClientManager {
	@Autowired
	private KafkaTransactionDao transactionDao;

	private final Map<String, WatchDogRMI> rmiProxyMap;

	public RpcClientManager() {
		rmiProxyMap = new HashMap<String, WatchDogRMI>();
	}

	public WatchDogRMI getRMiProxy(Integer dogId) {
		KafkaWatchDog dog = transactionDao.getDogById(dogId);
		String rmiUrl = "rmi://" + dog.getIp() + ":" + dog.getRmiPort()
				+ "/watchdog_rmi";
		if (!rmiProxyMap.containsKey(rmiUrl)) {
			org.springframework.remoting.rmi.RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
			rmiProxy.setServiceUrl(rmiUrl);
			rmiProxy.setServiceInterface(WatchDogRMI.class);
			rmiProxy.setLookupStubOnStartup(false);
			rmiProxy.setRefreshStubOnConnectFailure(true);
			rmiProxy.afterPropertiesSet();
			WatchDogRMI rmiProxyObj = (WatchDogRMI) rmiProxy.getObject();
			rmiProxyMap.put(rmiUrl, rmiProxyObj);
		}
		return rmiProxyMap.get(rmiUrl);
	}

}
