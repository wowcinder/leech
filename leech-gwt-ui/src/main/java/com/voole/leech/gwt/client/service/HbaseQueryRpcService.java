/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.gwt.client.service;

import java.util.Date;

import org.hibernate.validator.engine.ValidationSupport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@RemoteServiceRelativePath("rpc/hbase_query.rpc")
public interface HbaseQueryRpcService extends RemoteService {
	PagingLoadResult<HbaseRecord<String>> get(EtlPagingLoadConfigBean config)
			throws SharedException;

	Short dummyShort();

	Double dummyDouble();

	Integer dummyInteger();

	Long dummyLong();

	Boolean dummyBoolean();

	Date dummyDate();

	Character dummyCharacter();

	ValidationSupport dummy();
}
