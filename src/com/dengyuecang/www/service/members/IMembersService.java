package com.dengyuecang.www.service.members;

import javax.servlet.http.HttpServletRequest;

import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.controller.api.members.model.request.UpdateMemberInformationRequest;
import com.dengyuecang.www.controller.api.members.model.request.VerifyRequest;
import com.dengyuecang.www.controller.api.publish.model.PublishLoginRequest;
import org.hibernate.sql.Update;
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
	 * 用户验证手机验证码并登录
	 * @param request
	 * @param headers
	 * @return
	 */
	public RespData verify(HttpHeaders headers, VerifyRequest request);

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
	public RespData bindMobile(HttpHeaders headers, String mobile, String pwd);

	/**
	 * 绑定微信
	 * @param headers
	 * @param weixin
	 * @return
	 */
	public RespData bindWeixin(HttpHeaders headers, String weixin);

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

	/**
	 * 获取用户信息
	 */
	public RespData updateInformation(HttpHeaders headers, UpdateMemberInformationRequest updateRequest);

	/**
	 * 发布系统用的登录接口
	 */
	public RespData publishLogin(HttpHeaders headers, PublishLoginRequest loginRequest);

	/**
	 * 修改登录密码
	 */
	public RespData updatePwd(HttpHeaders headers, VerifyRequest verifyRequest);

	/**
	 * 验证手机验证码
	 */
	public RespData verifyCode(HttpHeaders headers, VerifyRequest verifyRequest);

	/**
	 * 是否关注
	 */
	public int ifFocus(String memberId,String focusId);

}
