package com.dengyuecang.www.utils.weixin;

import java.util.List;

public class WeixinUserinfo {

	private String openid;
	
	private String access_token;
	
	private String expires_in;
	
	private String refresh_token;
	
	private String scope;
	
	private String nickname;
	
	private String sex;
	
	private String language;
	
	private String city;
	
	private String province;
	
	private String country;
	
	private String headimgurl;
	
	private List<String> privilege;

	private String unionid;
	
	public WeixinUserinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

  
	public WeixinUserinfo(String openid, String access_token,
			String expires_in, String refresh_token, String scope,
			String nickname, String sex, String language, String city,
			String province, String country, String headimgurl, List<String> privilege) {
		super();
		this.openid = openid;
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.scope = scope;
		this.nickname = nickname;
		this.sex = sex;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.headimgurl = headimgurl;
		this.privilege = privilege;
	}



	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
