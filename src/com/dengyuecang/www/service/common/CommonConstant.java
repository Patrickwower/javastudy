package com.dengyuecang.www.service.common;


import java.util.HashMap;
import java.util.Map;

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
//	public static final String STATIC_URL = "http://test.static.dengyuecang.com";//开发
//	public static final String STATIC_URL = "http://localhost:8080/dyc";//开发
	public static final String STATIC_URL = "http://static.dengyuecang.com";//生产
	
	
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
	public static final String ARTICLE_DETAIL_URL = "http://test.manager.dengyuecang.com/dyc/community/article/detail?articleId=";

//	public static final String ARTICLE_DETAIL_URL = "http://manager.dengyuecang.com/dyc/community/article/detail?articleId=";


	/**
	 * 文章分享页面
	 */
	public static final String ARTICLE_SHARE_URL = "http://test.manager.dengyuecang.com/dyc/community/article/share?articleId=";

//	public static final String ARTICLE_SHARE_URL = "http://manager.dengyuecang.com/dyc/community/article/share?articleId=";

	/**
	 * 短信验证码是否验证,true为不验证,false为验证
	 */
	public static final boolean SDK_MSG_LOCK = false;

	/**
	 * 是否直接在文章发布时,直接插入首页热门,true为直接插入,false为不插入
	 */
	public static final boolean PUBLISH_TO_INDEX_HOT = true;

	/**
	 * 文章状态
	 */
	public static final Map<String,String> ARTICLE_STATUS = new HashMap<String,String>();

	static {

		ARTICLE_STATUS.put("50","草稿");

		ARTICLE_STATUS.put("100","正常");

		ARTICLE_STATUS.put("200","禁用");

		ARTICLE_STATUS.put("300","删除");

	}

	/**
	 * banner状态
	 */
	public static final Map<String,String> BANNER_STATUS = new HashMap<String,String>();

	static {

		BANNER_STATUS.put("100","上线");

		BANNER_STATUS.put("200","下线");

	}

	/**
	 * 首页热门文章排序数量(最多排序到多少)
	 */

	public static final int INDEX_HOT_MAX_SORT = 30;


}
