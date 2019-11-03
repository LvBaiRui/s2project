package edu.prj.ui.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** 
 * @author: MrLiu 
 * @version: 1.0
 * @date: 2017年1月2日 下午7:45:53
 * 类说明 : 文件上传,遍历上传item
 *
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(UploadServlet.class);
	
	private String uploadPath = "d:/upload/a1/"; // 上传文件的目录
	private String tempPath = "d:/upload/tmp/"; // 临时文件目录

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");//设置响应编码
		request.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		//PrintWriter out = response.getWriter();//获取响应输出流
		//不用 DiskFileUpload 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory); // 上传
		
		upload.setFileSizeMax(1024*1024*1000); // 单个文件最大1MB
		upload.setSizeMax(1024*1024*1000); // 设置上传总文件大小100MB
		upload.setHeaderEncoding("UTF-8"); // 设置编码格式，解决上传文件名乱码问题
		
        factory.setSizeThreshold(1024*10); //  设置缓存大小 10KB
        factory.setRepository(new File(tempPath)); // 设置临时目录
		
        //得到一个保存了所有上传内容的List对象
        List<FileItem> fileItems = new ArrayList<FileItem>();
        try {
        	fileItems = upload.parseRequest(request); // 解析用户请求的参数,取出文件上传信息
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        
        Iterator<FileItem> i = fileItems.iterator();
	     // 依次处理每一个上传文件
	    while(i.hasNext()) {
	    	FileItem item = (FileItem)i.next();
	    	if (item.isFormField()) {//表示上传的是普通文本域
	    		logger.info("处理普通文本域 ...");
            	String fieldName=item.getFieldName(); // 字段名
                String value = item.getString("UTF-8"); // 字段值
                if("".equals(value.trim()) || value == null){             
                    logger.info("表单文本内容为空 ...");  
                }else{	
                	logger.info(fieldName+"="+value);
                	//logger.info(fieldName+"="+value+"<br>");
                }
            
            }else { // 表示是上传的文件，图片，mp3
	           	 logger.info("处理上传文件 ...");
	           	 String filename = item.getName(); //  获得文件名，这个文件名包括扩展名      
	           	 long fileSize = item.getSize();  // 文件大小 
	           	 String contentType=item.getContentType();
	           	 
	           	 if(("".equals(filename.trim())||filename==null) && fileSize == 0){             
	           		 logger.info("文件不存在...");  
	           		 break;  
	           	 }
	           	 
                 logger.info("包括扩展名的文件名：" + filename);
                 logger.info("contentType: "+contentType); //上传文件头 application/pdf
                 
                 String filenameOnly=filename.substring(filename.lastIndexOf('\\')+1);

                try {
					item.write(new File(uploadPath+filenameOnly)); //绝对路径，把上传文件输出到指定路径
				} catch (Exception e) {
					e.printStackTrace();
				}
                item.delete();
				logger.info(filenameOnly + ": 文件保存完毕 ...");  
				logger.info("文件大小为 ：" + fileSize/1024 + "KB");
           }
	        
	    }
	    //request.getRequestDispatcher("/upload_suc.jsp").forward(request, response);
	    writer.write("{\"status\":true}");
      //注意Eclipse的上传的文件是保存在项目的运行目录，而不是workspace中的工程目录里。
        
	}
}
