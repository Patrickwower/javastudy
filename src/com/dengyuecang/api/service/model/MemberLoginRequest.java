package com.dengyuecang.api.service.model;

public class MemberLoginRequest {

	
	private String mobile;
	
	private String pwd;

	private String memberId;
	
	private String token;

	public MemberLoginRequest() {
		super();
	}

	public MemberLoginRequest(String mobile, String pwd, String memberId,
			String token) {
		super();
		this.mobile = mobile;
		this.pwd = pwd;
		this.memberId = memberId;
		this.token = token;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}
