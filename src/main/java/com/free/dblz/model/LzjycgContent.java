package com.free.dblz.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_dblz_lzjycg_content") 
public class LzjycgContent {
	
	@Id
	private Long id = null;// 
	@Column
	private String lznr = null;//履职内容
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLznr() {
		return lznr;
	}
	public void setLznr(String lznr) {
		this.lznr = lznr;
	}
	
	

}
