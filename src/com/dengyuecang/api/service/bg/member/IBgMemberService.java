package com.dengyuecang.api.service.bg.member;

import com.dengyuecang.api.entity.Member;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.IBaseService;

public interface IBgMemberService extends IBaseService<Member>{

	/**
	 * 分页查询member
	 */
	public PageModel<Member> queryMember(PageModel<Member> page);
	
}
