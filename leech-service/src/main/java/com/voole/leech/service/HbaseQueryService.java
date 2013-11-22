/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.service;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.leech.hbasemeta.shared.entity.query.HbaseRecord;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public interface HbaseQueryService {
	PagingLoadResult<HbaseRecord<String>> get(EtlPagingLoadConfigBean config)
			throws SharedException;
}
