package com.dengyuecang.www.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("currentUser") != null) {
			return super.preHandle(request, response, handler);
		} else {
			
			String username = request.getParameter("username");
			
			String password = request.getParameter("password");
			
			if ("acang".equals(username)&&"dengyuecang".equals(password)) {
				return true;
			}
			
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			response.sendRedirect(basePath + "index.jsp");
			return false;
		}
	}

}
