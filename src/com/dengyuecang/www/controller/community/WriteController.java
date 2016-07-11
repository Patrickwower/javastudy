package com.dengyuecang.www.controller.community;

import com.dengyuecang.www.controller.community.model.LongTextRequest;
import com.dengyuecang.www.entity.community.Article;
import com.dengyuecang.www.service.community.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/community/write")
public class WriteController {

	Logger log = LoggerFactory.getLogger(WriteController.class);

	@Resource
	private IArticleService articleServiceImpl;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		log.info("书写首页");
		ModelAndView mav = new ModelAndView();

		List<Article> articleList = articleServiceImpl.queryArticle("8a2a67a25505963401550a65958a000f");

		mav.addObject("articles",articleList);

		mav.setViewName("write/index");
		
		return mav;
	}
	
	@RequestMapping("/toWrite")
	@ResponseBody
	public ModelAndView toWrite(){

		ModelAndView mav = new ModelAndView();

		mav.setViewName("write/write");

		return mav;
	}

	@RequestMapping("/write")
	@ResponseBody
	public ModelAndView write(LongTextRequest request){

		ModelAndView mav = new ModelAndView();

		log.info(request.getContent());

		articleServiceImpl.save(request);

		mav.setViewName("write/write");

		return mav;
	}


}
