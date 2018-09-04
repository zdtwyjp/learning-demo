package org.tech.spring.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.tech.spring.entity.BaseEntity;
import org.tech.spring.entity.vo.DataTablesList;
import org.tech.spring.entity.vo.PaginatedCondition;
import org.tech.spring.entity.vo.PaginatedList;

/**
 * 系统服务基础接口，所有系统服务接口须从本接口继承
 *
 */
public interface BaseService {
	/**
	 * 根据实体对象ID查询对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	<T extends Object> T queryEntity(Class<T> clazz, Serializable id);
	
	/**
	 * 查询所有实体
	 * 
	 * @param clazz
	 * @return
	 */
	<T extends Object> List<T> queryEntities(Class<T> clazz);

	/**
	 * 新增实体
	 * 
	 * @param <T>
	 * @param entity
	 */
	<T extends BaseEntity> Serializable addEntity(T entity);
	
	/**
	 * 更新实体
	 * 
	 * @param <T>
	 * @param entity
	 */
	<T extends BaseEntity> void updateEntity(T entity);
	
	/**
	 * 保存或者更新实体
	 * 
	 * @param <T>
	 * @param entity
	 */
	<T extends BaseEntity> void saveOrUpdateEntityWithNoLog(T entity);
	
	/**
	 * 保存实体（新增或者更新）
	 * 
	 * @param <T>
	 * @param entity
	 */
	<T extends BaseEntity> void saveOrUpdateEntity(T entity);

	/**
	 * 合并实体（实体合并后仍为游离态）
	 * @param entity
	 */
	<T extends BaseEntity> void mergeEntity(T entity);
	/**
	 * 删除实体
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 */
	<T extends BaseEntity> void deleteEntity(Class<T> clazz, Serializable id);

	/**
	 * 删除多个实体
	 * 
	 * @param <T>
	 * @param clazz
	 * @param ids
	 */
	<T extends BaseEntity> void deleteEntities(Class<T> clazz, Serializable[] ids);
	
	/**
	 * 查询分页数据
	 * 
	 * @param paginatedCondition
	 * @return
	 */
	PaginatedList QueryPagedData(PaginatedCondition paginatedCondition);
	
	/**
	 * SQL查询分页数据 
	 * @param paginatedCondition
	 * @return
	 */
	PaginatedList SQLQueryPagedData(PaginatedCondition paginatedCondition);
	
	/**
	 * 查询分页数据
	 * 
	 * @param paginatedCondition
	 * @return
	 */
	DataTablesList QueryDataTablesData(PaginatedCondition paginatedCondition);
	
	/**
	 * 查询分页数据
	 * 
	 * @param paginatedCondition
	 * @return
	 */
	DataTablesList SQLQueryQueryDataTablesData(PaginatedCondition paginatedCondition);
	
	void export(String msg);
	
	/**
	 * 转换实体
	 * @param clazz
	 * @param ids
	 * @return
	 */
	<T extends Object> Set<T> toEntities(Class<T> clazz, int[] ids);
	
	<T extends Object> List<T> toListEntities(Class<T> clazz, int[] ids);
}
