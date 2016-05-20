package com.dengyuecang.api.service.common;


/**
 * 基础信息类
 * @author acang
 *
 */
public class CommonConstant {
	
	
	/**
	 * 注册途径
	 */
	public static final String REGISTER_CHANNEL_WEIXIN = "weixin";
	public static final String REGISTER_CHANNEL_WEIBO = "weibo";
	public static final String REGISTER_CHANNEL_QQ = "qq";
	public static final String REGISTER_CHANNEL_APP = "app";
	
	/**
	 * 静态资源文件的网络访问路径+“资源用途名称”+“文件名”
	 * 暂时：上线时需要修改为
	 */
//	public static final String STATIC_URL = "http://test.static.dengyuecang.com";//开发
	public static final String STATIC_URL = "http://static.dengyuecang.com";//生产
	
	
	/**
	 * 二维码相关目录
	 */
	//输出路径
//	public static final String QR_OUT_PATH = "/Users/acang/Downloads/";//开发
	public static final String QR_OUT_PATH = "/home/soft/tomcat7/webapps/";//生产
	
	//中间logo源路径
//	public static final String QR_SOURCE_PATH = "/Users/acang/Downloads/dyc.jpg";//开发
	public static final String QR_SOURCE_PATH = "/home/soft/tomcat7/webapps/source/dyc.jpg";//生产
	
	
	
	
	/**
	 * 分享下载地址
	 */
	public static final String DOWNLOADURL = "http://api.dengyuecang.com/qr/index";
	
}
