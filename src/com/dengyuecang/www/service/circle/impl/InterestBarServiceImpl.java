package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.AddInterestBarRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentEvaluateRequest;
import com.dengyuecang.www.controller.api.circle.model.MomentRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.*;
import com.dengyuecang.www.service.circle.IInterestBarService;
import com.dengyuecang.www.service.circle.IMomentService;
import com.dengyuecang.www.service.circle.model.MomentCreater;
import com.dengyuecang.www.service.circle.model.MomentImg;
import com.dengyuecang.www.service.circle.model.MomentInterest;
import com.dengyuecang.www.service.circle.model.MomentResponse;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
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

        InterestBar interestBar = new InterestBar();

        interestBar.setName(addInterestBarRequest.getName());

        interestBar.setDetail(addInterestBarRequest.getDetail());

        String types = addInterestBarRequest.getTypes();

        String[] typeArray = types.split(",");

        for (String type :
                typeArray) {



        }

//        interestBar.setTypes();

        return null;
    }








}
