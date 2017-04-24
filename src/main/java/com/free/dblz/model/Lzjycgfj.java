package com.free.dblz.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_dblz_lzjycg_fj") 
public class Lzjycgfj {
	
	@Id
	private Long id = null;// id
	private Long fid = null;// 父id
	private String filename = null;// 附件名称
	private String fjlx = null;// 附件类型
	private String fjdx = null;// 附件大小
	private String fileurl = null;// 附件路径
	private String xzcs = null;// 下载次数
	private String fjzt = null;// 附件状态
	private Timestamp cjsj = null;// 创建时间
	private Timestamp xgsj = null;// 修改时间
	private String cjr = null;// 创建人
	private String xgr = null;// 修改人
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFjlx() {
		return fjlx;
	}
	public void setFjlx(String fjlx) {
		this.fjlx = fjlx;
	}
	public String getFjdx() {
		return fjdx;
	}
	public void setFjdx(String fjdx) {
		this.fjdx = fjdx;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getXzcs() {
		return xzcs;
	}
	public void setXzcs(String xzcs) {
		this.xzcs = xzcs;
	}
	public String getFjzt() {
		return fjzt;
	}
	public void setFjzt(String fjzt) {
		this.fjzt = fjzt;
	}
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}
	public Timestamp getXgsj() {
		return xgsj;
	}
	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getXgr() {
		return xgr;
	}
	public void setXgr(String xgr) {
		this.xgr = xgr;
	}
}
