package com.dengyuecang.api.entity;

import java.io.Serializable;

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
	
	
	
}
