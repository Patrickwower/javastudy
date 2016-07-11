package com.dengyuecang.www.service;

import com.dengyuecang.www.entity.log.QrUrlLog;
import com.longinf.lxcommon.service.IBaseService;

public interface IQrUrlLogService extends IBaseService<QrUrlLog>{

	public void addLog(String memberId);
	
}
