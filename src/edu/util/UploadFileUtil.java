package edu.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadFileUtil {
	
	private static int DEF_MAX_SIZE_MB=600;//默认上传大小5mb
	private static int UploadMaxFileSize=1024*1024*DEF_MAX_SIZE_MB;//服务端保存的上传文件的最大限制
	private static String UploadRootName="products/1";//服务端保存的上传文件的根目录
	/*
	 * 处理服务端保存的上传的根目录
	 */
	private static String dealRootDir(HttpServletRequest request) {
		String upLoadFilePath=request.getServletContext().getRealPath(UploadRootName);
		File vRootDir=new File(upLoadFilePath);
		if(!vRootDir.exists()&&!vRootDir.isDirectory()) {
			vRootDir.mkdir();
		}
		return upLoadFilePath;
	}
   /*
    * 上传文件的扩展名列表
    */
	private static List<String> fileExtList=Arrays.asList("avi","mp4","mp3","rmvb","jpg","png","gif");
	/*
	 * 获得上传文件的扩展名列表
	 */
	public static List<String> getFileExtList() {
		return fileExtList;
		
	}
	public static UploadFileResult uploadFile(HttpServletRequest request) {
		System.out.println("进来了");
		UploadFileResult result = new UploadFileResult();
		String uploaddFilePath = dealRootDir(request);
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(UploadMaxFileSize);
			try {
				
				List<FileItem> items = upload.parseRequest(request); 
				File saveFile=null;
				boolean isUnallowedType=false;
				for(FileItem item:items) {
					isUnallowedType=false;
					if(item.isFormField()) {//普通表单字段
						String name = item.getFieldName();//表单字段的name的属性
						String value=item.getString("utf-8");
						System.out.println("name"+"value"+"..........");
						request.setAttribute(name, value);//将parameter的内容放到attribute里
					}else {//文件表单字段
						String filename=item.getName();
						if(filename.length()>0) {
							int index=filename.lastIndexOf(".");//查找.字段
							String extName=index==-1?"":filename.substring(index+1).toLowerCase();
							if(getFileExtList().contains(extName)) {//判断文件扩展名是否在范围内
								File fullFile=new File(item.getName());
								String oldName=fullFile.getName();
								String newName=oldName;
								//使用UUID随机生成唯一个名称把-替换
								String uuid=UUID.randomUUID().toString().replace("-", "");
								newName=uuid;
								//newName=newName+"."+extName;
								newName=newName+"."+extName;
								saveFile=new File(uploaddFilePath,newName);
								item.write(saveFile);
								
								//上传成功
								result.setCode(0);
								result.setDesc(String.format("/%s/%s", UploadRootName,newName));
								System.out.println("上传成功");
							}else {
								isUnallowedType = true;// 上传失败
							   }
													
							}
						}
					if(isUnallowedType) {
						result.setCode(-2);
						result.setDesc("图片上传失败，文件类型只能是："+getFileExtList().toString());
					}
				}
			} catch (FileUploadBase.SizeLimitExceededException e) {
				// TODO: handle exception
				result.setCode(-99);
				result.setDesc("图片上传失败："+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
