package com.free.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.sql.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.Const;
import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.web.Servlets;
import com.free.system.model.Department;
import com.free.system.model.User;
import com.free.system.model.UserDepartment;
import com.free.system.service.DepartmentService;
import com.free.system.service.UserDeptService;
import com.free.system.service.UserService;
import com.google.gson.Gson;

/**
 * 工作部门
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/system/department")
public class DepartmentController {

	@Resource
	private DepartmentService service;
	@Resource
	private UserDeptService userDeptService;
	@Resource
	private UserService userService;
	
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
		
		return service.queryPage(pager);
	}
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree")
	@ResponseBody
	public List<Department> tree(HttpServletRequest request ){
		
		List<Department> list = service.query(null);
		 return list;
	}
	
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree_zyz")
	@ResponseBody
	public List<Department> tree_zyz(HttpServletRequest request ){
		
		List<Department> list = service.query(Cnd.where("fid","=",Const.DEPARTMENT_FID_ZYZ));
		 return list;
	}
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree_dbt")
	@ResponseBody
	public List<Department> tree_dbt(HttpServletRequest request ){
		
		List<Department> list = service.query(Cnd.where("fid","=",Const.DEPARTMENT_FID_DBT));
		 return list;
	}
	
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree_multi")
	@ResponseBody
	public List<Department> tree_multi(HttpServletRequest request ){
		
		List<Department> list = service.query(null);
		 return list;
	}

	/*
	 * 部门及人员tree
	 */
	@RequestMapping("/ajax_depUserTree")
	@ResponseBody
	public List<Department> depUserTree(HttpServletRequest request ,
			@RequestParam(value="id",defaultValue="0",required=false) int id,
			@RequestParam(value="xm",defaultValue="",required=false) String xm){
		

		List<User> lUser;
		if (xm.length()>0){
			lUser = userService.query(Cnd.where("xm","like",xm));//按条件查询
		}else{
			lUser = userService.query(null);//查所有
		}
		List<Department> lDept;
		if (xm.length()>0 ){
			Criteria cri = Cnd.cri();
			for (User user : lUser) {
				//2014-10-22 modified by lijin 将当前人的部门从关系表中查出
				List<UserDepartment> list = userDeptService.find(Cnd.where("userid","=",user.getId()));
				for(UserDepartment ud:list){
					cri.where().or("id","=", ud.getDeptid());
				}
				
			}
			lDept = service.query(cri);//查询当前人所在的部门
			List<Department> in_res = new ArrayList<Department>();//递归查询的所有部门
			
			//查所有部门放入map 供下面递归查询用
			List<Department> all = service.query(null);
			Map<Long,Department> allMap = new HashMap<Long,Department>();
			Map<Long,Department> selected = new HashMap<Long,Department>();
			for (Department depart : all) {
				allMap.put(depart.getId(), depart);
			}
			
			// 将人所在部门的根级部门也都查出来
			for (int i = 0; i < lDept.size(); i++) {
				Department depart = lDept.get(i);
				in_res.add(depart);
				selected.put(depart.getId(), depart);
				boolean a=true;
				while(a){
					Department dep = allMap.get(depart.getFid());
					if (dep!=null){
						depart =dep;
						selected.put(depart.getId(), depart);
						in_res.add(depart);
					}else{
						a=false;
					}
				}
			}
			lDept = in_res;
			lDept = new ArrayList<Department>();
			Iterator it = selected.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry key = (Entry) it.next();
				lDept.add((Department)key.getValue());
			}
			System.out.println(lDept.size());
			System.out.println(selected.size());
			//System.out.println(new Gson().toJson(in_res));
		}else{
			lDept= service.query(null);
		}
		
		//将所查结果组成组成主子关系返回json  
		// 最后将用户也伪装成部门放在最末节点，因为用户的id有可能和部门id重，所以下面单独处理
		List<Department> list = lDept;
		List<Department> lRes = new ArrayList<Department>();
		Map<Long,Department> map = new HashMap<Long,Department>();
		
		for (Department depart : list) {
			map.put(depart.getId(), depart);
		}
		for (Department depart : list) {
			if(depart.getFid()>0){
				Department dep = map.get(depart.getFid());
				dep.getChildren().add(depart);
			}else{
				lRes.add(depart);
			}
		}

		for (User user : lUser) {
			//2014-10-22 modified by lijin 将当前人的部门从关系表中查出
			List<UserDepartment> udlist = userDeptService.find(Cnd.where("userid","=",user.getId()));
			for(UserDepartment ud:udlist){
				Department dep = map.get(ud.getDeptid());
				if(dep!=null){
					Department uDep = new Department();
					uDep.setDeptname(user.getXm());
					uDep.setId(user.getId());
					uDep.setIconSkin("iconUser");//显示人的图标
					dep.getChildren().add(uDep);
				}
			}
		}
		
		return lRes;
	}
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		
		return "jsp/system/input-department";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Department department,
						HttpServletRequest request){
		if (department.getId()==null){
			service.insert(department);
		}else{
			service.update(department);
		}
		return "ok";	
	}
	
	/*
	 * 检查部门中是否存在人员
	 */
	@RequestMapping("/check")
	@ResponseBody
	public String check(@RequestParam(value="id",defaultValue="0") int deptid,
						HttpServletRequest request){
		List<UserDepartment> list = userDeptService.find(Cnd.where("deptid","=",deptid));
		if(list.size() == 0){
			return "ok";
		}
		return "fail";
	}
	
	/*
	 * 批量保存
	 */
	@RequestMapping("/save_user_dept")
	@ResponseBody
	public String save_user_dept(HttpServletRequest request,
			@RequestParam(value="deptid",defaultValue="0") int deptid,
			@RequestParam(value="userids") String userids){
		
		userDeptService.save_pl(deptid, userids);
		
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