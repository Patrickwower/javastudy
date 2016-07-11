package com.dengyuecang.www.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.dengyuecang.www.entity.Member;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;

public interface IMemberService extends IBaseService<Member>{

	public List<Member> queryAll();
	
	public RespData registerAndLogin(String json,HttpHeaders headers);
	
	public Member saveMember(String json,HttpHeaders headers);
	
	public RespData login(String json,HttpHeaders headers);
	
	public Member getMemberByUserid(String memberId);
	
	public RespData allInfo(HttpHeaders headers);
	
	public RespData setAllInfo(HttpHeaders headers,String json);
	
	public RespData getBasicInfo();
	
	public RespData bindMobile(HttpHeaders headers, String json);

	public RespData updateHead(String imgId,HttpHeaders headers);
	
	public RespData updateNickname(HttpHeaders headers,String nickname);

}
