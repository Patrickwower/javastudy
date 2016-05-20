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
@Table(name = "dyc_member_qq")
public class Qq implements Serializable{

	private String id;
	private Member member;
	private String openId;
	private String city;
	private String province;
	private String gender;
	private String is_lost;
	private String is_yellow_vip;
	private String is_yellow_year_vip;
	private String level;
	private String msg;
	private String nickname;
	private String ret;
	private String vip;
	private String yellow_vip_level;
	
	
	private String figureurl;
	private String figureurl_1;
	private String figureurl_2;
	private String figureurl_qq_1;
	private String figureurl_qq_2;
	
	private String qq_info;
	
	public Qq() {
		super();
	}
	public Qq(String id, Member member, String city, String province,
			String gender, String is_lost, String is_yellow_vip,
			String is_yellow_year_vip, String level, String msg,
			String nickname, String ret, String vip, String yellow_vip_level,
			String figureurl, String figureurl_1, String figureurl_2,
			String figureurl_qq_1, String figureurl_qq_2) {
		super();
		this.id = id;
		this.member = member;
		this.city = city;
		this.province = province;
		this.gender = gender;
		this.is_lost = is_lost;
		this.is_yellow_vip = is_yellow_vip;
		this.is_yellow_year_vip = is_yellow_year_vip;
		this.level = level;
		this.msg = msg;
		this.nickname = nickname;
		this.ret = ret;
		this.vip = vip;
		this.yellow_vip_level = yellow_vip_level;
		this.figureurl = figureurl;
		this.figureurl_1 = figureurl_1;
		this.figureurl_2 = figureurl_2;
		this.figureurl_qq_1 = figureurl_qq_1;
		this.figureurl_qq_2 = figureurl_qq_2;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIs_lost() {
		return is_lost;
	}
	public void setIs_lost(String is_lost) {
		this.is_lost = is_lost;
	}
	public String getIs_yellow_vip() {
		return is_yellow_vip;
	}
	public void setIs_yellow_vip(String is_yellow_vip) {
		this.is_yellow_vip = is_yellow_vip;
	}
	public String getIs_yellow_year_vip() {
		return is_yellow_year_vip;
	}
	public void setIs_yellow_year_vip(String is_yellow_year_vip) {
		this.is_yellow_year_vip = is_yellow_year_vip;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getYellow_vip_level() {
		return yellow_vip_level;
	}
	public void setYellow_vip_level(String yellow_vip_level) {
		this.yellow_vip_level = yellow_vip_level;
	}
	public String getFigureurl() {
		return figureurl;
	}
	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}
	public String getFigureurl_1() {
		return figureurl_1;
	}
	public void setFigureurl_1(String figureurl_1) {
		this.figureurl_1 = figureurl_1;
	}
	public String getFigureurl_2() {
		return figureurl_2;
	}
	public void setFigureurl_2(String figureurl_2) {
		this.figureurl_2 = figureurl_2;
	}
	public String getFigureurl_qq_1() {
		return figureurl_qq_1;
	}
	public void setFigureurl_qq_1(String figureurl_qq_1) {
		this.figureurl_qq_1 = figureurl_qq_1;
	}
	public String getFigureurl_qq_2() {
		return figureurl_qq_2;
	}
	public void setFigureurl_qq_2(String figureurl_qq_2) {
		this.figureurl_qq_2 = figureurl_qq_2;
	}
	
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQq_info() {
		return qq_info;
	}
	public void setQq_info(String qq_info) {
		this.qq_info = qq_info;
	}
	
	
}
