package com.voole.leech.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.leech.dao.CTypeLogModelMetaDao;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.shared.exception.SharedException;

@Service
@Transactional
public class CTypeLogModelMetaServiceImpl implements CTypeLogModelMetaService {
	@Autowired
	private CTypeLogModelMetaDao cTypeLogModelMetaDao;

	@Override
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaDao.getLogModelVersionRootNode(versionId);
	}

	@Override
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion logModelVersion) {
		return cTypeLogModelMetaDao.updateLogModelVersion(logModelVersion);
	}

	@Override
	public <T extends CTypeLogModelColumn> T updateLogModelColumn(T column) {
		return cTypeLogModelMetaDao.updateLogModelColumn(column);
	}

	@Override
	public <T extends CTypeLogModelColumn> T saveLogModelColumn(T column) {
		return cTypeLogModelMetaDao.saveLogModelColumn(column);
	}

	@Override
	public void deleteLogModelColumn(Integer id) {
		cTypeLogModelMetaDao.deleteLogModelColumn(id);
	}

	@Override
	public CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr) {
		return cTypeLogModelMetaDao.move(prev, parent, curr);
	}

}
