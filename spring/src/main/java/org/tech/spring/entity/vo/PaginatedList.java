package org.tech.spring.entity.vo;

import java.util.List;
import java.util.Map;

public class PaginatedList {
	/**
	 * 满足查询条件的总行数
	 */
	private long total;

	/**
	 * 总记录数据（表格中所有行数）
	 */
	private long max;

	/**
	 * 数据列表
	 */
	private List<Map<String, Object>> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

}
