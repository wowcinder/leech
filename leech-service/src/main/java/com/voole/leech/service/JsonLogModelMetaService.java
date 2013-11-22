package com.voole.leech.service;

import javax.validation.ConstraintViolationException;

import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.shared.exception.SharedException;

public interface JsonLogModelMetaService {
	JsonLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;

	JsonLogModelVersion updateLogModelVersion(
			JsonLogModelVersion logModelVersion);

	<T extends JsonLogModelColumn> T updateLogModelColumn(T column);

	<T extends JsonLogModelColumn> T saveLogModelColumn(T column);

	public void deleteLogModelColumn(Integer id);
}
