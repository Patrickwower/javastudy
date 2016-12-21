package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.*;
import com.dengyuecang.www.service.circle.IInterestBarService;
import com.dengyuecang.www.service.circle.common.InterestBarCommonConstant;
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

        interestBar.setImg_url(prepareInterestBarCover(memberId));

        interestBarDao.save(interestBar);

        Map<String,Object> response = new HashMap<String,Object>();

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    private String prepareInterestBarCover(String memberId){

        List<InterestBar> barList = queryList(memberId);

        List<String> imgs = InterestBarCommonConstant.INTEREST_BAR_COVER;

        for (InterestBar interestBar:barList
             ) {
//            if (imgs.contains(interestBar.getImg_url())){
                imgs.remove(interestBar.getImg_url());
//            }
        }

        Random r = new Random();

        return imgs.get(r.nextInt(imgs.size()));
    }

    @Override
    public RespData queryList(HttpHeaders headers) {

        String memberId = headers.getFirst("memberId");

        Map<String,Object> response = new HashMap<String,Object>();

        response.put("interestBars",this.queryList(memberId));

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }

    private List<InterestBar> queryList(String memberId){

        String hql = "from InterestBar ib where ib.creater=? ";

        Query q = interestBarDao.createQuery(hql);

        q.setString(0,memberId);

        List<InterestBar> interestBars = q.list();

        return interestBars;
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
