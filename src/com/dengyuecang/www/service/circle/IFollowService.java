package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.follow.FollowRequest;
import com.dengyuecang.www.entity.MemberFollow;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by Administrator on 2017/1/10.
 */
public interface IFollowService  extends IBaseService<MemberFollow>{

    public RespData onFollow(HttpHeaders headers, String follow_id);

    public RespData cancleFollow(HttpHeaders headers,String follow_id);

    public RespData followList(HttpHeaders headers, FollowRequest followRequest);

    public RespData fansList(HttpHeaders headers,FollowRequest followRequest);

    public String ifFollow(String memberId, String followedId);

    public String followCount(String memberId);

    public String fansCount(String memberId);

}
