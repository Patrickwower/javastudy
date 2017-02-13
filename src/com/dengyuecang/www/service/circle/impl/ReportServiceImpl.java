package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.comment.CommentRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyAddRequest;
import com.dengyuecang.www.controller.api.circle.model.comment.reply.ReplyDeleteRequest;
import com.dengyuecang.www.controller.api.circle.model.report.ReportAddRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.circle.Moment;
import com.dengyuecang.www.entity.circle.MomentComment;
import com.dengyuecang.www.entity.circle.MomentCommentReply;
import com.dengyuecang.www.entity.circle.Report;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.IReplyService;
import com.dengyuecang.www.service.circle.IReportService;
import com.dengyuecang.www.service.circle.common.MessageCommonConstant;
import com.dengyuecang.www.service.circle.model.message.MessageAdd;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class ReportServiceImpl extends BaseService<Report> implements IReportService{


    @Resource(name="hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Moment> momentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentComment> momentCommentDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<MomentCommentReply> replyDao;

    @Resource(name="hibernateBaseDao")
    private BaseDao<Report> reportDao;

    @Resource
    private IMessageService messageServiceImpl;


    @Override
    public BaseDao<Report> getSuperDao() {
        return null;
    }


    @Override
    public RespData add(HttpHeaders headers, ReportAddRequest reportAddRequest) {

        try {
            String memberId = headers.getFirst("memberId");

            Member member = memberDao.get(Member.class,memberId);

            String momentId = reportAddRequest.getMomentId();

            String content = reportAddRequest.getContent();

            Report report = new Report();

            report.setTimestamp(System.currentTimeMillis());

            report.setCtime(new Date());

            report.setContent(content);



            if (member!=null){
                report.setMember(member);
            }

            if (StringUtils.isNotEmpty(momentId)){

                Moment moment = momentDao.get(Moment.class,momentId);

                if (moment!=null){
                    report.setMoment(moment);
                }
            }


            reportDao.save(report);

            return RespCode.getRespData(RespCode.SUCCESS,new HashMap<String,String>());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }



}
