package org.tech.spring.entity.vo;

import java.util.List;
import java.util.Map;

public class DataTablesList {

	private int draw;// 请求次数

	private int recordsTotal;// 总记录数

	private int recordsFiltered;// 过滤后总记录数

	private List<Map<String, Object>> data;// 返回数据

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

}
