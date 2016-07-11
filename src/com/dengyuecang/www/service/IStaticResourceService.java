package com.dengyuecang.www.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import com.dengyuecang.www.controller.tool.model.FileUploadRequest;
import com.dengyuecang.www.entity.StaticResource;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;

public interface IStaticResourceService extends IBaseService<StaticResource>{

	/**
	 * 图片上传
	 * @param headers
	 * @param file
	 * @param request
	 * @param fileUploadRequest
	 * @return
	 */
	public RespData imgUpload(HttpHeaders headers, MultipartFile file, HttpServletRequest request,FileUploadRequest fileUploadRequest);

	/**
	 * 图片上传
	 * @param headers
	 * @param file
	 * @param request
	 * @param fileUploadRequest
	 * @return
	 */
	public String imgUploadUrl(HttpHeaders headers, MultipartFile file, HttpServletRequest request, FileUploadRequest fileUploadRequest);

	/**
	 * 生成二维码
	 * 返回真实地址realPath和网络地址urlPath
	 */
	public Map<String, String> getQrImg(String content,String source_img, String targetFilePath);
	
	/**
	 * 根据用户id获取二维码
	 * @param memberId
	 */
	public void saveQr(String memberId);
}
