package com.free.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.Const;
import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.common.utils.TimeUtil;

import com.free.system.model.Department;
import com.free.system.model.User;
import com.free.system.model.UserDepartment;
import com.free.system.model.UserRole;

/**
 * 人大代表信息
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */

@Service
public class UserService extends BaseService {

	@Autowired
	private Dao dao;


	public void create() {
		dao.create(User.class, false);
	}
	public int delete(Integer id) {
		//先删除用户部门关系
		List list = dao.query(UserDepartment.class, Cnd.where("userid","=",id));
		dao.delete(list);
		return dao.delete(User.class,id);
	}

	public User insert(User record) {
		//首先保存用户生成id
		dao.insert(record);
		//添加所属部门
		String deptid_group = record.getDeptid_group();
		if(StringUtils.isNotBlank(deptid_group)){
			String deptname_group = record.getDeptname_group();
			String[] deptid_arr = deptid_group.split(",");
			String[] deptname_arr = deptname_group.split(",");
			for(int i = 0;i < deptid_arr.length;i++){
				if(StringUtils.isNotBlank(deptid_arr[i])){
					UserDepartment ud = new UserDepartment();
					ud.setUserid(record.getId());
					ud.setDeptid(Long.valueOf(deptid_arr[i]));
					ud.setDeptname(deptname_arr[i]);
					//判断是否为代表
					Department dept = dao.fetch(Department.class,Long.valueOf(deptid_arr[i]));
					//将非代表团的部门id置入主表
					if(record.getDeptid() == null && !dept.getFid().equals(Const.DEPARTMENT_FID_DBT) && !dept.getId().equals(Const.DEPARTMENT_FID_DBT)){
						record.setDeptid(Long.valueOf(deptid_arr[i]));
						record.setDeptname(deptname_arr[i]);
					}
					
					if(dept.getFid().equals(Const.DEPARTMENT_FID_DBT) || dept.getId().equals(Const.DEPARTMENT_FID_DBT)){
						//record.setRepresentflag("1");
						record.setDbt(dept.getDeptname());
						record.setDbtid(dept.getId());
					}
					
					dao.insert(ud);
				}
			}
			
			//主表deptid为空，说明是代表，只有代表团，将第一个代表团置入主表
			if(record.getDeptid() == null && StringUtils.isNotBlank(deptid_arr[0])){
				record.setDeptid(Long.valueOf(deptid_arr[0]));
				record.setDeptname(deptname_arr[0]);
			}
			
		}
		
		
		
		//更新数据
		dao.update(record);
		return record;
	}
	
	//删除子表
	public void deleteUserRole(int id) {
		User user = dao.fetch(User.class, id);
		dao.clearLinks(user, "userRoles");
	}

	//插入子表信息
	public void insertUserRole(int[] roles,long id) {
		User user = new User();
		user.setId(id);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (int roleid : roles) {
			userRoles.add(new UserRole(id,roleid));
		}
		user.setUserRoles(userRoles);
		dao.insertLinks(user,"userRoles");
		
	}

	public User fetchUserRoles(Long id) {
		return dao.fetchLinks(dao.fetch(User.class, id), "userRoles");
	}
	
	public User fetch(Long id) {
		return dao.fetch(User.class,id);
	}

	public List<User> query(Condition c){
		return dao.query(User.class,c, null);
	}

	public int update(User record) {
		return dao.update(record);
	}
	
	public int updateIgnoreNull(User record) {
		
		return dao.updateIgnoreNull(record);
	}

