package com.free.dblz.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.free.common.Const;
import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.UserUtils;
import com.free.common.utils.web.Servlets;
import com.free.dblz.service.LzhdjlService;
import com.free.dblz.service.LzhdjlfjService;
import com.free.dblz.model.Lzhdjl;
import com.free.dblz.model.Lzhdjlfj;
import com.free.system.model.User;

/**
 * 代表履职--履职信息记录
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/dblz/lzhdjl")
public class LzhdjlController {

	@Resource
	private LzhdjlService service;
	@Resource
	private LzhdjlfjService fjService;
	
	
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
	
	@RequestMapping("/addpage")
	public String forwardInput(HttpServletRequest request) throws Exception{
		Lzhdjl entity = new Lzhdjl();
		User user = UserUtils.getUser();
		entity.setFbrxm(user.getXm());
		request.setAttribute("ob", entity);
		return "jsp/dblz/input-lzhdjl";
	}
	
	@RequestMapping("/lzhdjl_list")
	public String forwardList(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-lzhdjl";
	}
	
	@RequestMapping("/lzhdjl_list_hz")
	public String forwardHZList(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-lzhdjl-hz";
	}
	
	/*
	 * 列表json
	 */
	@RequestMapping("/my_ajax_list")
	@ResponseBody
	public Map mylist(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		User user = UserUtils.getUser();
		searchParams.put("LIKE_rdrealname", user.getXm());
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
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
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
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		List<Lzhdjlfj> fileList =fjService.queryByFid(id);
		request.setAttribute("fileList", fileList);
		
		return "jsp/dblz/edit-lzhdjl";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Lzhdjl lzhdjl,HttpServletRequest request,
			@RequestParam(value="filename",defaultValue="") String[] filenames,
			@RequestParam(value="fileurl",defaultValue="") String[] fileurls,
			@RequestParam(value="fjid",defaultValue="") long[] fjid,
			@RequestParam(value="users",defaultValue="",required=false) String users){
		
		//首先要删除附件列表
		if (lzhdjl.getId()==null){
			service.insert(lzhdjl);
		}else{
			//更新要删除附件
			fjService.delByFid(lzhdjl.getId().intValue());
			service.update(lzhdjl);
		}
		
		if (lzhdjl.getId()>0){
			//插入附件表
			for (int i = 0; i < filenames.length; i++) {
				Lzhdjlfj fj = new Lzhdjlfj();
				fj.setFilename(filenames[i]);
				fj.setFileurl(fileurls[i]);
				fj.setFid(lzhdjl.getId());
				//fj.setId(fjid[i]);
				if (fj.getId()==null){
					fjService.insert(fj);	
				}else{
					
				}
			}
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
		fjService.delByFid(id);
		return "ok";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		List<Lzhdjlfj> fileList =fjService.queryByFid(id);
		request.setAttribute("fileList", fileList);
		
		return "jsp/dblz/view-lzhdjl";	
	}
}