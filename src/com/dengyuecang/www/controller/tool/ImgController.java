package com.dengyuecang.www.controller.tool;

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

import com.dengyuecang.www.controller.tool.model.FileUploadRequest;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;

@RestController
@RequestMapping("/img")
public class ImgController {

	@Resource
	private IStaticResourceService staticResourceServiceImpl;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public RespData upload(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = true) MultipartFile file,HttpServletRequest request,FileUploadRequest fileUploadRequest){
		
		RespData resp = RespCode.getRespData(RespCode.ERROR);
		
		try {
			
			String type = headers.getFirst("type");
			
			String usefor = headers.getFirst("usefor");
		
			fileUploadRequest.setType(type);
			
			fileUploadRequest.setUsefor(usefor);
			
			return staticResourceServiceImpl.imgUpload(headers, file, request, fileUploadRequest);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
		
	}

	@RequestMapping(value="/uploadForArticle",method=RequestMethod.POST)
	@ResponseBody
	public String uploadForArticle(@RequestHeader HttpHeaders headers, @RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, FileUploadRequest fileUploadRequest){


		try {

			String type = headers.getFirst("type");

			String usefor = headers.getFirst("usefor");

			fileUploadRequest.setType(type);

			fileUploadRequest.setUsefor(usefor);

			return staticResourceServiceImpl.imgUploadUrl(headers, file, request, fileUploadRequest);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
}
