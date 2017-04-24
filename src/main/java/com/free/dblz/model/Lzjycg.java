package com.free.dblz.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 代表履职--履职信息记录
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_dblz_lzjycg") 
public class Lzjycg {
	
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
	private Long fbrid = null;//发布人id
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
	private Long cjrid = null;//创建人id
	@Column
	private String xgr = null;// 修改人
	@Column
	private String hdzzdwid = null;//活动组织单位id
	@Column
	private String shzt = null;//审核状态
	@Column
	private String ly = null;//来源 1--个人调教 2--单位提交
	@Column
	private String fj = null;//全文
	@Column
	private String filename = null;//全文
	@Column
	private Long contentid = null;// 内容id
	
	@One(target=LzjycgContent.class,field="contentid")
	private LzjycgContent content = null;//内容
	
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
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getLy() {
		return ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getFbrid() {
		return fbrid;
	}
	public void setFbrid(Long fbrid) {
		this.fbrid = fbrid;
	}
	public Long getContentid() {
		return contentid;
	}
	public void setContentid(Long contentid) {
		this.contentid = contentid;
	}
	public LzjycgContent getContent() {
		return content;
	}
	public void setContent(LzjycgContent content) {
		this.content = content;
	}
	public Long getCjrid() {
		return cjrid;
	}
	public void setCjrid(Long cjrid) {
		this.cjrid = cjrid;
	}
	

}