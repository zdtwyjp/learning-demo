package org.tech.spring.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class PaginatedCondition {
	
	/**
	 * 是否禁用分页（返回全部结果，注意使用时可能造成的内存安全性问题）
	 */
	private boolean disablePaginated = false;
	
	/**
	 * 查询列（hql select内容）
	 */
	private String[] selects;
	
	/**
	 * 分组（group by）内容（从selects中选择一个或者多个元素的数组序号，0表示第1列）
	 */
	private int[] groupBy;

	/**
	 * 被查询实体名（类名）
	 */
	private String entityName;
	
	/**
	 * 当前页（从1开始）
	 */
	private int page = 1;

	/**
	 * 分页的行数
	 */
	private int rows = 20;

	/**
	 * 排序字段（必须和selects其中的一个值）
	 */
	private String[] sort;

	/**
	 * 排序方向（'asc'或'desc'）
	 */
	private String[] order;
	
	public static final String ORDER_ASC = "asc";
	
	public static final String ORDER_DESC = "desc";

	/**
	 * 过滤条件（每项为一个独立的查询条件，多项用and连接）
	 */
	private List<String> filters;
	
	/**
	 * 查询记录去重
	 */
	private boolean distinct;
	
	/**
	 * 获取返回不分页的查询条件实例
	 * @param entityName
	 * @param selects
	 * @param order
	 * @param sort
	 * @return
	 */
	public static PaginatedCondition getDisablePaginatedCondition(String entityName, String[] selects, String[] filters, String sort, String order){
		PaginatedCondition  paginatedCondition = new PaginatedCondition();
		paginatedCondition.setEntityName(entityName);
		paginatedCondition.setSelects(selects);
		paginatedCondition.setOrder(new String[]{order});
		paginatedCondition.setSort(new String[]{sort});
		
		if (filters != null && filters.length > 0) {
			List<String> filtersList = new ArrayList<String>();
			for(String filter: filters){
				filtersList.add(filter);
			}
			paginatedCondition.setFilters(filtersList);
		}
		
		paginatedCondition.setDisablePaginated(true);
		
		return paginatedCondition;
	}

	public String[] getSelects() {
		return selects;
	}

	public void setSelects(String[] selects) {
		this.selects = selects;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}	

	public String[] getSort() {
		return sort;
	}

	public void setSort(String[] sort) {
		this.sort = sort;
	}

	public String[] getOrder() {
		return order;
	}

	public void setOrder(String[] order) {
		this.order = order;
	}

	public List<String> getFilters() {
		return filters;
	}

	public void setFilters(List<String> filters) {
		this.filters = filters;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public int getFirstRow() {
		return (page - 1) * rows;
	}

	public boolean isDisablePaginated() {
		return disablePaginated;
	}

	public void setDisablePaginated(boolean disablePaginated) {
		this.disablePaginated = disablePaginated;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public int[] getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(int[] groupBy) {
		this.groupBy = groupBy;
	}
	
}
