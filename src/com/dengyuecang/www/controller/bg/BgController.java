package com.dengyuecang.www.controller.bg;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bg")
public class BgController {

	Logger log = LoggerFactory.getLogger(BgController.class);

	@RequestMapping("/login")
	public ModelAndView login(HttpSession session){
		
		Map<String, String> currentUser = new HashMap<String,String>();
		
		currentUser.put("acang", "dengyuecang");
		
		session.setAttribute("currentUser",currentUser);
		
		return index();
	}
	
	@RequestMapping("/index")
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
	}
	
	
}
