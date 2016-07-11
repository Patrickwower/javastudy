package com.dengyuecang.www.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dengyuecang.www.service.IMemberService;
import com.dengyuecang.www.utils.HeaderUtils;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;


@RestController
@RequestMapping("/basic")
public class BasicDataController {

	@Resource
	private IMemberService memberServiceImpl;
	
	@RequestMapping(value="/getAll",method=RequestMethod.POST)
	@ResponseBody
	public RespData getAll(String json,@RequestHeader HttpHeaders headers){
		
		RespData resp = RespCode.getRespData(RespCode.HEADER_PARAM_ERROR);
		
		String headCheck = HeaderUtils.checkHeader(headers,false);
		
		if (!"ok".equals(headCheck)) {
			resp.setData(headCheck);
			return resp;
		}
		
		return memberServiceImpl.getBasicInfo();
		
	}
	
}
