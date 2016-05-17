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
import com.dengyuecang.api.service.IQrUrlLogService;
import com.dengyuecang.api.service.IStaticResourceService;
import com.dengyuecang.api.utils.ImgUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;

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
