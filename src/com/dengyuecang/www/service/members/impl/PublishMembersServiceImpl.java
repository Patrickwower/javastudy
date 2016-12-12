package com.dengyuecang.www.service.members.impl;

import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.controller.api.members.model.request.VerifyRequest;
import com.dengyuecang.www.controller.api.members.model.response.*;
import com.dengyuecang.www.controller.api.publish.model.PublishLoginRequest;
import com.dengyuecang.www.controller.api.publish.model.VerifyInviteCodeRequest;
import com.dengyuecang.www.controller.api.publish.model.WeixinLoginRequest;
import com.dengyuecang.www.entity.*;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.service.members.IPublishMembersService;
import com.dengyuecang.www.service.members.model.*;
import com.dengyuecang.www.utils.JsonUtils;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.dengyuecang.www.utils.sharesdk.SmsUtil;
import com.dengyuecang.www.utils.sharesdk.SmsVerifyRequest;
import com.dengyuecang.www.utils.weixin.WeixinOAuth2Util;
import com.dengyuecang.www.utils.weixin.WeixinUserinfo;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import com.longinf.lxframework.util.security.MD5Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class PublishMembersServiceImpl extends BaseService<Member> implements IPublishMembersService{

	Logger log = LoggerFactory.getLogger(PublishMembersServiceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<Member> memberDao;
	@Resource(name = "hibernateBaseDao")
	private BaseDao<InviteCode> codeDao;

	@Resource
	private IMembersService membersServiceImpl;

	@Override
	public BaseDao<Member> getSuperDao() {
		return null;
	}

	@Override
	public RespData login(HttpHeaders headers, WeixinLoginRequest loginRequest) {


		try {

			WeixinUserinfo userinfo = WeixinOAuth2Util.oauth2(loginRequest.getCode());

			MemberRegisterRequest registerRequest = this.weixinUserinfoToMemberRegisterRequest(userinfo);

			RespData respData = membersServiceImpl.register(headers,registerRequest);

			Map<String,Object> response = (Map<String, Object>) respData.getData();

			response.put("userinfo",userinfo);

			String memberId = (String) response.get("memberId");

			Member member = memberDao.get(Member.class,memberId);

			if (StringUtils.isNotEmpty(member.getInviteCode()))response.put("invited","true");
			if (StringUtils.isEmpty(member.getInviteCode()))response.put("invited","false");

			return RespCode.getRespData(RespCode.SUCCESS,response);
		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	@Override
	public RespData verifyInviteCode(HttpHeaders headers, VerifyInviteCodeRequest inviteRequest) {


		if (StringUtils.isEmpty(inviteRequest.getInviteCode())){

			return RespCode.getRespData(RespCode.INVATE_CODE_NEED);

		}

		InviteCode code = codeDao.get(InviteCode.class,inviteRequest.getInviteCode());

		if (code==null){

			return RespCode.getRespData(RespCode.INVATE_CODE_NOT_EXIST);
		}

		if ("100".equals(code.getStatus())){

			code.setEtime(new Date());

			code.setStatus("200");

			codeDao.saveOrUpdate(code);

			String memberId = headers.getFirst("memberId");

			if (StringUtils.isEmpty(memberId))memberId=inviteRequest.getMemberId();

			Member member = memberDao.get(Member.class,memberId);

			member.setInviteCode(code.getCode());

			memberDao.saveOrUpdate(member);

			return RespCode.getRespData(RespCode.SUCCESS);

		}else if ("0".equals(code.getStatus())){

			return RespCode.getRespData(RespCode.INVATE_CODE_NOT_PUBLISH);

		}else if("200".equals(code.getStatus())){

			return RespCode.getRespData(RespCode.INVATE_CODE_USED);

		}



		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	private MemberRegisterRequest weixinUserinfoToMemberRegisterRequest(WeixinUserinfo userinfo){

		MemberRegisterRequest memberRegisterRequest = new MemberRegisterRequest();

		memberRegisterRequest.setOpenId(userinfo.getOpenid());

		memberRegisterRequest.setGender("1".equals(userinfo.getSex())?"男":"女");

		memberRegisterRequest.setIcon(userinfo.getHeadimgurl());

		memberRegisterRequest.setNickname(userinfo.getNickname());

		String weixin = JsonUtils.toJSONString(JsonUtils.toJSONObject(userinfo));

		memberRegisterRequest.setWeixin(weixin);

		return memberRegisterRequest;
	}

}
