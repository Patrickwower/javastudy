package com.dengyuecang.api.service.members.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.dengyuecang.api.controller.members.model.MemberRegisterRequest;
import com.dengyuecang.api.entity.Demand;
import com.dengyuecang.api.entity.Function;
import com.dengyuecang.api.entity.Identity;
import com.dengyuecang.api.entity.Member;
import com.dengyuecang.api.entity.MemberDemand;
import com.dengyuecang.api.entity.MemberFunction;
import com.dengyuecang.api.entity.MemberIdentity;
import com.dengyuecang.api.entity.MemberInfo;
import com.dengyuecang.api.entity.Qq;
import com.dengyuecang.api.entity.StaticResource;
import com.dengyuecang.api.entity.Weibo;
import com.dengyuecang.api.entity.Weixin;
import com.dengyuecang.api.service.IStaticResourceService;
import com.dengyuecang.api.service.common.CommonConstant;
import com.dengyuecang.api.service.members.IMembersService;
import com.dengyuecang.api.service.members.model.DemandRequest;
import com.dengyuecang.api.service.members.model.FunctionRequest;
import com.dengyuecang.api.service.members.model.IdentityRequest;
import com.dengyuecang.api.service.members.model.MemberInfoRequest;
import com.dengyuecang.api.utils.JsonUtils;
import com.dengyuecang.api.utils.RespCode;
import com.dengyuecang.api.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;

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
		
		if (StringUtils.isNotEmpty(request.getMobile())) {
			
			String mobile = request.getMobile();
			
			boolean mobileExits = ifMemberExits(mobile, CommonConstant.REGISTER_CHANNEL_APP);
			if (mobileExits) {
				return RespCode.getRespData(RespCode.REG_MOBILE_EXISTS, "手机号已存在，请更换");
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
			Map<String, String> loginResponse = new HashMap<String, String>();
			
			loginResponse.put("memberId", member.getId());
			
			loginResponse.put("token", member.getToken());
			
			return RespCode.getRespData(RespCode.SUCESS,loginResponse);
		}
		return RespCode.getRespData(RespCode.ERROR,"注册时发生问题，请重新注册");
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

//		Map<String, String[]> params = request.getParameterMap();
		
		String mobile = (String)request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		
		if (StringUtils.isEmpty(mobile)) {
			return RespCode.getRespData(RespCode.JSON_ERROR,"需要mobile参数");
		}
		
		if (StringUtils.isEmpty(pwd)) {
			return RespCode.getRespData(RespCode.JSON_ERROR,"需要pwd参数");
		}
		
		Member member = (Member)memberDao.createQuery("select a from Member a,MemberInfo b where a.memberInfo.id=b.id and a.pwd='"+pwd+"' and b.mobile='"+mobile+"' ").uniqueResult();
		
		if (member!=null) {
			
			Map<String, String> loginResponse = new HashMap<String, String>();
			
			loginResponse.put("memberId", member.getId());
			
			loginResponse.put("token", member.getToken());
			
			return RespCode.getRespData(RespCode.SUCESS,loginResponse);
		}
		
		return RespCode.getRespData(RespCode.USER_NOT_EXIST,"用户不存在，请重新注册");
	}

//	private Member getMemberByMobileAndPwd(String mobile, String pwd){
//		
//		Member member = (Member)memberDao.createQuery("select a from Member a,MemberInfo b where a.memberInfo.id=b.id and a.pwd='"+pwd+"' and b.mobile='"+mobile+"' ").uniqueResult();
//		
//		return member;
//	}
	
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
		
		return RespCode.getRespData(RespCode.SUCESS,member);
		
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
		
//		MemberRequest request = (MemberRequest)JSONObject.toBean(jsonObject, MemberRequest.class, map);
		
		MemberInfoRequest infoRequest = (MemberInfoRequest)JSONObject.toBean(jsonObject,MemberInfoRequest.class,map);
		
		
//		MemberRequest request = JsonUtils.toBean(json, MemberRequest.class, map);
		
		member = this.saveIdentity(infoRequest, member, headers);
		
		member = this.saveDemand(infoRequest, member, headers);
		
		member = this.saveFunction(infoRequest, member, headers);
		
		return RespCode.getRespData(RespCode.SUCESS,"保存成功");
		
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
				return RespCode.getRespData(RespCode.REG_MOBILE_EXISTS,"该手机号码已存在，请更换其他手机号码");
			}
			
			this.updateMobile(memberId, mobile);
			
			return RespCode.getRespData(RespCode.SUCESS,mobile);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return RespCode.getRespData(RespCode.ERROR);
		
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
		StaticResource sr = staticResourceDao.get(StaticResource.class, imgId);
		
		if (sr!=null) {
			
			String memberId = headers.getFirst("memberId");
			
			updateHead(memberId, sr.getUrlPath(),imgId);
			
			Map<String, String> result = new HashMap<String,String>();
			
			result.put("urlPath", sr.getUrlPath());
			
			return RespCode.getRespData(RespCode.SUCESS,result);
		}
		
		return RespCode.getRespData(RespCode.ERROR,"头像修改失败");
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
			
			String memberId = headers.getFirst("memberId");
			log.info("更新昵称的ID："+memberId);
			this.updateNickname(memberId, nickname);
			log.info("更新完成");
			return RespCode.getRespData(RespCode.SUCESS,"昵称修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RespCode.getRespData(RespCode.ERROR,"昵称修改失败");
		
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

}
