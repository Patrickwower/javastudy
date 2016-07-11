package com.dengyuecang.www.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;


/**
 * 通用函数
 * @author botao
 *
 */
public class CommonUtils {
	
	private static String uploadNumber ;  //1测试 (生产) 2开发
	private static Properties properties;
	static{
		properties = PropertiesUtils.getProperties("config.properties");
		uploadNumber = properties.getProperty("uploadNumber");
		
	}
	

	/**
	 * 通用上传图片功能
	 * @param file spring的上传文件
	 * @param uploadFile 对应conf.properties文件中要上传到的目录
	 * @return 图片名称
	 * @throws IOException 
	 */
	public static String commonUploadImage(MultipartFile[] files,HttpSession session,String uploadFile) throws IOException{
		//读取配置文件
		String path = properties.getProperty(uploadFile);
		String image_name = null;
		for(MultipartFile file:files){
			String fileName = UUID.randomUUID().toString();
			String ext = file.getOriginalFilename().substring(file.getName().lastIndexOf(".") + 1);
			File tempFile = new File(getImgDir(session) + path + fileName+ "." + ext);
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			file.transferTo(tempFile);
			if(image_name == null){
				image_name = tempFile.getName(); 				
			}else{
				image_name += ","+tempFile.getName();
			}
		}
		return image_name;
	}
	
	/**
	 * 获取文件地址
	 * @param session
	 * @return
	 */
	private static String getImgDir(HttpSession session) {
		if(uploadNumber.equals("1")){
			//服务器
			 String path=session.getServletContext().getRealPath("/");
			 int pos=path.lastIndexOf("/", path.length()-1);
			 pos=path.lastIndexOf("/", pos-1);
			 path=path.substring(0, pos+1);
			 System.out.println("====================="+path);
			 return path;
		}else{
			//本地
			String serverPath = session.getServletContext().getRealPath("/");
			int last = serverPath.lastIndexOf("\\");
			String temp = serverPath.substring(0, last);
			int twiceLast = temp.lastIndexOf("\\");
			String finalStr = temp.substring(0, twiceLast + 1);
			return finalStr;
		}
	}
	
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * @param long1 第一点经度
	 * @param lat1  第一点纬度
	 * @param long2 第二点经度
	 * @param lat2  第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double Distance(double long1, double lat1, double long2,double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2* R* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)* Math.cos(lat2) * sb2 * sb2));
		return d;
	}
	
	/**
	 * 非空判断
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if("".equals(str) || null == str){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 非空判断
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if("".equals(str) || null == str){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
		   date = format.parse(str);
	   }catch (ParseException e) {
		   e.printStackTrace();
	   }
	   return date;
	}
	
}
