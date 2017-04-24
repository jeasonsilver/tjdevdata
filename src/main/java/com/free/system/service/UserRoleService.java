package com.free.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.free.system.model.UserRole;

@Service
public class UserRoleService {

	@Autowired
	private Dao dao;
	
	public List<UserRole> queryByUserid(long userid){
		return dao.query(UserRole.class, Cnd.where("userid","=",userid));
	}
	
	public void setUserRole(long userid,String roleids){
		//先将用户之前数据删了
		dao.clear(UserRole.class,Cnd.where("userid","=",userid));
		//然后添加新的
		if(StringUtils.isNotBlank(roleids)){
			String[] roles = roleids.split(",");
			for(String roleid : roles){
				if(StringUtils.isNotBlank(roleid)){
					UserRole ur = new UserRole();
					ur.setUserid(userid);
					ur.setRoleid(Integer.valueOf(roleid));
					dao.insert(ur);
				}
			}
		}
	}
}
