package com.dengyuecang.www.controller.api.sys;


import com.dengyuecang.www.controller.api.sys.model.ArticleListRequest;
import com.dengyuecang.www.controller.api.sys.model.UserLoginRequest;
import com.dengyuecang.www.service.sys.ISysArticleService;
import com.dengyuecang.www.service.sys.ISysService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/sys/article")
public class SysArticleController {

	Logger log = LoggerFactory.getLogger(SysArticleController.class);
	
	@Resource
	private ISysArticleService sysArticleServiceImpl;
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public RespData list(@RequestHeader HttpHeaders headers, ArticleListRequest articleListRequest){
		
		try {

			log.info("文章列表");

			return sysArticleServiceImpl.articleList(headers,articleListRequest);
		
		} catch (Exception e) {
			
		}
		
		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

}
