package com.dengyuecang.www.service.bigbox.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acang on 2016/11/9.
 */
public class BigBoxCommon {

    /**
     * 大盒子h5 资源地址
     */
    public static final String H5_SOURCE = "/Applications/tomcat7/webapps/h5source/";

    /**
     * 大盒子h5 图片输出地址
     */
    public static final String H5_IMG_PATH = "/Applications/tomcat7/webapps/h5img/";

    /**
     * 大盒子推广h5的人物描述-哪里长
     */
    public static final String[] BIG_BOX_DESCRIPTION = {"腿"
            , "腿毛"
            , "睫毛"
            , "脸"
            , "耳垂"
            , "汗毛"
            , "腋毛"
            , "XX"
    };

    /**
     * 大盒子推广h5的长度数字
     */
    public static final String[] BIG_BOX_NUMBER = {"一"
            , "二"
            , "三"
            , "四"
            , "五"
            , "六"
            , "七"
            , "八"
            , "九"};

    /**
     * 大盒子推广h5的人物角色-什么人
     */
    public static final String[] BIG_BOX_ROLE = {"活动策划"
            , "活动执行"
            , "活动摄影"
            , "活动文案"
            , "家长"
            , "指导老师"
            , "你爸爸"
            , "搞传销的"
            , "卖安利的"
            , "吃安利的"
            , "打酱油的吃瓜群众"
            , "广场舞大妈"
            , "秘制观众"
            , "你大爷"
            , "灵魂歌手"
            , "贴小广告的"
            , "背锅侠"
            , "颜值担当"
            , "睁眼民"
            , "门卫老大爷"
            , "勾手老大爷"
            , "灵活死胖子"
            , "印度阿三"
            , "消化"
            , "圣母"
            , "校长"
            , "教导主任"
            , "班主任"
    };

    /**
     * 大盒子推广h5的人物活动-在干嘛
     */
    public static final String[] BIG_BOX_ACTION = {
//            "开着车"
//            , "唱着红歌"
//            , "挥着手绢"
//            , "跳着舞"
//            , "喊着欧巴"
             "竞选着美国总统"
            , "牵着刘恺威的老婆"
            , "摸着Angelababy的肚子"
            , "抱着李易峰的大腿"
            , "拿着大宝剑"
            , "抱着井柏然的腿"
            , "摊着葛优"
            , "怀着杨洋的孩子"
            , "看着颗猕猴桃"
            , "捏着脚"
            , "穿着全套椰子"

    };


    public static final Map<String, String> DESCRIPTION_PINYIN = new HashMap<String, String>();

    public static final Map<String, String> NUMBER_PINYIN = new HashMap<String, String>();

    public static final Map<String, String> ROLE_PINYIN = new HashMap<String, String>();

    public static final Map<String, String> ACTION_PINYIN = new HashMap<String, String>();


    static {

        DESCRIPTION_PINYIN.put("腿", "tui");
        DESCRIPTION_PINYIN.put("脸", "lian");
        DESCRIPTION_PINYIN.put("腿毛", "tuimao");
        DESCRIPTION_PINYIN.put("睫毛", "jiemao");
        DESCRIPTION_PINYIN.put("耳垂", "erchui");
        DESCRIPTION_PINYIN.put("汗毛", "hanmao");
        DESCRIPTION_PINYIN.put("腋毛", "yemao");
        DESCRIPTION_PINYIN.put("XX", "xx");

        NUMBER_PINYIN.put("一", "yi");
        NUMBER_PINYIN.put("二", "er");
        NUMBER_PINYIN.put("三", "san");
        NUMBER_PINYIN.put("四", "si");
        NUMBER_PINYIN.put("五", "wu");
        NUMBER_PINYIN.put("六", "liu");
        NUMBER_PINYIN.put("七", "qi");
        NUMBER_PINYIN.put("八", "ba");
        NUMBER_PINYIN.put("九", "jiu");

        ROLE_PINYIN.put("活动策划", "huodongcehua");
        ROLE_PINYIN.put("活动执行", "huodongzhixing");
        ROLE_PINYIN.put("活动摄影", "huodongsheying");
        ROLE_PINYIN.put("活动文案", "huodongwenan");
        ROLE_PINYIN.put("家长", "jiazhang");
        ROLE_PINYIN.put("指导老师", "zhidaolaoshi");
        ROLE_PINYIN.put("你爸爸", "nibaba");
        ROLE_PINYIN.put("搞传销的", "gaochuanxiaode");
        ROLE_PINYIN.put("卖安利的", "maianlide");
        ROLE_PINYIN.put("吃安利的", "chianlide");
        ROLE_PINYIN.put("打酱油的吃瓜群众", "dajiangyoudechiguaqunzhong");
        ROLE_PINYIN.put("广场舞大妈", "guangchangwudama");
        ROLE_PINYIN.put("秘制观众", "mizhiguanzhong");
        ROLE_PINYIN.put("你大爷", "nidaye");
        ROLE_PINYIN.put("灵魂歌手", "linghungeshou");
        ROLE_PINYIN.put("贴小广告的", "tiexiaoguanggaode");
        ROLE_PINYIN.put("背锅侠", "beiguoxia");
        ROLE_PINYIN.put("颜值担当", "yanzhidandang");
        ROLE_PINYIN.put("睁眼民", "zhengyanmin");
        ROLE_PINYIN.put("门卫老大爷", "menweilaodaye");
        ROLE_PINYIN.put("勾手老大爷", "goushoulaodaye");
        ROLE_PINYIN.put("灵活死胖子", "linghuosipangzi");
        ROLE_PINYIN.put("印度阿三", "yinduasan");
        ROLE_PINYIN.put("校花", "xiaohua");
        ROLE_PINYIN.put("圣母", "shengmu");
        ROLE_PINYIN.put("校长", "xiaozhang");
        ROLE_PINYIN.put("教导主任", "jiaodaozhuren");
        ROLE_PINYIN.put("班主任", "banzhuren");

//        ACTION_PINYIN.put("开着车", "kaizheche");
//        ACTION_PINYIN.put("唱着红歌", "changzhehongge");
//        ACTION_PINYIN.put("挥着手绢", "huizheshoujuan");
//        ACTION_PINYIN.put("跳着舞", "tiaozhewu");
//        ACTION_PINYIN.put("喊着欧巴", "hanzheouba");
        ACTION_PINYIN.put("竞选着美国总统", "jingxuanzhemeiguozongtong");
        ACTION_PINYIN.put("牵着刘恺威的老婆", "qianzheliukaiweidelaopo");
        ACTION_PINYIN.put("摸着Angelababy的肚子", "mozheangelababydeduzi");
        ACTION_PINYIN.put("抱着李易峰的大腿", "baozheliyifengdedatui");
        ACTION_PINYIN.put("拿着大宝剑", "nazhedabaojian");
        ACTION_PINYIN.put("抱着井柏然的腿", "baozhejingborandetui");
        ACTION_PINYIN.put("摊着葛优", "tanzhegeyou");
        ACTION_PINYIN.put("怀着杨洋的孩子", "huaizheyangyangdehaizi");
        ACTION_PINYIN.put("看着颗猕猴桃", "kanzhekemihoutao");
        ACTION_PINYIN.put("捏着脚", "niezhejiao");
        ACTION_PINYIN.put("穿着全套椰子", "chuanzhequantaoyezi");



    }


}
