package com.free.system.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 菜单信息
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_menu") 
public class Menu {
	
	@Id
	private Long id = null;// id
	@Column
	private String menuname = null;// 菜单名称
	@Column
	private String url = null;// 菜单url
	@Column
	private String pid = null;// 父级菜单ID
	@Column
	private String sfxs = null;// 是否显示
	@Column
	private String sx = null;// 顺序
	
	private boolean checked ;// 是否选中
	private boolean open ;// 是否打开
	
	//------------------------------
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getSfxs() {
		return sfxs;
	}
	public void setSfxs(String sfxs) {
		this.sfxs = sfxs;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}


}