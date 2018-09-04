package org.tech.spring.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.tech.spring.dao.BaseDao;
import org.tech.spring.entity.BaseEntity;
import org.tech.spring.entity.vo.DataTablesList;
import org.tech.spring.entity.vo.PaginatedCondition;
import org.tech.spring.entity.vo.PaginatedList;

/**
 * 系统服务基础类，所有服务类须从本类继承
 */
public class BaseServiceImpl implements BaseService {

	protected BaseDao baseDao;

	public String getModule() {
		String thePackage = this.getClass().getPackage().getName().substring(9); // 去掉包名前的"org.chit."
		return thePackage.substring(0, thePackage.indexOf("."));
	}

	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public <T> T queryEntity(Class<T> clazz, Serializable id) {
		return baseDao.getEntity(clazz, id);
	}

	@Override
	public <T> List<T> queryEntities(Class<T> clazz) {
		return baseDao.getEntities(clazz);
	}

	@Override
	public <T extends BaseEntity> Serializable addEntity(T entity) {
		return baseDao.createEntity(entity);
	}

	@Override
	public <T extends BaseEntity> void updateEntity(T entity) {
		baseDao.updateEntity(entity);
	}

	@Override
	public <T extends BaseEntity> void saveOrUpdateEntityWithNoLog(T entity) {
		baseDao.saveOrUpdateEntity(entity);
	}

	@Override
	public <T extends BaseEntity> void saveOrUpdateEntity(T entity) {
		baseDao.saveOrUpdateEntity(entity);
	}

	@Override
	public <T extends BaseEntity> void mergeEntity(T entity) {
		baseDao.mergeEntity(entity);
	}

	@Override
	public <T extends BaseEntity> void deleteEntity(Class<T> clazz,
			Serializable id) {
		baseDao.deleteEntity(clazz, id);
	}

	@Override
	public <T extends BaseEntity> void deleteEntities(Class<T> clazz,
			Serializable[] ids) {
		baseDao.deleteEntities(clazz, ids);
	}

	@Override
	public PaginatedList QueryPagedData(PaginatedCondition paginatedCondition) {
		return baseDao
				.getPaginatedList(paginatedCondition, PaginatedList.class);
	}

	@Override
	public PaginatedList SQLQueryPagedData(PaginatedCondition paginatedCondition) {
		return baseDao.getPaginatedListBySQL(paginatedCondition,
				PaginatedList.class);
	}

	@Override
	public DataTablesList QueryDataTablesData(
			PaginatedCondition paginatedCondition) {
		return baseDao.getPaginatedList(paginatedCondition,
				DataTablesList.class);
	}

	@Override
	public DataTablesList SQLQueryQueryDataTablesData(
			PaginatedCondition paginatedCondition) {
		return baseDao.getPaginatedListBySQL(paginatedCondition,
				DataTablesList.class);
	}

	@Override
	public void export(String msg) {
	}

	@Override
	public <T extends Object> Set<T> toEntities(Class<T> clazz, int[] ids) {
		if (ids != null && ids.length > 0) {
			Set<T> entities = new HashSet<T>();
			for (int id : ids) {
				T entity = baseDao.getEntity(clazz, id);
				entities.add(entity);
			}
			return entities;
		} else {
			return null;
		}
	}

	@Override
	public <T extends Object> List<T> toListEntities(Class<T> clazz, int[] ids) {
		Set<T> entities = toEntities(clazz, ids);
		if (entities == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		list.addAll(entities);
		return list;
	}

}
