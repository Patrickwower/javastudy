package com.dengyuecang.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.dengyuecang.api.entity.Demand;
import com.dengyuecang.api.entity.Function;
import com.dengyuecang.api.entity.Identity;
import com.dengyuecang.api.entity.Member;
import com.dengyuecang.api.entity.MemberDemand;
import com.dengyuecang.api.entity.MemberFunction;
import com.dengyuecang.api.entity.MemberIdentity;
import com.dengyuecang.api.entity.MemberInfo;
import com.dengyuecang.api.entity.Qq;
import com.dengyuecang.api.entity.Weibo;
import com.dengyuecang.api.entity.Weixin;
import com.dengyuecang.api.service.IMemberService;
import com.dengyuecang.api.service.common.CommonConstant;
import com.dengyuecang.api.service.model.DemandRequest;
import com.dengyuecang.api.service.model.FunctionRequest;
import com.dengyuecang.api.service.model.IdentityRequest;
import com.dengyuecang.api.service.model.MemberInfoRequest;
import com.dengyuecang.api.service.model.MemberLoginRequest;
import com.dengyuecang.api.service.model.MemberRegisterRequest;
import com.dengyuecang.api.service.model.MemberRequest;
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
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<Identity> identityDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Demand> demandDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Function> functionDao;
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<MemberIdentity> memberIdentityDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<MemberDemand> memberDemandDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<MemberFunction> memberFunctionDao;
	

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
	public Member saveMember(String json, HttpHeaders headers) {

		try {

			JSONObject jsonObject = JsonUtils.toJSONObject(json);
			
			MemberRegisterRequest request = JsonUtils.toBean(jsonObject, MemberRegisterRequest.class);
			
//			Member member = JsonUtils.toBean(jsonObject, Member.class);

			MemberInfoRequest infoRequest = request.getMemberInfo();
			
			MemberInfo info = new MemberInfo();
			
			Weixin weixin = request.getWeixin();
			Weibo weibo = null;
			Object weibo_obj = request.getWeibo();
			Qq qq = request.getQq();

			//weixin
			if (weixin != null) {
				
				Member m = getMemberByUniqueIdAndChannel(weixin.getOpenId(),CommonConstant.REGISTER_CHANNEL_WEIXIN);
				if (m!=null) {
					return m;
				}
				weixinDao.save(weixin);
				info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_WEIXIN);
			}

			//qq
			if (qq != null) {
				Member m = getMemberByUniqueIdAndChannel(infoRequest.getOpenId(),CommonConstant.REGISTER_CHANNEL_QQ);
				if (m!=null) {
					return m;
				}
				qq.setOpenId(infoRequest.getOpenId());
				qqDao.save(qq);
				info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_QQ);
			}

			//weibo
			if (weibo_obj != null) {
				
				Member m = getMemberByUniqueIdAndChannel(infoRequest.getOpenId(),CommonConstant.REGISTER_CHANNEL_WEIBO);
				if (m!=null) {
					return m;
				}
				weibo = new Weibo();
				weibo.setWeibo_info(JsonUtils.toJSONString(weibo_obj));
				weibo.setOpenId(infoRequest.getOpenId());
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

			Member member = new Member();
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
	public Member getMemberByUserid(String memberId) {
		return memberDao.get(Member.class, memberId);
	}

	@Override
	public RespData registerAndLogin(String json, HttpHeaders headers) {
		
		Member member = this.saveMember(json, headers);
		
		if (member!=null) {
			return RespCode.getRespData(RespCode.SUCESS,member);
		}
		
		return RespCode.getRespData(RespCode.ERROR);
	}

	@Override
	public RespData getAllInfo(HttpHeaders headers) {
		
		String memberId = headers.getFirst("memberId");
		
		Member member = this.getMemberByUserid(memberId);
		
		
		
		//身份信息
		List<Identity> identityList = 
				identityDao.createQuery("select a from Identity a,MemberIdentity b where a.id=b.identity.id and b.status='101' and b.member.id='"+memberId+"'").list();
		
		member.setIdentityList(identityList);
		
		//需求信息
		List<Demand> demandList = 
				demandDao.createQuery("select a from Demand a,MemberDemand b where a.id=b.demand.id and b.status='101' and b.member.id='"+memberId+"'").list();
		
		member.setDemandList(demandList);
		
		//功能信息
		List<Function> functionList = 
				functionDao.createQuery("select a from Function a,MemberFunction b where a.id=b.function.id and b.status='101' and b.member.id='"+memberId+"'").list();
		
		member.setFunctionList(functionList);
		
		if (identityList.size()>0) {
			member.setIfFeedBack("1");
		}
		
		return RespCode.getRespData(RespCode.SUCESS,member);
		
	}

	@Override
	public RespData setAllInfo(HttpHeaders headers, String json) {
		
		String memberId = headers.getFirst("memberId");
		
		Member member = this.getMemberByUserid(memberId);
		
		JSONObject jsonObject = JsonUtils.toJSONObject(json);
		
		HashMap<String, Class> map = new HashMap<String,Class>();
		
		map.put("identityList", IdentityRequest.class);
		
		map.put("demandList", DemandRequest.class);
		
		map.put("functionList", FunctionRequest.class);
		
		MemberRequest request = (MemberRequest)JSONObject.toBean(jsonObject, MemberRequest.class, map);
		
		
//		MemberRequest request = JsonUtils.toBean(json, MemberRequest.class, map);
		
		member = this.saveIdentity(request, member, headers);
		
		member = this.saveDemand(request, member, headers);
		
		member = this.saveFunction(request, member, headers);
		
		return RespCode.getRespData(RespCode.SUCESS,member);
	}

	private Member saveIdentity(MemberRequest request,Member member,HttpHeaders headers){
		
		List<IdentityRequest> idenList = request.getIdentityList();
		
		List<Identity> iList = new ArrayList<Identity>();
		try {
			
			memberIdentityDao.createQuery("update MemberIdentity a set a.status=201 where a.member.id='"+member.getId()+"' ").executeUpdate();
			
			for (IdentityRequest identity : idenList) {
				
				Identity iden = (Identity) identityDao.createQuery("from Identity a where a.name='"+identity.getName()+"'").uniqueResult();
				
				if (iden!=null) {
					
				}else {
					iden = new Identity();
					iden.setCreater(member.getId());
					iden.setCreateTime(new Date());
					iden.setCreateType("member");
					iden.setName(identity.getName());
					iden.setStatus("3");
					iden.setVersion(headers.getFirst("version"));
				}
				identityDao.saveOrUpdate(iden);
				iList.add(iden);
				
				
				
//				MemberIdentity mi = (MemberIdentity) memberIdentityDao.createQuery("from MemberIdentity a where a.identity='"+iden.getId()+"' and a.member.id='"+member.getId()+"' ").uniqueResult();
			
				MemberIdentity mi = new MemberIdentity();
				
				mi.setIdentity(iden);
				mi.setMember(member);
				mi.setStatus("101");
				mi.setCity(identity.getCity());
				mi.setCtime(new Date());
				mi.setIP("");
				mi.setOrganization(identity.getOrganization());
				
				memberIdentityDao.saveOrUpdate(mi);
				
			}
			member.setIdentityList(iList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return member;
	}
	
	private Member saveDemand(MemberRequest request,Member member,HttpHeaders headers){
		
		List<DemandRequest> demandList = request.getDemandList();
		
		List<Demand> dList = new ArrayList<Demand>();
		try {
			
			memberDemandDao.createQuery("update MemberDemand a set a.status=201 where a.member.id='"+member.getId()+"' ").executeUpdate();
			
			for (DemandRequest demand : demandList) {
				
				Demand d = (Demand) demandDao.createQuery("from Demand a where a.name='"+demand.getName()+"'").uniqueResult();
				
				if (d!=null) {
					
				}else {
					d = new Demand();
					d.setCreater(member.getId());
					d.setCreateTime(new Date());
					d.setCreateType("member");
					d.setName(demand.getName());
					d.setStatus("3");
					d.setVersion(headers.getFirst("version"));
				}
				demandDao.saveOrUpdate(d);
				dList.add(d);
				
				MemberDemand md  = new MemberDemand();
					
				md.setDemand(d);
				md.setMember(member);
					
				md.setCtime(new Date());
				md.setStatus("101");
				
				memberDemandDao.saveOrUpdate(md);
				
			}
			member.setDemandList(dList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return member;
		
	}
	
	private Member saveFunction(MemberRequest request,Member member,HttpHeaders headers){
		

		List<FunctionRequest> functionList = request.getFunctionList();
		
		List<Function> fList = new ArrayList<Function>();
		try {
			
			memberFunctionDao.createQuery("update MemberFunction a set a.status=201 where a.member.id='"+member.getId()+"' ").executeUpdate();
			
			for (FunctionRequest function : functionList) {
				
				Function f = (Function) functionDao.createQuery("from Function a where a.name='"+function.getName()+"'").uniqueResult();
				
				if (f!=null) {
					
				}else {
					f = new Function();
					f.setCreater(member.getId());
					f.setCreateTime(new Date());
					f.setCreateType("member");
					f.setName(function.getName());
					f.setStatus("3");
					f.setVersion(headers.getFirst("version"));
				}
				functionDao.saveOrUpdate(f);
				fList.add(f);
				
				MemberFunction mf = new MemberFunction();
					
				mf.setFunction(f);
				mf.setMember(member);
				mf.setCtime(new Date());
				mf.setStatus("101");
				
				memberFunctionDao.saveOrUpdate(mf);
				
			}
			member.setFunctionList(fList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	@Override
	public RespData getBasicInfo(){
		
		Map<String, List> datas = new HashMap<String, List>();
		
		List<Identity> identityList = identityDao.createQuery("SELECT	a FROM	Identity a LEFT JOIN MemberIdentity b ON a.id = b.identity.id and a.status='1' GROUP BY	a.id order by count(b.id) DESC").list();
		datas.put("identity",identityList);
		
		List<Demand> demandList = demandDao.createQuery("SELECT	a FROM	Identity a LEFT JOIN MemberDemand b ON a.id = b.demand.id and a.status='1' GROUP BY	a.id order by count(b.id) DESC").list();
		datas.put("demand",demandList);
		
		List<Function> functionList = functionDao.createQuery("SELECT	a FROM	Function a LEFT JOIN MemberFunction b ON a.id = b.function.id and a.status='1' GROUP BY	a.id order by count(b.id) DESC").list();
		datas.put("function",functionList);
		
		
		return RespCode.getRespData(RespCode.SUCESS,datas); 
	}

	@Override
	public RespData bindMobile(HttpHeaders headers, String json) {
		
		try {
			String memberId = headers.getFirst("memberId");
			
			JSONObject jsonObject = JsonUtils.toJSONObject(json);
			
			String mobile = (String)jsonObject.get("mobile");
			
			this.updateMobile(memberId, mobile);
			
			return RespCode.getRespData(RespCode.SUCESS,mobile);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
		return RespCode.getRespData(RespCode.ERROR);
	}
	
	private void updateMobile(String memberId,String mobile){
		
		Member member = memberDao.get(Member.class, memberId);
		
		MemberInfo info = member.getMemberInfo();
		
		info.setMobile(mobile);
		
		memberInfoDao.saveOrUpdate(info);
		
	}

	private String getCurrentMemberNum(){
		
		
		
		return null;
	}
	
}
