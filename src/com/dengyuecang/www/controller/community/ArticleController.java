package com.dengyuecang.www.controller.community;

import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.service.community.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("/community/article")
public class ArticleController {

	Logger log = LoggerFactory.getLogger(ArticleController.class);

	@Resource
	private IArticleService articleServiceImpl;
	
	@RequestMapping("/detail")
	public ModelAndView detail(String articleId){

		log.info("文章详情");

		ModelAndView mav = new ModelAndView();

		Article article = articleServiceImpl.queryArticleById(articleId);

		mav.addObject("article",article);

		mav.setViewName("article/detail");
		
		return mav;
	}

	@RequestMapping("/share")
	public ModelAndView share(String articleId){

		log.info("文章分享");

		ModelAndView mav = new ModelAndView();

		mav.addObject("article",articleId);

		mav.setViewName("article/share");

		return mav;
	}

}
