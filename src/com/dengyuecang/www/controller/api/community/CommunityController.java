package com.dengyuecang.www.controller.api.community;


import com.dengyuecang.www.controller.api.community.model.ArticleRequest;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

Logger log = LoggerFactory.getLogger(CommunityController.class);
	
	@Resource
	private IArticleService articleServiceImpl;

	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ResponseBody
	public RespData index(@RequestHeader HttpHeaders headers){

		try {
			return articleServiceImpl.index();
		} catch (Exception e) {

		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}

	@RequestMapping(value="/articles",method=RequestMethod.POST)
	@ResponseBody
	public RespData articles(@RequestHeader HttpHeaders headers, ArticleRequest articleRequest){

		try {
			return articleServiceImpl.queryArticles(articleRequest);
		}catch (Exception e){
			e.printStackTrace();
		}

		return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
	}
	
}
