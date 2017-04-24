package com.free.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadUtil {
	
	public static String uploadFile(HttpServletRequest request, HttpServletResponse response,String ctxPath) throws Exception{
		 String responseStr="";  
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
	        //获取前台传值  
	        String[] userNames = multipartRequest.getParameterValues("userName");  
	        String[] contents = multipartRequest.getParameterValues("content");  
	        String userName="";  
	        String content="";  
	        if(userNames!=null){  
	            userName=userNames[0];  
	        }  
	        if(contents!=null){  
	            content=contents[0];  
	        }  
         Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
//	        String ctxPath = Const.UPLOADFILEPATH_HYWJ; 
	        //创建文件夹  
	            File file = new File(ctxPath);    
	            if (!file.exists()) {    
	                file.mkdirs();    
	            }    
	            String fileName = null;
	            String path=null;
	            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
	                // 上传文件名      
	                MultipartFile mf = entity.getValue();    
	                fileName = mf.getOriginalFilename();  
	               //String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();      
	               //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	               // String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  
	                
	                String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
	                String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;

	                String newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
	                
	                
	                File uploadFile = new File(ctxPath + newFileName);    
	                try {  
	                    FileCopyUtils.copy(mf.getBytes(), uploadFile); 
	                    path = ctxPath+newFileName;
	                responseStr="上传成功";  
	            } catch (IOException e) {  
	                responseStr="上传失败";  
	                e.printStackTrace();  
	            }    
	            }
	            
	           return path;
	}
	
	
	/**
	 * 
	 * 同上个方法基本一样，只是这个是返回真实的文件名，并不带路径,更加解决藕(推荐)
	 * @param request
	 * @param response
	 * @param ctxPath
	 * @return
	 * @throws Exception
	 */
	public static String uploadFileReturnFileName(HttpServletRequest request, HttpServletResponse response,String ctxPath) throws Exception{
		 String responseStr="";  
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
	        //获取前台传值  
	        String[] userNames = multipartRequest.getParameterValues("userName");  
	        String[] contents = multipartRequest.getParameterValues("content");  
	        String userName="";  
	        String content="";  
	        if(userNames!=null){  
	            userName=userNames[0];  
	        }  
	        if(contents!=null){  
	            content=contents[0];  
	        }  
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
//	        String ctxPath = Const.UPLOADFILEPATH_HYWJ; 
	        //创建文件夹  
	            File file = new File(ctxPath);    
	            if (!file.exists()) {    
	                file.mkdirs();    
	            }    
	            String fileName = null;
	            String path=null;
	            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
	                // 上传文件名      
	                MultipartFile mf = entity.getValue();    
	                fileName = mf.getOriginalFilename();  
	               //String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();      
	               //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	               // String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  
	                
	                String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
	                String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;

	                String newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
	                
	                
	                File uploadFile = new File(ctxPath + newFileName);    
	                try {  
	                    FileCopyUtils.copy(mf.getBytes(), uploadFile); 
	                    path = newFileName;
	                responseStr="上传成功";  
	            } catch (IOException e) {  
	                responseStr="上传失败";  
	                e.printStackTrace();  
	            }    
	            }
	            
	           return path;
	}
	
	/**
	 * 
	 * 删除文件
	 * @param request
	 * @param response
	 * @param ctxPath
	 * @return
	 * @throws Exception
	 */
	public static void deleteFile(String ctxPath) { 
        File file = new File(ctxPath);    
        if (file.exists()) {    
            file.delete();    
        }  
    }
}
