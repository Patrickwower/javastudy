package com.dengyuecang.api.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.UniqueIdVendor;

import net.sf.json.JSONObject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.dengyuecang.api.entity.Member;
import com.dengyuecang.api.entity.MemberInfo;
import com.dengyuecang.api.entity.Qq;
import com.dengyuecang.api.entity.Weibo;
import com.dengyuecang.api.entity.Weixin;
import com.dengyuecang.api.service.IMemberService;
import com.dengyuecang.api.service.common.CommonConstant;
import com.dengyuecang.api.service.model.MemberInfoRequest;
import com.dengyuecang.api.service.model.MemberLoginRequest;
import com.dengyuecang.api.service.model.MemberRegisterRequest;
import com.dengyuecang.api.utils.JsonUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;

@Service
public class MemberServiceImpl extends BaseService<Member> implements
		IMemberService {

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Member> memberDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<MemberInfo> memberInfoDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Weixin> weixinDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Weibo> weiboDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Qq> qqDao;

	@Override
	public List<Member> queryAll() {

		List<Member> memberList = memberDao.createQuery("from Member").list();

		return memberList;
	}

	@Override
	public BaseDao<Member> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member register(String json, HttpHeaders headers) {

		try {

			JSONObject jsonObject = JsonUtils.toJSONObject(json);
			
			MemberRegisterRequest request = JsonUtils.toBean(jsonObject, MemberRegisterRequest.class);
			
			Member member = JsonUtils.toBean(jsonObject, Member.class);

			MemberInfoRequest infoRequest = request.getMemberInfo();
			
			MemberInfo info = new MemberInfo();
			
			Weixin weixin = request.getWeixin();
			Weibo weibo = request.getWeibo();
			Qq qq = request.getQq();

			//weixin
			if (weixin != null) {
				
				Member m = getMemberByUniqueIdAndChannel(weixin.getOpenid(),CommonConstant.REGISTER_CHANNEL_WEIXIN);
				if (m!=null) {
					return m;
				}
				weixinDao.save(weixin);
				info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_WEIXIN);
			}

			//qq
			if (qq != null) {
				Member m = getMemberByUniqueIdAndChannel(qq.getOpenId(),CommonConstant.REGISTER_CHANNEL_QQ);
				if (m!=null) {
					return m;
				}
				qqDao.save(qq);
				info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_QQ);
			}

			//weibo
			if (weibo != null) {
				
				Member m = getMemberByUniqueIdAndChannel(weibo.getOpenId(),CommonConstant.REGISTER_CHANNEL_WEIBO);
				if (m!=null) {
					return m;
				}
				
				weiboDao.save(weibo);
				info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_WEIBO);
			}

			//app
			if (infoRequest != null) {
				if (infoRequest.getMobile() != null) {
					
					Member m = getMemberByUniqueIdAndChannel(infoRequest.getMobile(),CommonConstant.REGISTER_CHANNEL_APP);
					if (m!=null) {
						return m;
					}					
					info.setMobile(infoRequest.getMobile());
					info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_APP);
				}else{
					info.setIcon(infoRequest.getIcon());
					info.setGender(infoRequest.getGender());
					info.setNickname(infoRequest.getNickname());
					info.setOpenId(infoRequest.getOpenId());					
				}
				
				
				info.setCreateTime(new Date());
				
				memberInfoDao.save(info);
			}

			member.setMemberInfo(info);
			member.setPwd(infoRequest.getPwd());
			member.setWeixin(weixin);
			member.setWeibo(weibo);
			member.setQq(qq);
			memberDao.save(member);

			return member;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Member getMemberByUniqueIdAndChannel(String uniqueId, String channel) {

		if (CommonConstant.REGISTER_CHANNEL_APP.equals(channel)) {

			MemberInfo info = (MemberInfo) memberInfoDao.createQuery(
					"from MemberInfo where mobile='" + uniqueId + "' ")
					.uniqueResult();

			if (info != null) {

				Member member = (Member) memberDao
						.createQuery("from Member where info='" + info.getId()
								+ "'").uniqueResult();

				return member;
			}

		} else if (CommonConstant.REGISTER_CHANNEL_WEIXIN.equals(channel)) {

			Weixin weixin = (Weixin) weixinDao.createQuery(
					"from Weixin where openId='" + uniqueId + "' ")
					.uniqueResult();

			if (weixin != null) {
				Member member = (Member) memberDao
						.createQuery("from Member where weixin='"
								+ weixin.getId() + "'").uniqueResult();

				return member;
			}

		} else if (CommonConstant.REGISTER_CHANNEL_QQ.equals(channel)) {

			Qq qq = (Qq) qqDao.createQuery(
					"from Qq where openId='" + uniqueId + "' ").uniqueResult();

			if (qq != null) {
				Member member = (Member) memberDao
						.createQuery("from Member where qq='" + qq.getId()
								+ "'").uniqueResult();

				return member;
			}

		} else if (CommonConstant.REGISTER_CHANNEL_WEIBO.equals(channel)) {

			Weibo weibo = (Weibo) weiboDao.createQuery(
					"from Weibo where openId='" + uniqueId + "' ")
					.uniqueResult();

			if (weibo != null) {
				Member member = (Member) memberDao
						.createQuery("from Member where weibo='"
								+ weibo.getId() + "'").uniqueResult();

				return member;
			}

		}

		return null;

	}

	private boolean ifMemberExists(String uniqueId, String channel) {

		if (CommonConstant.REGISTER_CHANNEL_APP.equals(channel)) {

			MemberInfo info = (MemberInfo) memberInfoDao.createQuery(
					"from MemberInfo where mobile='" + uniqueId + "' ")
					.uniqueResult();

			if (info != null) {
				return true;
			}

		} else if (CommonConstant.REGISTER_CHANNEL_WEIXIN.equals(channel)) {

			Weixin weixin = (Weixin) weixinDao.createQuery(
					"from Weixin where openId='" + uniqueId + "' ")
					.uniqueResult();

			if (weixin != null) {
				return true;
			}

		} else if (CommonConstant.REGISTER_CHANNEL_QQ.equals(channel)) {

			Qq qq = (Qq) qqDao.createQuery(
					"from Qq where openId='" + uniqueId + "' ").uniqueResult();

			if (qq != null) {
				return true;
			}

		} else if (CommonConstant.REGISTER_CHANNEL_WEIBO.equals(channel)) {

			Weibo weibo = (Weibo) weiboDao.createQuery(
					"from Weibo where openId='" + uniqueId + "' ")
					.uniqueResult();

			if (weibo != null) {
				return true;
			}

		}

		return false;
	}

	@Override
	public RespData login(String json, HttpHeaders headers) {

		JSONObject jsonObject = JsonUtils.toJSONObject(json);
		MemberLoginRequest request = JsonUtils.toBean(jsonObject, MemberLoginRequest.class);
		
		if (request.getMobile()!=null) {
			
			String mobile = request.getMobile();
			String pwd = request.getPwd();
			
			Member member = (Member)memberDao.createQuery("select a from Member a,MemberInfo b where a.memberInfo.id=b.id and a.pwd='"+pwd+"' and b.mobile='"+mobile+"' ").uniqueResult();
			
			if (member!=null) {
				return RespCode.getRespData(RespCode.SUCESS,member);
			}
			
		}else {
			String memberId = headers.getFirst("memberId");
			
			String token = headers.getFirst("token");
			
			Member member = (Member)memberDao.createQuery("from Member where id='"+memberId+"' and token='"+token+"' ").uniqueResult();
			
			if (member!=null) {
				return RespCode.getRespData(RespCode.SUCESS,member);
			}
		}
		
		return RespCode.getRespData(RespCode.ERROR);
	}

	@Override
	public RespData getMemberByUserid(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespData registerAndLogin(String json, HttpHeaders headers) {
		
		Member member = this.register(json, headers);
		
		if (member!=null) {
			return RespCode.getRespData(RespCode.SUCESS,member);
		}
		
		return RespCode.getRespData(RespCode.ERROR);
	}

}
