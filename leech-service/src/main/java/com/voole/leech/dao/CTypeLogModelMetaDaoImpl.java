package com.voole.leech.dao;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import com.voole.leech.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import com.voole.leech.shared.HbaseVersionChangeUtil;
import com.voole.leech.shared.exception.SharedException;

@Repository
public class CTypeLogModelMetaDaoImpl implements CTypeLogModelMetaDao {
	@Resource(name = "leechSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		String hql = "select r.id from {0} v  inner join v.rootNode as r where v.id =:versionId  ";
		hql = MessageFormat.format(hql,
				CTypeLogModelVersion.class.getSimpleName());
		Integer rid = (Integer) getSession().createQuery(hql)
				.setInteger("versionId", versionId).uniqueResult();

		CTypeLogModelGroupColumn rootNode = (CTypeLogModelGroupColumn) getSession()
				.createCriteria(CTypeLogModelGroupColumn.class)
				.createAlias("columns", "columns", Criteria.LEFT_JOIN)
				.addOrder(Order.asc("columns.pos")).add(Restrictions.idEq(rid))
				.uniqueResult();

		initChildColumns(rootNode);
		return rootNode;
	}

	@SuppressWarnings("unchecked")
	protected void initChildColumns(CTypeLogModelGroupColumn parentNode) {
		List<CTypeLogModelColumn> columns = parentNode.getColumns();
		if (columns == null) {
			return;
		}
		for (CTypeLogModelColumn column : columns) {
			if (column instanceof CTypeLogModelGroupColumn) {
				((CTypeLogModelGroupColumn) column).setColumns(getSession()
						.createCriteria(CTypeLogModelColumn.class)
						.addOrder(Order.asc("pos"))
						.createAlias("groupColumn", "groupColumn")
						.add(Restrictions.eq("groupColumn.id", column.getId()))
						.list());
				initChildColumns((CTypeLogModelGroupColumn) column);
			}
		}
	}

	@Override
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion logModelVersion) {
		CTypeLogModelVersion old = (CTypeLogModelVersion) getSession().load(
				CTypeLogModelVersion.class, logModelVersion.getId());
		HbaseTableVersion oldVersion = old.getRootNode().getHbaseTableVersion();
		HbaseTableVersion newVersion = logModelVersion.getRootNode()
				.getHbaseTableVersion();
		if (HbaseVersionChangeUtil.isChange(oldVersion, newVersion)) {
			if (old.getRootNode().getColumns() != null) {
				for (CTypeLogModelColumn column : old.getRootNode()
						.getColumns()) {
					if (column instanceof CTypeLogModelSimpleColumn) {
						((CTypeLogModelSimpleColumn) column)
								.setHbaseTableColumn(null);
						getSession().update(column);
					}
				}
			}

		}
		getSession().merge(logModelVersion);
		return logModelVersion;
	}

	@Override
	public <T extends CTypeLogModelColumn> T updateLogModelColumn(T column) {
		if (column instanceof CTypeLogModelGroupColumn) {
			checkGroupColumn((CTypeLogModelGroupColumn) column);
			getSession().merge(column);
		} else {
			getSession().update(column);
		}
		return column;
	}

	@Override
	public <T extends CTypeLogModelColumn> T saveLogModelColumn(T column) {
		CTypeLogModelGroupColumn parent = (CTypeLogModelGroupColumn) getSession()
				.load(CTypeLogModelGroupColumn.class,
						column.getGroupColumn().getId());
		int pos = 1;
		if (parent.getColumns() != null) {
			pos = parent.getColumns().size() + 1;
		}
		column.setPos(pos);
		getSession().persist(column);
		return column;
	}

	protected void checkGroupColumn(CTypeLogModelGroupColumn column) {
		CTypeLogModelGroupColumn old = (CTypeLogModelGroupColumn) getSession()
				.load(CTypeLogModelGroupColumn.class, column.getId());

		HbaseTableVersion oldVersion = old.getHbaseTableVersion();
		HbaseTableVersion newVersion = column.getHbaseTableVersion();
		if (HbaseVersionChangeUtil.isChange(oldVersion, newVersion)
				&& old.getColumns() != null) {
			for (CTypeLogModelColumn item : old.getColumns()) {
				if (item instanceof CTypeLogModelSimpleColumn) {
					((CTypeLogModelSimpleColumn) item)
							.setHbaseTableColumn(null);
					getSession().update(item);
				}
			}
		}
	}

	@Override
	public void deleteLogModelColumn(Integer id) {
		CTypeLogModelColumn column = (CTypeLogModelColumn) getSession().load(
				CTypeLogModelColumn.class, id);
		getSession().delete(column);
		fixedIndex(column.getGroupColumn());
	}

	protected void fixedIndex(CTypeLogModelGroupColumn parent) {
		if (parent == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<CTypeLogModelColumn> columns = (List<CTypeLogModelColumn>) getSession()
				.createCriteria(CTypeLogModelColumn.class)
				.add(Restrictions.eq("groupColumn", parent))
				.addOrder(Order.asc("pos")).list();
		if (columns != null) {
			int pos = 1;
			for (CTypeLogModelColumn column : columns) {
				column.setPos(pos);
				getSession().flush();
				pos++;
			}
		}
	}

	@Override
	public CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr) {
		CTypeLogModelGroupColumn oldParent = null;
		curr = (CTypeLogModelColumn) getSession().get(
				CTypeLogModelColumn.class, curr.getId());
		oldParent = curr.getGroupColumn();

		// 添加到新的parent
		CTypeLogModelGroupColumn newParent = (CTypeLogModelGroupColumn) getSession()
				.createCriteria(CTypeLogModelGroupColumn.class)
				.createAlias("columns", "columns", Criteria.LEFT_JOIN)
				.addOrder(Order.asc("columns.pos"))
				.add(Restrictions.idEq(parent.getId())).uniqueResult();

		int prevPos = 0;
		if (prev != null) {
			for (CTypeLogModelColumn column : newParent.getColumns()) {
				if (column.getId().equals(prev.getId())) {
					prevPos = column.getPos();
					break;
				}
			}
		}
		if (newParent.getColumns() == null) {
			newParent.setColumns(new ArrayList<CTypeLogModelColumn>());
		}
		newParent.getColumns().add(prevPos, curr);
		int pos = 1;
		for (CTypeLogModelColumn column : newParent.getColumns()) {
			if (!column.equals(curr)) {
				column.setPos(pos);
			}
			pos++;
		}
		getSession().flush();
		curr.setGroupColumn(newParent);
		curr.setPos(prevPos + 1);
		// 更新tableColumn
		if (curr instanceof CTypeLogModelSimpleColumn
				&& HbaseVersionChangeUtil.isChange(
						oldParent.getHbaseTableVersion(),
						newParent.getHbaseTableVersion())) {
			((CTypeLogModelSimpleColumn) curr).setHbaseTableColumn(null);
		}
		getSession().flush();
		// 修复旧的parent的pos
		fixedIndex(oldParent);
		return curr;
	}
}
