package com.dengyuecang.www.controller.tool.model;

public class FileUploadRequest {

//	资源类型  image  file 
	private String type;
	
//	用途：用户icon头像head，用户二维码qr，用户身份卡片identity
	private String usefor;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsefor() {
		return usefor;
	}

	public void setUsefor(String usefor) {
		this.usefor = usefor;
	}
	
}
