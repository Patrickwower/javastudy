package com.dengyuecang.www.controller.api.community;

import com.dengyuecang.www.controller.api.community.model.*;
import com.dengyuecang.www.service.community.IArticleService;
import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import org.omg.CORBA.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.xml.stream.events.Comment;

/**
 * Created by acang on 16/7/11.
 */

@RestController
@RequestMapping("/api/community/article")
public class APIArticleController {

    @Resource
    private IArticleService articleServiceImpl;

    @RequestMapping("/recommends")
    @ResponseBody
    public RespData recommendList(@RequestHeader HttpHeaders headers){

        try {
            return articleServiceImpl.recommends(headers);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/list")
    @ResponseBody
    public RespData list(@RequestHeader HttpHeaders headers, ArticleRequest articleRequest){

        try {
            return articleServiceImpl.queryArticles(headers, articleRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/detail")
    @ResponseBody
    public RespData detail(@RequestHeader HttpHeaders headers, String articleId){

        try {
            return articleServiceImpl.articleData(headers,articleId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/evaluate")
    public RespData evaluate(@RequestHeader HttpHeaders headers, EvaluteRequest evaluteRequest){

        try {
            return articleServiceImpl.evalute(headers, evaluteRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/comment")
    @ResponseBody
    public RespData comment(@RequestHeader HttpHeaders headers, CommentRequest commentRequest){

        try {
            return articleServiceImpl.comment(headers, commentRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/comments")
    @ResponseBody
    public RespData commentList(@RequestHeader HttpHeaders headers, ArticleCommentRequest request){

        try {
            return articleServiceImpl.comments(request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping("/focus")
    public RespData focusAuthor(@RequestHeader HttpHeaders headers, FocusAuthorRequest request){

        try {
            return articleServiceImpl.focusAuthor(headers, request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value="myFocusAuthors",method = RequestMethod.POST)
    @ResponseBody
    public RespData myFocusAuthors(@RequestHeader HttpHeaders headers, FocusAuthorRequest focusRequest){

        try {
            return articleServiceImpl.focusList(headers,focusRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);
    }

    @RequestMapping(value = "/byAuthor",method = RequestMethod.POST)
    @ResponseBody
    public RespData listByAuthor(@RequestHeader HttpHeaders headers, ArticleRequest articleRequest){

        try {
            return articleServiceImpl.listByAuthor(headers,articleRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        return RespCode.getRespData(RespCode.UNKNOW_EXCEPTION);

    }

    @RequestMapping("/test")
    public ModelAndView test(){

        ModelAndView mav = new ModelAndView();

        mav.setViewName("redirect:http://www.baidu.com?abc=123");

        return mav;
    }

}
