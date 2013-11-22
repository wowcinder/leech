/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.init;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import com.voole.leech.bean.ScanedAccessAuthority;
import com.voole.leech.server.rpc.open.OpenRpcService;
import com.voole.leech.service.AuthorizeService;
import com.voole.leech.util.ClassScaner;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Component
public class AuthorizeInit implements InitializingBean {
	private ClassScaner scanner;
	@Autowired
	private AuthorizeService authorizeService;

	public AuthorizeInit() {
		try {
			scanner = new ClassScaner("xdata.etl.cinder.server.rpc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set<ScanedAccessAuthority> list = new HashSet<ScanedAccessAuthority>();
		for (Class<?> clazz : scanner.getClazzes()) {
			if (!RemoteService.class.isAssignableFrom(clazz)
					|| OpenRpcService.class.isAssignableFrom(clazz)) {
				continue;
			}
			AuthorizeGroupAnnotation authorizeGroupAnnotation = clazz
					.getAnnotation(AuthorizeGroupAnnotation.class);
			String agName = null;
			if (authorizeGroupAnnotation != null
					&& authorizeGroupAnnotation.value().length() != 0) {
				agName = authorizeGroupAnnotation.value();
			}
			for (Method method : clazz.getMethods()) {
				AuthorizeAnnotation authorizeAnnotation = method
						.getAnnotation(AuthorizeAnnotation.class);
				AuthorizeAnnotations authorizeAnnotations = method
						.getAnnotation(AuthorizeAnnotations.class);
				if (authorizeAnnotation == null && authorizeAnnotations == null) {
					continue;
				}
				if (authorizeAnnotation != null) {
					ScanedAccessAuthority s = ScanedAccessAuthority.get(
							authorizeAnnotation, agName);
					if (s != null) {
						list.add(s);
					}
				}
				if (authorizeAnnotations != null) {
					for (AuthorizeAnnotation authorizeAnnotation2 : authorizeAnnotations
							.value()) {
						ScanedAccessAuthority s = ScanedAccessAuthority.get(
								authorizeAnnotation2, agName);
						if (s != null) {
							list.add(s);
						}
					}
				}
			}

		}
		authorizeService.deal(list);
	}
}
