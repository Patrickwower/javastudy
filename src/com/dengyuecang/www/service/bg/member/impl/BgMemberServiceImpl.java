package com.dengyuecang.www.service.bg.member.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.service.bg.member.IBgMemberService;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.BaseService;

@Service
public class BgMemberServiceImpl extends BaseService<Member> implements
		IBgMemberService {

	@Resource(name="hibernateBaseDao")
	private BaseDao<Member> memberDao;
	
	@Override
	public BaseDao<Member> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel<Member> queryMember(PageModel<Member> page) {
		Criteria c =  memberDao.createCriteria(Member.class);
		
		c.addOrder(Order.desc("memberId"));
		
		memberDao.pagedQuery(c, page);
		
		return page;
	}
	
	

}
