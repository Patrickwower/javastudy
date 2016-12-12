package com.dengyuecang.www.service.feedback.impl;


import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.feedback.Feedback;
import com.dengyuecang.www.service.feedback.FeedbackService;
import com.dengyuecang.www.service.feedback.model.FeedbackRequest;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxrent on 2016/12/8.
 */
@Service
public class FeedbackServiceImpl extends BaseService<Feedback> implements FeedbackService {


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Feedback> feedbackDao;

    @Override
    public RespData feedback(String id, String content, String ctime) {

        Map<String,String> response = new HashMap<String,String>();

        if(content == ""){
            return RespCode.getRespData(RespCode.ERROR,response);
        }else {
            return RespCode.getRespData(RespCode.SUCESS,response);
        }

    }

    @Override
    public RespData save(HttpHeaders headers, FeedbackRequest feedbackRequest) {

        String memberId = headers.getFirst("memberId");

        Member member = memberDao.get(Member.class,memberId);

        Feedback feedback = new Feedback();

        feedback.setContent(feedbackRequest.getContent());

        feedback.setCtime(new Date());

        feedback.setMember(member);

        try {

            feedbackDao.save(feedback);
        }catch (Exception e){

            e.printStackTrace();
        }


        Map<String,String> response = new HashMap<String,String>();

        return RespCode.getRespData(RespCode.SUCESS,response);

    }

    @Override
    public BaseDao<Feedback> getSuperDao() {
        return null;
    }
}
