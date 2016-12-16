package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.entity.Weixin;
import com.dengyuecang.www.entity.circle.InterestBar;
import com.dengyuecang.www.entity.circle.InterestType;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.service.circle.InformationService;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.UpdateInfo;
import com.dengyuecang.www.service.members.model.CommunityMemberResponse;
import com.dengyuecang.www.service.members.model.RelatedAccount;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private BaseDao<MemberInfo> memberInfoDao;

    @Resource(name = "hibernateBaseDao")
    private  BaseDao<InterestBar> interestBarBaseDao;

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
    public RespData interestType() {

        String hql = "from InterestType";

        Query q = interestTypeDao.createQuery(hql);

        List<InterestType> interestTypes = q.list();

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestTypes",interestTypes);

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    @Override
    public RespData updateinfo(HttpHeaders headers, UpdateInfo updateInfo) {

        String memberId = headers.getFirst("memberId");

        Member member =  memberDao.get(Member.class,memberId);

        MemberInfo memberInfo = member.getMemberInfo();

//        UpdateInfo updateInfo = new UpdateInfo();

        if (updateInfo.getNickname() != null){
            memberInfo.setNickname(updateInfo.getNickname());
        }
        if (updateInfo.getIntroduction() != null){
            memberInfo.setIntroduction(updateInfo.getIntroduction());
        }
        if (updateInfo.getSchool() != null){
            memberInfo.setSchool(updateInfo.getSchool());
        }
        if (updateInfo.getCity() != null){
            memberInfo.setCity(updateInfo.getCity());
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


}
