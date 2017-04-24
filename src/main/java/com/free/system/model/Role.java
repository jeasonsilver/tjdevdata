package com.free.system.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

/**
 * 角色
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_role") 
public class Role {
	
	@Id
	private Integer id = null;// 
	@Column("rolename") 
	private String rolename = null;// 角色
	@Column("description") 
	private String description = null;// 描述

	@Many(target = RoleMenu.class, field = "roleid")
    private List<RoleMenu> roleMenus;
	
	@Many(target = UserRole.class, field = "roleid")
    private List<UserRole> userRoles;
	
	@Many(target = RoleAuthorize.class, field = "role_id")
    private List<RoleAuthorize> roleAuthorizes;
	
	//---------------------------------
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<RoleMenu> getRoleMenus() {
		return roleMenus;
	}
	public void setRoleMenus(List<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public List<RoleAuthorize> getRoleAuthorizes() {
		return roleAuthorizes;
	}
	public void setRoleAuthorizes(List<RoleAuthorize> roleAuthorizes) {
		this.roleAuthorizes = roleAuthorizes;
	}
	



}