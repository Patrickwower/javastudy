//package com.dengyuecang.api.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.dengyuecang.www.service.impl.model.WeixinUserinfo;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//public class WeixinOAuth2Util{
//	
//	private static final Logger log = LoggerFactory.getLogger(WeixinOAuth2Util.class);
//	
//	private static final String APPID = "wx15bc570b0d7d043b";
//	
//	private static final String SECRET = "9d993a9a42b0a55527da940c04ba38e3";
//	
//	private static final String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
//	
//	private static final String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
//	
//	private static final String getUserInfo = "https://api.weixin.qq.com/sns/userinfo";
//	
//    public static WeixinUserinfo oauth2(String code)throws Exception{
//    	
//    	Map<String, String> getTokenParams = new HashMap<String, String>();
//    	
//    	getTokenParams.put("appid", APPID);
//    	
//    	getTokenParams.put("secret", SECRET);
//    	
//    	getTokenParams.put("code", code);
//    	
//    	getTokenParams.put("grant_type", "authorization_code");
//    	
//    	//获取access_token
//    	String tokenJson = HttpUtil.sendPost(getTokenUrl, getTokenParams, null);
//    	
//    	log.info("获取token结果："+tokenJson);
//
//    	JSONObject tokenObj = JSONObject.fromObject(tokenJson);
//
//    	WeixinUserinfo userinfo = (WeixinUserinfo)JSONObject.toBean(tokenObj,WeixinUserinfo.class);
//    	
//    	log.info("access_token:"+userinfo.getAccess_token());
//    	
//    	if (userinfo!=null&&"snsapi_base".equals(userinfo.getScope())) {
//			return userinfo;
//		}
//    	
//    	Map<String, String> getUserinfoParams = new HashMap<String, String>();
//    	
//    	
//    	getUserinfoParams.put("openid", userinfo.getOpenid());
//    	
//    	getUserinfoParams.put("access_token", userinfo.getAccess_token());
//    	
//    	getUserinfoParams.put("lang", "zh_CN");
//    	
//    	//获取userinfo
//    	String userinfoJson = HttpUtil.sendPost(getUserInfo, getUserinfoParams, null);
//    	
//    	log.info("userinfo:"+userinfoJson);
//    	
//    	JSONObject userinfoObj = JSONObject.fromObject(userinfoJson);
//    	
//    	WeixinUserinfo userinfo2 = (WeixinUserinfo)JSONObject.toBean(userinfoObj,WeixinUserinfo.class);
//    	
//    	userinfo2.setAccess_token(userinfo.getAccess_token());
//    	userinfo2.setRefresh_token(userinfo.getRefresh_token());
//    	
//    	return userinfo2;
//    }
//    
////    private static WeixinUserinfo fromJsonstrToObject(Class clazz,String str){
////    	
////    	JSONObject jsonobject = JSONObject.fromObject(str);
////    	WeixinUserinfo WeixinUserinfo = null;
////    	  try {
////    	   //获取一个json数组
////    	   JSONArray array = jsonobject.getJSONArray("passports");
////    	   //将json数组 转换成 List<PassPortForLendsEntity>泛型
////    	   List<PassPortForLendsEntity> list = new ArrayList<PassPortForLendsEntity>();
////    	   for (int i = 0; i < array.size(); i++) {   
////    	            JSONObject object = (JSONObject)array.get(i);  
////    	            PassPortForLendsEntity passport = (PassPortForLendsEntity)JSONObject.toBean(object,
////    	              PassPortForLendsEntity.class);
////    	            if(passport != null){
////    	             list.add(passport);
////    	            }  
////    	         }
////    	   //转换PassportLendsEntity 实体类
////    	   passportlends = (PassportLendsEntity)JSONObject.toBean(jsonobject, PassportLendsEntity.class);
////    	
////    	
////    	return null;
////    }
//    
//}