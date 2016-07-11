package com.dengyuecang.www.controller.bg;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.service.bg.member.IBgMemberService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.params.PageModel;

@RestController
@RequestMapping("/bg/member")
public class BgMemberController {

	Logger log = LoggerFactory.getLogger(BgMemberController.class);

	@Resource
	private IBgMemberService bgMemberServiceImpl;
	
	@RequestMapping("/list")
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("member/list");
		
		return mav;
	}
	
	@RequestMapping("/members")
	@ResponseBody
	public RespData members(PageModel<Member> page){
		
		page = bgMemberServiceImpl.queryMember(page);
		
		return RespCode.getdataGrid(page.getList(), page.getTotal());
	}
	
	
}
