package com.dengyuecang.www.service.circle.impl;

import com.dengyuecang.www.controller.api.circle.model.follow.FollowRequest;
import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.entity.MemberFollow;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.service.circle.IFollowService;
import com.dengyuecang.www.service.circle.IInterestTypeService;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.common.MessageCommonConstant;
import com.dengyuecang.www.service.circle.model.FollowListInfo;
import com.dengyuecang.www.service.circle.model.message.MessageAdd;
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
 * Created by Administrator on 2017/1/10.
 */
@Service
public class FollowServiceImpl extends BaseService<MemberFollow> implements IFollowService {

    @Resource(name = "hibernateBaseDao")
    private BaseDao<Member> memberDao;

    @Resource(name = "hibernateBaseDao")
    private BaseDao<MemberFollow> followBaseDao;

    @Resource
    private IInterestTypeService interestTypeServiceImpl;

    @Resource
    private IMessageService messageServiceImpl;

    @Override
    public RespData onFollow(HttpHeaders headers, String followed_id) {

        try {

            String hql = "from MemberFollow f where f.followed.id=? and f.follow.id=?";
            Query fd = followBaseDao.createQuery(hql);

            String follow_id = headers.getFirst("memberId");
            fd.setString(0,followed_id);
            fd.setString(1,follow_id);
            int size = fd.list().size();
            Member follow = memberDao.get(Member.class,follow_id);
            Member followed = memberDao.get(Member.class,followed_id);
            MemberFollow memberFollow = new MemberFollow();
            memberFollow.setFollow(follow);
            memberFollow.setFollowed(followed);
            memberFollow.setTimestamp(System.currentTimeMillis());
            memberFollow.setCtime(new Date());

            if (size == 0){

                followBaseDao.save(memberFollow);

                //站内消息
                MessageAdd messageAdd = new MessageAdd();

                messageAdd.setType(MessageCommonConstant.TYPE_FOLLOW);
                messageAdd.setMomentId(null);
                messageAdd.setServiceId(follow_id);
                messageAdd.setSenderId(follow_id);
                messageAdd.setRecipientId(followed_id);

                messageServiceImpl.add(headers,messageAdd);

            }
            Map<String,Object> response = new HashMap<String,Object>();
            response.put("msg","关注成功!");
            response.put("fansCount",this.fansCount(followed_id));
            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());

    }

