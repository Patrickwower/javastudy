package com.dengyuecang.www.interceptors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dengyuecang.www.service.members.IMembersService;
import com.dengyuecang.www.utils.HeaderUtils;
import com.dengyuecang.www.utils.JsonUtils;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;

public class APIInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(APIInterceptor.class);
	
	@Resource
	private IMembersService membersServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String target = request.getHeader("target");
		
		if ("API".equals(target)) {
			
			RespData rd = HeaderUtils.check(request, true);
			
			if (RespCode.SUCESS.equals(rd.getRespCode())) {
				
				rd = membersServiceImpl.checkToken(request);
				
				if (RespCode.SUCESS.equals(rd.getRespCode())) {
					return true;					
				}
				
			}
			
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");  
		    PrintWriter out = null;  
		    try {  
		        out = response.getWriter();  
		        out.append(JsonUtils.toJSONString(JsonUtils.toJSONObject(rd)));
		        logger.debug("返回是\n");  
		        logger.debug(JsonUtils.toJSONString(JsonUtils.toJSONObject(rd)));
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    } finally {  
		        if (out != null) {  
		            out.close();  
		        }  
		    }  
			
			
			
			return false;
		}else {
			return super.preHandle(request, response, handler);
		}
	}

	
}
