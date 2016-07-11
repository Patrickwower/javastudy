package com.dengyuecang.www.controller.tool;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dengyuecang.www.service.IQrUrlLogService;

@RestController
@RequestMapping("/qr")
public class QrUrlController {

	@Resource
	private IQrUrlLogService qrUrlLogServiceImpl;
	
	@RequestMapping("/index")
	public ModelAndView index(String memberId){
		
		ModelAndView mav = new ModelAndView();
		
		qrUrlLogServiceImpl.addLog(memberId);
		
		mav.setViewName("qr/index");
		mav.addObject("memberId",memberId);
		
		return mav;
		
	}
	
	
}
