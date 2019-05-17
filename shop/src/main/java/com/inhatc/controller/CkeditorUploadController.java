package com.inhatc.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.JsonObject;




@Controller
public class CkeditorUploadController {
 
    private static final Logger logger = LoggerFactory.getLogger(CkeditorUploadController.class);
 
    //파일 디렉토리 사용시
    @Resource(name="uploadPath")
    private String uploadPath;

    
    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			 @RequestParam MultipartFile upload) throws IOException {
		OutputStream out1 = null;
		PrintWriter printWriter1 = null;
		JsonObject json1 = new JsonObject();


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*Calendar today = Calendar.getInstance();  
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		String monthStr="";
		
		
		if(month<10) monthStr ="0"+month;
		else monthStr =""+month;*/
		
		
		
		//String defaultPath = request.getSession().getServletContext().getRealPath("/");
		//String contextPath = request.getSession().getServletContext().getContextPath();
		String contextPath = request.getSession().getServletContext().getContextPath();
		//String fileUploadPathTail = "ckeditor\\"+year+"" +monthStr;
		//String fileUploadPath = uploadPath+fileUploadPathTail;
		String fileUploadPath1 = uploadPath;
		String fileUrl1="";
		//System.out.println("defaultPath : " + defaultPath);
		System.out.println("contextPath : " + contextPath);
		//System.out.println("fileUploadPathTail : " + fileUploadPathTail);
		System.out.println("fileUploadPath : " + fileUploadPath1);
		String test1="";
		try{
			if( upload!=null) {
	            String fileName1 = upload.getOriginalFilename();
	            System.out.println("fileName : " + fileName1);
	            String fileNameExt1 = fileName1.substring(fileName1.indexOf(".")+1);
	            System.out.println("fileNameExt : " + fileNameExt1);
	            
	            if(!"".equals(fileName1)) {
	            	File destD = new File(fileUploadPath1);
	            	
	            	if(!destD.exists()) {
	            		destD.mkdirs();
	            	}
	            	File destination = File.createTempFile("ckeditor_","."+fileNameExt1,destD);
	            	upload.transferTo(destination);
	            	fileUrl1=fileUploadPath1+"\\"+destination.getName();
	            	System.out.println("fileUrl : " + fileUrl1 );
	            	System.out.println("destination : " + destination.getName());
	            	String tmp = destination.getName();
	            	test1 = tmp.substring(9, tmp.length()-4);
	            	System.out.println("test : " + test1);
	            	
	            	printWriter1 = response.getWriter();
	            	
	            	String fileUrl5 = fileUploadPath1.substring(46)+"\\"+destination.getName();

		            json1.addProperty("uploaded", 1);
		            json1.addProperty("fileName", fileName1);
		            json1.addProperty("url", fileUrl5);
		            printWriter1.println(json1);
	            }
	            
	            
	            //printWriter.println(json);
			}

/*			String callback = request.getParameter("CKEditorFuncNum");
            System.out.println("callback : " + callback );
            
            printWriter = response.getWriter();
 
            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + fileUrl
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
            printWriter.flush();*/
 
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (out1 != null) {
                    out1.close();
                }
                if (printWriter1 != null) {
                    printWriter1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        return;
	}     
}