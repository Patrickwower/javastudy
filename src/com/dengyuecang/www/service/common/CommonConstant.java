package com.dengyuecang.www.service.common;


/**
 * 基础信息类
 * @author acang
 *
 */
public class CommonConstant {
//	af32b7f0c2fd22319eb877923fbf99c1
	
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
	public static final String STATIC_URL = "http://test.static.dengyuecang.com";//开发
//	public static final String STATIC_URL = "http://localhost:8080/dyc";//开发
//	public static final String STATIC_URL = "http://static.dengyuecang.com";//生产
	
	
	/**
	 * 二维码相关目录
	 */
	//输出路径
//	public static final String QR_OUT_PATH = "/Users/acang/Downloads/";//本地
	public static final String QR_OUT_PATH = "/home/soft/tomcat7/webapps/";//生产
	
	//中间logo源路径
//	public static final String QR_SOURCE_PATH = "/Users/acang/Downloads/dyc.jpg";//本地
	public static final String QR_SOURCE_PATH = "/home/soft/tomcat7/webapps/source/dyc.jpg";//生产
	
	
	
	
	/**
	 * 分享下载地址
	 */
	public static final String DOWNLOADURL = "http://api.dengyuecang.com/qr/index";


	/**
	 * 文章详情地址
	 */
	public static final String ARTICLE_DETAIL_URL = "http://test.app.dengyuecang.com/article_h5.html?articleId=";

//	public static final String ARTICLE_DETAIL_URL = "http://app.dengyuecang.com/article_h5.html?articleId=";


}
