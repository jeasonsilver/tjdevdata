package com.free.common.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author qs
 *
 */
public class PageQuery {
	public static int DEFAULT_PAGE_SIZE = 20;
	private int pageNumber;
    private int pageSize;
    private Map<String, Object> params;
    
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getPageSize() {
		return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	//向参数里加值
	public void setItem(String key,Object value) {
		if (this.params==null){
			setParams(new HashMap());
		}
		this.params.put(key, value);
	}
}
