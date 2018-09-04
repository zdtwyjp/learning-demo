package org.tech.spring.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.tech.spring.entity.BaseEntity;
import org.tech.spring.entity.vo.DataTablesList;
import org.tech.spring.entity.vo.PaginatedCondition;
import org.tech.spring.entity.vo.PaginatedList;

@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getEntity(Class<T> clazz, Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntities(Class<T> clazz) {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getName()).list();
	}

	@Override
	public <T extends BaseEntity> Serializable createEntity(T entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public <T extends BaseEntity> void deleteEntity(Class<T> clazz,
			Serializable id) {
		sessionFactory.getCurrentSession().delete(getEntity(clazz, id));
	}

	@Override
	public <T extends BaseEntity> void deleteEntities(Class<T> clazz,
			Serializable[] ids) {
		for (Serializable id : ids) {
			sessionFactory.getCurrentSession().delete(getEntity(clazz, id));
		}
	}

	@Override
	public <T extends BaseEntity> void updateEntity(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public <T extends BaseEntity> void mergeEntity(T entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	@Override
	public <T extends BaseEntity> void saveOrUpdateEntity(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getPaginatedList(PaginatedCondition paginatedCondition,
			Class<T> clazz) {
		// PaginatedList paginatedList = new PaginatedList();
		String[] selects = paginatedCondition.getSelects();

		String hql = getQueryString(paginatedCondition);
		String countHql = "select count(*) from "
				+ paginatedCondition.getEntityName()
				+ getFilterQueryString(paginatedCondition);
		int total = 0;
		if (paginatedCondition.isDistinct()) {
			total = sessionFactory.getCurrentSession().createQuery(hql).list()
					.size();
		} else {
			total = ((Number) sessionFactory.getCurrentSession()
					.createQuery(countHql).uniqueResult()).intValue();
		}
		// paginatedList.setTotal(total);

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Iterator<Object[]> it;
		if (paginatedCondition.isDisablePaginated()) { // 禁用分页，返回全部结果
			it = query.list().iterator();
		} else {
			int start = ((paginatedCondition.getPage() - 1) * paginatedCondition
					.getRows());
			it = query.setFirstResult(start)
					.setMaxResults(paginatedCondition.getRows()).iterate();
		}
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

		while (it.hasNext()) {
			Object[] results = it.next();
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i = 0; i < selects.length; i++) {
				row.put(getSelectName(selects[i]), results[i]);
			}
			rows.add(row);
		}
		// paginatedList.setRows(rows);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (t instanceof PaginatedList) {
			PaginatedList pl = (PaginatedList) t;
			pl.setTotal(total);
			pl.setRows(rows);
			return (T) pl;
		} else if (t instanceof DataTablesList) {
			DataTablesList dl = (DataTablesList) t;
			dl.setRecordsFiltered(total);
			dl.setRecordsFiltered(total);
			dl.setData(rows);
			return (T) dl;
		}
		return null;
	}

	private String getFilterQueryString(PaginatedCondition paginatedCondition) {
		List<String> filters = paginatedCondition.getFilters();
		StringBuffer filterBuf = new StringBuffer();
		if (filters != null && filters.size() > 0) {
			filterBuf.append(" where " + filters.get(0));
			for (int i = 1; i < filters.size(); i++) {
				filterBuf.append(" and " + filters.get(i));
			}
		}
		return filterBuf.toString();
	}

	private String getGroupByQueryString(PaginatedCondition paginatedCondition) {
		int[] groupBy = paginatedCondition.getGroupBy();
		if (groupBy != null && groupBy.length > 0) {
			String[] selects = paginatedCondition.getSelects();
			StringBuffer groupByBuf = new StringBuffer();
			groupByBuf.append(" group by " + parseSelectStr(selects[0]));
			for (int i = 1; i < groupBy.length; i++) {
				groupByBuf.append(parseSelectStr("," + selects[i]));
			}
			return groupByBuf.toString();
		} else {
			return "";
		}
	}

	/**
	 * 解析select项字符串，如："p.name as pname" --> "p.name"
	 * 
	 * @param select
	 * @return
	 */
	private String parseSelectStr(String select) {
		int pos = select.indexOf(" as ");
		if (pos > 0) {
			return select.substring(0, pos).trim();
		}
		return select.trim();
	}

	private String getQueryString(PaginatedCondition paginatedCondition) {
		String[] selects = paginatedCondition.getSelects();
		StringBuffer selectBuf = new StringBuffer();
		selectBuf.append("select ");
		if (paginatedCondition.isDistinct()) {
			selectBuf.append("distinct ");
		}
		selectBuf.append(selects[0]);
		for (int i = 1; i < selects.length; i++) {
			selectBuf.append("," + selects[i]);
		}
		String orderBy = getOrderByString(paginatedCondition);
		String hql = selectBuf.toString() + " from "
				+ paginatedCondition.getEntityName()
				+ getFilterQueryString(paginatedCondition)
				+ getGroupByQueryString(paginatedCondition) + orderBy;
		return hql;
	}
	
	private String getOrderByString(PaginatedCondition paginatedCondition){
		String rt = "";
		String[] order = paginatedCondition.getOrder();
		String[] sort = paginatedCondition.getSort();
		if(order == null || sort == null){
			return rt;
		}
		if(order.length != sort.length){
			throw new IllegalArgumentException("order长度和sort长度不等。");
		}
		for(int i=0,len=order.length; i<len; i++){
			if(rt.trim().length() > 0){
				rt += ",";
			}
			rt += sort[i] + " " + order[i];
		}
		if(rt.trim().length() > 0){
			rt = " order by " + rt;
		}
		return rt;
	}

	/**
	 * 获取select项的名称，如："p.name as pname" --> "pname" "p.name   pname" --> "pname"
	 * 
	 * @param select
	 * @return
	 */
	private String getSelectName(String select) {
		String[] arr = select.trim().split(" as ");
		if (arr.length == 2) {
			return arr[1].trim();
		}
		arr = select.trim().split(" ");
		if (arr.length > 1) {
			return arr[arr.length - 1].trim();
		}
		return select;
	}

	@Override
	public <T> T getPaginatedListBySQL(PaginatedCondition paginatedCondition,
			Class<T> clazz) {
		// PaginatedList paginatedList = new PaginatedList();
		String[] selects = paginatedCondition.getSelects();

		String sql = getQueryString(paginatedCondition);
		String countHql = "select count(*) from "
				+ paginatedCondition.getEntityName()
				+ getFilterQueryString(paginatedCondition);
		int total = 0;
		if (paginatedCondition.isDistinct()) {
			total = sessionFactory.getCurrentSession().createQuery(sql).list()
					.size();
		} else {
			total = ((Number) sessionFactory.getCurrentSession()
					.createSQLQuery(countHql).uniqueResult()).intValue();
		}
		// paginatedList.setTotal(total);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		int start = ((paginatedCondition.getPage() - 1) * paginatedCondition
				.getRows());
		List<Object[]> result = query.setFirstResult(start)
				.setMaxResults(paginatedCondition.getRows()).list();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for (Object[] re : result) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i = 0; i < selects.length; i++) {
				row.put(getSelectName(selects[i]), re[i]);
			}
			rows.add(row);
		}
		// paginatedList.setRows(rows);
		// return paginatedList;
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (t instanceof PaginatedList) {
			PaginatedList pl = (PaginatedList) t;
			pl.setTotal(total);
			pl.setRows(rows);
			return (T) pl;
		} else if (t instanceof DataTablesList) {
			DataTablesList dl = (DataTablesList) t;
			dl.setRecordsFiltered(total);
			dl.setRecordsFiltered(total);
			dl.setData(rows);
			return (T) dl;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySQL(String sql, Object[] values) {
		SQLQuery sq = this.getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				sq.setParameter(i, values[i]);
			}
		}
		return sq.list();
	}

	@Override
	public <T extends BaseEntity> List<T> find(String queryString) {
		return this.find(queryString, null);
	}

	@Override
	public <T extends BaseEntity> List<T> find(String queryString, Object value) {
		return this.find(queryString, new Object[] { value });
	}

	@Override
	public <T extends BaseEntity> T findObject(String queryString, Object value) {
		List<T> list = find(queryString, value);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public <T extends BaseEntity> T findObject(String queryString,
			Object[] value) {
		List<T> list = find(queryString, value);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseEntity> List<T> find(String queryString,
			Object[] values) {
		Query query = this.getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

}
