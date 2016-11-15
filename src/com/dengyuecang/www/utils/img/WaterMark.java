package com.dengyuecang.www.utils.img;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import com.sun.image.codec.jpeg.*;
import com.sun.javafx.tk.FontLoader;
import org.apache.commons.lang.StringUtils;

import java.text.AttributedString;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;


public class WaterMark {
   /**
      * 给图片添加水印
//     * @param filePath 需要添加水印的图片的路径
//     * @param markContent 水印的文字
//     * @param markContentColor 水印文字的颜色
//     * @param qualNum 图片质量
//     * @param fontType 字体
//     * @param fontsize 字体大小
     * @return
     * @author zhongweihai newwei2001@yahoo.com.cn
     */
    public static boolean createMarkcreateMark(String from,String to,String markContent,String markContent2)
    {

        String sourceLocation = markContent2;

        ImageIcon imgIcon=new ImageIcon(from);
        Image theImg =imgIcon.getImage();
        int width=theImg.getWidth(null);
        int height= theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);


        //第一块
        Graphics2D g=bimage.createGraphics();
        g.setColor(new Color(20,33,100));
        g.drawImage(theImg, 0, 0, null );


        AttributedString ats = new AttributedString(markContent);
        AttributedString ats2 = new AttributedString(markContent2);
        AttributedString ats3 = new AttributedString(sourceLocation);

        //设置字体大小

        Font f = new Font("汉仪黑荔枝体简",Font.BOLD, 45);
        Font f2 = new Font("汉仪黑荔枝体简",Font.BOLD, 45);
        Font f3 = new Font("悦园",Font.BOLD, 22);

        System.out.println(f.getFontName());
        System.out.println(f2.getFontName());
        System.out.println(f3.getFontName());


        ats.addAttribute(TextAttribute.FONT, f, 0,markContent.length());
        ats2.addAttribute(TextAttribute.FONT, f2, 0,markContent2.length() );
        ats3.addAttribute(TextAttribute.FONT,f3,0,sourceLocation.length());

        AttributedCharacterIterator iter = ats.getIterator();
        AttributedCharacterIterator iter2 = ats2.getIterator();
        AttributedCharacterIterator iter3 = ats3.getIterator();


        /* 消除java.awt.Font字体的锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //第一块 字水印
        g.drawString(iter,340,230); //添加水印的文字和设置水印文字出现的内容

        //第二块 字水印
        g.drawString(iter2,260,350);

        //第三块 字水印
        g.drawString(iter3,305,595);

        g.dispose();



        try{
        FileOutputStream out=new FileOutputStream(to);
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
        param.setQuality(100f, true);
        encoder.encode(bimage, param);
        out.close();
        }catch(Exception e)
        { return false; }
        return true;
    }

    public static void main(String[] args)
    {
//        String fromPath = "";
//
//        String toPath = "";
//
//        WaterMark.createMark(fromPath,toPath,"老司机","小鲜肉");
     }
}