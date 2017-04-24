package com.free.system.model;

import java.sql.Timestamp;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

import com.free.common.utils.TimeUtil;

/**
 * 人大代表信息
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Table("t_system_user") 
public class User {

	@Column
	private Long deptid = null;// 所属部门ID
	@Id
	private Long id = null;// id
	@Column
	private String loginname = null;// 登录名
	@Column
	private String dbzh = null;// 代表证号
	@Column
	private String xm = null;// 姓名
	@Column
	private String password = null;// 密码
	@Column
	private String xb = null;// 性别
	@Column
	private String mz = null;// 民族
	@Column
	private String csrq = null;// 出生年月
	@Column
	private String whcd = null;// 文化程度
	@Column
	private String zzmm = null;// 政治面目
	@Column
	private String dbt = null;// 代表团
	@Column
	private Long dbtid = null;//代表团id
	@Column
	private String wjsdd = null;// 文件送达地
	@Column
	private String jszc = null;// 技术支撑
	@Column
	private String zy = null;// 职业
	@Column
	private String zw = null;// 职务
	@Column
	private String dwmc = null;// 单位名称
	@Column
	private String dwdh = null;// 单位电话
	@Column
	private String dwdz = null;// 单位地址
	@Column
	private String dwmb = null;// 单位邮编
	@Column
	private String dzyz = null;// 电子邮政
	@Column
	private String cz = null;// 传真
	@Column
	private String jtzz = null;// 家庭住址
	@Column
	private String jtyb = null;// 家庭邮编
	@Column
	private String jtdh = null;// 家庭电话
	@Column
	private String yddh = null;// 移动电话
	@Column
	private String sfdc = null;// 是否调出或逝世
	@Column
	private String memo = null;// 备注
	@Column
	private String zp = null;// 照片
	@Column
	private Timestamp cjsj = TimeUtil.getCurrentTimestamp();// 创建时间
	@Column
	private Timestamp xgsj = TimeUtil.getCurrentTimestamp();// 修改时间
	@Column
	private String cjr = null;// 创建人
	@Column
	private String xgr = null;// 修改人
	@Column
	private String sfqy = null;// 是否启用
	@Column 
	private String representflag = null;//代表标志 0--工作人员 1--天津市代表 2--全国代表
	@Column
	private String filename = null;//原照片名称
	@Column
	private String zyz = null;//专业组
	
	@Many(target = UserRole.class, field = "userid")
    private List<UserRole> userRoles;

	@Column
	private String deptname = null;// 所属部门
	@Column
	private String fbdw = null;// 发布单位
	@Column
	private Long fbdwid = null;//发布单位id
	
	private String deptname_group = null;
	private String deptid_group = null;
	private String zyzname_group = null;
	private String zyzid_group = null;
	
	
//---------------------------------------
	
	public Long getDeptid() {
		return deptid;
	}
	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getDbzh() {
		return dbzh;
	}
	public void setDbzh(String dbzh) {
		this.dbzh = dbzh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getWhcd() {
		return whcd;
	}
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public String getDbt() {
		return dbt;
	}
	public void setDbt(String dbt) {
		this.dbt = dbt;
	}
	public String getWjsdd() {
		return wjsdd;
	}
	public void setWjsdd(String wjsdd) {
		this.wjsdd = wjsdd;
	}
	public String getJszc() {
		return jszc;
	}
	public void setJszc(String jszc) {
		this.jszc = jszc;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getDwdh() {
		return dwdh;
	}
	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	public String getDwdz() {
		return dwdz;
	}
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	public String getDwmb() {
		return dwmb;
	}
	public void setDwmb(String dwmb) {
		this.dwmb = dwmb;
	}
	public String getDzyz() {
		return dzyz;
	}
	public void setDzyz(String dzyz) {
		this.dzyz = dzyz;
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = cz;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getJtyb() {
		return jtyb;
	}
	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	public String getJtdh() {
		return jtdh;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	public String getYddh() {
		return yddh;
	}
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	public String getSfdc() {
		return sfdc;
	}
	public void setSfdc(String sfdc) {
		this.sfdc = sfdc;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
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
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getRepresentflag() {
		return representflag;
	}
	public void setRepresentflag(String representflag) {
		this.representflag = representflag;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getZyz() {
		return zyz;
	}
	public void setZyz(String zyz) {
		this.zyz = zyz;
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
	public String getDeptname_group() {
		return deptname_group;
	}
	public void setDeptname_group(String deptname_group) {
		this.deptname_group = deptname_group;
	}
	public String getDeptid_group() {
		return deptid_group;
	}
	public void setDeptid_group(String deptid_group) {
		this.deptid_group = deptid_group;
	}
	public String getZyzname_group() {
		return zyzname_group;
	}
	public void setZyzname_group(String zyzname_group) {
		this.zyzname_group = zyzname_group;
	}
	public String getZyzid_group() {
		return zyzid_group;
	}
	public void setZyzid_group(String zyzid_group) {
		this.zyzid_group = zyzid_group;
	}
	public Long getDbtid() {
		return dbtid;
	}
	public void setDbtid(Long dbtid) {
		this.dbtid = dbtid;
	}
	

}