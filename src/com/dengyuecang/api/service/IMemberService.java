package com.dengyuecang.api.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.dengyuecang.api.entity.Member;
import com.dengyuecang.api.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;

public interface IMemberService extends IBaseService<Member>{

	public List<Member> queryAll();
	
	public RespData registerAndLogin(String json,HttpHeaders headers);
	
	public Member register(String json,HttpHeaders headers);
	
	public RespData login(String json,HttpHeaders headers);
	
	public RespData getMemberByUserid(String memberId);
	
}
