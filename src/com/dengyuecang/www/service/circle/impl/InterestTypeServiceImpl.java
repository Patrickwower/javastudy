package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.InterestBar;
import com.dengyuecang.www.entity.circle.InterestType;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.service.circle.IInterestBarService;
import com.dengyuecang.www.service.circle.IInterestTypeService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class InterestTypeServiceImpl extends BaseService<InterestType> implements IInterestTypeService{

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestType> interestTypeDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestBar> interestBarDao;



    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Override
    public BaseDao<InterestType> getSuperDao() {
        return null;
    }

    @Override
    public RespData queryList() {

        String hql = "from InterestType";

        Query q = interestTypeDao.createQuery(hql);

        List<InterestType> interestTypes = q.list();

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestTypes",interestTypes);


        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    @Override
    public List<String> queryInterestTagsByMemberId(String memberId) {

        String hql = "from InterestBar ib where ib.creater.id=? ";

        Query q = interestBarDao.createQuery(hql);

        q.setString(0,memberId);

        List<InterestBar> bars = q.list();

        List<String> tags = new ArrayList<String>();

        for (InterestBar bar :
                bars) {
            for (InterestType type :
                    bar.getTypes()) {
                if (!tags.contains(type.getName())){
                    tags.add(type.getName());
                }
            }
        }

        return tags;
    }
}
