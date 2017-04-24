package com.free.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.service.MonitorRealm.Principal;
import com.free.common.utils.Encryption;
import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.UserUtils;

import com.free.common.utils.web.Servlets;
import com.free.system.model.Department;

import com.free.system.model.User;
import com.free.system.model.UserDepartment;
import com.free.system.service.DepartmentService;

import com.free.system.service.RoleService;
import com.free.system.service.UserDeptService;
import com.free.system.service.UserService;

/**
 * 用户管理
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/system/user")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private UserDeptService userDeptService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private Dao dao;
	
	
	/*
	 * 用户列表
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page ,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		if(StringUtils.isNotBlank(request.getParameter("deptid")) && StringUtils.isNotBlank(request.getParameter("deptname"))){
			return service.queryPage(pager,request.getParameter("deptid"));
		}
		return service.queryPage(pager);
		
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") long id ){
		User u =  service.fetch(id);
		List<UserDepartment> list = userDeptService.find(Cnd.where("userid","=",u.getId()));
		StringBuffer deptname_group = new StringBuffer();
		StringBuffer deptid_group = new StringBuffer();
		for(UserDepartment ud : list){
			deptname_group.append(ud.getDeptname());
			deptname_group.append(",");
			deptid_group.append(ud.getDeptid());
			deptid_group.append(",");
		}
		u.setDeptname_group(deptname_group.toString());
		u.setDeptid_group(deptid_group.toString());
		request.setAttribute("ob", u);
		List<Department> l = departmentService.query(Cnd.where("id","=",u.getDeptid()));
		if(l.size()>0){
			u.setDeptname(l.get(0).getDeptname());
		}
		return "jsp/system/input-user";	
	}

	/*
	 * 本人信息
	 */
	@RequestMapping("/inputMy")
	public String input(HttpServletRequest request ){
		
	User u =  service.fetch(UserUtils.getUser().getId());
//		request.setAttribute("ob", u);
//		List<Department> l = departmentService.query(Cnd.where("id","=",u.getDeptid()));
//		if(l.size()>0){
//			u.setDeptname(l.get(0).getDeptname());
//		}
//		request.setAttribute("readonly", true);
//		return "jsp/system/input-user";	
		return "redirect:/dblz/representinfo/view.do?id="+u.getId();
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/inputChangePwd")
	public String inputChangePwd(HttpServletRequest request ){
		User u =  service.fetch(UserUtils.getUser().getId());
		request.setAttribute("ob", u);
		return "jsp/system/input-user-changePwd";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/inputLoginName")
	public String inputChangeLoginName(HttpServletRequest request ){
		User u =  service.fetch(UserUtils.getUser().getId());
		request.setAttribute("ob", u);
		return "jsp/system/input-user-changeLoginName";	
	}
	/*
	 * 录入
	 */
	@RequestMapping("/forgetPwd")
	@ResponseBody
	public String forgetPwd(HttpServletRequest request ,
			@RequestParam(value="xm",defaultValue="") String xm ,
			@RequestParam(value="yddh",defaultValue="") String yddh ){
		if (StringUtils.isNotBlank(xm) && StringUtils.isNotBlank(yddh)){
			//调手机接口，发送密码
			User u =  dao.fetch(User.class, Cnd.where("xm", "=", xm).and("yddh","=",yddh));
			if(u == null){
				return "nouser";
			}else{
				u.setPassword(Encryption.hashToMD5(u.getLoginname()));
				dao.updateIgnoreNull(u);
				
			/*	Message message = new Message();
				message.setYddh(u.getYddh());
				message.setMessage(u.getXm()+"您好！您在《天津市人大代表履职服务平台》的用户名为："+u.getLoginname()+",密码重置为："+u.getLoginname()+",请您尽快登陆平台后修改密码！");
				message.setFlag("0");
				message.setCjsj(TimeUtil.getCurrentTimestamp());
				dao.insert(message);*/
				//sendMessage.sendSMS(messageService, message);
				return "ok";
				
			}
		}	
		return "fail";	
	}
	/*
	 * 录入
	 */
	@RequestMapping("/changePwd")
	@ResponseBody
	public String changePwd(User user,
			@RequestParam(value="oldpassword",defaultValue="") String oldpassword ,
			HttpServletRequest request ){
		String pwd = UserUtils.getUser().getPassword();
		if (Encryption.hashToMD5(oldpassword).equals(pwd)){
			user.setPassword(Encryption.hashToMD5(user.getPassword()));
			service.updateIgnoreNull(user);
			//重新注册用户
			Subject currentUser = SecurityUtils.getSubject();
			User user_current = (User)currentUser.getSession().getAttribute("currentUser");
			user_current.setPassword(user.getPassword());
		}else{
			return "fail";
		}
		return "ok";	
	}
	
	
	/*
	 * 录入
	 */
	@RequestMapping("/changeLoginName")
	@ResponseBody
	public String changeLoginName(User user,
			@RequestParam(value="oldpassword",defaultValue="") String oldpassword ,
			HttpServletRequest request ){
		String pwd = UserUtils.getUser().getPassword();
		if (Encryption.hashToMD5(oldpassword).equals(pwd)){
			service.updateIgnoreNull(user);
			//重新注册用户
			Subject currentUser = SecurityUtils.getSubject();
			User user_current = (User)currentUser.getSession().getAttribute("currentUser");
			user_current.setLoginname(user.getLoginname());
		}else{
			return "fail";
		}
		return "ok";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/checkRepeak-loginname")
	@ResponseBody
	public String checkRepeakByLoginname(@RequestParam(value="loginname") String loginname ,
			HttpServletRequest request){
		List<User> list= service.query(Cnd.where("loginname","=",loginname));
		if (list.size() > 0){
			return "true";
		}else{
			return "false";
		}
	}
	
	
	/*
	 * 录入
	 */
	@RequestMapping("/checkRepeak-loginname-id")
	@ResponseBody
	public String checkRepeakById(@RequestParam(value="loginname") String loginname ,
			@RequestParam(value="id") Long id ,HttpServletRequest request){
		List<User> list= service.query(Cnd.where("loginname","=",loginname).and("id","!=",id));
		if (list.size() > 0){
			return "true";
		}else{
			return "false";
		}
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/changeUser")
	public String changeUser(User user,
			@RequestParam(value="oldpassword",defaultValue="") String oldpassword ,
			HttpServletRequest request ){
		String pwd = UserUtils.getUser().getPassword();
		if (Encryption.hashToMD5(oldpassword).equals(pwd)){
			user.setPassword(Encryption.hashToMD5(user.getPassword()));
			service.updateIgnoreNull(user);
			//重新注册用户
			Subject currentUser = SecurityUtils.getSubject();
			User user_current = (User)currentUser.getSession().getAttribute("currentUser");
			user_current.setLoginname(user.getLoginname());
			user_current.setPassword(user.getPassword());
			//currentUser.getSession().setAttribute("currentUser", user);
		}else{
			return "redirect:/login.jsp";
		}
		return "redirect:/index.jsp";	
	}
	/*
	 * 录入
	 */
	@RequestMapping("/inputRole")
	public String inputRole(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") long id ){
		request.setAttribute("ob", service.fetchUserRoles(id));
		
		request.setAttribute("roles", roleService.query(null));
		return "jsp/system/input-user-role";	
	}
	/*
	 * 保存用户的角色
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="role") int[] roles ){
		//先删除后插入
		service.deleteUserRole(id);
		service.insertUserRole(roles,id);
		
		return "ok";	
	}
	/*
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(@RequestParam(value="loginname") String ln,
			@RequestParam (value="password",defaultValue="") String pw,
			@RequestParam (value="uname",defaultValue="") String uname,
			HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(ln, pw);
		token.setRememberMe(true);
		
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			request.setAttribute("message", "登录失败：用户名或密码输入错误！");
			e.printStackTrace();
		}
		if(currentUser.isAuthenticated()){
			Principal principal = (Principal)currentUser.getPrincipal();
			Condition c = Cnd.where("loginname","=",principal.getloginname());
		    List<User> users = service.query(c);
		    currentUser.getSession().setAttribute("currentUser", service.fetchUserRoles(users.get(0).getId()));
		    currentUser.getSession().setAttribute("currentUserId", users.get(0).getId());
		    System.out.println(users.get(0).getPassword());
		    if(users.get(0).getPassword().equals(Encryption.hashToMD5(users.get(0).getLoginname()))){
		    	return "redirect:/repassword.jsp";//修改密码
		    }else{
		    	if(uname.endsWith("0")){
		    		return "redirect:/leaderIndex.jsp";
		    	} else{
		    		return "redirect:/index.jsp";
		    	}
		    	
		    }
			
		}else{
			request.setAttribute("message", "登录失败：用户没有权限");
		}
		return "login";	
	}
	
	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return "redirect:/login.jsp";
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(User user,
						HttpServletRequest request,
						@RequestParam (value="sf",defaultValue="") String[] sf){
		StringBuffer representflag = new StringBuffer();
		//先置入用户身份
		for(String s:sf){
			representflag.append(s+";");
		}
		user.setRepresentflag(representflag.toString());
		
		if (user.getId()==null){
			user.setPassword(Encryption.hashToMD5(user.getYddh()));//初始默认密码都是1
			//user.setRepresentflag("0");
			service.insert(user);
		}else{
			service.updateIgnoreNullWithDept(user);
		}
		return "common/success";	
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
