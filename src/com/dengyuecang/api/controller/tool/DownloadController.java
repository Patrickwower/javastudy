package com.dengyuecang.api.controller.tool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dengyuecang.api.controller.tool.model.FileUploadRequest;
import com.dengyuecang.api.service.IStaticResourceService;
import com.dengyuecang.api.utils.ImgUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;

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
