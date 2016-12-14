package com.dengyuecang.www.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.dengyuecang.www.utils.img.ImgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dengyuecang.www.controller.tool.model.FileUploadRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.entity.StaticResource;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.dengyuecang.www.utils.qr.util.LogoConfig;
import com.dengyuecang.www.utils.qr.util.ZXingPic;
import com.google.zxing.BarcodeFormat;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;

@Service
public class StaticResourceImpl extends BaseService<StaticResource> implements IStaticResourceService{

	Logger log= LoggerFactory.getLogger(StaticResourceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<StaticResource> staticResourceDao;
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<Member> memberDao;
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<MemberInfo> infoDao;


	@Override
	public Map<String,String> storeImageForCircleMoment(HttpHeaders headers, MultipartFile file,HttpServletRequest request){

		Map<String,String> urls = new HashMap<String,String>();

		FileUploadRequest fileUploadRequest = new FileUploadRequest();

		fileUploadRequest.setType("image");

		fileUploadRequest.setUsefor("circle");

		//获取文件名
		String fileName = this.getFileName(file.getOriginalFilename().toString());

		//获取网络路径
		String urlPath = CommonConstant.STATIC_URL;

		//获取服务器实际路径
		String realPath = getRealPath(request, fileUploadRequest);

		//二阶目录

		String secondPath = getSecondPath(fileUploadRequest.getUsefor());

		urlPath += secondPath;
		realPath += secondPath;

		String source_url = urlPath+fileName;

		urls.put("source_url",source_url);

		String source_path = realPath+fileName;

		urls.put("source_path",source_path);

		this.storeImage(headers,file,urlPath,realPath,fileName);

		String thumbnail_url = source_url.substring(0,source_url.lastIndexOf("."))+"_thumbnail"+source_url.substring(source_url.lastIndexOf("."));

		String thumbnail_path = source_path.substring(0,source_path.lastIndexOf("."))+"_thumbnail"+source_path.substring(source_path.lastIndexOf("."));

		urls.put("thumbnail_url",thumbnail_url);

		urls.put("thumbnail_path",thumbnail_path);

		try {
			ImgUtils.setFromToScaleHW(source_path, thumbnail_path, "0.5", "375", "370");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return urls;

	}

	private void storeImage(HttpHeaders headers, MultipartFile file, String source_url,String source_path,String fileName) {

		log.info("上传图片开始");

		try {

			String urlPath = source_url;
			String realPath = source_path;


			File targetFile = new File(realPath,fileName);

			if(!targetFile.exists()){
				targetFile.mkdirs();
			}

			try {
				file.transferTo(targetFile);
				log.info("上传图片保存完成");

				urlPath += fileName;
				realPath += fileName;

			} catch (Exception e) {
				e.printStackTrace();
			}


			StaticResource sr = new StaticResource();

			String memberId = headers.getFirst("memberId");

			sr.setCreater(memberId);

			sr.setCtime(new Date());

			sr.setUrlPath(urlPath);

			sr.setPath(realPath);

			sr.setType("image");

			sr.setUseFor("circle");

			staticResourceDao.save(sr);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public RespData imgUpload(HttpHeaders headers, MultipartFile file, HttpServletRequest request,FileUploadRequest fileUploadRequest) {
		
		log.info("上传图片开始");
		
		try {
			//获取文件名
			String fileName = this.getFileName(file.getOriginalFilename().toString());
			
			//获取网络路径
			String urlPath = CommonConstant.STATIC_URL;
			
			//获取服务器实际路径
			String realPath = getRealPath(request, fileUploadRequest);
			
			//二阶目录
			
			String secondPath = getSecondPath(fileUploadRequest.getUsefor());
			
			urlPath += secondPath;
			realPath += secondPath;
			
			
			File targetFile = new File(realPath,fileName);
			
			if(!targetFile.exists()){  
	            targetFile.mkdirs();
	        } 
			
			try {  
	            file.transferTo(targetFile);
	            log.info("上传图片保存完成");
	            
	            urlPath += fileName;
	            realPath += fileName;
	            
			} catch (Exception e) {  
	            e.printStackTrace();  
	        } 
			
			
			StaticResource sr = new StaticResource();
			
			String memberId = headers.getFirst("memberId");
			
			sr.setCreater(memberId);
			
			sr.setCtime(new Date());
			
			sr.setUrlPath(urlPath);
			
			sr.setPath(realPath);
			
			sr.setType(fileUploadRequest.getType());
			
			sr.setUseFor(fileUploadRequest.getUsefor());
			
			staticResourceDao.save(sr);
			
			return RespCode.getRespData(RespCode.SUCCESS,sr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RespCode.getRespData(RespCode.ERROR);
	}

	@Override
	public String imgUploadUrl(HttpHeaders headers, MultipartFile file, HttpServletRequest request, FileUploadRequest fileUploadRequest) {

		log.info("上传图片开始");

		try {
			//获取文件名
			String fileName = this.getFileName(file.getOriginalFilename().toString());

			//获取网络路径
			String urlPath = CommonConstant.STATIC_URL;

			//获取服务器实际路径
			String realPath = getRealPath(request, fileUploadRequest);

			//二阶目录

			String secondPath = getSecondPath(fileUploadRequest.getUsefor());

			urlPath += secondPath;
			realPath += secondPath;


			File targetFile = new File(realPath,fileName);

			if(!targetFile.exists()){
				targetFile.mkdirs();
			}

			try {
				file.transferTo(targetFile);
				log.info("上传图片保存完成");

				urlPath += fileName;
				realPath += fileName;

			} catch (Exception e) {
				e.printStackTrace();
			}


			StaticResource sr = new StaticResource();

			String memberId = headers.getFirst("memberId");

			sr.setCreater(memberId);

			sr.setCtime(new Date());

			sr.setUrlPath(urlPath);

			sr.setPath(realPath);

			sr.setType(fileUploadRequest.getType());

			sr.setUseFor(fileUploadRequest.getUsefor());

			staticResourceDao.save(sr);

			return sr.getUrlPath();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 设置文件名
	 * @param file
	 * @return
	 */
	private String getFileName(String fileName){
		
		//获取源文件的后缀名
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		
		//uuid随机码加上后缀名
		fileName = UUID.randomUUID()+suffix;
		
		return fileName;
	}
	
	private String getRealPath(HttpServletRequest request,FileUploadRequest fileUploadRequest){
		
		String realPath = request.getSession().getServletContext().getRealPath("");
		
		realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		
		return realPath;
	}
	
//	/**
//	 * 获取网络路径
//	 * @return
//	 */
//	private String getUrlPath(String usefor){
//		
//		String urlPath = CommonConstant.STATIC_URL;
//		
//		return urlPath;
//	}
	
	/**
	 * 获取二层目录，主目录下的目录
	 * @param fileUploadRequest
	 * @return
	 */
	private String getSecondPath(String usefor){
		
		Date d = new Date();
		
		Format f = new SimpleDateFormat("yyyy/MM/dd/HH/");
		
		String secondPath = "/"+usefor+"/"+f.format(d);
		
		return secondPath;
	}
	
	@Override
	public BaseDao<StaticResource> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getQrImg(String content, String source_img,String targetFilePath) {

		Map<String, String> pathMap = new HashMap<String, String>();
		
		String secondPath = getSecondPath("qr");
		
		targetFilePath += secondPath;
		
		String urlPath = CommonConstant.STATIC_URL;
		
		urlPath += secondPath;
		
		try {
			File folder = new File(targetFilePath);
			if (!folder.exists()||!folder.isDirectory()) {
				folder.mkdirs();
			}
			
			String newFileName = UUID.randomUUID()+".jpg";
			log.info("新建文件名："+newFileName);
			File file = new File(targetFilePath+newFileName);
			
			pathMap.put("realPath", targetFilePath+newFileName);
			
			pathMap.put("urlPath", urlPath+newFileName);
			
			ZXingPic zp = new ZXingPic();

			BufferedImage bim = zp.getQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, 300, 300,
					zp.getDecodeHintType());

			ImageIO.write(bim, "jpg", file); 
			
			if (source_img!=null) {
				String qr_img = zp.addLogo_QRCode(file, new File(source_img),new LogoConfig());				
				
				pathMap.put("realPath", targetFilePath+qr_img);
				
				pathMap.put("urlPath", urlPath+qr_img);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pathMap;
	}
	
	public void saveQr(String memberId){
		
		String downloadUrl = CommonConstant.DOWNLOADURL+"?memberId="+memberId;
		
		String source_img = CommonConstant.QR_SOURCE_PATH;
		
		String targetFilePath = CommonConstant.QR_OUT_PATH;
		
		Map<String, String> pathMap = this.getQrImg(downloadUrl, source_img, targetFilePath);
		
		log.info("真实路径名："+pathMap.get("realPath"));
		log.info("网络路径名："+pathMap.get("urlPath"));
		
		
		StaticResource sr = new StaticResource();
		
		sr.setCreater(memberId);
		
		sr.setCtime(new Date());
		
		sr.setUrlPath(pathMap.get("urlPath"));
		
		sr.setPath(pathMap.get("realPath"));
		
		sr.setType("image");
		
		sr.setUseFor("qr");
		
		staticResourceDao.save(sr);
		
		Member member = memberDao.get(Member.class, memberId);
		
		MemberInfo info = member.getMemberInfo();
		
		info.setQr(sr.getUrlPath());
		info.setQrId(sr.getId());
		
		infoDao.saveOrUpdate(info);
		
	}

	@Override
	public StaticResource getResourceByUrl(String url) {

		StaticResource staticResource = (StaticResource) staticResourceDao.createQuery("from StaticResource where urlPath='"+url+"' ").uniqueResult();

		return staticResource;

	}


}
