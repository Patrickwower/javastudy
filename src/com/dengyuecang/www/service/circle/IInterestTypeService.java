package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.ImproveInformationRequest;
import com.dengyuecang.www.controller.api.circle.model.LoginRequest;
import com.dengyuecang.www.controller.api.circle.model.RegisterRequest;
import com.dengyuecang.www.entity.MemberInfo;
import com.dengyuecang.www.entity.circle.InterestType;
import com.dengyuecang.www.service.circle.model.UpdateInfo;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lxrent on 2016/12/13.
 */
public interface IInterestTypeService extends IBaseService<InterestType> {

    public RespData queryList();

    public List<String> queryInterestTagsByMemberId(String memberId);

}
