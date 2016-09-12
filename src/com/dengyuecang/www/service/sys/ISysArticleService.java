package com.dengyuecang.www.service.sys;

import com.dengyuecang.www.controller.api.sys.model.ArticleListRequest;
import com.dengyuecang.www.controller.api.sys.model.UserLoginRequest;
import com.dengyuecang.www.entity.StaticProvince;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.utils.RespData;
import com.longinf.lxcommon.service.IBaseService;
import org.springframework.http.HttpHeaders;

public interface ISysArticleService extends IBaseService<Article>{

	/**
	 * 文章列表
	 * @param headers
	 * @return
	 */
	public RespData articleList(HttpHeaders headers, ArticleListRequest articleListRequest);



}
