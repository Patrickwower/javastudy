package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.*;
import com.dengyuecang.www.controller.tool.model.FileUploadRequest;
import com.dengyuecang.www.entity.*;
import com.dengyuecang.www.entity.circle.InterestBar;
import com.dengyuecang.www.entity.circle.InterestType;
import com.dengyuecang.www.entity.circle.ResetPwdLog;
import com.dengyuecang.www.service.IMemberService;
import com.dengyuecang.www.service.IStaticResourceService;
import com.dengyuecang.www.service.circle.IInformationService;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.UpdateInfo;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.members.model.CommunityMemberResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.dengyuecang.www.utils.sharesdk.SmsUtil;
import com.dengyuecang.www.utils.sharesdk.SmsVerifyRequest;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by lxrent on 2016/12/13.
 */
@Service
public class InformationServiceImpl extends BaseService<MemberInfo> implements IInformationService {

    @Override
    public BaseDao<MemberInfo> getSuperDao() {
        return null;
    }

    @Resource(name = "hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<MemberInfo> memberInfoDao;

    @Resource(name = "hibernateBaseDao")
    private  BaseDao<InterestBar> interestBarBaseDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<InviteCode> codeDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<ResetPwdLog> resetPwdLogDao;

    @Resource
    private IMemberService memberServiceImpl;

    @Resource
    private IStaticResourceService staticResourceServiceImpl;

    @Override
    public RespData information(HttpHeaders headers, String memberId) {

        CommunityMemberResponse cMemberResponse = new CommunityMemberResponse();

        if (StringUtils.isEmpty(memberId)){

            memberId = headers.getFirst("memberId");

        }

        Member member = memberDao.get(Member.class,memberId);

        cMemberResponse.setNickname(member.getMemberInfo().getNickname());

        cMemberResponse.setId(member.getId());

        cMemberResponse.setHead(member.getMemberInfo().getIcon());

        cMemberResponse.setCtime(member.getMemberInfo().getCreateTime());

//        cMemberResponse.setIntroduction(member.getMemberInfo().getIntroduction());

        cMemberResponse.setSex(member.getMemberInfo().getGender()==null?"男":member.getMemberInfo().getGender());

        cMemberResponse.setCity(member.getMemberInfo().getCity()==null?"":member.getMemberInfo().getCity());

        cMemberResponse.setSchool(member.getMemberInfo().getSchool()==null?"":member.getMemberInfo().getSchool());

//
        try {

            String hql = "from InterestBar ib where ib.creater=? ";

            Query q = interestBarBaseDao.createQuery(hql);

            q.setString(0,memberId);

            List<InterestBar> interestBars = q.list();

            List<MomentInterest> mis = new ArrayList<MomentInterest>();

            for (InterestBar ib:interestBars){

                MomentInterest mi = new MomentInterest();

                mi.setBar_id(ib.getId());

                mi.setName(ib.getName());

                for (InterestType it:ib.getTypes()){

                    mi.getTypes().add(it.getName());

                }
                mis.add(mi);

            }


            cMemberResponse.setInterestBars(mis);

        }catch (Exception e){

            e.printStackTrace();

        }

        Map<String, Object> response = new HashMap<String, Object>();

        response.put("memberinfo",cMemberResponse);

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestType> interestTypeDao;

    @Override
    public RespData updateinfo(HttpHeaders headers, UpdateInfo updateInfo) {

        String memberId = headers.getFirst("memberId");

        Member member =  memberDao.get(Member.class,memberId);

        MemberInfo memberInfo = member.getMemberInfo();

//        UpdateInfo updateInfo = new UpdateInfo();

        if (StringUtils.isNotEmpty(updateInfo.getNickname())){
            memberInfo.setNickname(updateInfo.getNickname());
        }
        if (StringUtils.isNotEmpty(updateInfo.getIntroduction())){
            memberInfo.setIntroduction(updateInfo.getIntroduction());
        }
        if (StringUtils.isNotEmpty(updateInfo.getSchool())){
            memberInfo.setSchool(updateInfo.getSchool());
        }
        if (StringUtils.isNotEmpty(updateInfo.getCity())){
            memberInfo.setCity(updateInfo.getCity());
        }

        if (StringUtils.isNotEmpty(updateInfo.getProvince())){
            memberInfo.setProvince(updateInfo.getProvince());
        }
        if (StringUtils.isNotEmpty(updateInfo.getArea())){
            memberInfo.setArea(updateInfo.getArea());
        }
        if (StringUtils.isNotEmpty(updateInfo.getSex())){
            memberInfo.setGender(updateInfo.getSex());
        }

        try {
            memberInfoDao.saveOrUpdate(memberInfo);
//            memberInfoDao.update(memberInfo);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,Object> response = new HashMap<String,Object>();

        response.put(memberId,memberInfo);

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    @Override
    public RespData register(HttpHeaders headers, RegisterRequest registerRequest) throws Exception {

        RespData rdInviteCode = checkInviteCode(headers,registerRequest.getInviteCode());

        //验证邀请码
        if (!RespCode.SUCCESS.equals(rdInviteCode.getRespCode())){
            return rdInviteCode;
        }

        //验证手机是否存在
        Member member = memberServiceImpl.getMemberByUniqueIdAndChannel(registerRequest.getMobile(), CommonConstant.REGISTER_CHANNEL_APP);

        if (member!=null) {
            return RespCode.getRespData(RespCode.REG_MOBILE_EXISTS,"该手机已注册，请登录");
        }

        //验证手机验证码

        RespData rdVerifyCode = checkVerifyCode(registerRequest.getAppkey(),registerRequest.getMobile(),registerRequest.getZone(),registerRequest.getVerifyCode());

        if (!RespCode.SUCCESS.equals(rdVerifyCode.getRespCode())){
            return rdVerifyCode;
        }

        //保存
        InviteCode code = codeDao.get(InviteCode.class,registerRequest.getInviteCode());

        code.setEtime(new Date());

        code.setStatus("200");

        codeDao.saveOrUpdate(code);

        member = saveMemberApp(headers,registerRequest);

        if (member!=null){

            Map<String, Object> response = new HashMap<String, Object>();

            response.put("memberId",member.getId());

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    private Member saveMemberApp(HttpHeaders headers,RegisterRequest request){

        MemberInfo info = new MemberInfo();

        info.setMobile(request.getMobile());
        info.setCreateChannel(CommonConstant.REGISTER_CHANNEL_APP);
        info.setAppId(request.getMobile());
        info.setCreateTime(new Date());
        info.setIntroduction("");


        memberInfoDao.save(info);

        Member member = new Member();

        member.setPwd(request.getPwd());

        member.setMemberInfo(info);
        member.setPwd(request.getPwd()==null?"":request.getPwd());

        member.setInviteCode(request.getInviteCode());

        memberDao.save(member);

        return member;
    }

    private RespData checkVerifyCode(String appkey, String phone, String zone, String code){

        //验证码校验

        SmsVerifyRequest smsVerifyRequest = new SmsVerifyRequest();

        smsVerifyRequest.setAppkey(appkey);

        smsVerifyRequest.setPhone(phone);

        smsVerifyRequest.setZone(zone);

        smsVerifyRequest.setCode(code);

        String verifyResult = SmsUtil.requestData(SmsUtil.SMS_VERIFY_URL,smsVerifyRequest.getParams());

        if (!"{\"status\":200}".equals(verifyResult)){

            return RespCode.getRespData(RespCode.MOBILE_CODE_ERROR, new HashMap<String, String>());

        }

        return RespCode.getRespData(RespCode.SUCCESS, new HashMap<String, String>());
    }

    private RespData checkInviteCode(HttpHeaders headers,String inviteCode){

        if (StringUtils.isEmpty(inviteCode)){

            return RespCode.getRespData(RespCode.INVATE_CODE_NEED);

        }

        InviteCode code = codeDao.get(InviteCode.class,inviteCode);

        if (code==null){

            return RespCode.getRespData(RespCode.INVATE_CODE_NOT_EXIST);
        }

        if ("100".equals(code.getStatus())){

            return RespCode.getRespData(RespCode.SUCCESS);

        }else if ("0".equals(code.getStatus())){

            return RespCode.getRespData(RespCode.INVATE_CODE_NOT_PUBLISH);

        }else if("200".equals(code.getStatus())){

            return RespCode.getRespData(RespCode.INVATE_CODE_USED);

        }

        return null;
    }

    @Override
    public RespData improveInformation(HttpHeaders headers, MultipartFile file, ImproveInformationRequest improveInformationRequest, HttpServletRequest servletRequest) {

        Map<String,Object> response = new HashMap<String,Object>();

        String memberId = headers.getFirst("memberId");

        Member member = memberDao.get(Member.class,memberId);

        if (member==null){
            RespCode.getRespData(RespCode.MEMBER_NOT_EXIST,response);
        }

        MemberInfo memberInfo = member.getMemberInfo();

        //设置昵称

        memberInfo.setNickname(improveInformationRequest.getNickname());


        //设置性别

        memberInfo.setGender(improveInformationRequest.getSex());

        //设置头像

        String headurl = this.saveHead(headers,file,servletRequest);

        memberInfo.setIcon(headurl);

        //保存

        memberInfoDao.saveOrUpdate(memberInfo);

        response.put("msg","补充信息成功");

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    /**
     * 保存头像
     */
    private String saveHead(HttpHeaders headers, MultipartFile file, HttpServletRequest servletRequest){

        FileUploadRequest fileUploadRequest = new FileUploadRequest();

        fileUploadRequest.setType("image");

        fileUploadRequest.setUsefor("head");

        RespData rd = staticResourceServiceImpl.imgUpload(headers,file,servletRequest,fileUploadRequest);

        StaticResource sr = (StaticResource)rd.getData();

        return sr.getUrlPath();
    }

    @Override
    public RespData login(HttpHeaders headers, LoginRequest loginRequest) {

        //手机或者用户名登录

        String loginId = loginRequest.getLoginId();

        String pwd = loginRequest.getPwd();

        String hql = "from Member m where (m.memberInfo.mobile=? or m.username=? ) and m.pwd=? ";

        Query q = memberDao.createQuery(hql);

        q.setString(0,loginId);

        q.setString(1,loginId);

        q.setString(2,pwd);

        Member member = (Member) q.uniqueResult();

        if (member==null){

           return RespCode.getRespData(RespCode.MEMBER_NOT_EXIST_OR_PWD_ERROR);

        }

        //返回用户id

        Map<String,String> response = new HashMap<String,String>();

        response.put("memberId",member.getId());

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    @Override
    public RespData modifyPwd(HttpHeaders headers, ModifyPwdRequest modifyPwdRequest) {

        String memberId = headers.getFirst("memberId");

        Map<String,String> response = new HashMap<String,String>();
        try {



             if (memberId!=null){
                 Member member = memberDao.get(Member.class,memberId);

                 if (member!=null){

                     if (member.getPwd().equals(modifyPwdRequest.getOldPwd())){

                         if (modifyPwdRequest.getNewPwd().equals(modifyPwdRequest.getComfirmPwd())){

                             member.setPwd(modifyPwdRequest.getNewPwd());

                             memberDao.saveOrUpdate(member);

                             return RespCode.getRespData(RespCode.SUCCESS,response);
                         }

                     }else {

                     }

                 }else {
                     return  RespCode.getRespData(RespCode.COMMENT_NOT_CURRENT_MEMBER,response);
                 }
             }else {
                 return  RespCode.getRespData(RespCode.HEADER_MEMBERID_NEEDED,response);
             }

         }catch (Exception e){
             e.printStackTrace();
         }

        return RespCode.getRespData(RespCode.HEADER_MEMBERID_NEEDED,response);
    }

    @Override
    public RespData findPwdByVerify(HttpHeaders headers, FindPwdByVerifyRequest findPwdByVerifyRequest) {

        String mobile = findPwdByVerifyRequest.getMobile();

        //验证手机验证码

        RespData rdVerifyCode = checkVerifyCode(findPwdByVerifyRequest.getAppkey(),findPwdByVerifyRequest.getMobile(),findPwdByVerifyRequest.getZone(),findPwdByVerifyRequest.getVerifyCode());

        if (!RespCode.SUCCESS.equals(rdVerifyCode.getRespCode())){
            return rdVerifyCode;
        }

        //验证手机是否存在
        Member member = memberServiceImpl.getMemberByUniqueIdAndChannel(mobile, CommonConstant.REGISTER_CHANNEL_APP);

        if (member==null){
            return RespCode.getRespData(RespCode.MEMBER_NOT_EXIST);
        }

        ResetPwdLog rpl = new ResetPwdLog();

        rpl.setMobile(mobile);

        rpl.setStatus("100");

        resetPwdLogDao.save(rpl);

        Map<String,String> response = new HashMap<String,String>();

        response.put("apply_id",rpl.getId());

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    @Override
    public RespData resetPwd(HttpHeaders headers, ResetPwdRequest resetPwdRequest) {

        String hql = "from ResetPwdLog where id=? and mobile=? and status='100' ";

        Query q = resetPwdLogDao.createQuery(hql);

        q.setString(0,resetPwdRequest.getApply_id());

        q.setString(1,resetPwdRequest.getMobile());

        ResetPwdLog rpl = (ResetPwdLog) q.uniqueResult();

        if (rpl!=null);

        Member member = memberServiceImpl.getMemberByUniqueIdAndChannel(rpl.getMobile(), CommonConstant.REGISTER_CHANNEL_APP);

        member.setPwd(resetPwdRequest.getPwd());

        memberDao.saveOrUpdate(member);

        Map<String,String> response = new HashMap<String,String>();

        response.put("msg","密码重置成功,请登录");

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }


}
