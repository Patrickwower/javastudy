package com.dengyuecang.www.controller.api.sys;


import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.controller.api.members.model.request.UpdateMemberInformationRequest;
import com.dengyuecang.www.controller.api.members.model.request.VerifyRequest;
import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.service.sys.ISysService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/sys")
public class SysController {

Logger log = LoggerFactory.getLogger(SysController.class);
	
	@Resource
	private ISysService sysServiceImpl;
	
	@RequestMapping(value="/provinces",method=RequestMethod.POST)
	@ResponseBody
	public RespData register(@RequestHeader HttpHeaders headers, MemberRegisterRequest request){
		
		try {
			
			return sysServiceImpl.getAllProvince(headers);
		
		} catch (Exception e) {
			
		}
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}



}
