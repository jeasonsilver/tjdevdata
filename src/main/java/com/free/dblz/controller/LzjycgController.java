package com.free.dblz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
import com.free.common.utils.FileOperateUtil;
import com.free.common.utils.NewPager;
import com.free.common.utils.SearchFilter;
import com.free.common.utils.TranslateUtils;
import com.free.common.utils.UploadUtil;
import com.free.common.utils.UserUtils;
import com.free.common.utils.web.Servlets;
import com.free.dblz.model.Lzjycg;
import com.free.dblz.model.Lzjycgfj;
import com.free.dblz.service.LzjycgService;
import com.free.dblz.service.LzjycgfjService;

import com.free.system.model.User;

/**
 * 代表履职--履职信息记录
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/dblz/lzjycg")
public class LzjycgController {

	@Resource
	private LzjycgService service;
	@Resource
	private LzjycgfjService fjService;
	
	
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
	
	@RequestMapping("/download")
	public String download(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) throws Exception {
		Lzjycg entity = service.fetch(id);
		String downLoadPath = Const.UPLOADFILEPATH_LZFJ+entity.getFj();
		String realName = entity.getFilename();
		try{
			FileOperateUtil.download(request, response, downLoadPath, realName);
		}catch(Exception e){
			e.printStackTrace();
			return "common/error";
		}
		
		return null;
	}
	//删除附件
	@RequestMapping("/ajax_deleteFile")
	@ResponseBody
    public String ajax_deleteFile(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) {
		Lzjycg entity = service.fetch(id);
        UploadUtil.deleteFile(Const.UPLOADFILEPATH_LZFJ+entity.getFj());
        entity.setFj("");
        entity.setFilename("");
		service.updateIgnoreNull(entity);
        return "ok";  
    }
	
	@RequestMapping("/addpage")
	public String fowardInput(HttpServletRequest request) throws Exception{
		Lzjycg entity = new Lzjycg();
		User user = UserUtils.getUser();
		entity.setFbrxm(user.getXm());
		entity.setFbrid(user.getId());
		if ("0".equals(user.getRepresentflag())){//非代表
			entity.setLy("2");
			entity.setHdzzdw(user.getFbdw());
		}else{
			entity.setLy("1");
		}
		request.setAttribute("ob", entity);
		return "jsp/dblz/input-lzjycg";
	}
	
	@RequestMapping("/lzjycg_list")
	public String fowardList(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-lzjycg";
	}
	
	@RequestMapping("/my_lzjycg_list")
	public String fowardMyLzjycgList(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-my-lzjycg";
	}
	
	@RequestMapping("/lzjycg_list_hz")
	public String fowardHZList(HttpServletRequest request) throws Exception{
		return "jsp/dblz/list-lzjycg-hz";
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
		searchParams.put("EQ_cjrid", user.getId().toString());
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
	@RequestMapping("/ajax_list_sh")
	@ResponseBody
	public Map listSh(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ) throws Exception{
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_shzt", "1");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		
		Map map = service.queryPage(pager);
		
		//转码列
		List<Lzjycg> list = (List<Lzjycg>) map.get("Rows");
		Map codeAttributes = new HashMap();
		codeAttributes.put("shzt", "审核状态");
		TranslateUtils.retrievePageList(list, codeAttributes);
		
		return map;
	}
	
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ) throws Exception{
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		pager.setOrders(new String[]{"shzt", pager.ASC,"cjsj", pager.DESC}); 
		
		Map map = service.queryPage(pager);
		
		//转码列
		List<Lzjycg> list = (List<Lzjycg>) map.get("Rows");
		Map codeAttributes = new HashMap();
		codeAttributes.put("shzt", "审核状态");
		TranslateUtils.retrievePageList(list, codeAttributes);
		
		return map;
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		List<Lzjycgfj> fileList =fjService.queryByFid(id);
		request.setAttribute("fileList", fileList);
		
		return "jsp/dblz/edit-lzjycg";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Lzjycg lzjycg,HttpServletRequest request,
			@RequestParam(value="filename",defaultValue="") String[] filenames,
			@RequestParam(value="fileurl",defaultValue="") String[] fileurls,
			@RequestParam(value="fjid",defaultValue="") long[] fjid,
			@RequestParam(value="users",defaultValue="",required=false) String users){
		
		//首先要删除附件列表
		if (lzjycg.getId()==null){
			lzjycg.setShzt("0");
			service.insert(lzjycg);
		}else{
			service.update(lzjycg);
		}
		/**
		if (lzjycg.getId()>0){
			//插入附件表
			for (int i = 0; i < filenames.length; i++) {
				Lzjycgfj fj = new Lzjycgfj();
				fj.setFilename(filenames[i]);
				fj.setFileurl(fileurls[i]);
				fj.setFid(lzjycg.getId());
				//fj.setId(fjid[i]);
				if (fj.getId()==null){
					fjService.insert(fj);	
				}else{
					
				}
			}
		}
		**/
		return "common/success_self";	
	}
	
	
	/*
	 * 保存(弹出页面) 为了刷新父页面
	 */
	@RequestMapping("/save_bypop")
	public String savebypop(Lzjycg lzjycg,HttpServletRequest request,
			@RequestParam(value="filename",defaultValue="") String[] filenames,
			@RequestParam(value="fileurl",defaultValue="") String[] fileurls,
			@RequestParam(value="fjid",defaultValue="") long[] fjid,
			@RequestParam(value="users",defaultValue="",required=false) String users){
		
		//首先要删除附件列表
		if (lzjycg.getId()==null){
			lzjycg.setShzt("0");
			service.insert(lzjycg);
		}else{
			service.update(lzjycg);
		}
		/**
		if (lzjycg.getId()>0){
			//插入附件表
			for (int i = 0; i < filenames.length; i++) {
				Lzjycgfj fj = new Lzjycgfj();
				fj.setFilename(filenames[i]);
				fj.setFileurl(fileurls[i]);
				fj.setFid(lzjycg.getId());
				//fj.setId(fjid[i]);
				if (fj.getId()==null){
					fjService.insert(fj);	
				}else{
					
				}
			}
		}
		**/
		return "common/success_close";	
	}
	
	
	/*
	 * 保存
	 */
	@RequestMapping("/save-sh")
	public String saveSh(Lzjycg lzjycg,HttpServletRequest request,
			@RequestParam(value="filename",defaultValue="") String[] filenames,
			@RequestParam(value="fileurl",defaultValue="") String[] fileurls,
			@RequestParam(value="fjid",defaultValue="") long[] fjid,
			@RequestParam(value="users",defaultValue="",required=false) String users){
		
		service.updateIgnoreNull(lzjycg);
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
		fjService.delByFid(id);
		
		return "ok";	
	}
	
	/*
	 * 录入
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) throws Exception{
		
		Map codeAttributes = new HashMap();
		codeAttributes.put("ly", "履职经验来源");
		Lzjycg ob = service.fetch(id);
		TranslateUtils.retrieveFormCode(ob, codeAttributes);
		request.setAttribute("ob", ob);
//		List<Lzjycgfj> fileList =fjService.queryByFid(id);
//		request.setAttribute("fileList", fileList);
		
		
		
		return "jsp/dblz/view-lzjycg";	
	}
	
	@RequestMapping("/view-sh")
	public String viewSh(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id) throws Exception{
		Map codeAttributes = new HashMap();
		codeAttributes.put("ly", "履职经验来源");
		Lzjycg ob = service.fetch(id);
		TranslateUtils.retrieveFormCode(ob, codeAttributes);
		request.setAttribute("ob", ob);
		
		return "jsp/dblz/view-lzjycg-sh";	
	}
}