package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.*;
import com.dengyuecang.www.service.circle.IInterestBarService;
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
public class InterestBarServiceImpl extends BaseService<InterestBar> implements IInterestBarService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestBar> interestBarDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<InterestType> interestTypeDao;

    @Override
    public BaseDao<InterestBar> getSuperDao() {
        return null;
    }


    @Override
    public RespData addInterestBar(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest) {

        String memberId = headers.getFirst("memberId");

        InterestBar interestBar = new InterestBar();

        interestBar.setName(addInterestBarRequest.getName());

        interestBar.setDetail(addInterestBarRequest.getDetail());

        String types = addInterestBarRequest.getTypes();

        String[] typeArray = types.split(",");

        String hql = "from InterestType where name=? ";

        Set<InterestType> typeSet = new HashSet<InterestType>();

        for (String type :
                typeArray) {
            Query q = interestTypeDao.createQuery(hql);

            q.setString(0,type);

            InterestType interestType = (InterestType) q.uniqueResult();

            if (interestType==null){

                interestType = new InterestType();
                interestType.setName(type);
                interestType.setCreater(memberId);
                interestType.setParent_id("");

                interestTypeDao.save(interestType);
            }

            typeSet.add(interestType);

//            interestBar.getTypes().add(interestType);

        }

        interestBar.setTypes(typeSet);

        interestBar.setCreater(memberId);

        interestBar.setCtime(new Date());

        interestBar.setTimestamp(System.currentTimeMillis());

        interestBarDao.save(interestBar);

        Map<String,Object> response = new HashMap<String,Object>();

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    @Override
    public RespData queryList(HttpHeaders headers) {

        String hql = "from InterestBar";

//        String memberId = headers.getFirst("memberId");

        Query q = interestBarDao.createQuery(hql);

        List<InterestBar> interestBars = q.list();

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestBars",interestBars);


        return RespCode.getRespData(RespCode.SUCCESS,response);




    }

    @Override
    public RespData update(HttpHeaders headers, AddInterestBarRequest addInterestBarRequest) {

        String memberId = headers.getFirst("memberId");

        InterestBar interestBar = new InterestBar();

        String bar_id = addInterestBarRequest.getBar_id();

        if (bar_id!=null){
            interestBar = interestBarDao.get(InterestBar.class,bar_id);
        }

        interestBar.setName(addInterestBarRequest.getName());

        interestBar.setDetail(addInterestBarRequest.getDetail());

        String types = addInterestBarRequest.getTypes();

        String[] typeArray = types.split(",");

        String hql = "from InterestType where name=? ";

        Set<InterestType> typeSet = new HashSet<InterestType>();

        for (String type :
                typeArray) {
            Query q = interestTypeDao.createQuery(hql);

            q.setString(0,type);

            InterestType interestType = (InterestType) q.uniqueResult();

            if (interestType==null){

                interestType = new InterestType();
                interestType.setName(type);
                interestType.setCreater(memberId);
                interestType.setParent_id("");

                interestTypeDao.save(interestType);
            }

            typeSet.add(interestType);

        }

        interestBar.setTypes(typeSet);

        interestBar.setCreater(memberId);

        interestBar.setCtime(new Date());

        interestBar.setTimestamp(System.currentTimeMillis());

        interestBarDao.save(interestBar);

        Map<String,Object> response = new HashMap<String,Object>();

        return RespCode.getRespData(RespCode.SUCCESS,response);
    }

}
