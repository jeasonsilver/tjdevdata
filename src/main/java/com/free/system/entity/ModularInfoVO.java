package com.free.system.entity;

import java.io.Serializable;

/**
 * 栏目统计数据 信息对象
 * @ClassName: ModularInfoVO 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 2, 2015 5:52:47 PM 
 * @version V1.0
 *
 */
public class ModularInfoVO  implements Serializable{

	private static final long serialVersionUID = 1155143218589410016L;

	/*季度 【预留】*/
	private Integer quarter;
	
	/*季度名称 【预留】*/
	private String quarterName;
	
	/*统计数量*/
	private Integer count;

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public String getQuarterName() {
		return quarterName;
	}

	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
