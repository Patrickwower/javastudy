package com.dengyuecang.www.utils.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringUtils;

public class TestImg {

    public static void main(String[] args) {
        String filePath = "/Users/acang/Downloads/16_watermark.jpg";
        String outPath = "/Users/acang/Downloads/16_watermark_123.jpg";
        drawTextInImg(filePath, outPath, new FontText("中国", 1, "#CC2BAC", 40, "黑体"));

    }

    public static void drawTextInImg(String filePath,String outPath, FontText text) {
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image img = imgIcon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bimage.createGraphics();
        g.setColor(getColor(text.getWm_text_color()));
        g.setBackground(Color.white);
        g.drawImage(img, 0, 0, null);
        Font font = null;
        if (StringUtils.isEmpty(text.getWm_text_font())
                && text.getWm_text_size() != null) {
            font = new Font(text.getWm_text_font(), Font.BOLD,
                    text.getWm_text_size());
        } else {
            font = new Font(null, Font.BOLD, 15);
        }

        g.setFont(font);
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(text.getText(), null);
        int textWidth = (int) bounds.getWidth();
        int textHeight = (int) bounds.getHeight();
        int left = 0;
        int top = textHeight;
        
        //九宫格控制位置
        if(text.getWm_text_pos()==2){
            left = width/2;
        }
        if(text.getWm_text_pos()==3){
            left = width -textWidth;
        }
        if(text.getWm_text_pos()==4){
            top = height/2;
        }
        if(text.getWm_text_pos()==5){
            left = width/2;
            top = height/2;
        }
        if(text.getWm_text_pos()==6){
            left = width -textWidth;
            top = height/2;
        }
        if(text.getWm_text_pos()==7){
            top = height - textHeight;
        }
        if(text.getWm_text_pos()==8){
            left = width/2;
            top = height - textHeight;
        }
        if(text.getWm_text_pos()==9){
            left = width -textWidth;
            top = height - textHeight;
        }
        g.drawString(text.getText(), left, top);
        g.dispose();

        try {
            FileOutputStream out = new FileOutputStream(outPath);
            ImageIO.write(bimage, "JPEG", out);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // color #2395439
    public static Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }
        if (color.length() != 6) {
            return null;
        }
        try {
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}