package com.dengyuecang.www.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dyc_member")
public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;

	private String memberId;
	
	private String pwd;
	
	private String token;

	private String username;

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private Qq qq;
	private Weixin weixin;
	private Weibo weibo;
	private MemberInfo memberInfo;

	@OneToOne
	@JoinColumn(name="qq")
	public Qq getQq() {
		return qq;
	}

	public void setQq(Qq qq) {
		this.qq = qq;
	}

	@OneToOne
	@JoinColumn(name="weixin")
	public Weixin getWeixin() {
		return weixin;
	}

	public void setWeixin(Weixin weixin) {
		this.weixin = weixin;
	}

	@OneToOne
	@JoinColumn(name="weibo")
	public Weibo getWeibo() {
		return weibo;
	}

	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}

	
	@OneToOne
	@JoinColumn(name="info")
	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}
	
	private List<Identity> identityList = new ArrayList<Identity>();
	private List<Demand> demandList = new ArrayList<Demand>();
	private List<Function> functionList = new ArrayList<Function>();

	@Transient
	public List<Identity> getIdentityList() {
		return identityList;
	}

	public void setIdentityList(List<Identity> identityList) {
		this.identityList = identityList;
	}

	@Transient
	public List<Demand> getDemandList() {
		return demandList;
	}

	public void setDemandList(List<Demand> demandList) {
		this.demandList = demandList;
	}

	@Transient
	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	/**
	 * 是否已反馈 0 未反馈, 1 已反馈
	 */
	private String ifFeedBack = "0";

	@Transient
	public String getIfFeedBack() {
		return ifFeedBack;
	}

	public void setIfFeedBack(String ifFeedBack) {
		this.ifFeedBack = ifFeedBack;
	}
	
	//城市
	private String city;
	//组织
	private String organization;

	@Transient
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Transient
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	private String inviteCode;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
}
