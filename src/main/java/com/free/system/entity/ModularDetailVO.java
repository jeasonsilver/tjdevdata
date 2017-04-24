package com.free.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 详细信息对象
 * @ClassName: ModularDetailVO 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 2, 2015 5:36:50 PM 
 * @version V1.0
 *
 */
public class ModularDetailVO implements Serializable{

	private static final long serialVersionUID = 4633979183881249284L;
	
	/*页面名称*/
	private String pageName;
	/*合计次数*/
	private Integer totalCount;
	
	/*页面集合才*/
	private List<ModularInfoVO> modularInfoList = new ArrayList<ModularInfoVO>();
	
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<ModularInfoVO> getModularInfoList() {
		return modularInfoList;
	}
	public void setModularInfoList(List<ModularInfoVO> modularInfoList) {
		this.modularInfoList = modularInfoList;
	}
	
}
