package com.dengyuecang.www.service.sys.impl;

import com.dengyuecang.www.controller.api.sys.model.UserLoginRequest;
import com.dengyuecang.www.entity.*;
import com.dengyuecang.www.entity.sys.User;
import com.dengyuecang.www.service.sys.ISysService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import org.hibernate.Query;
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

	@Resource(name = "hibernateBaseDao")
	private BaseDao<User> userDao;


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

	@Override
	public RespData login(HttpHeaders headers, UserLoginRequest loginRequest) {

		String hql = "from User u where u.username=? and u.pwd=? ";

		Query q = userDao.createQuery(hql);

		q.setString(0,loginRequest.getUsername());

		q.setString(1,loginRequest.getPwd());

		User user  = (User) q.uniqueResult();

		Map<String,String> response = new HashMap<String,String>();

		response.put("userid",user.getId());

		if (user!=null){
			return RespCode.getRespData(RespCode.SUCESS,response);
		}

		return RespCode.getRespData(RespCode.USERNAME_OR_PWD_WRONG,response);

	}
}
