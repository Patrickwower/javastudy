package com.dengyuecang.api.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dengyuecang.api.entity.LxTest;
import com.dengyuecang.api.service.ITestService;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.BaseService;

@Service
public class TestServiceImpl extends BaseService<LxTest> implements ITestService {

	@Resource(name="hibernateBaseDao")
	private BaseDao<LxTest> lxDao;

	@Override
	public String testHello() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LxTest> queryAllByPage(PageModel<LxTest> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDao<LxTest> getSuperDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
