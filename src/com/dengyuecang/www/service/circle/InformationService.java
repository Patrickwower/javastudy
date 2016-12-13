package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by lxrent on 2016/12/13.
 */
public interface InformationService extends IBaseService<MemberInfo> {

    public RespData personInformation(HttpHeaders headers);

    public RespData memberInfo(HttpHeaders headers);

    public RespData information(HttpHeaders headers, String memberId);


}
