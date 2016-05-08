package com.dengyuecang.api.utils;

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
	
	public static String ERROR="-1";
	
	public static String SUCESS="0";
	
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
