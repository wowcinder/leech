package com.voole.leech.dao.hbasemeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTable;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableColumn;
import com.voole.leech.hbasemeta.shared.entity.base.HbaseTableVersion;
import com.voole.leech.shared.exception.SharedException;
import com.voole.leech.shared.paging.EtlPagingLoadConfigBean;

@Repository
public class HbaseMetaDaoImpl implements HbaseMetaDao {
	@Resource(name = "leechSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public HbaseTable saveHbaseTable(HbaseTable hbaseTable) {
		return save(hbaseTable);
	}

	@Override
	public void deleteHbaseTable(Integer id) {
		delete(HbaseTable.class, id);
	}

	@Override
	public HbaseTable updateHbaseTable(HbaseTable hbaseTable) {
		return update(hbaseTable);
	}

	@Override
	public PagingLoadResult<HbaseTable> pagingHbaseTable(
			EtlPagingLoadConfigBean config) throws SharedException {
		return paging(HbaseTable.class, config);
	}

	@Override
	public HbaseTableVersion saveHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) {
		getSession().save(hbaseTableVersion);
		return hbaseTableVersion;
	}

	@Override
	public void deleteHbaseTableVersion(Integer id) {
		delete(HbaseTableVersion.class, id);
	}

	@Override
	public HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) {
		return update(hbaseTableVersion);
	}

	@Override
	public PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException {
		return paging(HbaseTableVersion.class, config);
	}

	@Override
	public HbaseTableColumn saveHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) {
		getSession().save(hbaseTableColumn);
		return hbaseTableColumn;
	}

	@Override
	public void deleteHbaseTableColumn(Integer id) {
		delete(HbaseTableColumn.class, id);
	}

	@Override
	public HbaseTableColumn updateHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) {
		return update(hbaseTableColumn);
	}

	@Override
	public PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException {
		return paging(HbaseTableColumn.class, config);
	}

	public <T> T save(T t) {
		getSession().save(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	protected <T> void delete(Class<T> clazz, Integer id) {
		T t = (T) getSession().load(clazz, id);
		getSession().delete(t);
	}

	protected <T> T update(T t) {
		getSession().update(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	protected <T> PagingLoadResult<T> paging(Class<T> clazz,
			EtlPagingLoadConfigBean config) {
		PagingLoadResultBean<T> pr = new PagingLoadResultBean<T>();
		pr.setOffset(config.getOffset());

		Criteria criteria = getSession().createCriteria(clazz);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setTotalLength((int) rowCount);

		criteria.setProjection(null);
		criteria.setFirstResult(config.getOffset());
		criteria.setMaxResults(config.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		pr.setData(criteria.list());

		return pr;
	}

	@Override
	public <T> void delete(Class<T> clazz, List<Integer> ids) {
		for (Integer id : ids) {
			delete(clazz, id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HbaseTableColumn> getColumnsByVersionId(Integer id) {
		return getSession().createCriteria(HbaseTableColumn.class)
				.createAlias("version", "version")
				.add(Restrictions.eq("version.id", id)).list();
	}

	@Override
	public List<HbaseTableColumn> getTableAllColumns(String table,
			String[] versions) throws SharedException,
			ConstraintViolationException {
		Session s = getSession();
		Criteria criteria = s
				.createCriteria(HbaseTableColumn.class)
				.createAlias("version", "v")
				.createAlias("v.table", "t")
				.add(Restrictions.eq("t.name", table))
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.desc("v.version")).addOrder(Order.asc("pos"));
		if (versions != null && versions.length > 0) {
			criteria.add(Restrictions.in("v.version", versions));
		}
		@SuppressWarnings("unchecked")
		List<HbaseTableColumn> list = criteria.list();
		List<HbaseTableColumn> result = new ArrayList<HbaseTableColumn>();
		Set<String> names = new HashSet<String>();
		for (HbaseTableColumn column : list) {
			if (!names.contains(column.getName())) {
				result.add(column);
				names.add(column.getName());
			}
		}

		return result;
	}

	@Override
	public Map<String, Set<HbaseTableColumn>> getMetaForQuery(String tableName,
			String[] versions) {
		Map<String, Set<HbaseTableColumn>> map = new HashMap<String, Set<HbaseTableColumn>>();
		Session s = getSession();
		Criteria criteria = s
				.createCriteria(HbaseTableVersion.class)
				.createAlias("table", "t")
				.add(Restrictions.eq("t.name", tableName))
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		if (versions == null || versions.length == 0) {// 所有
		} else {
			criteria.add(Restrictions.in("version", versions));
		}
		@SuppressWarnings("unchecked")
		List<HbaseTableVersion> tableVersions = criteria.list();
		for (HbaseTableVersion hbaseTableVersion : tableVersions) {
			Set<HbaseTableColumn> set = new HashSet<HbaseTableColumn>();
			set.addAll(hbaseTableVersion.getColumns());
			map.put(hbaseTableVersion.getVersion(), set);
		}

		return map;
	}

}
