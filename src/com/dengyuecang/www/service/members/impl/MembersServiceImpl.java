package com.dengyuecang.www.service.members.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.dengyuecang.www.controller.api.members.model.request.MemberRegisterRequest;
import com.dengyuecang.www.controller.api.members.model.response.DemandResponse;
import com.dengyuecang.www.controller.api.members.model.response.FunctionResponse;
import com.dengyuecang.www.controller.api.members.model.response.IdentityResponse;
import com.dengyuecang.www.controller.api.members.model.response.InfoResponse;
import com.dengyuecang.www.controller.api.members.model.response.MemberResponse;
import com.dengyuecang.www.entity.Demand;
import com.dengyuecang.www.entity.Function;
import com.dengyuecang.www.entity.Identity;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.MemberDemand;
import com.dengyuecang.www.entity.MemberFunction;
import com.dengyuecang.www.entity.MemberIdentity;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.entity.Qq;
import com.dengyuecang.www.entity.StaticResource;
import com.dengyuecang.www.entity.Weibo;
import com.dengyuecang.www.entity.Weixin;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.service.members.model.DemandRequest;
import com.dengyuecang.www.service.members.model.FunctionRequest;
import com.dengyuecang.www.service.members.model.IdentityRequest;
import com.dengyuecang.www.service.members.model.MemberInfoRequest;
import com.dengyuecang.www.utils.JsonUtils;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import com.longinf.lxframework.util.security.MD5Util;

@Service
public class MembersServiceImpl extends BaseService<Member> implements IMembersService{

	Logger log = LoggerFactory.getLogger(MembersServiceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<Member> memberDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<MemberInfo> memberInfoDao;
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<StaticResource> staticResourceDao;
	
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

	@Resource
	private IStaticResourceService staticResourceServiceImpl;
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<Weixin> weixinDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Weibo> weiboDao;

	@Resource(name = "hibernateBaseDao")
	private BaseDao<Qq> qqDao;
	
	@Override
	public RespData register(HttpHeaders headers,MemberRegisterRequest request) {
		
		Member member = new Member();
		//数据验证
		if(StringUtils.isEmpty(request.getMobile())&&StringUtils.isEmpty(request.getOpenId())){
			return RespCode.getRespData(RespCode.MOBILE_OPENID, new HashMap<>());
		}
		
		if (StringUtils.isEmpty(request.getOpenId())&&StringUtils.isNotEmpty(request.getMobile())) {
			if (StringUtils.isEmpty(request.getPwd())) {
				return RespCode.getRespData(RespCode.PWD_NEEDED, new HashMap<>());
			}
		}
		if (StringUtils.isEmpty(request.getMobile())&&StringUtils.isNotEmpty(request.getOpenId())) {
			
			if (StringUtils.isEmpty(request.getNickname())) {
				return RespCode.getRespData(RespCode.NICKNAME_NEEDED, new HashMap<>());
			}
			
			if (StringUtils.isEmpty(request.getIcon())) {
				return RespCode.getRespData(RespCode.ICON_NEEDED, new HashMap<>());
			}
			
			if (StringUtils.isEmpty(request.getGender())) {
				return RespCode.getRespData(RespCode.GENDER_NEEDED, new HashMap<>());
			}
			
			
			if (StringUtils.isEmpty(request.getWeixin())&&StringUtils.isEmpty(request.getWeibo())&&StringUtils.isEmpty(request.getQq())) {
				return RespCode.getRespData(RespCode.WEIBO_WEIXIN_QQ_NEEDED, new HashMap<>());
			}
			
		}
		
		if (StringUtils.isNotEmpty(request.getMobile())) {
			
			String mobile = request.getMobile();
			
			Member memberExist = getMemberByMobile(mobile);
			if (memberExist!=null) {
				
				if (CommonConstant.REGISTER_CHANNEL_APP.equals(memberExist.getMemberInfo().getCreateChannel())) {
					
					return RespCode.getRespData(RespCode.MOBILE_REGISTERED, new HashMap<String, String>());
				}else {
					return RespCode.getRespData(RespCode.MOBILE_BOUND, new HashMap<String, String>());
				}
				
			}
			
			member = saveMemberApp(headers, request);
			
		}else {
			String openId = request.getOpenId();
			
			String channel = "";
			
			if (StringUtils.isNotEmpty(request.getWeixin())) {
				channel = CommonConstant.REGISTER_CHANNEL_WEIXIN;
			}else if (StringUtils.isNotEmpty(request.getQq())) {
				channel = CommonConstant.REGISTER_CHANNEL_QQ;
			}else if(StringUtils.isNotEmpty(request.getWeibo())){
				channel = CommonConstant.REGISTER_CHANNEL_WEIBO;
			}
			
			boolean memberExits = ifMemberExits(openId, channel);
			
			if (memberExits) {
				
				member = getMemberByOpenId(openId,channel);
				
			}else {
				
				member = saveMemberThird(headers, request);
				
			}
			
		}
		
		if (StringUtils.isNotEmpty(member.getId())) {
			
			String token = member.getToken();
			
			//待有了id后，生成token，回填token
			if (StringUtils.isEmpty(member.getToken())) {
				headers.set("memberId", member.getId());
				token = this.makeToken(headers, null);
				memberDao.createQuery("update Member set token='"+token+"' where id='"+member.getId()+"' ").executeUpdate();
			}
			
			Map<String, String> loginResponse = new HashMap<String, String>();
			
			loginResponse.put("memberId", member.getId());
			
			loginResponse.put("token", token);
			
			return RespCode.getRespData(RespCode.SUCESS,loginResponse);
		}
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<>());
	}
	
