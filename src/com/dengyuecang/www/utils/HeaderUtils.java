package com.dengyuecang.www.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;

public class HeaderUtils {

	public static String checkHeader(HttpHeaders headers,boolean ifCheck){
		
		if (!ifCheck) {
			return "ok";
		}
		
		String deviceAgent = headers.getFirst("deviceAgent");
		String deviceType = headers.getFirst("deviceType");
		String deviceVersion = headers.getFirst("deviceVersion");
		String deviceId = headers.getFirst("deviceId");
		String channel = headers.getFirst("channel");
		String memberId = headers.getFirst("memberId");
		String memberToken = headers.getFirst("memberToken");
		String version = headers.getFirst("version");
		
		if (StringUtils.isEmpty(deviceAgent)) {
			return RespCode.HEADER_DEVICE_AGENT_NEEDED;
		}
		
		if (StringUtils.isEmpty(deviceType)) {
			return RespCode.HEADER_DEVICE_TYPE_NEEDED;
		}
		
		if (StringUtils.isEmpty(deviceVersion)) {
			return RespCode.HEADER_DEVICE_VERSION_NEEDED;
		}

		if (StringUtils.isEmpty(deviceId)) {
			return RespCode.HEADER_DEVICE_ID_NEEDED;
		}
		
		if (StringUtils.isEmpty(channel)) {
			return RespCode.HEADER_CHANNEL_NEEDED;
		}
		
		if (StringUtils.isEmpty(version)) {
			return RespCode.HEADER_VERSION_NEEDED;
		}
		
		return "ok";
	}
	
	public static RespData check(HttpServletRequest request,boolean ifCheck){
		
		if (!ifCheck) {
			return RespCode.getRespData(RespCode.SUCESS,new HashMap<>());
		}
		
		String deviceAgent = request.getHeader("deviceAgent");
		String deviceType = request.getHeader("deviceType");
		String deviceVersion = request.getHeader("deviceVersion");
		String deviceId = request.getHeader("deviceId");
		String channel = request.getHeader("channel");
		String memberId = request.getHeader("memberId");
		String memberToken = request.getHeader("memberToken");
		String version = request.getHeader("version");
		
		if (StringUtils.isEmpty(deviceAgent)) {
			return RespCode.getRespData(RespCode.HEADER_DEVICE_AGENT_NEEDED,new HashMap<>());
		}
		
		if (StringUtils.isEmpty(deviceType)) {
			return RespCode.getRespData(RespCode.HEADER_DEVICE_TYPE_NEEDED,new HashMap<>());
		}
		
		if (StringUtils.isEmpty(deviceVersion)) {
			return RespCode.getRespData(RespCode.HEADER_DEVICE_VERSION_NEEDED,new HashMap<>());
		}

		if (StringUtils.isEmpty(deviceId)) {
			return RespCode.getRespData(RespCode.HEADER_DEVICE_ID_NEEDED,new HashMap<>());
		}
		
		if (StringUtils.isEmpty(channel)) {
			return RespCode.getRespData(RespCode.HEADER_CHANNEL_NEEDED,new HashMap<>());
		}
		
		if (StringUtils.isEmpty(version)) {
			return RespCode.getRespData(RespCode.HEADER_VERSION_NEEDED,new HashMap<>());
		}
		
		return RespCode.getRespData(RespCode.SUCESS,new HashMap<>());
	}
	
}
