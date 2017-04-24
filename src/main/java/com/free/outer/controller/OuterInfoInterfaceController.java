package com.free.outer.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.free.common.Const;
import com.free.common.utils.DesUtil;


/**
 * 
 * 模板自动生成   for FreeUI
 * @author mefly
 *
 */
@Controller
@RequestMapping(value="/outer/interface")
public class OuterInfoInterfaceController {
	
	@RequestMapping("/fetchAttachment")
	public void yajyDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="fj",defaultValue="0") String fj){
		try {
			BufferedInputStream bis = null;  
	        BufferedOutputStream bos = null; 
			FileInputStream is = new FileInputStream(Const.UPLOADFILEPATH_YAJY+fj);
			OutputStream os = response.getOutputStream();
			
			bis = new BufferedInputStream(is);  
	        bos = new BufferedOutputStream(os);  
	        
	        byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	            bos.write(buff, 0, bytesRead);  
	        }  
	        bis.close();  
	        bos.close();  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadAttachment")
	@ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		
		
		String fj =  request.getHeader("fj");
		String password =  request.getHeader("password");
		InputStream ins = request.getInputStream();
		//首先验证口令
		String key = "*@&KEY$^";
		String pass = DesUtil.decrypt(password, key);
		if(!pass.equals("lijin")){
			return "口令error";
		}  
        String ctxPath = Const.UPLOADFILEPATH_YAJY; 
        
        //创建文件夹  
            File file = new File(ctxPath);    
            if (!file.exists()) {    
                file.mkdirs();    
            }

            File fileFj = new File(ctxPath + fj); 
            inputstreamtofile(ins,fileFj);

           
            return "success";  
    }
	
	
	public static void inputstreamtofile(InputStream ins,File file) {
		  try {
		   OutputStream os = new FileOutputStream(file);
		   int bytesRead = 0;
		   byte[] buffer = new byte[8192];
		   while ((bytesRead = ins.read(buffer, 0, buffer.length)) != -1) {
		    os.write(buffer, 0, bytesRead);
		   }
		   os.close();
		   ins.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	}
	
	public static void main(String[] args) throws Exception{
		
	/**	
		URL url=new URL("http://localhost:8080/tjrd/outer/interface/yajyDetail.do");  
        URLConnection con=url.openConnection();  
        con.setDoOutput(true);  
        OutputStream out=con.getOutputStream();  
        FileInputStream is = new FileInputStream("E:\\post_yajy.ashx_one.xml");
        //System.out.println("Exedata satart\n"+request+"\nExe end");  
//        out.write(new String(request.getBytes("ISO-8859-1")));
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
        	out.write(buffer, 0, bytesRead);
		}
  
        out.flush();  
        out.close();  
        BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));  
        String line="";  
        for(line=br.readLine();line!=null;line=br.readLine()) {  
            System.out.println(line);  
        }  
        **/
		
//		String sn = null;
//		StringBuffer sb=new StringBuffer();
//		sb.append("TJRD").append("rd");
//		String result = "";
//
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(sb.toString().getBytes("UTF8"));
//		byte s[] = md.digest();
//
//		for (int i = 0; i < s.length; i++) {
//		result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
//		}
//		sn= result;
//
//		System.out.println(sn);
		
		//口令
		String key = "*@&KEY$^";
		String password = DesUtil.encrypt("lijin", key);
		
		URL url = new URL("http://localhost:8080/tjrd/outer/interface/uploadAttachment.do"); 
		URLConnection con = url.openConnection();  
        con.setDoOutput(true);  
        con.addRequestProperty("fj", "aaa.xml");
        con.addRequestProperty("password", password);
        OutputStream out=con.getOutputStream(); 
        
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("e://aaa.xml"));  
        BufferedOutputStream bos = new BufferedOutputStream(out);  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bos.flush();
        bis.close();  
        bos.close(); 
        
        InputStream is = con.getInputStream();
        Scanner sc = new Scanner(is);
        System.out.println(sc.nextLine());  
        sc.close();
        out.close();
		
	}
	

}
