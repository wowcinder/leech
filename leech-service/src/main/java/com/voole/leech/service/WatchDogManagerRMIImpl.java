/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.leech.logmodelmeta.shared.rmi.WatchDogManagerRMI;
import com.voole.leech.service.kafka.WatchDogManagerRMIService;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service("watchdog_rmi_service")
public class WatchDogManagerRMIImpl implements WatchDogManagerRMI {
	@Autowired
	private WatchDogManagerRMIService rmiService;

	@Override
	public Integer login(Integer rmiPort) {
		return rmiService.login(getClientIp(), rmiPort);
	}

	@Override
	public void logoff() {
		final String ip = getClientIp();
		new Thread() {
			@Override
			public void run() {
				rmiService.logoff(ip);
			}
		}.start();
	}

	@Override
	public void tick() {
		final String ip = getClientIp();
		new Thread() {
			@Override
			public void run() {
				rmiService.tick(ip);
			}
		}.start();
	}

	protected String getClientIp() {
		String clienthost;
		try {
			clienthost = RemoteServer.getClientHost();
			InetAddress ia = java.net.InetAddress.getByName(clienthost);
			return ia.getHostAddress();

		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
