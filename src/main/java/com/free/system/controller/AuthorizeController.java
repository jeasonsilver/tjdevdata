package com.free.system.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.web.Servlets;
import com.free.system.model.Authorize;
import com.free.system.service.AuthorizeService;

/**
 * 
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/system/authorize")
public class AuthorizeController {

	@Resource
	private AuthorizeService service;
	
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
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		pager.setOrder(NewPager.ASC);
		pager.setOrderBy("sx");
		
		
		return service.queryPage(pager);
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		
		return "jsp/system/input-authorize";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Authorize authorize,
						HttpServletRequest request){
		if (authorize.getId()==null){
			service.insert(authorize);
		}else{
			service.update(authorize);
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
}