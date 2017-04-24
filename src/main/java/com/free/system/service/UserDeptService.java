package com.free.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.Const;
import com.free.system.model.Department;
import com.free.system.model.User;
import com.free.system.model.UserDepartment;

@Service
public class UserDeptService {

	
	@Autowired
	private Dao dao;
	
	public List<UserDepartment> find(Condition cnd){
		return dao.query(UserDepartment.class, cnd);
	}
	/**
	 * 批量保存
	 * @param deptid
	 * @param userids
	 */
	public void save_pl(Integer deptid,String userids){
		boolean isDbt = false;
		Department dept = dao.fetch(Department.class,deptid);
		if(dept.getFid().equals(Const.DEPARTMENT_FID_DBT) || dept.getId().equals(Const.DEPARTMENT_FID_DBT)){
			isDbt = true;
		}
		//1--删除中间表中该部门的人员
		dao.delete(dao.query(UserDepartment.class, Cnd.where("deptid","=",deptid)));
		//2--添加中间表改部门的人员--同时要是代表团、就要更新代表标志
		if(StringUtils.isNotBlank(userids)){
			String[] users = userids.split(",");
			for(String userid:users){
				if(StringUtils.isNotBlank(userid)){
					UserDepartment ud = new UserDepartment();
					ud.setDeptid(Long.valueOf(deptid));
					ud.setDeptname(dept.getDeptname());
					ud.setUserid(Long.valueOf(userid));
					dao.insert(ud);
					//更新代表标志
					if(isDbt){
						User user = new User();
						user.setId(Long.valueOf(userid));
						user.setRepresentflag(Const.REPRESENTFLAG_DB);
						dao.updateIgnoreNull(user);
					}
				}
			}
		}
		
	}
}
