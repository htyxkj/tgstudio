package org.business.entity;

import java.util.List;

public class PageInfo<T> {
	private Integer currentPage;//当前页号
	private Integer pageSize;//每页条数
	private Integer totalSize;//总条数
	private Integer totalPage;//总页数
	private T condition;//添加
	private List<T> rows;//查询数据
	
	private String fileUrl;
	private String dowUrl;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public T getCondition() {
		return condition;
	}
	public void setCondition(T condition) {
		this.condition = condition;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotalPage() {
		if(totalSize%pageSize>0)
			return (totalSize/pageSize)+1;
		else
			return totalSize/pageSize;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getDowUrl() {
		return dowUrl;
	}
	public void setDowUrl(String dowUrl) {
		this.dowUrl = dowUrl;
	}
}
