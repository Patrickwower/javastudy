package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.entity.Weixin;
import com.dengyuecang.www.service.circle.InformationService;
import com.dengyuecang.www.service.members.model.CommunityMemberResponse;
import com.dengyuecang.www.service.members.model.RelatedAccount;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

}
