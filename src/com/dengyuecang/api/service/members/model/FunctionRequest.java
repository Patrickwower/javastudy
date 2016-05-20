package com.dengyuecang.api.service.members.model;

public class FunctionRequest {

	private String name;
	
	private String content;


	public FunctionRequest(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	public FunctionRequest() {
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
	
}
