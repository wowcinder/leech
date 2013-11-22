/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.common.shared.entity.createtime;

import java.util.Date;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
public interface EntityWithCreateTime {
	Date getCreateTime();

	void setCreateTime(Date createTime);

	String getCreateTimePropertyName();
}