	private Member saveMemberApp(HttpHeaders headers,MemberRegisterRequest request){
		
		MemberInfo info = new MemberInfo();
		
		info.setMobile(request.getMobile());
		info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_APP);
		info.setOpenId(request.getMobile());
		info.setCreateTime(new Date());
		
		memberInfoDao.save(info);
		
		Member member = new Member();
		
		member.setPwd(request.getPwd());
		
		member.setMemberInfo(info);
		member.setPwd(request.getPwd()==null?"":request.getPwd());

		memberDao.save(member);
		
		return member;
	}
	
	private Member saveMemberThird(HttpHeaders headers,MemberRegisterRequest request){
		
		String openId = request.getOpenId();
		
		String nickname = request.getNickname();
		
		String icon = request.getIcon();
		
		String gender = request.getGender();
		
		MemberInfo info = new MemberInfo();
		
		
		info.setCreateTime(new Date());
		
		info.setOpenId(openId);
		info.setGender(gender);
		info.setIcon(icon);
		info.setNickname(nickname);
		
		
		
		String weixin_info = request.getWeixin();
		String qq_info = request.getQq();
		String weibo_info = request.getWeibo();
		
		Member member = new Member();

		if (StringUtils.isNotEmpty(weixin_info)) {
			
			JSONObject jsonObject = JsonUtils.toJSONObject(weixin_info);
			
			Weixin weixin = JsonUtils.toBean(jsonObject, Weixin.class);
			
			weixin.setWeixin_info(weixin_info);
			
			weixinDao.save(weixin);
			
			info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_WEIXIN);
			
			member.setWeixin(weixin);
			
		}else if (StringUtils.isNotEmpty(qq_info)) {
			
			JSONObject jsonObject = JsonUtils.toJSONObject(qq_info);
			
			Qq qq = JsonUtils.toBean(jsonObject, Qq.class);
			
			qq.setOpenId(request.getOpenId());
			
			qq.setQq_info(qq_info);
			
			qqDao.save(qq);
			
			info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_QQ);
			
			member.setQq(qq);

		}else if (StringUtils.isNotEmpty(weibo_info)) {
			
			Weibo weibo = new Weibo();
			
			weibo.setOpenId(openId);
			
			weibo.setWeibo_info(weibo_info);
			
			weiboDao.save(weibo);
			
			info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_WEIBO);
			
			member.setWeibo(weibo);

			
		}
		
		memberInfoDao.save(info);
		member.setMemberInfo(info);
		member.setPwd(request.getPwd()==null?"":request.getPwd());
		memberDao.save(member);
		
		
		return member;
	}

	private Member getMemberByOpenId(String openId,String channel){
		
		Query query = memberDao.createQuery("select a from Member a,MemberInfo b where a.memberInfo.id=b.id and b.openId=? and b.createChannel=?");
		
		query.setString(0, openId);
		
		query.setString(1, channel);
		
		Member member = new Member();
		
		try {
			member = (Member)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		return member;
	}
	
	private Member getMemberByMobile(String mobile){
		
		Query query = memberDao.createQuery("select a from Member a,MemberInfo b where a.memberInfo.id=b.id and b.mobile=?");
		
		query.setString(0, mobile);
		
		try {
			Member member = (Member)query.uniqueResult();
			return member;
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return null;
	}
	
	
	
	private boolean ifMemberExits(String openId,String channel){
		
		if (CommonConstant.REGISTER_CHANNEL_APP.equals(channel)) {
			return ifMobileExist(openId);
		}else {
			Member member = getMemberByOpenId(openId,channel);
			if (member!=null) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public RespData login(HttpHeaders headers, HttpServletRequest request) {

		String mobile = (String)request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		
		if (StringUtils.isEmpty(mobile)) {
			return RespCode.getRespData(RespCode.LOGIN_MOBILE_NEEDED,new HashMap<>());
		}
		
		if (StringUtils.isEmpty(pwd)) {
			return RespCode.getRespData(RespCode.LOGIN_PWD_NEEDED,new HashMap<>());
		}
		
		Member member = (Member)memberDao.createQuery("select a from Member a,MemberInfo b where a.memberInfo.id=b.id and a.pwd='"+pwd+"' and b.mobile='"+mobile+"' ").uniqueResult();
		
		if (member!=null) {
			
			//方便旧数据未生成token的，在这里补充
			if (StringUtils.isEmpty(member.getToken())) {
				headers.set("memberId", member.getId());
				member.setToken(this.makeToken(headers, request));
				memberDao.saveOrUpdate(member);
			}
			
			Map<String, String> loginResponse = new HashMap<String, String>();
			
			loginResponse.put("memberId", member.getId());
			
			loginResponse.put("token", StringUtils.isEmpty(member.getToken())?"":member.getToken());
			
			return RespCode.getRespData(RespCode.SUCESS,loginResponse);
		}
		
		return RespCode.getRespData(RespCode.MEMBER_NOT_EXIST_OR_PWD_ERROR,new HashMap<>());
	}

	/**
	 * 获取用户信息
	 * 有待继续整改重构
	 */
	@Override
	public RespData memberInfo(HttpHeaders headers) {

		String memberId = headers.getFirst("memberId");
		
		Member member = memberDao.get(Member.class,memberId);
		
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
			
			Query q = memberIdentityDao.createQuery("select b from MemberIdentity b where b.status='101' and b.member.id='"+memberId+"' order by b.ctime desc");
			
			q.setFirstResult(0);
			q.setMaxResults(0);
			
			MemberIdentity mi = (MemberIdentity)q.uniqueResult();
			
			member.setCity(mi.getCity());
			member.setOrganization(mi.getOrganization());
		}
		if (StringUtils.isEmpty(member.getMemberInfo().getQr())) {
			staticResourceServiceImpl.saveQr(member.getId());
		}
		
		return fromModelToResponse(member);
		
	}

	/**
	 * model转化为输出对象，member
	 * @return
	 */
	private RespData fromModelToResponse(Member member){
		
		MemberResponse mr = new MemberResponse();
		
		mr.setMemberId(member.getId());
		
		mr.setStatus("");
		
		InfoResponse ir = new InfoResponse();
		
		ir.setGender(member.getMemberInfo().getGender()==null?"":member.getMemberInfo().getGender());
		ir.setIcon(member.getMemberInfo().getIcon()==null?"":member.getMemberInfo().getIcon());
		ir.setMobile(member.getMemberInfo().getMobile()==null?"":member.getMemberInfo().getMobile());
		ir.setNickname(member.getMemberInfo().getNickname()==null?"":member.getMemberInfo().getNickname());
		ir.setQr(member.getMemberInfo().getQr()==null?"":member.getMemberInfo().getQr());
		ir.setCity(member.getCity()==null?"":member.getCity());
		ir.setOrganization(member.getOrganization()==null?"":member.getOrganization());
		ir.setIsFeedBack(member.getIfFeedBack());
		
		for (Identity identity : member.getIdentityList()) {
			IdentityResponse idr = new IdentityResponse();
			
			idr.setId(identity.getId());
			idr.setName(identity.getName());
			
			mr.getIdentitys().add(idr);
		};
		
		mr.setMemberInfo(ir);
		
		for (Demand demand : member.getDemandList()) {
			
			DemandResponse dr = new DemandResponse();
			
			dr.setId(demand.getId());
			dr.setName(demand.getName());
			
			mr.getDemands().add(dr);
			
		}
		
		for (Function function : member.getFunctionList()) {
			
			FunctionResponse fr = new FunctionResponse();
			
			fr.setId(function.getId());
			fr.setName(function.getName());
			
			mr.getFunctions().add(fr);
			
		}
		
		return RespCode.getRespData(RespCode.SUCESS,mr);
	}
	
	@Override
	public RespData updateMemberInfo(HttpHeaders headers, HttpServletRequest request) {

		String memberId = headers.getFirst("memberId");
		
		Member member = memberDao.get(Member.class, memberId);
		
		String json = request.getParameter("json");
		
		JSONObject jsonObject = JsonUtils.toJSONObject(json);
		
		HashMap<String, Class> map = new HashMap<String,Class>();
		
		map.put("identityList", IdentityRequest.class);
		
		map.put("demandList", DemandRequest.class);
		
		map.put("functionList", FunctionRequest.class);
		
		
		MemberInfoRequest infoRequest = (MemberInfoRequest)JSONObject.toBean(jsonObject,MemberInfoRequest.class,map);
		
		member = this.saveIdentity(infoRequest, member, headers);
		
		member = this.saveDemand(infoRequest, member, headers);
		
		member = this.saveFunction(infoRequest, member, headers);
		
		return RespCode.getRespData(RespCode.SUCESS,new HashMap<>());
		
	}

	private Member saveIdentity(MemberInfoRequest request,Member member,HttpHeaders headers){
		
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
	
	private Member saveDemand(MemberInfoRequest request,Member member,HttpHeaders headers){
		
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
	
	private Member saveFunction(MemberInfoRequest request,Member member,HttpHeaders headers){
		

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
	public RespData bindMobile(HttpHeaders headers, String mobile) {

		try {
			String memberId = headers.getFirst("memberId");
			
			if (ifMobileExist(mobile)) {
				return RespCode.getRespData(RespCode.MOBILE_BOUND);
			}
			
			this.updateMobile(memberId, mobile);
			
			return RespCode.getRespData(RespCode.SUCESS,new HashMap<>());

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return RespCode.getRespData(RespCode.ERROR,new HashMap<>());
		
	}
	
	/**
	 * 验证手机是否已经存在
	 * @param mobile
	 * @return
	 */
	private boolean ifMobileExist(String mobile){
		
		Query q = memberInfoDao.createQuery("from MemberInfo a where a.mobile=?");
		
		q.setString(0,mobile);
		
		MemberInfo memberInfo = (MemberInfo)q.uniqueResult();
		
		if (memberInfo!=null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 更新绑定的手机
	 * @param memberId
	 * @param mobile
	 */
	private void updateMobile(String memberId,String mobile){
		
		Member member = memberDao.get(Member.class, memberId);
		
		MemberInfo info = member.getMemberInfo();
		
		info.setMobile(mobile);
		
		memberInfoDao.saveOrUpdate(info);
		
	}

	@Override
	public RespData updateHead(HttpHeaders headers, String imgId) {
		// TODO Auto-generated method stub
		log.info("更新头像");
		log.info("头像的资源id:"+imgId);
		
		if (StringUtils.isEmpty(imgId)) {
			return RespCode.getRespData(RespCode.ICON_NEEDED, new HashMap<>());
		}
		
		StaticResource sr = staticResourceDao.get(StaticResource.class, imgId);
		
		if (sr!=null) {
			
			String memberId = headers.getFirst("memberId");
			
			updateHead(memberId, sr.getUrlPath(),imgId);
			
			Map<String, String> result = new HashMap<String,String>();
			
			result.put("urlPath", sr.getUrlPath());
			
			return RespCode.getRespData(RespCode.SUCESS,result);
		}else {
			return RespCode.getRespData(RespCode.STATIC_RESOURCE_NOTFOUND,new HashMap<>());
		}
		
	}
	
	/**
	 * 更新头像图片地址
	 * @param memberId
	 * @param imgPath
	 * @param imgId
	 */
	
	private void updateHead(String memberId,String imgPath,String imgId){
		
		Member member = memberDao.get(Member.class, memberId);
		
		MemberInfo info = member.getMemberInfo();
		
		info.setIcon(imgPath);
		
		info.setImgId(imgId);
		
		memberInfoDao.saveOrUpdate(info);
		
	}

	@Override
	public RespData updateNickname(HttpHeaders headers, String nickname) {
		log.info("更新昵称为："+nickname);
		try {
			
			if (StringUtils.isEmpty(nickname)) {
				return RespCode.getRespData(RespCode.NICKNAME_NEEDED,new HashMap<>());
			}
			
			String memberId = headers.getFirst("memberId");
			log.info("更新昵称的ID："+memberId);
			this.updateNickname(memberId, nickname);
			log.info("更新完成");
			return RespCode.getRespData(RespCode.SUCESS,new HashMap<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RespCode.getRespData(RespCode.UPDATE_NICKNAME_EXCEPTION,new HashMap<>());
		
	}
	
	/**
	 * 更新昵称
	 * @param memberId
	 * @param nickname
	 */
	private void updateNickname(String memberId,String nickname){
		
		Member member = memberDao.get(Member.class, memberId);
		
		MemberInfo info = member.getMemberInfo();
		
		info.setNickname(nickname);
		
		memberInfoDao.saveOrUpdate(info);
		
	}

	@Override
	public RespData updateQr(String memberId) {
		
		
		
		return null;
	}

	@Override
	public BaseDao<Member> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespData checkToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String memberId = request.getHeader("memberId");
		
		String memberToken = request.getHeader("memberToken");
		
		if (StringUtils.isEmpty(memberId)) {
			return RespCode.getRespData(RespCode.HEADER_MEMBERID_NEEDED,new HashMap<>());
		}
		
		if (StringUtils.isEmpty(memberToken)) {
			return RespCode.getRespData(RespCode.HEADER_MEMBERTOKEN_NEEDED,new HashMap<>());
		}
		
		Member member = memberDao.get(Member.class, memberId);
		
		if (member!=null) {
			if (!memberToken.equals(member.getToken())) {
				return RespCode.getRespData(RespCode.HEADER_MEMBERTOKEN_ERROR,new HashMap<>());
			}
		}else {
			return RespCode.getRespData(RespCode.MEMBER_NOT_EXIST,new HashMap<>());
		}
		
		return RespCode.getRespData(RespCode.SUCESS,new HashMap<>());
	}
	
	/**
	 * memberToken的生成机制，header中的所有参数除token外k=v的形式自然顺序连接，做md5加密
	 * @param headers
	 * @param request
	 * @return
	 */
	private String makeToken(HttpHeaders headers,HttpServletRequest request){
		
		Set<String> headerKeys = headers.keySet();
		
		String str = "";
		
		for (String key : headerKeys) {
			if ("memberToken".equals(key)) {
				continue;
			}
			String value = headers.getFirst(key);
			
			String kv = key+"="+value;
			str += kv;
		}
		
		str = MD5Util.encode(str);
		
		return str;
	}

}
