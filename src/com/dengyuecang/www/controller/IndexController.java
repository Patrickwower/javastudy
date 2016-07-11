package com.dengyuecang.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/service")
	@ResponseBody
	public String service(String serviceId){
		System.out.println(serviceId);
		return serviceId;
		
	}
	
	@RequestMapping("/service2")
	public ModelAndView service2(String serviceId){
		System.out.println(serviceId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("serviceId",serviceId);
		mav.setViewName("index");
		
		return mav;
		
	}
}
