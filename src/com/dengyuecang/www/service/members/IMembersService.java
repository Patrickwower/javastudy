package com.dengyuecang.www.service.members;

import javax.servlet.http.HttpServletRequest;

import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import org.springframework.http.HttpHeaders;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;

public interface IMembersService extends IBaseService<Member>{

	/**
	 * 注册用户
	 * @param request
	 * @param headers
	 * @return
	 */
	public RespData register(HttpHeaders headers, MemberRegisterRequest request);
	
	/**
	 * 用户登录
	 * @param request
	 * @param headers
	 * @return
	 */
	public RespData login(HttpHeaders headers, HttpServletRequest request);
	
	/**
	 * 获取用户所有基础信息
	 * @param headers
	 * @return
	 */
	public RespData memberInfo(HttpHeaders headers);
	
	/**
	 * 更新用户基础信息(主要是为了修改identity,demand,function)
	 * @param request
	 * @param headers
	 * @return
	 */
	public RespData updateMemberInfo(HttpHeaders headers, HttpServletRequest request);
	
	/**
	 * 绑定手机号
	 * @param headers
	 * @param mobile
	 * @return
	 */
	public RespData bindMobile(HttpHeaders headers, String mobile);

	/**
	 * 更新头像
	 * @param headers
	 * @param imgId
	 * @return
	 */
	public RespData updateHead(HttpHeaders headers, String imgId);
	
	/**
	 * 更新昵称
	 * @param headers
	 * @param nickname
	 * @return
	 */
	public RespData updateNickname(HttpHeaders headers, String nickname);

	/**
	 * 更新个人介绍
	 * @param headers
	 * @param introduction
	 * @return
	 */
	public RespData updateIntroduction(HttpHeaders headers, String introduction);

	/**
	 * 更新分享用二维码
	 * @param memberId
	 * @return
	 */
	public RespData updateQr(String memberId);
	
	/**
	 * 验证memberToken
	 */
	public RespData checkToken(HttpServletRequest request);

	/**
	 * 获取用户信息
	 */
	public RespData information(HttpHeaders headers, String memberId);

}
