package com.free.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_authorize") 
public class Authorize {
	
	@Id
	private Long id = null;// 
	@Column//("module") 
	private String module = null;// 模块
	@Column//("description") 
	private String description = null;// 描述
	@Column//("sx") 
	private String sx = null;// 顺序
	
	
	//---------------------------------
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}

}