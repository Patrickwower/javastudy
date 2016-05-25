package com.dengyuecang.api.controller.members.model.response;

import java.util.ArrayList;
import java.util.List;

public class MemberResponse {

	private String memberId = "";
	
	private String status = "";
	
	private InfoResponse memberInfo;
	
	private List<IdentityResponse> identitys = new ArrayList<IdentityResponse>();
	
	private List<DemandResponse> demands = new ArrayList<DemandResponse>();
	
	private List<FunctionResponse> functions = new ArrayList<FunctionResponse>();

	public MemberResponse() {
		super();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InfoResponse getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(InfoResponse memberInfo) {
		this.memberInfo = memberInfo;
	}

	public List<IdentityResponse> getIdentitys() {
		return identitys;
	}

	public void setIdentitys(List<IdentityResponse> identitys) {
		this.identitys = identitys;
	}

	public List<DemandResponse> getDemands() {
		return demands;
	}

	public void setDemands(List<DemandResponse> demands) {
		this.demands = demands;
	}

	public List<FunctionResponse> getFunctions() {
		return functions;
	}

	public void setFunctions(List<FunctionResponse> functions) {
		this.functions = functions;
	}
	
}
