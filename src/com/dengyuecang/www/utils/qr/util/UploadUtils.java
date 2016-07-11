package com.dengyuecang.www.utils.qr.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.dengyuecang.www.utils.PropertiesUtils;


public class UploadUtils {
	
	private static final Logger log = LoggerFactory.getLogger(UploadUtils.class);
	
	public static String excelFolder = "upload/excel/";

	public static String getPropertiesValue(String propertyName) {
		Properties properties = PropertiesUtils
				.getProperties("conf.properties");
		String value = properties.getProperty(propertyName);
		log.info("存储路径："+value);
		return value;
	}

	// 处理图片路径问题
	/**
	 * 给图片前面加上服务器的链接
	 * @param imgPath
	 * @return
	 */
	public static String handlerImgStr(String imgPath) {
		if (imgPath != null && !"".equals(imgPath)) {
			if (imgPath.startsWith("http")) {
				return imgPath;
			} else {
				String img = getPropertiesValue("server")
						+ getPropertiesValue("focus.img.path") + imgPath;
				return img;
			}
		}
		return "";
	}
	
	/**
	 * 给图片前面加上服务器的链接
	 * @param imgPath
	 * @return
	 */
	public static String handlerQuestionImgStr(String imgPath) {
		if (imgPath != null && !"".equals(imgPath)) {
			if (imgPath.startsWith("http")) {
				return imgPath;
			} else {
				String img = getPropertiesValue("server")
						+ getPropertiesValue("question.img.path") + imgPath;
				return img;
			}
		}
		return "";
	}

	// 处理图片路径问题
	public static String getImgName(String all) {
		if (all != null && !"".equals(all)) {
			if (all.startsWith("http")) {
				String imagePath = getPropertiesValue("server")
						+ getPropertiesValue("img.path");
				String imgName = all.replace(imagePath, "");
				System.out.println(imgName);
				return imgName;
			} else {
				return all;
			}
		}
		return "";
	}

	/**
	 * 获取Tomcat下webapps的路径
	 * 分为linux系统下和windows系统下两种
	 * @return tomcat下webapps文件夹绝对路径
	 */
	public static  String getWebAppsDir(){
		String path = "";
		try {
//			windows
//			path = ContextLoader.getCurrentWebApplicationContext().getResource("\\").getFile().getAbsolutePath()+"\\";
			//linux
			path = ContextLoader.getCurrentWebApplicationContext().getResource("/").getFile().getAbsolutePath()+"/";
			log.info("系统路径:"+path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Linux服务器
		int pos = path.lastIndexOf("/", path.length() - 1);
		pos = path.lastIndexOf("/", pos - 1);
		path = path.substring(0, pos + 1);
		return path;
		
		//Windows 本地
//		 int last = path.lastIndexOf("\\");
//		 String temp = path.substring(0,last);
//		 int twiceLast = temp.lastIndexOf("\\");
//		 String finalStr = temp.substring(0, twiceLast+1);
//		 return finalStr;
	}
	
	/**
	 * 返回上传目标文件夹的路径(绝对路径)
	 * @return
	 */
	public static String getFilePathByPropertyName(String propertyName){
		return getWebAppsDir()+getPropertiesValue(propertyName);
	}
	
	/**
	 * 返回excel生成目录的文件夹路径
	 * @return
	 */
	public static String getExcelPath(){
		return getWebAppsDir()+excelFolder;
	}
	
	public static String getAppPath(){
		return getFilePathByPropertyName("app.path");
	}
	
	
	/**
	 * 描述:返回一个根据当前时间生成的文件名
	 * @param originName
	 * @return
	 */
	public static String getCurrentTimeFileName(String originName){
		String timespan = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+1;// 当前时间转化为yyyymmddhhmmss;
		String ext = originName.substring(originName.lastIndexOf(".") + 1);
		String targetFileName =timespan + "."+ ext;
		log.info("保存文件名："+targetFileName);
		return targetFileName;
	}
	/**
	 * 根据当前时间 生成一个excel的文件
	 * @return
	 */
	public static String getCurrentTimeFileNameOfExcel(){
		String timespan = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+1;// 当前时间转化为yyyymmddhhmmss;
		String targetFileName =timespan + "."+ "xls";
		return targetFileName;
	}
	
	/**
	 * 根据当前时间 生成一个excel的文件 大数据导出时
	 * @return
	 */
	public static String getCurrentTimeFileNameOfBigExcel(){
		String timespan = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+1;// 当前时间转化为yyyymmddhhmmss;
		String targetFileName =timespan + "."+ "xlsx";
		return targetFileName;
	}
	
	/**
	 * 上传焦点图片
	 * @param file
	 * @param propertyName
	 * @return
	 */
	public static String uploadFocusImg(MultipartFile file){
		String newFileName = UploadUtils.getCurrentTimeFileName(file.getOriginalFilename());
		String targetFilePath = getFilePathByPropertyName("focus.img.path");
		File folder = new File(targetFilePath);
		File targetFile = new File(targetFilePath+newFileName);
		if(!folder.exists()&&!folder.isDirectory()){
			folder.mkdir();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetFile.getName();
	}
	
	/**
	 * 上传帮助与反馈图片
	 * @param file
	 * @param propertyName
	 * @return
	 */
	public static String uploadQuestionImg(MultipartFile file){
		String newFileName = UploadUtils.getCurrentTimeFileName(file.getOriginalFilename());
		String targetFilePath = getFilePathByPropertyName("question.img.path");
		File folder = new File(targetFilePath);
		File targetFile = new File(targetFilePath+newFileName);
		if(!folder.exists()&&!folder.isDirectory()){
			folder.mkdir();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetFile.getName();
	}
	/**
	 * 上传excel文件
	 * @param file
	 * @return
	 */
	public static String uploadExcelFile(MultipartFile file){
		String newFileName = UploadUtils.getCurrentTimeFileName(file.getOriginalFilename());
		String targetFilePath = getExcelPath();
		File folder = new File(targetFilePath);
		File targetFile = new File(targetFilePath+newFileName);
		if(!folder.exists()&&!folder.isDirectory()){
			folder.mkdir();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetFile.getName();
	}
	
	/**
	 * 获取excel文件的绝对路径
	 */
	public static String getRealPathOfExcel(String excelName){
		String excelFolderPath = getExcelPath();
		return excelFolderPath+excelName; 
	}
	
}
