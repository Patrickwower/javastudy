package com.dengyuecang.www.service.model;

import java.io.Serializable;

public class IdentityRequest implements Serializable{

	private String name;
	
	private String content;

	private String city;
	
	private String organization;
	
	public IdentityRequest(String name, String content, String city,
			String organization) {
		super();
		this.name = name;
		this.content = content;
		this.city = city;
		this.organization = organization;
	}

	public IdentityRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
}
