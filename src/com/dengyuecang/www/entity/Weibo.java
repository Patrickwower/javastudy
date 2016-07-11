package com.dengyuecang.www.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="dyc_member_weibo")
public class Weibo implements Serializable{

	private String id;
	private Member member;
	
	private String openId;
	
	private String weibo_info;
	
	public Weibo() {
		super();
	}
	public Weibo(String id, Member member) {
		super();
		this.id = id;
		this.member = member;
	}
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
	
	@Transient
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getWeibo_info() {
		return weibo_info;
	}
	public void setWeibo_info(String weibo_info) {
		this.weibo_info = weibo_info;
	}
	
}
