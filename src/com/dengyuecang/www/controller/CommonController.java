package com.dengyuecang.www.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dengyuecang.www.utils.CommonParameter;
import com.dengyuecang.www.utils.CommonUtils;

@Controller
@RequestMapping("/common")
public class CommonController {
	/**
	 * 图片上传
	 * @param file
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request,@RequestParam(value = "img_file", required = false) MultipartFile[] file,HttpSession session){
		String image_path = "test/img";
		try {
			image_path = CommonUtils.commonUploadImage(file, session,"correction.img");
			image_path = CommonParameter.MARKET+image_path;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return image_path;
		}
	}
}
