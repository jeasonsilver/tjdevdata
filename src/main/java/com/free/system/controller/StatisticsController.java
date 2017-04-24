package com.free.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.system.model.Department;
import com.free.system.service.DepartmentService;
import com.free.system.service.UserService;


@Controller
@RequestMapping(value="/system/stat")
public class StatisticsController {

	@Autowired
	private UserService service;
	@Autowired
	private DepartmentService deptService;
	
	
	@RequestMapping("/static")
    public String loginStat(HttpServletRequest request, HttpServletResponse response) {
		//提取市人大下面所有department
		StringBuffer ids = new StringBuffer();
		ids.append("110000");
		ids.append(",");
		getChildDepartment("110000",ids);
		request.setAttribute("dbcount", service.dbCount());//代表总数
		request.setAttribute("dbdlcount", service.db_dlCount());//代表登录总数
		request.setAttribute("srdcount", service.srdCount(ids));
		request.setAttribute("srddlcount", service.srd_dlCount(ids));
		request.setAttribute("gzrycount", service.gzryCount());
		request.setAttribute("gzrydlcount", service.gzry_dlCount());
		return "jsp/system/statistics";
	}
	
	@RequestMapping("/dbStat")
    public String dbStat(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("db_ydl", service.db_ydl());
		request.setAttribute("db_wdl", service.db_wdl());
		return "jsp/system/dbStat";
	}
	
	private void getChildDepartment(String fid,StringBuffer result){
		List<Department> list = deptService.query(Cnd.where("fid","=",fid));
		for(Department dept:list){
			result.append(dept.getId());
			result.append(",");
			//递归
			getChildDepartment(dept.getId().toString(),result);
		}
	}
	
	@RequestMapping("/gzryStat")
    public String gzryStat(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("gzry_ydl", service.gzry_ydl());
		request.setAttribute("gzry_wdl", service.gzry_wdl());
		return "jsp/system/gzryStat";
	}
	
	@RequestMapping("/srdStat")
    public String srdStat(HttpServletRequest request, HttpServletResponse response) {
		//提取市人大下面所有department
		StringBuffer ids = new StringBuffer();
		ids.append("110000");
		ids.append(",");
		getChildDepartment("110000",ids);
		
		request.setAttribute("gzry_ydl", service.srd_ydl(ids));
		request.setAttribute("gzry_wdl", service.srd_wdl(ids));
		return "jsp/system/gzryStat";
	}
}
