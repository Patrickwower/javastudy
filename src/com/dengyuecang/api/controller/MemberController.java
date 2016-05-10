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
@RequestMapping("/member")
public class MemberController {
	
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
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		return memberServiceImpl.login(json, headers);
		
	}
	
	/**
	 * 提供反馈信息，填写身份，需求，功能三项
	 */
	@RequestMapping(value="/setMemberInfo",method=RequestMethod.POST)
	@ResponseBody
	public RespData setMemberInfo(@RequestHeader HttpHeaders headers,String json){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		try {
			resp = memberServiceImpl.setAllInfo(headers, json);
		} catch (Exception e) {
			
		}
		
		
		return resp;
	}
	
	
	/**
	 * 获取所有信息
	 * @return
	 */
	@RequestMapping(value="/memberInfo",method=RequestMethod.POST)
	@ResponseBody
	public RespData getAllMemberInfo(@RequestHeader HttpHeaders headers){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		return memberServiceImpl.getAllInfo(headers);	
	}
	
	/**
	 * 绑定手机号
	 */
	@RequestMapping(value="/bindMobile",method=RequestMethod.POST)
	@ResponseBody
	public RespData bindMobile(@RequestHeader HttpHeaders headers, String json){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		return memberServiceImpl.bindMobile(headers, json);	
		
	}
	
	
	
}
