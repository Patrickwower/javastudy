package com.dengyuecang.www.service.startlog.impl;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.feedback.Feedback;
import com.dengyuecang.www.entity.startlog.StartLog;
import com.dengyuecang.www.service.startlog.StartLogService;
import com.dengyuecang.www.service.startlog.model.StartLogRequest;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.ldap.StartTlsRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxrent on 2016/12/12.
 */
@Service
public class StartLogServiceImpl extends BaseService<StartLog> implements StartLogService {

    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<StartLog> startLogBaseDao ;

    @Override
    public RespData savenumber(HttpHeaders headers, String num) {

        Map<String,String> response = new HashMap<String,String>();

        if(num == ""){
            return RespCode.getRespData(RespCode.ERROR,response);
        }else {
            return RespCode.getRespData(RespCode.SUCCESS,response);
        }


    }
    @Override
    public RespData save(HttpHeaders headers) {

        String memberId = headers.getFirst("memberId");

        Member member = memberDao.get(Member.class,memberId);

        StartLog startlog = new StartLog();

        startlog.setLogtime(new Date());

        startlog.setMember(member);

        try {

            startLogBaseDao.save(startlog);
        }catch (Exception e){

            e.printStackTrace();
        }

        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCCESS,response);

    }


    @Override
    public BaseDao<StartLog> getSuperDao() {
        return null;
    }

}
