package com.free.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 角色权限
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_role_authorize") 
public class RoleAuthorize {
	
	@Id
	private Integer id = null;// 
	@Column("role_id") 
	private Long role_id = null;// 角色Id
	@Column("auth_id") 
	private Long auth_id = null;// 权限id
	
	
	//---------------------------------
	public RoleAuthorize() {}
	
	public RoleAuthorize(long role_id, long auth_id) {
		super();
		this.role_id = role_id;
		this.auth_id = auth_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public Long getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(Long auth_id) {
		this.auth_id = auth_id;
	}


}