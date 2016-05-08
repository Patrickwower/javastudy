package com.dengyuecang.api.service.model;

public class MemberInfoRequest {

	private String openId;
	private String nickname;
	private String icon;
	private String gender;
	private String mobile;
	private String pwd;
	
	public MemberInfoRequest() {
		super();
	}

	public MemberInfoRequest(String openId, String nickname, String icon,
			String gender, String mobile, String pwd) {
		super();
		this.openId = openId;
		this.nickname = nickname;
		this.icon = icon;
		this.gender = gender;
		this.mobile = mobile;
		this.pwd = pwd;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
}
