package com.inhatc.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

  private static final Logger logger = 
      LoggerFactory.getLogger(UploadFileUtils.class);

//  public static String uploadFile(String uploadPath, 
//      String originalName, 
//      byte[] fileData)throws Exception{
//    
//    return null;
//  }
//  
    public static String attach_path = "resources/upload/";
 
    public static String path(HttpServletRequest request) {
        String uploadPath = "";
        try {
            String root_path = request.getServletContext().getRealPath("/");
 
            uploadPath = root_path + attach_path.replace('/', File.separatorChar);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadPath;
    }
  
  public static String uploadFile(String uploadPath, 
                              String originalName, 
                              byte[] fileData)throws Exception{
    
    UUID uid = UUID.randomUUID();
    
    String savedName = uid.toString() +"_"+originalName;
    
    //String savedPath = calcPath(uploadPath);
    
    //File target = new File(uploadPath +savedPath,savedName);
    File target = new File(uploadPath ,savedName);
    FileCopyUtils.copy(fileData, target);
    
    String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
    
    String uploadedFileName = null;
    
    if(MediaUtils.getMediaType(formatName) != null){
      uploadedFileName = makeThumbnail(uploadPath, savedName);
      //uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
    }else{
      uploadedFileName = makeIcon(uploadPath, savedName);
      //uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
    }
    
    return uploadedFileName;
    
  }
  
/*  private static  String makeIcon(String uploadPath, 
      String path, 
      String fileName)throws Exception{

    String iconName = 
        uploadPath + path + File.separator+ fileName;
    
    return iconName.substring(
        uploadPath.length()).replace(File.separatorChar, '/');
  }*/

  private static  String makeIcon(String uploadPath, 
	      String fileName)throws Exception{

	    String iconName = 
	        uploadPath + File.separator+ fileName;
	    
	    return iconName.substring(
	        uploadPath.length()).replace(File.separatorChar, '/');
	  }
  
  private static  String makeThumbnail(
          String uploadPath, 
          String fileName)throws Exception{
        
BufferedImage sourceImg = 
    ImageIO.read(new File(uploadPath, fileName));

BufferedImage destImg = 
    Scalr.resize(sourceImg, 
        Scalr.Method.AUTOMATIC, 
        Scalr.Mode.FIT_TO_HEIGHT,100);

String thumbnailName = 
    uploadPath + File.separator +"s_"+ fileName;

File newFile = new File(thumbnailName);
String formatName = 
    fileName.substring(fileName.lastIndexOf(".")+1);


ImageIO.write(destImg, formatName.toUpperCase(), newFile);
return thumbnailName.substring(
    uploadPath.length()).replace(File.separatorChar, '/');
}

/*  private static  String makeThumbnail(
              String uploadPath, 
              String path, 
              String fileName)throws Exception{
            
    BufferedImage sourceImg = 
        ImageIO.read(new File(uploadPath + path, fileName));
    
    BufferedImage destImg = 
        Scalr.resize(sourceImg, 
            Scalr.Method.AUTOMATIC, 
            Scalr.Mode.FIT_TO_HEIGHT,100);
    
    String thumbnailName = 
        uploadPath + path + File.separator +"s_"+ fileName;
    
    File newFile = new File(thumbnailName);
    String formatName = 
        fileName.substring(fileName.lastIndexOf(".")+1);
    
    
    ImageIO.write(destImg, formatName.toUpperCase(), newFile);
    return thumbnailName.substring(
        uploadPath.length()).replace(File.separatorChar, '/');
  }*/ 
  
  
/*  private static String calcPath(String uploadPath){
    
    Calendar cal = Calendar.getInstance();
    
    String yearPath = File.separator+cal.get(Calendar.YEAR);
    
    String monthPath = yearPath + 
        File.separator + 
        new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

    String datePath = monthPath + 
        File.separator + 
        new DecimalFormat("00").format(cal.get(Calendar.DATE));
    
    makeDir(uploadPath, yearPath,monthPath,datePath);
    makeDir(uploadPath);
    
    logger.info(datePath);
    
    return datePath;
  }
  
  
  private static void makeDir(String uploadPath, String... paths){
    
    if(new File(paths[paths.length-1]).exists()){
      return;
    }
    
    for (String path : paths) {
      
      File dirPath = new File(uploadPath + path);
      
      if(! dirPath.exists() ){
        dirPath.mkdir();
      } 
    }
  }*/
  
  
}
