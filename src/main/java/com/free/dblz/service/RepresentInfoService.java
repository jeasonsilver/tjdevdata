package com.free.dblz.service;

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
import com.free.system.model.Department;
import com.free.system.model.User;
import com.free.system.model.UserDepartment;
import com.free.system.model.UserRole;

@Service
public class RepresentInfoService extends BaseService{
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
		dao.insert(record);
		//添加专业组
		String zyzid_group = record.getZyzid_group();
		if(StringUtils.isNotBlank(zyzid_group)){
			String zyzname_group = record.getZyzname_group();
			String[] zyzid_arr = zyzid_group.split(",");
			String[] zyzname_arr = zyzname_group.split(",");
			
			for(int i = 0;i < zyzid_arr.length;i++){
				if(StringUtils.isNotBlank(zyzid_arr[i])){
					UserDepartment ud = new UserDepartment();
					ud.setUserid(record.getId());
					ud.setDeptid(Long.valueOf(zyzid_arr[i]));
					ud.setDeptname(zyzname_arr[i]);
					dao.insert(ud);
				}
			}
			
		}
		
		//为用户部门关系表 添加代表团
		Long dbtid = record.getDbtid();
		if(dbtid != null){
			UserDepartment ud = new UserDepartment();
			ud.setUserid(record.getId());
			ud.setDeptname(record.getDbt());
			ud.setDeptid(record.getDbtid());
			dao.insert(ud);
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

	public User fetchUserRoles(Integer id) {
		return dao.fetchLinks(dao.fetch(User.class, id), "userRoles");
	}
	
	public User fetch(Integer id) {
		return dao.fetch(User.class,id);
	}
	
	public List<User> query(Condition c){
		return dao.query(User.class,c, null);
	}
	//为了select2列表准备数据
	public List<User> getUsersForSelect2(){
		return dao.query(User.class,Cnd.where("representflag", "like", "%1%"), null);
	}
	public int update(User record) {
		return dao.update(record);
	}

	public int updateIgnoreNull(User record) {
		
		
		List<UserDepartment> list = dao.query(UserDepartment.class, Cnd.where("userid","=",record.getId()));
		for(UserDepartment ud:list){
			Department dept = dao.fetch(Department.class,ud.getDeptid());
			//先删除用户部门关系中专业组的部门
			if(dept.getFid().equals(Const.DEPARTMENT_FID_ZYZ) || dept.getId().equals(Const.DEPARTMENT_FID_ZYZ)){
				dao.delete(ud);
			}
			//删除用户部门关系表中 代表团
			if(dept.getFid().equals(Const.DEPARTMENT_FID_DBT) || dept.getId().equals(Const.DEPARTMENT_FID_DBT)){
				dao.delete(ud);
			}
		}

		
		
		
		//添加专业组
		String zyzid_group = record.getZyzid_group();
		if(StringUtils.isNotBlank(zyzid_group)){
			String zyzname_group = record.getZyzname_group();
			String[] zyzid_arr = zyzid_group.split(",");
			String[] zyzname_arr = zyzname_group.split(",");
			
			for(int i = 0;i < zyzid_arr.length;i++){
				if(StringUtils.isNotBlank(zyzid_arr[i])){
					UserDepartment ud = new UserDepartment();
					ud.setUserid(record.getId());
					ud.setDeptid(Long.valueOf(zyzid_arr[i]));
					ud.setDeptname(zyzname_arr[i]);
					dao.insert(ud);
				}
			}
			
		}
		
		
		//为用户部门关系表 添加代表团
		Long dbtid = record.getDbtid();
		if(dbtid != null){
			UserDepartment ud = new UserDepartment();
			ud.setUserid(record.getId());
			ud.setDeptname(record.getDbt());
			ud.setDeptid(record.getDbtid());
			dao.insert(ud);
		}
		
		return dao.updateIgnoreNull(record);
	}
	
	public Map<String,Object> queryPage(NewPager page){
//		page.setOrderBy("IF(ISNULL(dbzh),100000000,dbzh)");
//		page.setOrder(page.ASC);
		Criteria cri = getCriteriaFromPage(page);
	    List<User> list = dao.query(User.class, cri, page);
	    page.setRecordCount(dao.count(User.class, cri));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
	public List<User> queryXm(String xm){
		NewPager page = new NewPager();
		page.setPageSize(15);
		page.setPageNumber(1);
		//Criteria cri = getCriteriaFromPage(page);
		Criteria cri = Cnd.cri();
		cri.where().and("representflag","like","%1%").andLike("xm", xm);
		
	    List<User> list = dao.query(User.class, cri, page);
	    return list;
	}
	
	public List<User> queryQgdbXm(String xm){
		NewPager page = new NewPager();
		page.setPageSize(15);
		page.setPageNumber(1);
		//Criteria cri = getCriteriaFromPage(page);
		Criteria cri = Cnd.cri();
		cri.where().and("representflag","like","%2%").andLike("xm", xm);
		
	    List<User> list = dao.query(User.class, cri, page);
	    return list;
	}
	
	public List<User> queryXmNoLike(String xm){
		NewPager page = new NewPager();
		page.setPageSize(15);
		page.setPageNumber(1);
		//Criteria cri = getCriteriaFromPage(page);
		Criteria cri = Cnd.cri();
		cri.where().and("representflag","like","%1%").and("xm","=", xm);
		
	    List<User> list = dao.query(User.class, cri, page);
	    return list;
	}
	
	public User fetchByName(String dbname){
		return dao.fetch(User.class,Cnd.where("xm","=",dbname).and("representflag","like","%1%"));
	}
	
	public User fetchByName(String dbname,String dbzh){
		return dao.fetch(User.class,Cnd.where("xm","=",dbname).and("dbzh","=",dbzh).and("representflag","like","%1%"));
	}
	
}
