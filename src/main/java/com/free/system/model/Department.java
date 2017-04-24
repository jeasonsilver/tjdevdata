package com.free.system.model;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 工作部门
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_department") 
public class Department {
	
	@Id
	private Long id = null;// id
	@Column
	private String deptname = null;// 部门名称
	@Column
	private String deptphone = null;// 部门电话
	@Column
	private String deptadress = null;// 部门地址
	@Column
	private String deptfzr = null;// 部门负责人
	@Column
	private String deptlxr = null;// 部门联系人
	@Column
	private Long fid = null;// 父级部门ID
	@Column
	private Long ordercol = null;//排序
	

	private boolean isParent ;//tree 用来显示
	private boolean isUser; // tree里是否显示的是用户
	private List<Department> children = new ArrayList<Department>();
	private String iconSkin;// 树图标
	private String chkStyle;
	
	//---------------------------------
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptphone() {
		return deptphone;
	}
	public void setDeptphone(String deptphone) {
		this.deptphone = deptphone;
	}
	public String getDeptadress() {
		return deptadress;
	}
	public void setDeptadress(String deptadress) {
		this.deptadress = deptadress;
	}
	public String getDeptfzr() {
		return deptfzr;
	}
	public void setDeptfzr(String deptfzr) {
		this.deptfzr = deptfzr;
	}
	public String getDeptlxr() {
		return deptlxr;
	}
	public void setDeptlxr(String deptlxr) {
		this.deptlxr = deptlxr;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean getIsUser() {
		return isUser;
	}
	public void setIsUser(boolean isUser) {
		this.isUser = isUser;
	}
	public List<Department> getChildren() {
		return children;
	}
	public void setChildren(List<Department> children) {
		this.children = children;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public String getChkStyle() {
		return chkStyle;
	}
	public void setChkStyle(String chkStyle) {
		this.chkStyle = chkStyle;
	}
	public Long getOrdercol() {
		return ordercol;
	}
	public void setOrdercol(Long ordercol) {
		this.ordercol = ordercol;
	}
	
}