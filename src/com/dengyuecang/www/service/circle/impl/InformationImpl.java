package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.ImproveInformationRequest;
import com.dengyuecang.www.controller.api.circle.model.LoginRequest;
import com.dengyuecang.www.controller.api.circle.model.RegisterRequest;
import com.dengyuecang.www.entity.InviteCode;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.entity.Weixin;
import com.dengyuecang.www.entity.circle.InterestType;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.service.IMemberService;
import com.dengyuecang.www.service.circle.InformationService;
import com.dengyuecang.www.service.common.CommonConstant;
import com.dengyuecang.www.service.members.model.CommunityMemberResponse;
import com.dengyuecang.www.service.members.model.RelatedAccount;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxrent on 2016/12/13.
 */
@Service
public class InformationImpl extends BaseService<MemberInfo> implements InformationService {

    @Override
    public BaseDao<MemberInfo> getSuperDao() {
        return null;
    }

    @Override
    public RespData personInformation(HttpHeaders headers) {
        return null;
    }

    @Override
    public RespData memberInfo(HttpHeaders headers) {
        return null;
    }

    @Resource(name = "hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<InviteCode> codeDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<MemberInfo> memberInfoDao;

    @Resource
    private IMemberService memberServiceImpl;


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

        cMemberResponse.setIntroduction(member.getMemberInfo().getIntroduction());

        cMemberResponse.setSex(member.getMemberInfo().getGender()==null?"男":member.getMemberInfo().getGender());

        cMemberResponse.setCity(member.getMemberInfo().getCity()==null?"":member.getMemberInfo().getCity());

        cMemberResponse.setSchool(member.getMemberInfo().getSchool()==null?"":member.getMemberInfo().getSchool());

//		cMemberResponse.setTimestamp(member.getMemberIn);

        if (member.getMemberInfo().getEnrollmentDate()!=null){

            Format f = new SimpleDateFormat("yyyy");

            cMemberResponse.setEnrollment(f.format(member.getMemberInfo().getEnrollmentDate()));

        }

        if (member.getWeixin()!=null){

            Weixin weixin = member.getWeixin();

            RelatedAccount ra = new RelatedAccount();

            ra.setAccountId(weixin.getId());

            ra.setAccountNickname(weixin.getNickname());

            ra.setPlatform("weixin");

            cMemberResponse.getAccounts().add(ra);
        }

        if (StringUtils.isNotEmpty(member.getMemberInfo().getMobile())){

            RelatedAccount ra = new RelatedAccount();

            ra.setAccountId(member.getMemberInfo().getId());

            ra.setAccountNickname(member.getMemberInfo().getMobile());

            ra.setPlatform("mobile");

            cMemberResponse.getAccounts().add(ra);

        }

        Map<String, Object> response = new HashMap<String, Object>();

        response.put("memberinfo",cMemberResponse);

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestType> interestTypeDao;

    @Override
    public RespData interestType() {

        String hql = "from InterestType";

        Query q = interestTypeDao.createQuery(hql);

        List<InterestType> interestTypes = q.list();

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestTypes",interestTypes);

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
    public RespData improveInformation(HttpHeaders headers, MultipartFile file, ImproveInformationRequest improveInformationRequest) {

        //设置昵称

        //设置性别

        //设置头像

        //保存

        return null;
    }

    @Override
    public RespData login(HttpHeaders headers, LoginRequest loginRequest) {

        //手机或者用户名登录

        //返回用户id

        return null;
    }


}
