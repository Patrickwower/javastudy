package com.dengyuecang.api.controller.bg;

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
import org.springframework.web.servlet.ModelAndView;

import com.dengyuecang.api.controller.members.model.request.MemberRegisterRequest;
import com.dengyuecang.api.service.IMemberService;
import com.dengyuecang.api.service.members.IMembersService;
import com.dengyuecang.api.utils.HeaderUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;

@RestController
@RequestMapping("/bg/member")
public class BgMemberController {

	Logger log = LoggerFactory.getLogger(BgMemberController.class);

	@RequestMapping("/list")
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("jsp/index");
		
		return mav;
	}
	
	
}
