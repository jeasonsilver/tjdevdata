package com.free.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.web.Servlets;
import com.free.system.model.Role;
import com.free.system.model.RoleAuthorize;
import com.free.system.model.UserRole;
import com.free.system.service.AuthorizeService;
import com.free.system.service.RoleService;
import com.free.system.service.UserRoleService;

/**
 * 角色
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/system/role")
public class RoleController {

	@Resource
	private RoleService service;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private AuthorizeService authorizeService;
	@Resource
	private Dao dao;
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setOrder(pager.ASC);
		pager.setOrderBy("id");
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		
		return service.queryPage(pager);
	}
	
	
	
	
	/*
	 * json
	 */
	@RequestMapping("/ajax_all_list")
	@ResponseBody
	public List allList(HttpServletRequest request){
		return service.query(null);
	}
	
	
	/*
	 * 录入
	 */
	@RequestMapping("/userRole")
	public String userRole(HttpServletRequest request,
			@RequestParam(value="userid") long userid){
		
		List<UserRole> list = userRoleService.queryByUserid(userid);
		StringBuffer userRole = new StringBuffer();
		for(UserRole ur:list){
			userRole.append(ur.getRoleid()+",");
		}
		request.setAttribute("userRole", userRole.toString());
		request.setAttribute("userid", userid);
		
		return "jsp/system/tree-role";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		
		return "jsp/system/input-role";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/inputRoleMenu")
	public String inputRoleMenu(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		
		return "jsp/system/input-roleMenu";	
	}

	/*
	 * 得到role下所有user
	 */
	@RequestMapping("/fetchRoleUsers")
	@ResponseBody
	public List<UserRole> fetchRoleUsers(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		Role role = service.fetchRoleUsers(id);
		return role.getUserRoles();	
	}
	/*
	 * 录入
	 */
	@RequestMapping("/inputRoleAuthorize")
	public String inputRoleAuthorize(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetchRoleAuthorize(id));
		request.setAttribute("auths", authorizeService.query(null));
		
		return "jsp/system/input-roleAuthorize";	
	}
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Role role,
						HttpServletRequest request){
		if (role.getId()==null){
			service.insert(role);
		}else{
			service.update(role);
		}
		return "ok";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/saveRoleUser")
	@ResponseBody
	public String saveRoleUser(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="users",defaultValue="") String users){
		if ("".equals(users))
			return null;
		
		String[] str = users.split(",");
		//先删除后插入
		service.deleteRoleUsers(id);
		service.insertRoleUsers(str,id);
		
		return "ok";	
	}
	/*
	 * 保存角色的菜单
	 */
	@RequestMapping("/saveMenu")
	@ResponseBody
	public String saveMenu(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="menus",defaultValue="") String menus){
		if ("".equals(menus))
			return null;
		
		String[] str = menus.split(",");
		//先删除后插入
		service.deleteRoleMenu(id);
		service.insertRoleMenu(str,id);
		
		return "ok";	
	}
	/*
	 * 保存
	 */
	@RequestMapping("/saveAuthorize")
	@ResponseBody
	public String saveAuthorize(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="auths",defaultValue="") int[] auths){
		if ("".equals(auths))
			return null;
		
		//先删除后插入
		dao.clear(RoleAuthorize.class, Cnd.where("role_id","=",id));
		for (int auth : auths) {
			RoleAuthorize ra = new RoleAuthorize(id,auth);
			dao.insert(ra);
		}
		return "ok";	
	}
	/*
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		service.delete(id);
		
		return "ok";	
	}
	
	/*
	 * 删除
	 */
	@RequestMapping("/chooseUserRole")
	@ResponseBody
	public String chooseUserRole(HttpServletRequest request,
			@RequestParam(value="userid",defaultValue="0") long userid,
			@RequestParam(value="roleids",defaultValue="0") String roleids){
		userRoleService.setUserRole(userid, roleids);
		return "success";	
	}
}