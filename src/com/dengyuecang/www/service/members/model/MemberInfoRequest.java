package com.dengyuecang.www.service.members.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberInfoRequest implements Serializable{
	
	private List<IdentityRequest> identityList = new ArrayList<IdentityRequest>();
	
	private List<DemandRequest> demandList = new ArrayList<DemandRequest>();
	
	private List<FunctionRequest> functionList = new ArrayList<FunctionRequest>();

	public MemberInfoRequest() {
		super();
	}

	public MemberInfoRequest(List<IdentityRequest> identityList,
			List<DemandRequest> demandList, List<FunctionRequest> functionList) {
		super();
		this.identityList = identityList;
		this.demandList = demandList;
		this.functionList = functionList;
	}

	public List<IdentityRequest> getIdentityList() {
		return identityList;
	}

	public void setIdentityList(List<IdentityRequest> identityList) {
		this.identityList = identityList;
	}

	public List<DemandRequest> getDemandList() {
		return demandList;
	}

	public void setDemandList(List<DemandRequest> demandList) {
		this.demandList = demandList;
	}

	public List<FunctionRequest> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<FunctionRequest> functionList) {
		this.functionList = functionList;
	}
	
	
	
}
