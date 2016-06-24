package com.dengyuecang.api.controller.community;

import com.dengyuecang.api.controller.community.model.LongTextRequest;
import com.dengyuecang.api.entity.community.Article;
import com.dengyuecang.api.service.community.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
	
//	@RequestMapping("/toWrite")
//	@ResponseBody
//	public ModelAndView toWrite(){
//
//		ModelAndView mav = new ModelAndView();
//
//		mav.setViewName("write/write");
//
//		return mav;
//	}
//
//	@RequestMapping("/write")
//	@ResponseBody
//	public ModelAndView write(LongTextRequest request){
//
//		ModelAndView mav = new ModelAndView();
//
//		log.info(request.getContent());
//
//		articleServiceImpl.save(request);
//
//		mav.setViewName("write/write");
//
//		return mav;
//	}


}
