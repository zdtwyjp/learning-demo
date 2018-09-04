package org.tech.spring.dao;

import java.io.Serializable;
import java.util.List;

import org.tech.spring.entity.BaseEntity;
import org.tech.spring.entity.vo.PaginatedCondition;

public interface BaseDao {
	<T> T getEntity(Class<T> clazz, Serializable id);

	<T> List<T> getEntities(Class<T> clazz);

	<T extends BaseEntity> Serializable createEntity(T entity);

	<T extends BaseEntity> void deleteEntity(Class<T> clazz, Serializable id);

	<T extends BaseEntity> void deleteEntities(Class<T> clazz,
			Serializable[] ids);

	<T extends BaseEntity> void updateEntity(T entity);

	<T extends BaseEntity> void mergeEntity(T entity);

	<T extends BaseEntity> void saveOrUpdateEntity(T entity);

	<T> T getPaginatedList(PaginatedCondition paginatedCondition, Class<T> clazz);
	
	<T> T getPaginatedListBySQL(PaginatedCondition paginatedCondition, Class<T> clazz);
	
	/**
	 * SQL查询
	 * 
	 * @param sql
	 * @param values
	 * @return
	 * @author YangJunping
	 */
	List<Object[]> findBySQL(String sql, Object[] values);

	/**
	 * HQL查询方法
	 * 
	 * @param queryString
	 *            hql
	 * @return
	 * @author YangJunping
	 */
	<T extends BaseEntity> List<T> find(String queryString);

	/**
	 * HQL查询方法：带一个参数
	 * 
	 * @param queryString
	 *            hql
	 * @param value
	 *            参数值
	 * @return
	 * @author YangJunping
	 */
	<T extends BaseEntity> List<T> find(String queryString, Object value);

	/**
	 * HQL查询单个对象：带一个参数
	 * 
	 * @param queryString
	 *            hql
	 * @param value
	 *            参数值
	 * @return
	 * @author YangJunping
	 */
	<T extends BaseEntity> T findObject(String queryString, Object value);

	/**
	 * HQL查询单个对象：带多个参数
	 * 
	 * @param queryString
	 *            hql
	 * @param value
	 *            参数数组
	 * @return
	 * @author YangJunping
	 */
	<T extends BaseEntity> T findObject(String queryString, Object[] value);

	/**
	 * HQL查询方法：带多个参数
	 * 
	 * @param queryString
	 *            hql
	 * @param values
	 *            参数数组
	 * @return
	 * @author YangJunping
	 */
	<T extends BaseEntity> List<T> find(String queryString, Object[] values);
	
}
