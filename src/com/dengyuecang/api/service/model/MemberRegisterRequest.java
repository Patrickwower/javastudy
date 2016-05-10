package com.dengyuecang.api.service.model;

import com.dengyuecang.api.entity.Qq;
import com.dengyuecang.api.entity.Weibo;
import com.dengyuecang.api.entity.Weixin;

public class MemberRegisterRequest {

	private Weixin weixin;
	private Object weibo;
	private Qq qq;
	
	private MemberInfoRequest memberInfo;

	public MemberRegisterRequest() {
		super();
	}

	public MemberRegisterRequest(Weixin weixin, Object weibo, Qq qq,
			MemberInfoRequest memberInfo) {
		super();
		this.weixin = weixin;
		this.weibo = weibo;
		this.qq = qq;
		this.memberInfo = memberInfo;
	}

	public Weixin getWeixin() {
		return weixin;
	}

	public void setWeixin(Weixin weixin) {
		this.weixin = weixin;
	}

	public Object getWeibo() {
		return weibo;
	}

	public void setWeibo(Object weibo) {
		this.weibo = weibo;
	}

	public Qq getQq() {
		return qq;
	}

	public void setQq(Qq qq) {
		this.qq = qq;
	}

	public MemberInfoRequest getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfoRequest memberInfo) {
		this.memberInfo = memberInfo;
	}
	
	
	
}
