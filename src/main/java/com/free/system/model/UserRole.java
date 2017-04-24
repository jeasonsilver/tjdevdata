package com.free.system.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 用户角色
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_user_role") 
public class UserRole {
	
	@Id
	private Integer id = null;// 
	@Column("userid") 
	private Long userid = null;// 用户Id
	@Column("roleid") 
	private Integer roleid = null;// 角色id
	
	
	//---------------------------------
	public UserRole() {}
	
	public UserRole(long userid, int roleid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}