	public int updateIgnoreNullWithDept(User record) {
		
		//先删除用户部门关系
		List list = dao.query(UserDepartment.class, Cnd.where("userid","=",record.getId()));
		dao.delete(list);
		
		//首先添加所属部门
		String deptid_group = record.getDeptid_group();
		if(StringUtils.isNotBlank(deptid_group)){
			String deptname_group = record.getDeptname_group();
			String[] deptid_arr = deptid_group.split(",");
			String[] deptname_arr = deptname_group.split(",");
			for(int i = 0;i < deptid_arr.length;i++){
				if(StringUtils.isNotBlank(deptid_arr[i])){
					UserDepartment ud = new UserDepartment();
					ud.setUserid(record.getId());
					ud.setDeptid(Long.valueOf(deptid_arr[i]));
					ud.setDeptname(deptname_arr[i]);
					//只取第一个选
					if(i == 0){
						record.setDeptid(Long.valueOf(deptid_arr[i]));
						record.setDeptname(deptname_arr[i]);
					}
					
					//判断是否为代表
					Department dept = dao.fetch(Department.class,Long.valueOf(deptid_arr[i]));
					if(dept.getFid().equals(Const.DEPARTMENT_FID_DBT) || dept.getId().equals(Const.DEPARTMENT_FID_DBT)){
						//record.setRepresentflag("1");
						record.setDbt(dept.getDeptname());
						record.setDbtid(dept.getId());
					}
					
					dao.insert(ud);
				}
			}
		}
		return dao.updateIgnoreNull(record);
	}
	
	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<User> list = dao.query(User.class, cri, page);
	    page.setRecordCount(dao.count(User.class, cri));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
	public Map<String,Object> queryPage(NewPager page,String deptid){
		Criteria cri = getCriteriaFromPage(page);
		
		List<UserDepartment> udList = dao.query(UserDepartment.class, Cnd.where("deptid","=",deptid));
		long[] ids = new long[udList.size()];
		for(int i=0;i<udList.size();i++){
			ids[i] = udList.get(i).getUserid();
		}
		cri.where().andIn("id", ids);
		//cri.joinParams(en, obj, params, off)
		
	    List<User> list = dao.query(User.class, cri, page);
	    page.setRecordCount(dao.count(User.class, cri));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
	/**
	 * 发送短信 注意是新增时发短信
	 * @param uids id
	 * @param wjmc 内容
	 * @param type 类型 1会议文件
	 */
	public void sendMessage(String uids,String wjmc,int type) {
		if (StringUtils.isEmpty(uids))
			return;
		List<User> list =dao.query(User.class, Cnd.wrap("id in ("+uids+"0) "));
		for (User user : list) {
			if (user.getYddh()!=null && user.getYddh().length()==11){
				//例子： sendMessage.sendSMS("13702004234", "这个是用java发的中文短信");
				if (type==1){
					//sendMessage.sendSMS(user.getYddh(), "您好,天津市人大代表履职服务平台提醒您:有新的会议文件,文件名称："+wjmc);
				}else if (type==2){
					//sendMessage.sendSMS(user.getYddh(), "您好,天津市人大代表履职服务平台提醒您:有新的意见征集,名称为："+wjmc);
				}
			}
		}
	}
	
/*	*//**
	 * 发送短信 注意是新增时发短信
	 * @param uids id
	 * @param wjmc 内容
	 * @param type 类型 1会议文件
	 *//*
	public void sendMessageWithNr(String uids,String message) {
		if (StringUtils.isEmpty(uids))
			return;
		List<User> list =dao.query(User.class, Cnd.wrap("id in ("+uids+"0) "));
		for (User user : list) {
			if (user.getYddh()!=null && user.getYddh().length()==11){
				//System.out.println("发送短信--"+user.getXm()+"--"+user.getYddh()+"--"+message);
				//sendMessage.sendSMS(user.getYddh(),message);
				Message messagebean = new Message();
				messagebean.setYddh(user.getYddh());
				messagebean.setRealname(user.getXm());
				messagebean.setMessage(message);
				messagebean.setFlag("0");
				messagebean.setCjsj(TimeUtil.getCurrentTimestamp());
				dao.insert(messagebean);
			}
		}
	}*/
	
	/**
	 * 提取工作人员总数
	 * @return
	 */
	public int gzryCount(){
		return dao.count(User.class,Cnd.where("flagtemp","<>","1"));
	}
	
	/**
	 * 提取代表总数
	 * @return
	 */
	public int dbCount(){
		return dao.count(User.class,Cnd.wrap("flagtemp = '1' or flagtemp = '2'"));
	}
	
	
	/**
	 * 提取工作人员登录数
	 * @return
	 */
	public int gzry_dlCount(){
		return dao.count(User.class,Cnd.wrap("flagtemp <> '1' and MD5(loginname) <> `password`"));
	}
	
	/**
	 * 提取代表总数
	 * @return
	 */
	public int db_dlCount(){
		return dao.count(User.class,Cnd.wrap("(flagtemp = '1' or flagtemp = '2') and MD5(loginname) <> `password`"));
	}
	
	/**
	 * 市人大工作人员总数
	 * @return
	 */
	public int srdCount(StringBuffer ids){
		//提取市人大下面所有部门id
		return dao.count(User.class,Cnd.wrap(" deptid in ("+ids.toString()+"0) and flagtemp <> '1'"));
	}
	
	/**
	 * 市人大工作人员登录人员
	 * @return
	 */
	public int srd_dlCount(StringBuffer ids){
		//提取市人大下面所有部门id
		return dao.count(User.class,Cnd.wrap(" deptid in ("+ids.toString()+"0) and flagtemp <> '1' and MD5(loginname) <> `password`"));
	}
	
	
	/**
	 * 提取代表已登录信息
	 * @return
	 */
	public List<User> db_ydl(){
		return dao.query(User.class,Cnd.wrap("(flagtemp = '1' or flagtemp = '2') and MD5(loginname) <> `password` order by dbtid"));
	}
	/**
	 * 提取代表未登录信息
	 * @return
	 */
	public List<User> db_wdl(){
		return dao.query(User.class,Cnd.wrap("(flagtemp = '1' or flagtemp = '2') and MD5(loginname) = `password` order by dbtid"));
	}
	/**
	 * 工作人员已登录数
	 * @return
	 */
	public List<User> gzry_ydl(){
		return dao.query(User.class,Cnd.wrap("flagtemp <> '1' and MD5(loginname) <> `password`"));
	}
	
	/**
	 * 工作人员未登录数
	 * @return
	 */
	public List<User> gzry_wdl(){
		return dao.query(User.class,Cnd.wrap("flagtemp <> '1' and MD5(loginname) = `password`"));
	}
	/**
	 * 市人大工作人员已登录数
	 * @return
	 */
	public List<User> srd_ydl(StringBuffer ids){
		return dao.query(User.class,Cnd.wrap("deptid in ("+ids.toString()+"0) and flagtemp <> '1' and MD5(loginname) <> `password`"));
	}
	
	/**
	 * 市人大工作人员未登录数
	 * @return
	 */
	public List<User> srd_wdl(StringBuffer ids){
		return dao.query(User.class,Cnd.wrap("deptid in ("+ids.toString()+"0) and flagtemp <> '1' and MD5(loginname) = `password`"));
	}
	
}
