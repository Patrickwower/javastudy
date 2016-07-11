package com.dengyuecang.www.controller.api.members;


import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/members")
public class MembersController {

Logger log = LoggerFactory.getLogger(MembersController.class);
	
	@Resource
	private IMembersService membersServiceImpl;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public RespData register(@RequestHeader HttpHeaders headers, MemberRegisterRequest request){
		
		try {
			
			return membersServiceImpl.register(headers, request);
		
		} catch (Exception e) {
			
		}
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public RespData login(@RequestHeader HttpHeaders headers,HttpServletRequest request){
		
		try {
			log.info("1111122221");
			return membersServiceImpl.login(headers,request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
	/**
	 * 提供反馈信息，填写身份，需求，功能三项
	 */
	@RequestMapping(value="/setMemberInfo",method=RequestMethod.POST)
	@ResponseBody
	public RespData setMemberInfo(@RequestHeader HttpHeaders headers,HttpServletRequest request){
		
		try {
			return membersServiceImpl.updateMemberInfo(headers, request);
		} catch (Exception e) {
			
		}
		
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
	
	/**
	 * 获取所有信息
	 * @return
	 */
	@RequestMapping(value = "/memberInfo", method = RequestMethod.POST)
	@ResponseBody
	public RespData getAllMemberInfo(@RequestHeader HttpHeaders headers) {

		try {
			return membersServiceImpl.memberInfo(headers);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
	/**
	 * 绑定手机号
	 */
	@RequestMapping(value="/bindMobile",method=RequestMethod.POST)
	@ResponseBody
	public RespData bindMobile(@RequestHeader HttpHeaders headers,String mobile){
		
		try {
			return membersServiceImpl.bindMobile(headers,mobile);	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
		
	}
	
	/**
	 * 修改昵称
	 */
	@RequestMapping(value="/updateNickname",method=RequestMethod.POST)
	@ResponseBody
	public RespData updateNickname(@RequestHeader HttpHeaders headers, String nickname){
		try {
			return membersServiceImpl.updateNickname(headers, nickname);				
		} catch (Exception e) {
			// TODO: handle exception
		}
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
	/**
	 * 修改头像
	 */
	@RequestMapping(value="/updateHead",method=RequestMethod.POST)
	@ResponseBody
	public RespData updateHead(@RequestHeader HttpHeaders headers, String imgId){
		
		try {
			return membersServiceImpl.updateHead(headers, imgId);				
		} catch (Exception e) {
			// TODO: handle exception
		}
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
}
