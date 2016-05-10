package com.dengyuecang.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="dyc_member_identity")
public class MemberIdentity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private Member member;
	
	private Identity identity;

	private String city;
	
	private String organization;
	
	private Date ctime;
	
	private String IP;
	
	public MemberIdentity() {
		super();
	}

	
	
	public MemberIdentity(String id, Member member, Identity identity,
			String city, String organization, Date ctime, String iP) {
		super();
		this.id = id;
		this.member = member;
		this.identity = identity;
		this.city = city;
		this.organization = organization;
		this.ctime = ctime;
		IP = iP;
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


	@OneToOne
	@JoinColumn(name="memberId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@OneToOne
	@JoinColumn(name="identityId")
	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
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



	public Date getCtime() {
		return ctime;
	}



	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}



	public String getIP() {
		return IP;
	}



	public void setIP(String iP) {
		IP = iP;
	}
	
//	状态: 101 正常 ， 201  废弃
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
