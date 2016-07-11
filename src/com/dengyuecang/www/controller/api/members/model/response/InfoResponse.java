package com.dengyuecang.www.controller.api.members.model.response;

public class InfoResponse {

	private String nickname = "";
	
	private String mobile = "";
	
	private String gender = "";
	
	private String icon = "";
	
	private String qr = "";

	private String city = "";
	
	private String organization;
	
	//0否，1是
	private String isFeedBack;
	
	public InfoResponse() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getIsFeedBack() {
		return isFeedBack;
	}

	public void setIsFeedBack(String isFeedBack) {
		this.isFeedBack = isFeedBack;
	}
	
}
