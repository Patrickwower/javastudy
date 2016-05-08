package com.dengyuecang.api.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;

public class HeaderUtils {

	public static String checkHeader(HttpHeaders headers,boolean ifCheck){
		
		if (!ifCheck) {
			return "ok";
		}
		
		String userAgent = headers.getFirst("userAgent");
		String channelId = headers.getFirst("channelId");
		String productId = headers.getFirst("productId");
		String deviceId = headers.getFirst("deviceId");
		String memberId = headers.getFirst("memberId");
		String memberToken = headers.getFirst("memberToken");
		String appid = headers.getFirst("memberAgent");
		
		if (StringUtils.isEmpty(userAgent)) {
			return "userAgent参数为空";
		}
		
		if (StringUtils.isEmpty(channelId)) {
			return "channelId参数为空";
		}
		
		if (StringUtils.isEmpty(productId)) {
			return "productId参数为空";
		}
		
		if (StringUtils.isEmpty(deviceId)) {
			return "deviceId参数为空";
		}
		
		if (StringUtils.isEmpty(appid)) {
			return "appid参数为空";
		}
		
		
		return "ok";
	}
	
}
