package com.free.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.web.Servlets;
import com.free.system.model.Menu;
import com.free.system.service.MenuService;
import com.google.gson.Gson;

/**
 * 菜单信息
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/system/menu")
public class MenuController {

	@Resource
	private MenuService service;
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public QueryResult list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		pager.setOrder(NewPager.ASC);
		pager.setOrderBy("sx");

		return service.queryPage(pager);
	}
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_all")
	@ResponseBody
	public List<Menu> all(HttpServletRequest request,
			@RequestParam(value="pid",defaultValue="",required=false) String pid,
			@RequestParam(value="roleid",required=false) String roleid,
			@RequestParam(value="check",required=false) boolean check ){
		if (check){
			//查当前角色的菜单
			List<Menu> list =service.queryMenusByRole(roleid);
			return list;
		}else{
			//查所有菜单
			List<Menu> list = service.queryMenusByCurrentUserRole(null);
			return list;
		}
	}
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree")
	@ResponseBody
	public List<Menu> ajax_tree(HttpServletRequest request,
			@RequestParam(value="pid",defaultValue="",required=false) String pid,
			@RequestParam(value="roleid",required=false) String roleid,
			@RequestParam(value="check",required=false) boolean check ){
		
		//查所有菜单
		List<Menu> list = service.query(null);
		return list;
	}
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		return "jsp/system/input-menu";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Menu menu,HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") long id){
		if (id>0){
			service.update(menu);
		}else{
			service.insert(menu);
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
	 * 列表json
	 */
	@RequestMapping("/childMenu")
	@ResponseBody
	public List<Menu> childMenu(HttpServletRequest request,
			@RequestParam(value="pid",defaultValue="",required=false) String pid){
		
			//查所有菜单
			List<Menu> list = service.queryChildMenusByCurrentUserRole(pid);
			return list;
	}
	
	
}