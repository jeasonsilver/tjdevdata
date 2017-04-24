package com.free.dblz.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 常委会审议
 * @ClassName: Cwhsy 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 2, 2015 4:18:07 PM 
 * @version V1.0
 *
 */
@Table("t_dblz_cwhsy") 
public class Cwhsy {
	
	@Id
	private Long id = null;// id
	@Column
	private String bt = null;// 标题
	@Column
	private String fbrxm = null;// 发布人姓名
	@Column
	private String fbdw = null;// 发布单位
	@Column
	private Long fbdwid = null;//发布单位id
	@Column
	private String fbrq = null;// 发布日期
	@Column
	private String fwl = null;// 访问量
	@Column
	private Timestamp cjsj = null;// 创建时间
	@Column
	private Timestamp xgsj = null;// 修改时间
	@Column
	private String cjr = null;// 创建人
	@Column
	private String xgr = null;// 修改人
	@Column
	private Long djl = null;//点击率
	@Column
	private String filename = null;//原照片名称
	@Column
	private String fj = null;//附件
	@Column
	private Long cjrid = null;//采集人
	@Column
	private String zdflag = null;//置顶标志 1--置顶 2--不置顶
	@Column
	private Timestamp zdtime = null;//置顶时间
	@Column
	private Integer ordercol = null;//排序
	@Column
	private String xxly = null;//信息来源
	
	private CwhsyContent content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getFbrxm() {
		return fbrxm;
	}

	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}

	public String getFbdw() {
		return fbdw;
	}

	public void setFbdw(String fbdw) {
		this.fbdw = fbdw;
	}

	public Long getFbdwid() {
		return fbdwid;
	}

	public void setFbdwid(Long fbdwid) {
		this.fbdwid = fbdwid;
	}

	public String getFbrq() {
		return fbrq;
	}

	public void setFbrq(String fbrq) {
		this.fbrq = fbrq;
	}

	public String getFwl() {
		return fwl;
	}

	public void setFwl(String fwl) {
		this.fwl = fwl;
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

	public Long getDjl() {
		return djl;
	}

	public void setDjl(Long djl) {
		this.djl = djl;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public Long getCjrid() {
		return cjrid;
	}

	public void setCjrid(Long cjrid) {
		this.cjrid = cjrid;
	}

	public CwhsyContent getContent() {
		return content;
	}

	public void setContent(CwhsyContent content) {
		this.content = content;
	}

	public String getZdflag() {
		return zdflag;
	}

	public void setZdflag(String zdflag) {
		this.zdflag = zdflag;
	}

	public Timestamp getZdtime() {
		return zdtime;
	}

	public void setZdtime(Timestamp zdtime) {
		this.zdtime = zdtime;
	}

	public Integer getOrdercol() {
		return ordercol;
	}

	public void setOrdercol(Integer ordercol) {
		this.ordercol = ordercol;
	}

	public String getXxly() {
		return xxly;
	}

	public void setXxly(String xxly) {
		this.xxly = xxly;
	}

	
	
	
	//---------------------------------
	
	
	
	

}