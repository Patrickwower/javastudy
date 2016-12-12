package com.dengyuecang.www.service.bigbox.impl;

import com.dengyuecang.www.controller.api.h5.model.BigBoxUserInfo;
import com.dengyuecang.www.entity.bigbox.BigBoxUserinfo;
import com.dengyuecang.www.service.bigbox.IBigBoxService;
import com.dengyuecang.www.service.bigbox.common.BigBoxCommon;
import com.dengyuecang.www.service.common.CommonConstant;

import com.dengyuecang.www.utils.RespCode;
import com.dengyuecang.www.utils.RespData;
import com.dengyuecang.www.utils.img.WaterMark;
import com.longinf.lxcommon.dao.BaseDao;
import com.longinf.lxcommon.service.BaseService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * Created by acang on 16/6/22.
 */
@Service
public class BigBoxServiceImpl extends BaseService<BigBoxUserinfo> implements IBigBoxService{

    Logger log = org.slf4j.LoggerFactory.getLogger(BigBoxServiceImpl.class);

    @Resource(name="hibernateBaseDao")
    private BaseDao<BigBoxUserinfo> bigBoxUserinfoBaseDao;


    @Override
    public BaseDao<BigBoxUserinfo> getSuperDao() {
        return null;
    }

    @Override
    public String addImg(HttpHeaders headers, BigBoxUserInfo bigBoxUserinfo) {

        BigBoxUserinfo userinfo = new BigBoxUserinfo();

        userinfo.setLocation(bigBoxUserinfo.getLocation());

        userinfo.setName(bigBoxUserinfo.getName());

        userinfo.setMobile(bigBoxUserinfo.getMobile());

        userinfo.setRole(bigBoxUserinfo.getRole());

        userinfo.setNumber_1(this.getNumber());

        userinfo.setNumber_2(this.getNumber());

        userinfo.setAction(this.getAction());

        userinfo.setDescription(this.getDescription());

        userinfo.setCreate_date(new Date());

        //生成图片地址

//        Date d = new Date();
//
//        Format f = new SimpleDateFormat("yyyy/MM/dd/HH/");

        String imgPath = "/h5img/"
//                +f.format(d)
                ;

        String imgName = UUID.randomUUID()+".jpg";


        userinfo.setImgurl(CommonConstant.STATIC_URL+imgPath+imgName);



        try {

            System.out.println("作为一个");

            System.out.println(userinfo.getDescription());

            System.out.println(userinfo.getNumber_1());

            System.out.println("米");

            System.out.println(userinfo.getNumber_2());

            System.out.println("长 的");

            System.out.println(userinfo.getRole());

            System.out.println(userinfo.getName());

            System.out.println(userinfo.getAction());

            System.out.println("励志要在");

            System.out.println(userinfo.getLocation());

            System.out.println("搞事");

            bigBoxUserinfoBaseDao.save(userinfo);

            this.makePicForBigBox(userinfo);

            return userinfo.getImgurl();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public RespData addPic(HttpHeaders headers, BigBoxUserInfo bigBoxUserInfonfo) {

        String imgurl = this.addImg(headers, bigBoxUserInfonfo);

        if (StringUtils.isNotEmpty(imgurl)){

            Map<String,String> response = new HashMap<String,String>();

            response.put("imgurl",imgurl);

            return RespCode.getRespData(RespCode.SUCCESS,response);
        }

        return RespCode.getRespData(RespCode.ERROR);
    }

    private static String getDescription(){

        Random rand = new Random();

        int index = rand.nextInt(BigBoxCommon.BIG_BOX_DESCRIPTION.length);

        String description = BigBoxCommon.BIG_BOX_DESCRIPTION[index];

        return description;

    }

    private static String getNumber(){

        Random rand = new Random();

        int index = rand.nextInt(BigBoxCommon.BIG_BOX_NUMBER.length);

        String number = BigBoxCommon.BIG_BOX_NUMBER[index];

        return number;

    }

    private static  String getAction(){

        Random rand = new Random();

        int index = rand.nextInt(BigBoxCommon.BIG_BOX_ACTION.length);

        String action = BigBoxCommon.BIG_BOX_ACTION[index];

        return action;

    }

    private String makePicForBigBox(BigBoxUserinfo userinfo){

        String dPicPath = BigBoxCommon.H5_SOURCE+BigBoxCommon.DESCRIPTION_PINYIN.get(userinfo.getDescription())+".png";

        String n1PicPath = BigBoxCommon.H5_SOURCE+BigBoxCommon.NUMBER_PINYIN.get(userinfo.getNumber_1())+".png";

        String n2PicPath = BigBoxCommon.H5_SOURCE+BigBoxCommon.NUMBER_PINYIN.get(userinfo.getNumber_2())+".png";

        String rPicPath = BigBoxCommon.H5_SOURCE+BigBoxCommon.ROLE_PINYIN.get(userinfo.getRole())+".png";

        String aPicPath = BigBoxCommon.H5_SOURCE+BigBoxCommon.ACTION_PINYIN.get(userinfo.getAction())+".png";

        String source_img = BigBoxCommon.H5_SOURCE+"base.jpg";

        String h5_img = BigBoxCommon.H5_IMG_PATH+userinfo.getImgurl().replaceAll(CommonConstant.STATIC_URL+"/h5img","");

        try {
            Thumbnails.of(source_img)
                    .size(640, 640)  //必须要设置大小 不然会抛异常
                    .watermark(new Position() {
                        @Override
                        public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                            return new Point(113,20);
                        }
                    }, ImageIO.read(new File(dPicPath)), 1.0f)
                    .watermark(new Position() {
                        @Override
                        public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                            return new Point(360,20);
                        }
                    },ImageIO.read(new File(n1PicPath)), 1.0f)
                    .watermark(new Position() {
                        @Override
                        public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                            return new Point(530,20);
                        }
                    }, ImageIO.read(new File(n2PicPath)), 1.0f)
                    .watermark(new Position() {
                        @Override
                        public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                            return new Point(170,130);
                        }
                    },ImageIO.read(new File(rPicPath)), 1.0f)
                    .watermark(new Position() {
                        @Override
                        public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                            return new Point(25,220);
                        }
                    }, ImageIO.read(new File(aPicPath)), 1.0f)
                    //输出品质   越高 图片越大
                    .outputQuality(1.0f)
                    //角度
//                .rotate(200.0)
                    .toFile(h5_img);

            String fromPath = h5_img;

            String toPath = h5_img;

            WaterMark.createMarkcreateMark(fromPath,toPath,userinfo.getName(),userinfo.getLocation());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {

        System.out.println("作为一个");

        System.out.println(BigBoxServiceImpl.getDescription());

        System.out.println(BigBoxServiceImpl.getNumber());

        System.out.println("米");

        System.out.println(BigBoxServiceImpl.getNumber());

        System.out.println("长 的");

        System.out.println("勾手老大爷,");

        System.out.println("老王");

        System.out.println(BigBoxServiceImpl.getAction());

        System.out.println("励志要在");

        System.out.println("北大附中");

        System.out.println("搞事");

    }

}
