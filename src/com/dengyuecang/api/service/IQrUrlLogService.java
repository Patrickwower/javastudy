package com.dengyuecang.api.service;

import com.dengyuecang.api.entity.log.QrUrlLog;
import com.longinf.lxcommon.service.IBaseService;

public interface IQrUrlLogService extends IBaseService<QrUrlLog>{

	public void addLog(String memberId);
	
}