    @Override
    public RespData cancleFollow(HttpHeaders headers, String followed_id) {

        try {

            String follow_id = headers.getFirst("memberId");
            String hql = "from MemberFollow f where f.followed.id=? and f.follow.id=?";
            Query cfd = followBaseDao.createQuery(hql);
            cfd.setString(0,followed_id);
            cfd.setString(1,follow_id);

            MemberFollow follow    =  (MemberFollow) cfd.uniqueResult();

            if (follow != null){
                followBaseDao.delete(follow);
                Map<String,Object> response = new HashMap<String,Object>();
                response.put("msg","取关成功!");
                response.put("fansCount",this.fansCount(followed_id));
                return RespCode.getRespData(RespCode.SUCCESS,response);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData followList(HttpHeaders headers, FollowRequest followRequest) {

        try {

            String memberId = headers.getFirst("memberId");

            String follow_id = memberId;

            if (StringUtils.isNotEmpty(followRequest.getFollow_id())){
                follow_id = followRequest.getFollow_id();
            }

            int limit = 10;

            if (StringUtils.isNotEmpty(followRequest.getPageSize())){
                limit = Integer.valueOf(followRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(followRequest.getTimestamp())){
                timestamp = Long.valueOf(followRequest.getTimestamp());
            }

            String hql = "from MemberFollow f where f.follow.id=? and timestamp<"+timestamp;

            hql += " order by timestamp desc";

            Query fl = followBaseDao.createQuery(hql);
            fl.setString(0,follow_id);

            fl.setFirstResult(0);

            fl.setMaxResults(limit);

            List<MemberFollow> mfs =  fl.list();
            List<FollowListInfo> newarray = new ArrayList<FollowListInfo>();

            for (MemberFollow memberfollow:
                    mfs) {

                FollowListInfo fli = new FollowListInfo();

                fli.setFollowId(memberfollow.getFollow().getId());

                fli.setFollowedId(memberfollow.getFollowed().getId());

                fli.setImgurl(memberfollow.getFollowed().getMemberInfo().getIcon());

                fli.setNickname(memberfollow.getFollowed().getMemberInfo().getNickname());

                Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                fli.setCtime(f.format(memberfollow.getCtime()));

                fli.setTimestamp(memberfollow.getTimestamp()+"");

                List<String> interestTypes = interestTypeServiceImpl.queryInterestTagsByMemberId(memberfollow.getFollowed().getId());

                fli.setInterestname(interestTypes);

                //当前用户和此人关注的人的关注关系
                fli.setStatus(ifFollow(memberId,memberfollow.getFollowed().getId()));

                newarray.add(fli);

            }

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("followList",newarray);

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }

    @Override
    public RespData fansList(HttpHeaders headers,FollowRequest followRequest) {
        try {

            String memberId = headers.getFirst("memberId");

            String followed_id = memberId;

            if (StringUtils.isNotEmpty(followRequest.getFollowed_id())){
                followed_id = followRequest.getFollowed_id();
            }


            int limit = 10;

            if (StringUtils.isNotEmpty(followRequest.getPageSize())){
                limit = Integer.valueOf(followRequest.getPageSize());
            }

            long timestamp = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(followRequest.getTimestamp())){
                timestamp = Long.valueOf(followRequest.getTimestamp());
            }

            String hql = "from MemberFollow f where f.followed.id=? and timestamp<"+timestamp;

            hql += " order by timestamp desc";

            Query fl = followBaseDao.createQuery(hql);
            fl.setString(0,followed_id);

            fl.setFirstResult(0);

            fl.setMaxResults(limit);

            List<MemberFollow> mfs =  fl.list();
            List<FollowListInfo> newarray = new ArrayList<FollowListInfo>();

            for (MemberFollow memberfollow:
                    mfs) {

                FollowListInfo fli = new FollowListInfo();

                fli.setFollowId(memberfollow.getFollow().getId());

                fli.setFollowedId(memberfollow.getFollowed().getId());

                fli.setImgurl(memberfollow.getFollow().getMemberInfo().getIcon());

                fli.setNickname(memberfollow.getFollow().getMemberInfo().getNickname());

                Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                fli.setCtime(f.format(memberfollow.getCtime()));

                fli.setTimestamp(memberfollow.getTimestamp()+"");

                List<String> interestTypes = interestTypeServiceImpl.queryInterestTagsByMemberId(memberfollow.getFollow().getId());

                fli.setInterestname(interestTypes);

                //当前用户和此人粉丝的关注关系
                fli.setStatus(ifFollow(memberId,memberfollow.getFollow().getId()));

                newarray.add(fli);

            }

            Map<String,Object> response = new HashMap<String,Object>();

            response.put("fanslist",newarray);

            return RespCode.getRespData(RespCode.SUCCESS,response);

        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,new HashMap<String,String>());
    }


    public String ifFollow(String memberId,String followedId){

        String hql = "from MemberFollow f where f.followed.id=? and f.follow.id=?";

        Query q = followBaseDao.createQuery(hql);

        q.setString(0,followedId);

        q.setString(1,memberId);

        List l = q.list();

        if (l.size()>0){

            return "1";
        }

        return "0";
    }

    @Override
    public String followCount(String memberId) {

        return this.count(memberId,"follow");

    }

    @Override
    public String fansCount(String memberId) {

        return this.count(memberId,"fans");

    }


    private String count(String memberId,String type){

        String follow = "follow";
        //type 默认是follow
        if ("fans".equals(type)){
            follow = "followed";
        }

        String hql = "from MemberFollow f where f."+follow+".id=?";

        Query q = followBaseDao.createQuery(hql);

        q.setString(0,memberId);

        List l = q.list();

        return l.size()+"";

    }

    @Override
    public BaseDao<MemberFollow> getSuperDao() {
        return null;
    }
}
