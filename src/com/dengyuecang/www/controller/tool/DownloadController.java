package com.dengyuecang.www.controller.tool;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dengyuecang.www.service.IStaticResourceService;

@RestController
@RequestMapping("/download")
public class DownloadController {

	@Resource
	private IStaticResourceService staticResourceServiceImpl;
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView index(String memberId){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("download");
		
		return mav;
		
	}
	
	
}
