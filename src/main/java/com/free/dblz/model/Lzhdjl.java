package com.free.dblz.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 代表履职--履职信息记录
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_dblz_lzhdjl") 
public class Lzhdjl {
	
	@Id
	private Long id = null;// id
	@Column
	private String lzid = null;// 履职信息ID
	@Column
	private String lzbt = null;// 标题
	@Column
	private String rdname = null;// 人大代表用户名
	@Column
	private String rdrealname = null;// 人大代表姓名
	@Column
	private String lzlx = null;// 履职类型
	@Column
	private String lzrq = null;// 履职日期
	@Column
	private String fbrxm = null;// 发布人姓名
	@Column
	private String lznr = null;// 履职内容
	@Column
	private String xxzt = null;// 信息状态
	@Column
	private String hdzzdw = null;// 活动组织单位
	@Column
	private String lzlb = null;// 履职类别
	@Column
	private Timestamp cjsj = null;// 创建时间
	@Column
	private Timestamp xgsj = null;// 修改时间
	@Column
	private String cjr = null;// 创建人
	@Column
	private String xgr = null;// 修改人
	@Column
	private String hdzzdwid = null;//活动组织单位id
	
	
	//---------------------------------
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLzid() {
		return lzid;
	}
	public void setLzid(String lzid) {
		this.lzid = lzid;
	}
	public String getLzbt() {
		return lzbt;
	}
	public void setLzbt(String lzbt) {
		this.lzbt = lzbt;
	}
	public String getRdname() {
		return rdname;
	}
	public void setRdname(String rdname) {
		this.rdname = rdname;
	}
	public String getRdrealname() {
		return rdrealname;
	}
	public void setRdrealname(String rdrealname) {
		this.rdrealname = rdrealname;
	}
	public String getLzlx() {
		return lzlx;
	}
	public void setLzlx(String lzlx) {
		this.lzlx = lzlx;
	}
	public String getLzrq() {
		return lzrq;
	}
	public void setLzrq(String lzrq) {
		this.lzrq = lzrq;
	}
	public String getFbrxm() {
		return fbrxm;
	}
	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}
	public String getLznr() {
		return lznr;
	}
	public void setLznr(String lznr) {
		this.lznr = lznr;
	}
	public String getXxzt() {
		return xxzt;
	}
	public void setXxzt(String xxzt) {
		this.xxzt = xxzt;
	}
	public String getHdzzdw() {
		return hdzzdw;
	}
	public void setHdzzdw(String hdzzdw) {
		this.hdzzdw = hdzzdw;
	}
	public String getLzlb() {
		return lzlb;
	}
	public void setLzlb(String lzlb) {
		this.lzlb = lzlb;
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
	public String getHdzzdwid() {
		return hdzzdwid;
	}
	public void setHdzzdwid(String hdzzdwid) {
		this.hdzzdwid = hdzzdwid;
	}

}