package com.dengyuecang.www.service.members.model;

public class DemandRequest {

	private String name;
	
	private String content;

	public DemandRequest() {
		super();
	}

	public DemandRequest(String name, String content) {
		super();
		this.name = name;
		this.content = content;
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
	
}
