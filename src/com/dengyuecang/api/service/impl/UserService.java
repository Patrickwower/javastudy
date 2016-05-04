package com.dengyuecang.api.service.impl;

import org.springframework.stereotype.Service;

import com.dengyuecang.api.entity.LxUser;
import com.dengyuecang.api.service.IUserService;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;

@Service
public class UserService extends BaseService<LxUser>implements IUserService {

	@Override
	public BaseDao<LxUser> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
