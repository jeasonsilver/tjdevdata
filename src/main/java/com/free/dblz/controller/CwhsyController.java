package com.free.dblz.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
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
import com.free.common.utils.FileOperateUtil;
import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.TimeUtil;
import com.free.common.utils.UploadUtil;
import com.free.common.utils.UserUtils;
import com.free.common.utils.web.Servlets;
import com.free.dblz.model.Cwhsy;
import com.free.dblz.service.CwhsyService;
import com.free.system.model.User;

/**
 * 常委会审议
 * @ClassName: CwhsyController 
 * @Description: TODO
 * @Company 天津南大通用数据技术股份有限公司
 * @author 庞 
 * @date Apr 2, 2015 4:24:18 PM 
 * @version V1.0
 *
 */

@Controller
@RequestMapping(value="/dblz/cwhsy")
public class CwhsyController {

	@Resource
	private CwhsyService service;
	
	@Resource
	private Dao dao;
	
	@RequestMapping("/list")
	public String forwardShi(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-cwhsy";
	}
	
	
	
	@RequestMapping("/list-hz")
	public String forwardQing_hz(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-cwhsy-hz";
	}
	
	
	@RequestMapping("/input")
	public String forwardShiInput(HttpServletRequest request) throws Exception{
		Cwhsy cwhsy = new Cwhsy();
		cwhsy.setDjl(0l);//点击率
		
		cwhsy.setFbrxm(UserUtils.getUser().getXm());
		cwhsy.setFbdw(UserUtils.getUser().getFbdw());
		cwhsy.setFbdwid(UserUtils.getUser().getFbdwid());
		cwhsy.setFbrq(TimeUtil.getStringDate());
		//提取当前最大序号
		cwhsy.setOrdercol(service.getMaxOrderCol() +1); 
		request.setAttribute("ob", cwhsy);
		return "jsp/dblz/input-cwhsy";
	}
	
	@RequestMapping("/edit")
	public String forwardEdit(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) throws Exception{
		request.setAttribute("ob", service.fetch(id));
		return "jsp/dblz/input-cwhsy";
	}
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		User user = UserUtils.getUser();
		if (!UserUtils.isAdmin()){
			searchParams.put("EQ_cjrid", user.getId().toString());
		}
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
	@RequestMapping("/ajax_list_hz")
	@ResponseBody
	public Map list_hz(HttpServletRequest request,
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
	
	
	@RequestMapping("/ajax_upload")
	@ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
        String ctxPath = Const.UPLOADFILEPATH_LZFJ; 
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
	
	@RequestMapping("/ajax_deleteFile")
	@ResponseBody
    public String ajax_deleteFile(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) {
		Cwhsy en = service.fetch(id);
        UploadUtil.deleteFile(Const.UPLOADFILEPATH_LZFJ+en.getFj());
        en.setFj("");
        en.setFilename("");
		service.updateIgnoreNull(en);
        return "ok";  
    }
	/*
	 * 显示
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		Cwhsy cwhsy = service.fetch(id);
		request.setAttribute("ob", cwhsy);
		dao.update(Cwhsy.class, Chain.make("djl", cwhsy.getDjl()+1), Cnd.where("id", "=", id));
		
		return "jsp/dblz/view-cwhsy";	
	}
	
	
	
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Cwhsy cwhsy,
						HttpServletRequest request){
		
		User user = UserUtils.getUser();
		
		
		cwhsy.setDjl(0l);
		
		if(StringUtils.isNotBlank(cwhsy.getZdflag()) && cwhsy.getZdflag().equals("1")){//置顶
			cwhsy.setZdtime(TimeUtil.getCurrentTimestamp());
		}
		
		if (cwhsy.getId()==null){	
			cwhsy.setFbrxm(user.getXm());
			cwhsy.setCjr(user.getXm());
			cwhsy.setCjrid(user.getId());
			service.insert(cwhsy);
		}else{
			service.update(cwhsy);
		}
		return "common/success_close";	
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
	
	@RequestMapping("/download")
	public String download(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) throws Exception {
		Cwhsy cwhsy = service.fetch(id);
		String downLoadPath = Const.UPLOADFILEPATH_LZFJ+cwhsy.getFj();
		String realName = cwhsy.getFilename();
		try{
			FileOperateUtil.download(request, response, downLoadPath, realName);
		}catch(Exception e){
			e.printStackTrace();
			return "common/error";
		}
		
		return null;
	}
}