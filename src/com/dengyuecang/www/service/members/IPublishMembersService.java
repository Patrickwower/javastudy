package com.dengyuecang.www.service.members;

import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.controller.api.members.model.request.VerifyRequest;
import com.dengyuecang.www.controller.api.publish.model.PublishLoginRequest;
import com.dengyuecang.www.controller.api.publish.model.VerifyInviteCodeRequest;
import com.dengyuecang.www.controller.api.publish.model.WeixinLoginRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public interface IPublishMembersService extends IBaseService<Member>{

	/**
	 * 发布系统用的登录接口
	 */
	public RespData login(HttpHeaders headers, WeixinLoginRequest loginRequest);

	public RespData verifyInviteCode(HttpHeaders headers,VerifyInviteCodeRequest inviteRequest);

}
