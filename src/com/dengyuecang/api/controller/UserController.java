package com.dengyuecang.api.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dengyuecang.api.entity.Member;
import com.dengyuecang.api.service.IMemberService;
import com.dengyuecang.api.utils.HeaderUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private IMemberService memberServiceImpl;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public RespData register(String json,@RequestHeader HttpHeaders headers){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		try {
			
			resp = memberServiceImpl.registerAndLogin(json, headers);
		
		} catch (Exception e) {
			resp = RespCode.getRespData(RespCode.ERROR);
		}
		
		return resp;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public RespData login(String json,@RequestHeader HttpHeaders headers){
		
		return memberServiceImpl.login(json, headers);
		
	}
}
