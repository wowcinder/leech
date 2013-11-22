package com.voole.leech.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.leech.dao.JsonLogModelMetaDao;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import com.voole.leech.shared.exception.SharedException;

@Service
@Transactional
public class JsonLogModelMetaServiceImpl implements JsonLogModelMetaService {
	@Autowired
	private JsonLogModelMetaDao logModelMetaDao;

	@Override
	public JsonLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return logModelMetaDao.getLogModelVersionRootNode(versionId);
	}

	@Override
	public JsonLogModelVersion updateLogModelVersion(
			JsonLogModelVersion logModelVersion) {
		return logModelMetaDao.updateLogModelVersion(logModelVersion);
	}

	@Override
	public <T extends JsonLogModelColumn> T updateLogModelColumn(T column) {
		return logModelMetaDao.updateLogModelColumn(column);
	}

	@Override
	public <T extends JsonLogModelColumn> T saveLogModelColumn(T column) {
		return logModelMetaDao.saveLogModelColumn(column);
	}

	@Override
	public void deleteLogModelColumn(Integer id) {
		logModelMetaDao.deleteLogModelColumn(id);
	}


}
