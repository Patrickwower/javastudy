package com.dengyuecang.www.controller.api.wxlogin;

import com.dengyuecang.www.controller.api.wxlogin.model.WXLogin;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lxrent on 2016/11/24.
 */

@RestController
@RequestMapping("/wxlogin")

public class WXLoginController {

    @RequestMapping("/userinfo")
    @ResponseBody
    public RespData userinfo(@RequestHeader HttpHeaders headers, WXLogin wxLogin){

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }


}
