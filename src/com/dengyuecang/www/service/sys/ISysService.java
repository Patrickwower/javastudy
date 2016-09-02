package com.dengyuecang.www.service.sys;

import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.controller.api.members.model.request.UpdateMemberInformationRequest;
import com.dengyuecang.www.controller.api.members.model.request.VerifyRequest;
import com.dengyuecang.www.controller.api.publish.model.PublishLoginRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.StaticProvince;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public interface ISysService extends IBaseService<StaticProvince>{

	/**
	 * 获取所有省份
	 * @param headers
	 * @return
	 */
	public RespData getAllProvince(HttpHeaders headers);

}
