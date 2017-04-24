package com.free.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.service.BaseService;
import com.free.common.utils.NewPager;
import com.free.system.model.Role;
import com.free.system.model.RoleMenu;
import com.free.system.model.User;
import com.free.system.model.UserRole;

/**
 * 角色
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */

@Service
public class RoleService extends BaseService{
	
	@Autowired
	private Dao dao;

	public int delete(Integer id) {
		return dao.delete(Role.class,id);
	}

	public Role insert(Role record) {
		return dao.insert(record);
	}

	public Role fetch(Integer id) {
		return dao.fetch(Role.class,id);
	}

	public int updateIgnoreNull(Role record) {
		return dao.updateIgnoreNull(record);
	}

	public int update(Role record) {
		return dao.update(record);
	}

	public List<Role> query(Condition c){
		return dao.query(Role.class,c, null);
	}
	
	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Role> list = dao.query(Role.class, cri, page);
	    page.setRecordCount(dao.count(Role.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		
	    return map;
	}
	//删除子表
	public void deleteRoleMenu(int id) {
		Role role = dao.fetch(Role.class, id);
		dao.clearLinks(role, "roleMenus");
		
	}
	//删除子表
	public void deleteRoleUsers(int id) {
		Role role = dao.fetch(Role.class, id);
		dao.clearLinks(role, "userRoles");
		
	}
	//插入子表信息
	public void insertRoleMenu(String[] menus, int id) {
		Role role = new Role();
		role.setId(id);
		List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
		for (String menuid : menus) {
			roleMenus.add(new RoleMenu(id,Integer.parseInt(menuid)));
		}
		role.setRoleMenus(roleMenus);
		dao.insertLinks(role,"roleMenus");
	}
	//插入子表信息
	public void insertRoleUsers(String[] users, int id) {
		Role role = new Role();
		role.setId(id);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (String uid : users) {
			userRoles.add(new UserRole(Integer.parseInt(uid),id));
		}
		role.setUserRoles(userRoles);
		dao.insertLinks(role,"userRoles");
	}
	public Role fetchRoleUsers(Integer id) {
		return dao.fetchLinks(dao.fetch(Role.class, id), "userRoles");
	}

	public Role fetchRoleAuthorize(Integer id) {
		return dao.fetchLinks(dao.fetch(Role.class, id), "roleAuthorizes");
	}
}
