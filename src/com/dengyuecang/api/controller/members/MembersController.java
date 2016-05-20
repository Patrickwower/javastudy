package com.dengyuecang.api.controller.members;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dengyuecang.api.controller.members.model.MemberRegisterRequest;
import com.dengyuecang.api.service.IMemberService;
import com.dengyuecang.api.service.members.IMembersService;
import com.dengyuecang.api.utils.HeaderUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;

@RestController
@RequestMapping("/members")
public class MembersController {

Logger log = LoggerFactory.getLogger(MembersController.class);
	
	@Resource
	private IMembersService membersServiceImpl;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public RespData register(@RequestHeader HttpHeaders headers, MemberRegisterRequest request){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		try {
			
			resp = membersServiceImpl.register(headers, request);
		
		} catch (Exception e) {
			resp = RespCode.getRespData(RespCode.ERROR);
		}
		
		return resp;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public RespData login(@RequestHeader HttpHeaders headers,HttpServletRequest request){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		return membersServiceImpl.login(headers,request);
		
	}
	
	/**
	 * 提供反馈信息，填写身份，需求，功能三项
	 */
	@RequestMapping(value="/setMemberInfo",method=RequestMethod.POST)
	@ResponseBody
	public RespData setMemberInfo(@RequestHeader HttpHeaders headers,HttpServletRequest request){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		try {
			resp = membersServiceImpl.updateMemberInfo(headers, request);
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
		
		return membersServiceImpl.memberInfo(headers);	
	}
	
	/**
	 * 绑定手机号
	 */
	@RequestMapping(value="/bindMobile",method=RequestMethod.POST)
	@ResponseBody
	public RespData bindMobile(@RequestHeader HttpHeaders headers,String mobile){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		return membersServiceImpl.bindMobile(headers,mobile);	
		
	}
	
	/**
	 * 修改昵称
	 */
	@RequestMapping(value="/updateNickname",method=RequestMethod.POST)
	@ResponseBody
	public RespData updateNickname(@RequestHeader HttpHeaders headers, String nickname){
		
		RespData resp = RespCode.getRespData(RespCode.ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
			resp.setData(headCheck);
			return resp;
		}
		
		return membersServiceImpl.updateNickname(headers, nickname);	
		
	}
	
	/**
	 * 修改头像
	 */
	@RequestMapping(value="/updateHead",method=RequestMethod.POST)
	@ResponseBody
	public RespData updateHead(@RequestHeader HttpHeaders headers, String imgId){
		
		RespData resp = RespCode.getRespData(RespCode.ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
			resp.setData(headCheck);
			return resp;
		}
		
		return membersServiceImpl.updateHead(headers, imgId);	
		
	}
	
}
