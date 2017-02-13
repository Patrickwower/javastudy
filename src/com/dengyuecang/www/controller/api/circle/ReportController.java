package com.dengyuecang.www.controller.api.circle;

import com.dengyuecang.www.controller.api.circle.model.message.MessageRequest;
import com.dengyuecang.www.controller.api.circle.model.report.ReportAddRequest;
import com.dengyuecang.www.service.circle.IMessageService;
import com.dengyuecang.www.service.circle.IReportService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by acang on 17/2/13.
 */

@RestController
@RequestMapping("/api/circle/report")
public class ReportController {

    @Resource
    private IReportService reportServiceImpl;

    @RequestMapping("/add")
    @ResponseBody
    public RespData add(@RequestHeader HttpHeaders headers, ReportAddRequest reportAddRequest) {

        try {
            return reportServiceImpl.add(headers, reportAddRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }



}
