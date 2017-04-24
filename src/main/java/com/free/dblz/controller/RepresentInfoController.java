package com.free.dblz.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.free.common.Const;
import com.free.common.utils.Encryption;
import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.TranslateUtils;
import com.free.common.utils.UploadUtil;
import com.free.common.utils.UserUtils;
import com.free.common.utils.web.Servlets;
import com.free.dblz.service.RepresentInfoService;
import com.free.system.model.Department;
import com.free.system.model.User;
import com.free.system.model.UserDepartment;
import com.free.system.service.DepartmentService;
import com.free.system.service.UserDeptService;


@Controller
@RequestMapping(value="/dblz/representinfo")
public class RepresentInfoController {
	
	@Resource
	private RepresentInfoService service;
	@Resource
	private UserDeptService udService;
	@Resource
	private DepartmentService deptService;
	@Resource
	private Dao dao;
	@Resource
	private DepartmentService departmentService;
	@Resource
	//private MailService mailService;
	
	
	@RequestMapping("/dbxx_list")
	public String representInfomation(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-representinfo";
	}
	
	
	
	@RequestMapping("/ajax_upload")
	@ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
        String ctxPath = Const.UPLOADFILEPATH_DBLZ; 
        //创建文件夹  
            File file = new File(ctxPath);    
            if (!file.exists()) {    
                file.mkdirs();    
            }    
            String fileName = null;
            String path=null;
            String newFileName = null;
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
                // 上传文件名      
                MultipartFile mf = entity.getValue();    
                fileName = mf.getOriginalFilename();  
                
                String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
                String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;

                newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
                
                
                File uploadFile = new File(ctxPath + newFileName);    
                try {  
                    FileCopyUtils.copy(mf.getBytes(), uploadFile); 
                    path =ctxPath+newFileName;
            } catch (IOException e) {   
                e.printStackTrace();  
            }    
            }   
           
            return newFileName;  
    }

	//删除附件
	@RequestMapping("/ajax_deleteFile")
	@ResponseBody
    public String ajax_deleteFile(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) {
		User en = service.fetch(id);
        UploadUtil.deleteFile(Const.UPLOADFILEPATH_DBLZ+en.getZp());
        en.setZp("");
        en.setFilename("");
		service.updateIgnoreNull(en);
        return "ok";  
    }
	/*
	 * 用户列表
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page ,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("LIKE_representflag", "1");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setOrderBy("IF(ISNULL(dbzh),100000000,dbzh)");
		pager.setOrder(pager.ASC);
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		
		return service.queryPage(pager);
	}
	
	
	/*
	 * 用户列表
	 */
	@RequestMapping("/ajax_qgdb_list")
	@ResponseBody
	public Map qgdb_list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page ,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("LIKE_representflag", "2");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setOrderBy("IF(ISNULL(ordercol),100000000,ordercol)");
		pager.setOrder(pager.ASC);
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		
		return service.queryPage(pager);
	}
	
	@RequestMapping("/getXm")
	@ResponseBody
	public Map getXm(HttpServletRequest request,@RequestParam(value="query") String xm){
		List<User> list = service.queryXm(xm);
		List xmList = new ArrayList();
		for(User u:list){
			u.setYddh(UserUtils.hiddenTelephone(u.getYddh()));
			xmList.add(u.getXm());
		}
		
		Map resmap = new HashMap();
		resmap.put("query", xm);
		resmap.put("suggestions", xmList);
		resmap.put("data", xmList);
		
		return resmap;
	}
	
	
	@RequestMapping("/getQgdbXm")
	@ResponseBody
	public Map getQgdbXm(HttpServletRequest request,@RequestParam(value="query") String xm){
		List<User> list = service.queryQgdbXm(xm);
		List xmList = new ArrayList();
		for(User u:list){
			u.setYddh(UserUtils.hiddenTelephone(u.getYddh()));
			xmList.add(u.getXm());
		}
		
		Map resmap = new HashMap();
		resmap.put("query", xm);
		resmap.put("suggestions", xmList);
		resmap.put("data", xmList);
		
		return resmap;
	}
	
	
	@RequestMapping("/getDbByXm")
	@ResponseBody
	public Map getDbByXm(HttpServletRequest request,@RequestParam(value="q") String xm){
		List<User> list = service.queryXm(xm);
		List xmList = new ArrayList();
		for(User u:list){
			u.setYddh(UserUtils.hiddenTelephone(u.getYddh()));
			xmList.add(u);
		}
		
		Map resmap = new HashMap();
		resmap.put("q", xm);
		//resmap.put("suggestions", xmList);
		resmap.put("data", xmList);
		
		return resmap;
	}
	@RequestMapping("/getDb")
	@ResponseBody
	public Map getDb(HttpServletRequest request,@RequestParam(value="query") String xm){
		List<User> list = service.queryXm(xm);
		List xmList = new ArrayList();
		for(User u:list){
			u.setYddh(UserUtils.hiddenTelephone(u.getYddh()));
			xmList.add(u);
		}
		
		Map resmap = new HashMap();
		resmap.put("query", xm);
		resmap.put("suggestions", xmList);
		resmap.put("data", xmList);
		
		return resmap;
	}
	
	
	@RequestMapping("/checkDb")
	@ResponseBody
	public Map checkDb(HttpServletRequest request,@RequestParam(value="query") String xm){
		List<User> list = service.queryXmNoLike(xm);
		List xmList = new ArrayList();
		for(User u:list){
			u.setYddh(UserUtils.hiddenTelephone(u.getYddh()));
			xmList.add(u);
		}
		
		Map resmap = new HashMap();
		resmap.put("query", xm);
		resmap.put("suggestions", xmList);
		resmap.put("data", xmList);
		resmap.put("count", xmList.size());
		
		
		return resmap;
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(User user,
						HttpServletRequest request){
		if (user.getId()==null){
			user.setPassword(Encryption.hashToMD5(user.getYddh()));//默认密码为此用户手机号
			service.insert(user);
		}else{
			//修改密码
			if (StringUtils.isNoneEmpty(user.getPassword())){
				user.setPassword(Encryption.hashToMD5(user.getPassword()));
			}
			service.updateIgnoreNull(user);

			//mailService.sendMailForChangeRepresentInfo(user);
		}
		return "ok";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id ){
		User u =  service.fetch(id);
		//提取专业组
		StringBuffer zyzname_group = new StringBuffer();
		StringBuffer zyzid_group = new StringBuffer();
		List<UserDepartment> udlist=udService.find(Cnd.where("userid","=",u.getId()));
		for(UserDepartment ud:udlist){
			//提取部门的父id
			Department dept = deptService.fetch(Integer.valueOf(ud.getDeptid().toString()));
			if(dept.getFid().equals(Const.DEPARTMENT_FID_ZYZ)){
				zyzname_group.append(dept.getDeptname());
				zyzname_group.append(",");
				zyzid_group.append(dept.getId());
				zyzid_group.append(",");
			}
		}
		u.setZyzid_group(zyzid_group.toString());
		u.setZyzname_group(zyzname_group.toString());
		request.setAttribute("ob", u);
		List<Department> l = departmentService.query(Cnd.where("id","=",u.getDeptid()));
		if(l.size()>0){
			u.setDeptname(l.get(0).getDeptname());
		}
		return "jsp/dblz/input-represent";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/inputPw")
	public String inputPw(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id ){
		User u =  service.fetch(id);
		request.setAttribute("ob", u);
		return "jsp/dblz/input-represent-pw";	
	}
	
	@RequestMapping("/user_ajax")
	@ResponseBody
	public User user_ajax(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id ){
		User u =  service.fetch(id);
		request.setAttribute("ob", u);
		List<Department> l = departmentService.query(Cnd.where("id","=",u.getDeptid()));
		if(l.size()>0){
			u.setDeptname(l.get(0).getDeptname());
		}
		return u;
	}
	
	@RequestMapping("/ajax_fetchUserByxm")
	@ResponseBody
	public User ajax_fetchUserByxm(HttpServletRequest request,
			@RequestParam(value="xm",defaultValue="") String xm ){
		User u =  dao.fetch(User.class,Cnd.where("xm", "=", xm));
		if (u==null){
			return new User();
		}
		List<Department> l = departmentService.query(Cnd.where("id","=",u.getDeptid()));
		if(l.size()>0){
			u.setDeptname(l.get(0).getDeptname());
		}
		u.setYddh(UserUtils.hiddenTelephone(u.getYddh()));
		return u;
	}
	@RequestMapping("/ajax_fetchUserByxm_select2")
	@ResponseBody
	public List<Map> ajax_fetchUserByxm_select2(HttpServletRequest request,
			@RequestParam(value="q",defaultValue="") String q,
			@RequestParam(value="page_limit",defaultValue="10") String page_limit ){
		List<User> list =  dao.query(User.class,Cnd.wrap(" representflag=1 and xm like "+"'%"+q+"%'"+" limit "+page_limit));
		List<Map> lRes = new ArrayList<Map>();
		for (User user : list) {
			Map<String,String> m = new HashMap();
			m.put("text", user.getXm());
			m.put("id", user.getId()+"");
			lRes.add(m);
		}
		return lRes;
	}
	/*
	 * 查看
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request,@RequestParam(value="id",defaultValue="0") int id ) throws Exception{
		User u =  service.fetch(id);
		//转码列
		Map<String, String> codeAttributes = new HashMap<String, String>();
		codeAttributes.put("xb", "性别");
		codeAttributes.put("mz", "民族");
		codeAttributes.put("zyz", "专业组");
		TranslateUtils.retrieveFormCode(u, codeAttributes);
		
		//如果非本人 并且  当前用户是代表,不可以看到代表手机号
		if (!UserUtils.getUser().getId().equals(u.getId()) 
				&& "1".equals(UserUtils.getUser().getRepresentflag())){
			u.setYddh("");
		}
		request.setAttribute("ob", u);
		List<Department> l = departmentService.query(Cnd.where("id","=",u.getDeptid()));
		if(l.size()>0){
			u.setDeptname(l.get(0).getDeptname());
		}
		return "jsp/dblz/view-represent";	
	}
}
