package com.voole.leech.service;

import javax.validation.ConstraintViolationException;

import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.shared.exception.SharedException;

public interface CTypeLogModelMetaService {
	CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;

	/**
	 * @param logModelVersion
	 * @return
	 */
	CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion logModelVersion);

	/**
	 * @param column
	 * @return
	 */
	<T extends CTypeLogModelColumn> T updateLogModelColumn(T column);

	<T extends CTypeLogModelColumn> T saveLogModelColumn(T column);

	public void deleteLogModelColumn(Integer id);

	/**
	 * @param prev
	 * @param curr
	 * @return
	 */
	CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr);
}
