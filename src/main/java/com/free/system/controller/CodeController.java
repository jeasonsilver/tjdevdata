package com.free.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.web.Servlets;
import com.free.system.model.Code;
import com.free.system.model.User;
import com.free.system.service.CodeDicService;

/**
 * 用户管理
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/system/code")
public class CodeController {
	@Autowired
	private CodeDicService service;
	
	/*
	 * 得到名称
	 */
	@RequestMapping("/getNames")
	@ResponseBody
	public Map<String, Map> getName(HttpServletRequest request,
			@RequestParam(value="sectionName",defaultValue="") String[] sectionName){
		Map<String, Map>  map  = new HashMap();
		for (String string : sectionName) {
			map.put(string,service.getRealCodeMapBySectionName(string));
		}
		return map;
	}
	/*
	 * 字典列表
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page ,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize){
		
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
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id ){
		Code u =  service.fetch(id);
		request.setAttribute("ob", u);
		return "jsp/system/input-code";	
	}
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Code code,
						HttpServletRequest request){
		if (code.getId()==null){
			service.insert(code);
		}else{
			service.updateIgnoreNull(code);
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
