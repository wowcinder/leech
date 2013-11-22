/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.server.rpc;

import java.util.Date;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import com.voole.leech.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import com.voole.leech.gwt.client.service.HbaseQueryRpcService;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.server.AuthorizeNames.AuthorizeAnnotationNamesForHbaseQuery;
import com.voole.leech.service.HbaseQueryService;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Service
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForHbaseQuery.GROUP)
public class HbaseRpcQueryServiceImpl implements HbaseQueryRpcService {
	@Autowired
	private HbaseQueryService hbaseQueryService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForHbaseQuery.QUERY)
	public PagingLoadResult<HbaseRecord<String>> get(
			EtlPagingLoadConfigBean config) throws SharedException {
		return hbaseQueryService.get(config);
	}

	@Override
	public Short dummyShort() {
		return null;
	}

	@Override
	public Double dummyDouble() {
		return null;
	}

	@Override
	public Integer dummyInteger() {
		return null;
	}

	@Override
	public Long dummyLong() {
		return null;
	}

	@Override
	public Boolean dummyBoolean() {
		return null;
	}

	@Override
	public Date dummyDate() {
		return null;
	}

	@Override
	public Character dummyCharacter() {
		return null;
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

}
