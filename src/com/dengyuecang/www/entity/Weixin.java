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
@Table(name="dyc_member_weixin")
public class Weixin implements Serializable{

	private String id;
	private Member member;
	private String country;
	private String city;
	private String province;
	private String sex;
	private String headimgurl;
	private String language;
	private String nickname;
	private String openid;
	private String unionid;
	private Object[] privilege;
	
	private String weixin_info;
	
	public Weixin() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Weixin(String id, Member member, String country, String city,
			String province, String sex, String headimgurl, String language,
			String nickname, String openid, String unionid) {
		super();
		this.id = id;
		this.member = member;
		this.country = country;
		this.city = city;
		this.province = province;
		this.sex = sex;
		this.headimgurl = headimgurl;
		this.language = language;
		this.nickname = nickname;
		this.openid = openid;
		this.unionid = unionid;
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


//	@OneToOne
//	@JoinColumn(name="memberId")
	@Transient
	public Member getMember() {
		return member;
	}



	public void setMember(Member member) {
		this.member = member;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
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



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getHeadimgurl() {
		return headimgurl;
	}



	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getOpenid() {
		return openid;
	}



	public void setOpenid(String openid) {
		this.openid = openid;
	}



	public String getUnionid() {
		return unionid;
	}



	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	@Transient
	public Object[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Object[] privilege) {
		this.privilege = privilege;
	}

	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getWeixin_info() {
		return weixin_info;
	}



	public void setWeixin_info(String weixin_info) {
		this.weixin_info = weixin_info;
	}
}
