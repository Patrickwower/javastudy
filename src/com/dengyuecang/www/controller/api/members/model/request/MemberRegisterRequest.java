package com.dengyuecang.www.controller.api.members.model.request;

public class MemberRegisterRequest {

	private String mobile;
	
	private String pwd;
	
	//-------------上线两个区域，两种注册方式，第一种为电话+密码，第二种第三方平台登录，互斥，有一个有值，其他为null--------------
	
	private String nickname;
	
	private String openId;
	
	private String icon;
	
	private String gender;
	
	//第三方注册，三种方式，有一个不为空，上边四项需要
	//json字符串，需解析为对象
	private String weixin;
	
	private String qq;
	
	private String weibo;

	public MemberRegisterRequest() {
		super();
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	
	
}

