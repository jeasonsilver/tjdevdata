package com.free.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 栏目统计数量 VO
 * @ClassName: ModularVO 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 2, 2015 5:28:44 PM 
 * @version V1.0
 *
 */
public class ModularVO implements Serializable{

	private static final long serialVersionUID = -6930257003815145362L; 
	
	/*栏目名称*/
	private String modularName;
	
	/*详细对象  集合*/
	private List<ModularDetailVO> modularDetailList = new ArrayList<ModularDetailVO>();

	public ModularVO(){
		
	}
	
	public ModularVO(String modularName){
		this.modularName = modularName;
	}
	
	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public List<ModularDetailVO> getModularDetailList() {
		return modularDetailList;
	}

	public void setModularDetailList(List<ModularDetailVO> modularDetailList) {
		this.modularDetailList = modularDetailList;
	}
	
}
