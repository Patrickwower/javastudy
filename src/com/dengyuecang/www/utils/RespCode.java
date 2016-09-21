package com.dengyuecang.www.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class RespCode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private static Map<String,RespCode> respCodeMap=new HashMap<String,RespCode>();
	
	public RespCode(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	//头部类
	
	public static String HEADER_DEVICE_AGENT_NEEDED = "102";
	
	public static String HEADER_DEVICE_TYPE_NEEDED = "103";
	
	public static String HEADER_DEVICE_VERSION_NEEDED = "104";
	
	public static String HEADER_DEVICE_ID_NEEDED = "105";
	
	public static String HEADER_CHANNEL_NEEDED = "106";
	
	public static String HEADER_VERSION_NEEDED = "107";
	
	public static String HEADER_MEMBERID_NEEDED = "108";
	
	public static String HEADER_MEMBERTOKEN_NEEDED = "109";
	
	public static String HEADER_MEMBERTOKEN_ERROR = "110";
	
	public static String HEADER_MEMBERTOKEN_TIMEOUT = "111";
	
	//通用类
	public static String ERROR="-1";
	
	public static String SUCESS="0";
	
	public static String UNKNOW_EXCEPTION = "-2";
	
	public static String MEMBER_NOT_EXIST = "1002";

	public static String WEIXIN_EXIST = "1014";

	public static String STATIC_RESOURCE_NOTFOUND = "1012";

	public static String UNLOGIN = "1113";
	
	//注册类
	public static String MOBILE_REGISTERED = "1003";
	
	public static String MOBILE_BOUND = "1004";
	
	public static String MOBILE_OPENID = "1006";

	//注册时的数据验证
	public static String PWD_NEEDED= "1007";

	public static String NICKNAME_NEEDED = "1008";

	public static String ICON_NEEDED = "1009";

	public static String GENDER_NEEDED = "1010";

	public static String WEIBO_WEIXIN_QQ_NEEDED = "1011";

	public static String MOBILE_CODE_ERROR = "1013";

	//发布系统登录异常
	public static String INVATE_CODE_NEED = "1201";

	public static String INVATE_CODE_USED = "1202";

	public static String INVATE_CODE_NOT_PUBLISH = "1203";

	public static String INVATE_CODE_NOT_EXIST = "1204";






	//登录类
	public static String LOGIN_MOBILE_NEEDED = "2001";

	public static String LOGIN_PWD_NEEDED = "2002";
	
	public static String MEMBER_NOT_EXIST_OR_PWD_ERROR = "10";
	
	//修改会员信息类
	public static String UPDATE_NICKNAME_EXCEPTION = "2101";
	
	public static String UPDATE_HEAD_EXCEPTION = "2102";

	public static String UPDATE_INTRODUCTION_EXCEPTION = "2102";


	//管理平台权限部分异常
	public static String USERNAME_OR_PWD_WRONG = "3001";

	//社区类
	//社区话题
//	public static String

	//文章类异常
	public static String ARTICLE_NOT_EXIST = "4001";

	public static String ARTICLE_ALREADY_INDEX = "4002";

	public static String ARTICLE_ALREADY_BANNER = "4003";



	//评论类异常
	public static String COMMENT_ID_NEED = "5001";

	public static String COMMENT_NOT_EXIST = "5002";

	public static String COMMENT_NOT_CURRENT_MEMBER = "5003";

	
	public static String MOBILE_NOVALID="11";
	
	public static String PWD_NOVALID="12";
	
	public static String CAPTCHA_NOVALID="13";
	
	public static String USER_NOT_EXIST="14";
	
	public static String CAPTCHA_ERROR="15";
	
	public static String OLDPWD_ERROR="16";
	
	public static String REG_MOBILE_EXISTS="17";
	
	public static String REG_MARKET_EXISTS="37";
	
	public static String LOGIN_PWD_ERROR="18";
	
	public static String SEND_CAPTCHA_TYPE_NOVALID="19";
	
	public static String SEND_CAPTCHA_TOO_FAST="20";
	
	public static String SERVER_APPVER_NONEXIS="21";
	
	public static String FILE_NONEXIS="22";
	
	public static String MSG_RESULT_ERROR="23";
	
	public static String STATISTICS_TYPE_ERROR="24";
	
	public static String AUT_TYPE_ERROR="25";
	
	public static String NUMBER_LIMITE	="26";
	
	public static String JSON_ERROR	="27";
	
	public static String FRIEND_ALREADY_EXIST = "28";
	
	public static String ALREADY_INVITED ="29";
	
	public static String NO_MORE_FOCUS ="30";
	
	public static String NO_AUTHORITY="99";
	public static String USER_FREEZE="98";
	public static String VISITOR_NO_AUTHORITY="97";
	
	public static String LIMIT_VISIT="200";
	public static String LIMIT_VISIT_CODE="201";
	public static String LIMIT_VISIT_CODE_NOT="202";
	public static String LIMIT_VISIT_TOTAL_CODE_NOT="203";
	
	public static String FOCUS_PRAISE="204";//当前焦点文章已点赞
	public static String USER_SEARCH_LIMIT="205";
	public static String CONNECT_COMPANY_FAIL = "206";
	public static String MATCHABLE_TRUE = "207";
	public static String MATCHABLE_FALSE = "208";
	public static String PASSCARD_OVERLIMIT = "209";
	public static String MOBILE_ONLY ="210";
	public static String VISIT_COMPANY_LIMIT ="211";
	public static String WILL_STOP_SERVE = "212";
	
	public static String USER_NOT_XIAJI = "222";
	public static String PARAM_EMPTY = "223";
	public static String HEADER_PARAM_ERROR = "224";
	
	static{
		
		respCodeMap.put(HEADER_CHANNEL_NEEDED,new RespCode(HEADER_CHANNEL_NEEDED,"header中 channel 不能为空"));
		respCodeMap.put(HEADER_DEVICE_AGENT_NEEDED,new RespCode(HEADER_DEVICE_AGENT_NEEDED,"header中 deviceAgent不能为空"));
		respCodeMap.put(HEADER_DEVICE_ID_NEEDED,new RespCode(HEADER_DEVICE_ID_NEEDED,"header中 deviceId不能为空"));
		respCodeMap.put(HEADER_DEVICE_TYPE_NEEDED,new RespCode(HEADER_DEVICE_TYPE_NEEDED,"header中 deviceType不能为空"));
		respCodeMap.put(HEADER_DEVICE_VERSION_NEEDED,new RespCode(HEADER_DEVICE_VERSION_NEEDED,"header中 deviceVersion不能为空"));
		respCodeMap.put(HEADER_VERSION_NEEDED,new RespCode(HEADER_VERSION_NEEDED,"header中 version不能为空"));
		respCodeMap.put(HEADER_MEMBERID_NEEDED,new RespCode(HEADER_MEMBERID_NEEDED,"header中 memberId不能为空"));
		respCodeMap.put(HEADER_MEMBERTOKEN_NEEDED,new RespCode(HEADER_MEMBERTOKEN_NEEDED,"header中 memberToken不能为空"));
		respCodeMap.put(HEADER_MEMBERTOKEN_ERROR,new RespCode(HEADER_MEMBERTOKEN_ERROR,"memberToken验证失败"));
		respCodeMap.put(HEADER_MEMBERTOKEN_TIMEOUT,new RespCode(HEADER_MEMBERTOKEN_TIMEOUT,"memberToken过期失效"));
		
		
		respCodeMap.put(UNKNOW_EXCEPTION,new RespCode(UNKNOW_EXCEPTION,"未知异常"));
		respCodeMap.put(MEMBER_NOT_EXIST,new RespCode(MEMBER_NOT_EXIST,"会员不存在，请先注册"));
		respCodeMap.put(MOBILE_REGISTERED,new RespCode(MOBILE_REGISTERED,"手机已注册，请登录"));
		respCodeMap.put(MOBILE_BOUND,new RespCode(MOBILE_BOUND,"手机已绑定其他会员"));
		respCodeMap.put(MOBILE_OPENID,new RespCode(MOBILE_OPENID,"mobile和openId必须有一项不为空"));
		respCodeMap.put(UNLOGIN,new RespCode(UNLOGIN,"请先登陆"));
		respCodeMap.put(WEIXIN_EXIST,new RespCode(WEIXIN_EXIST,"微信号已绑定其他账号"));


		//文章类异常
		respCodeMap.put(ARTICLE_NOT_EXIST,new RespCode(ARTICLE_NOT_EXIST,"文章不存在"));

		respCodeMap.put(ARTICLE_ALREADY_INDEX,new RespCode(ARTICLE_ALREADY_INDEX,"文章已在首页列表"));

		respCodeMap.put(ARTICLE_ALREADY_BANNER,new RespCode(ARTICLE_ALREADY_BANNER,"文章已在banner列表"));


		//评论类异常描述
		respCodeMap.put(COMMENT_ID_NEED,new RespCode(COMMENT_ID_NEED,"需要评论id"));

		respCodeMap.put(COMMENT_NOT_EXIST,new RespCode(COMMENT_NOT_EXIST,"该评论不存在"));

		respCodeMap.put(COMMENT_NOT_CURRENT_MEMBER,new RespCode(COMMENT_NOT_CURRENT_MEMBER,"非当前用户的评论"));


		respCodeMap.put(PWD_NEEDED,new RespCode(PWD_NEEDED,"密码不能为空"));
		
		respCodeMap.put(NICKNAME_NEEDED,new RespCode(NICKNAME_NEEDED,"昵称不能为空"));
		
		respCodeMap.put(ICON_NEEDED,new RespCode(ICON_NEEDED,"头像不能为空"));
		
		respCodeMap.put(GENDER_NEEDED,new RespCode(GENDER_NEEDED,"性别不能为空"));
		
		respCodeMap.put(WEIBO_WEIXIN_QQ_NEEDED,new RespCode(WEIBO_WEIXIN_QQ_NEEDED,"微博，微信，qq授权登录信息，必须有一项不为空"));

		respCodeMap.put(MOBILE_CODE_ERROR,new RespCode(MOBILE_CODE_ERROR,"短信验证码错误"));
		
		respCodeMap.put(LOGIN_MOBILE_NEEDED,new RespCode(LOGIN_MOBILE_NEEDED,"mobile不能为空"));

		respCodeMap.put(LOGIN_PWD_NEEDED,new RespCode(LOGIN_PWD_NEEDED,"pwd不能为空"));
		
		respCodeMap.put(MEMBER_NOT_EXIST_OR_PWD_ERROR,new RespCode(MEMBER_NOT_EXIST_OR_PWD_ERROR,"用户不存在或密码错误"));
		
		respCodeMap.put(UPDATE_NICKNAME_EXCEPTION,new RespCode(UPDATE_NICKNAME_EXCEPTION,"昵称修改发生异常"));
		
		respCodeMap.put(UPDATE_HEAD_EXCEPTION,new RespCode(UPDATE_HEAD_EXCEPTION,"头像修改发生异常"));

		respCodeMap.put(UPDATE_INTRODUCTION_EXCEPTION,new RespCode(UPDATE_INTRODUCTION_EXCEPTION,"介绍修改发生异常"));

		respCodeMap.put(USERNAME_OR_PWD_WRONG,new RespCode(USERNAME_OR_PWD_WRONG,"管理平台,用户名或密码错误"));


		//


		respCodeMap.put(INVATE_CODE_NEED,new RespCode(INVATE_CODE_NEED,"邀请码必须提供"));

		respCodeMap.put(INVATE_CODE_NOT_PUBLISH,new RespCode(INVATE_CODE_NOT_PUBLISH,"邀请码未发布"));

		respCodeMap.put(INVATE_CODE_USED,new RespCode(INVATE_CODE_USED,"邀请码已使用"));

		respCodeMap.put(INVATE_CODE_NOT_EXIST,new RespCode(INVATE_CODE_NOT_EXIST,"邀请码不存在,请核对后输入"));
		
		
		respCodeMap.put(SUCESS,new RespCode(SUCESS,"操作成功"));
		respCodeMap.put(MOBILE_NOVALID,new RespCode(MOBILE_NOVALID,"手机号码不符合规则"));
		respCodeMap.put(PWD_NOVALID,new RespCode(PWD_NOVALID,"密码不符合规则"));
		respCodeMap.put(CAPTCHA_NOVALID,new RespCode(CAPTCHA_NOVALID,"验证码不符合规则"));
		respCodeMap.put(CAPTCHA_ERROR,new RespCode(CAPTCHA_ERROR,"验证码错误"));
		respCodeMap.put(OLDPWD_ERROR,new RespCode(OLDPWD_ERROR,"原密码错误"));
		respCodeMap.put(NUMBER_LIMITE,new RespCode(NUMBER_LIMITE,"数量超过限制"));
		respCodeMap.put(JSON_ERROR,new RespCode(JSON_ERROR,"参数错误"));
		respCodeMap.put(FRIEND_ALREADY_EXIST,new RespCode(FRIEND_ALREADY_EXIST,"已经是好友"));
		respCodeMap.put(REG_MOBILE_EXISTS,new RespCode(REG_MOBILE_EXISTS,"用户已经存在"));
		respCodeMap.put(REG_MARKET_EXISTS,new RespCode(REG_MARKET_EXISTS,"市场信息已存在"));
		respCodeMap.put(ALREADY_INVITED,new RespCode(ALREADY_INVITED,"已经邀请过"));
		respCodeMap.put(NO_MORE_FOCUS,new RespCode(NO_MORE_FOCUS,"没有焦点了"));
		respCodeMap.put(USER_NOT_XIAJI,new RespCode(USER_NOT_XIAJI,"该员工没有下级"));
		respCodeMap.put(PARAM_EMPTY,new RespCode(PARAM_EMPTY,"参数不能为空"));
		
		respCodeMap.put(HEADER_PARAM_ERROR,new RespCode(HEADER_PARAM_ERROR,"请求头参数异常"));
		
		respCodeMap.put(USER_NOT_EXIST,new RespCode(USER_NOT_EXIST,"用户不存在"));
		respCodeMap.put(LOGIN_PWD_ERROR,new RespCode(LOGIN_PWD_ERROR,"密码错误"));
		
		respCodeMap.put(SEND_CAPTCHA_TYPE_NOVALID,new RespCode(SEND_CAPTCHA_TYPE_NOVALID,"验证码类型错误"));
		respCodeMap.put(SEND_CAPTCHA_TOO_FAST,new RespCode(SEND_CAPTCHA_TOO_FAST,"请求验证码过快，不发送短信"));
		
		
		respCodeMap.put(SERVER_APPVER_NONEXIS,new RespCode(SERVER_APPVER_NONEXIS,"服务端不存在此应用的版本"));
		respCodeMap.put(FILE_NONEXIS,new RespCode(SERVER_APPVER_NONEXIS,"上传文件不存在"));
		
		//短信
		respCodeMap.put(MSG_RESULT_ERROR,new RespCode(MSG_RESULT_ERROR,"短信通道返回状态失败"));
		
		respCodeMap.put(STATISTICS_TYPE_ERROR,new RespCode(STATISTICS_TYPE_ERROR,"统计类型错误"));
		respCodeMap.put(AUT_TYPE_ERROR,new RespCode(AUT_TYPE_ERROR,"认证类型错误"));
		
		respCodeMap.put(NO_AUTHORITY,new RespCode(NO_AUTHORITY,"请先登录"));
		respCodeMap.put(USER_FREEZE,new RespCode(USER_FREEZE,"用户已冻结"));
		respCodeMap.put(VISITOR_NO_AUTHORITY,new RespCode(VISITOR_NO_AUTHORITY,"请先注册"));
		respCodeMap.put(LIMIT_VISIT,new RespCode(LIMIT_VISIT,"当天访问限制"));
		respCodeMap.put(LIMIT_VISIT_CODE,new RespCode(LIMIT_VISIT_CODE,"验证码通过"));
		respCodeMap.put(LIMIT_VISIT_CODE_NOT,new RespCode(LIMIT_VISIT_CODE_NOT,"验证码不通过"));
		respCodeMap.put(LIMIT_VISIT_TOTAL_CODE_NOT,new RespCode(LIMIT_VISIT_TOTAL_CODE_NOT,"当天访问企业已达上限"));
		respCodeMap.put(FOCUS_PRAISE,new RespCode(FOCUS_PRAISE,"当前焦点文章已点赞"));
		respCodeMap.put(CONNECT_COMPANY_FAIL,new RespCode(CONNECT_COMPANY_FAIL,"关联公司失败"));
		respCodeMap.put(USER_SEARCH_LIMIT,new RespCode(USER_SEARCH_LIMIT,"查询次数超过限制"));
		respCodeMap.put(MATCHABLE_TRUE,new RespCode(MATCHABLE_TRUE,"能够自动关联公司"));
		respCodeMap.put(MATCHABLE_FALSE,new RespCode(MATCHABLE_FALSE,"不能关联公司"));
		respCodeMap.put(PASSCARD_OVERLIMIT, new RespCode(MATCHABLE_FALSE,"发送短信次数超出限制"));
		respCodeMap.put(MOBILE_ONLY, new RespCode(MOBILE_ONLY,"请使用手机访问"));
		respCodeMap.put(VISIT_COMPANY_LIMIT, new RespCode(VISIT_COMPANY_LIMIT,"访问公司限制"));
		respCodeMap.put(WILL_STOP_SERVE, new RespCode(WILL_STOP_SERVE,"此版本即将停止服务，请尽快升级新版本"));
		respCodeMap.put(ERROR,new RespCode(ERROR,"系统异常"));
	}
	
	public static RespData getRespData(String type){
		RespData respData=new RespData();
		respData.setRespCode(respCodeMap.get(type).getCode());
		respData.setRespDesc(respCodeMap.get(type).getMsg());
		return respData;
	}
	
	/**
	 * 封装easyUI数据表哥返回格式
	 * @param type
	 * @param data
	 * @return
	 */
	public static RespData getdataGrid(Object data,Long total){
		RespData respData=new RespData();
		respData.setRows(data);
		respData.setTotal(total);
		return respData;
	}

	public static RespData getRespData(String type,Object data){
		RespData respData=new RespData();
		respData.setRespCode(respCodeMap.get(type).getCode());
		respData.setRespDesc(respCodeMap.get(type).getMsg());
		respData.setData(data);
		return respData;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
