package com.dengyuecang.www.service.sys.impl;

import com.dengyuecang.www.entity.*;
import com.dengyuecang.www.service.sys.ISysService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl extends BaseService<StaticProvince> implements ISysService{

	Logger log = LoggerFactory.getLogger(SysServiceImpl.class);
	
	@Resource(name = "hibernateBaseDao")
	private BaseDao<StaticProvince> provinceDao;


	@Override
	public BaseDao<StaticProvince> getSuperDao() {
		return null;
	}

	@Override
	public RespData getAllProvince(HttpHeaders headers) {

		Map<String,Object> response = new HashMap<String,Object>();

		try {

			List<StaticProvince> provinces =  provinceDao.createQuery("select a from StaticProvince a ").list();

			response.put("provinces",provinces);

			return RespCode.getRespData(RespCode.SUCESS,response);

		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION,response);

	}
}
