package com.dengyuecang.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/service")
	@ResponseBody
	public String service(String serviceId){
	
		return serviceId;
		
	}
}
