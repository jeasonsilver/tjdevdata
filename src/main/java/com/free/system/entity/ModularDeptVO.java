package com.free.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 按部门统计模块记录数量 VO
 * @ClassName: ModularDeptVO 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 3, 2015 10:27:00 AM 
 * @version V1.0
 *
 */
public class ModularDeptVO implements Serializable{

	private static final long serialVersionUID = -7370359927502280721L;

	/*部门名称*/
	private String deptName;
	
	/*栏目VO对象*/
	private List<ModularVO> modularList = new ArrayList<ModularVO>();

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<ModularVO> getModularList() {
		return modularList;
	}

	public void setModularList(List<ModularVO> modularList) {
		this.modularList = modularList;
	}
	
}
