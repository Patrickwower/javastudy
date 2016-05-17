package com.dengyuecang.api.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dengyuecang.api.entity.log.QrUrlLog;
import com.dengyuecang.api.service.IQrUrlLogService;
import com.dengyuecang.api.utils.qr.util.ZXingPic;
import com.google.zxing.BarcodeFormat;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;

@Service
public class QrUrlLogServiceImpl extends BaseService<QrUrlLog> implements IQrUrlLogService{

	Logger log= LoggerFactory.getLogger(QrUrlLogServiceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<QrUrlLog> qrDao;
	

	@Override
	public void addLog(String memberId) {
		// TODO Auto-generated method stub
		QrUrlLog qul = new QrUrlLog();
		
		qul.setCtime(new Date());
		
		qul.setMemberId(memberId);
		
		qrDao.save(qul);
		
	}

	@Override
	public BaseDao<QrUrlLog> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
