package com.dengyuecang.www.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(path = "/admin/upload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			FileOutputStream fop = null;
			try {
				byte[] bytes = file.getBytes();
				// store the bytes somewhere
				String path = "/Users/osborn/Downloads/temp/";
				File fi = new File(path + file.getOriginalFilename());
				if (!fi.exists()) {
					fi.createNewFile();
				}
				fop = new FileOutputStream(fi);
				fop.write(bytes);
				fop.flush();
				fop.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fop != null) {
						fop.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "admin/uploadSuccess";
		}
		return "admin/uploadFailure";
	}
}
