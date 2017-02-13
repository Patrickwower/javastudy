package com.dengyuecang.www.service.circle;

import com.dengyuecang.www.controller.api.circle.model.report.ReportAddRequest;
import com.dengyuecang.www.entity.circle.Report;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

/**
 * Created by acang on 2017/2/10.
 */
public interface IReportService extends IBaseService<Report> {

    public RespData add(HttpHeaders headers, ReportAddRequest reportAddRequest);

}
