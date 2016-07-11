package com.dengyuecang.www.utils.qr.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;

public class Test {

	public static void main(String[] args) {
		String fromFilePath = "/Users/acang/Downloads/";
		String targetFilePath = "/Users/acang/Downloads/qr/";
		
		String source_img = "dyc.jpg";
		
		String content = "http://fir.im/dengyuecang";
		
		
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

			ImageIO.write(bim, "jpg", file); 
			String qr_img = zp.addLogo_QRCode(file, new File(fromFilePath+source_img),new LogoConfig());				

			System.out.println(qr_img);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
