package com.dengyuecang.www.utils.img;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.dengyuecang.www.utils.RegexUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;


public class ImgUtils {
    /**
     * 按照指定大小缩放  按照比例
     * @throws Exception
     */
    public static void testOne() throws Exception {
        //File file = new File("image/1.jpg");
        //System.out.println(file.exists());
        Thumbnails.of("/Users/acang/Downloads/16.jpg").size(810, 600).toFile("/Users/acang/Downloads/16_200x300.jpg");
    }

    /***
     * 按照指定比例进行缩放
     * @throws Exception
     */
    public static void testTwo() throws Exception {
        Thumbnails.of("image/1.jpg").scale(0.25f).toFile("image/a_0.25%.jpg");
    }

    /***
     * 根据指定大小进行缩放 不按照比例
     * @throws Exception
     */
    public static void testThree() throws Exception {
        Thumbnails.of("image/1.jpg").size(200, 300)
                //设置是否按比例  false  图片可能会走形 默认true
                .keepAspectRatio(false)
                .toFile("image/1_200x300_no.jpg");
    }

    /**
     * 添加水印 （好像只能添加图片水印？）
     * @throws Exception
     */
    public static void testFour() throws Exception {

        Thumbnails.of("/Users/acang/Downloads/16.jpg")
                .size(1920, 1080)  //必须要设置大小 不然会抛异常
                .watermark(new Position() {
                    @Override
                    public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                        return new Point(800,500);
                    }
                },ImageIO.read(new File("/Users/acang/Downloads/128x128/heart.png")), 0.8f)
                .watermark(new Position() {
                    @Override
                    public Point calculate(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                        return new Point(800,350);
                    }
                },ImageIO.read(new File("/Users/acang/Downloads/128x128/girl.png")), 0.8f)
                //输出品质   越高 图片越大
                .outputQuality(1.0f)
//                .rotate(200.0)
                .toFile("/Users/acang/Downloads/16_watermark.jpg");
    }




    /***
     * 裁剪
     * @throws Exception
     */
    public static void testFive() throws Exception {

////        Thumbnails.of("/Users/acang/Downloads/16.jpg").asBufferedImage();
//
//        String imagePath = "/Users/acang/Downloads/37179063_1407421362265_800x600.jpg";
//
//        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
//        int width = bufferedImage.getWidth();
//        int height = bufferedImage.getHeight();
//
//        int squareWidth = 0;
//
//        if (height>width){
//            squareWidth = width;
//        }else{
//            squareWidth = height;
//        }
//
//        Thumbnails.of(imagePath)
//                //从原图哪里开始裁剪   裁减多少
//                .sourceRegion(Positions.CENTER, squareWidth, squareWidth)
//                //新图的大小
//                .size(squareWidth, squareWidth).toFile("/Users/acang/Downloads/16_crop.jpg");

    }

    /**
     * 改变图片格式
     * @throws Exception
     */
    public static void testSix() throws Exception {
        Thumbnails.of("image/1.jpg")
                .size(1920, 1080)
                .outputFormat("png")
                .toFile("image/1_png.png");
    }

    public static void testName() throws Exception {
        System.out.println(System.getProperty("user.dir"));
    }

    public static void main(String[] args) throws Exception {

            ImgUtils.makeWord();

    }

    /**
     * 从中心剪裁
     */
    public static void squareCrop(String resource,String target) throws Exception{

        String imagePath = resource;

        Thumbnails.of(imagePath)
                //从原图哪里开始裁剪   裁减多少
                .sourceRegion(Positions.TOP_LEFT, 155, 73)
                //新图的大小
                .size(155, 73).toFile(target);

    }





    public static void makeWord(){

        try {
            ImgUtils.squareCrop("/Users/acang/Downloads/words/description.png","/Users/acang/Downloads/words/part/lian.png");

        }catch (Exception e){


            e.printStackTrace();
        }








    }

















}
