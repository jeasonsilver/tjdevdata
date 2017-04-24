package com.free.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_system_codedic")
public class Code implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6451042982273924032L;
	@Id
	private	Integer id = null;
	@Column
	private String section = null; // 全局类型代码
	@Column
	private String sectionname = null; // 全局类型名称
	@Column
	private String code = null;// 局部类型代码
	@Column
	private String codename = null;// 局部类型名称
	@Column
	private String memo = null;// 说明
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
