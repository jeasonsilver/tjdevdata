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
@Table("t_system_role_menu") 
public class RoleMenu {
	
	@Id
	private Integer id = null;// 
	@Column("roleid") 
	private Integer roleid = null;// 角色ID
	@Column("menuid") 
	private Integer menuid = null;// 菜单ID
	
	
	//---------------------------------
	public RoleMenu() {}
	
	public RoleMenu(int roleid, int menuid) {
		super();
		this.roleid = roleid;
		this.menuid = menuid;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

}