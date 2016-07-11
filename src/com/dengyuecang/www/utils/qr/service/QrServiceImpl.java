package com.dengyuecang.www.utils.qr.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dengyuecang.www.utils.qr.util.LogoConfig;
import com.dengyuecang.www.utils.qr.util.UploadUtils;
import com.dengyuecang.www.utils.qr.util.ZXingPic;
import com.google.zxing.BarcodeFormat;


@Service
public class QrServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(QrServiceImpl.class);
	
	private static String source_img = "source.png";

	public static String createSpreadImg(String phone){
		log.info("创建推广qr："+phone);
//		String url = "http://123.56.115.117:8080/duijiebao/spread/spreadToNew?phone="+phone;
		String url = "http://123.56.115.117:8080/shaoshao_apply/spread/spreadToNew?phone="+phone;
//		String url = "http://localhost:8080/duijiebao/spread/spreadToNew?phone="+phone;
		String qr_img = QrServiceImpl.createQrImg(url, source_img);
		if (qr_img!=null) {
			return qr_img;
		}
		return "";
	}
	
	public static String createDownloadImg(String phone){
		log.info("创建下载qr："+phone);
//		String url = "http://123.56.115.117:8080/duijiebao/download/app_download_transfer?phone="+phone;
		String url = "http://123.56.115.117:8080/shaoshao_apply/download/app_download_transfer?phone="+phone;
//		String url = "http://localhost:8080/duijiebao/download/app_download_transfer?phone="+phone;
		String download_img = QrServiceImpl.createQrImg(url, source_img);
		if (download_img!=null) {
			return download_img;
		}
		return "";
	}

	public static String createQrImg(String content,String source_img){
		
		String fromFilePath = UploadUtils.getFilePathByPropertyName("img.qr.source");
		String targetFilePath = UploadUtils.getFilePathByPropertyName("img.qr.store");
		
		try {
			File folder = new File(targetFilePath);
			if (!folder.exists()||!folder.isDirectory()) {
				folder.mkdirs();
			}
			
			String newFileName = UploadUtils.getCurrentTimeFileName(source_img);
			File file = new File(targetFilePath+newFileName);
			
			ZXingPic zp = new ZXingPic();

			BufferedImage bim = zp.getQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, 300, 300,
					zp.getDecodeHintType());

			ImageIO.write(bim, "jpeg", file); 
			String qr_img = zp.addLogo_QRCode(file, new File(fromFilePath+source_img),new LogoConfig());
			return qr_img;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
