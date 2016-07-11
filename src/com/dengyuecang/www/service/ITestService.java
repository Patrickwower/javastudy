package com.dengyuecang.www.service;

import java.util.List;

import com.dengyuecang.www.entity.LxTest;
import com.longinf.lxcommon.dao.params.PageModel;
import com.longinf.lxcommon.service.IBaseService;

public interface ITestService extends IBaseService<LxTest> {

	public String testHello();
	
	public List<LxTest> queryAllByPage(PageModel<LxTest> page);
}
