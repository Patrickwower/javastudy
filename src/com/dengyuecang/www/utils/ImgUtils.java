package com.dengyuecang.www.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgUtils {

	private static final Logger log = LoggerFactory.getLogger(ImgUtils.class);

	/**

	 * 该类实现了图片的合并功能，可以选择水平合并或者垂直合并。
	 * 当然此例只是针对两个图片的合并，如果想要实现多个图片的合并，只需要自己实现方法 BufferedImage
	 * mergeImage(BufferedImage[] imgs, boolean isHorizontal)即可；
	 * 而且这个方法更加具有通用性，但是时间原因不实现了，方法和两张图片实现是一样的
	 */


	    /**
	     * @param fileUrl
	     *            文件绝对路径或相对路径
	     * @return 读取到的缓存图像
	     * @throws IOException
	     *             路径错误或者不存在该文件时抛出IO异常
	     */
	    public static BufferedImage getBufferedImage(String fileUrl)
	            throws IOException {
	        File f = new File(fileUrl);
	        return ImageIO.read(f);
	    }

	    /**
	     * @param savedImg
	     *            待保存的图像
	     * @param saveDir
	     *            保存的目录
	     * @param fileName
	     *            保存的文件名，必须带后缀，比如 "beauty.jpg"
	     * @param format
	     *            文件格式：jpg、png或者bmp
	     * @return
	     */
	    public static boolean saveImage(BufferedImage savedImg, String saveDir,
	            String fileName, String format) {
	        boolean flag = false;

	        // 先检查保存的图片格式是否正确
	        String[] legalFormats = { "jpg", "JPG", "png", "PNG", "bmp", "BMP" };
	        int i = 0;
	        for (i = 0; i < legalFormats.length; i++) {
	            if (format.equals(legalFormats[i])) {
	                break;
	            }
	        }
	        if (i == legalFormats.length) { // 图片格式不支持
	            System.out.println("不是保存所支持的图片格式!");
	            return false;
	        }

	        // 再检查文件后缀和保存的格式是否一致
	        String postfix = fileName.substring(fileName.lastIndexOf('.') + 1);
	        if (!postfix.equalsIgnoreCase(format)) {
	            System.out.println("待保存文件后缀和保存的格式不一致!");
	            return false;
	        }

	        String fileUrl = saveDir + fileName;
	        File file = new File(fileUrl);
	        try {
	            flag = ImageIO.write(savedImg, format, file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return flag;
	    }

	    /**
	     * 待合并的两张图必须满足这样的前提，如果水平方向合并，则高度必须相等；如果是垂直方向合并，宽度必须相等。
	     * mergeImage方法不做判断，自己判断。
	     * 
	     * @param img1
	     *            待合并的第一张图
	     * @param img2
	     *            带合并的第二张图
	     * @param isHorizontal
	     *            为true时表示水平方向合并，为false时表示垂直方向合并
	     * @return 返回合并后的BufferedImage对象
	     * @throws IOException
	     */
	    public static BufferedImage mergeImage(BufferedImage img1,
	            BufferedImage img2, boolean isHorizontal) throws IOException {
	        int w1 = img1.getWidth();
	        int h1 = img1.getHeight();
	        int w2 = img2.getWidth();
	        int h2 = img2.getHeight();

	        // 从图片中读取RGB
	        int[] ImageArrayOne = new int[w1 * h1];
	        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
	        int[] ImageArrayTwo = new int[w2 * h2];
	        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

	        // 生成新图片
	        BufferedImage DestImage = null;
	        if (isHorizontal) { // 水平方向合并
	            DestImage = new BufferedImage(w1+w2, h1, BufferedImage.TYPE_INT_RGB);
	            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
	            DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);
	        } else { // 垂直方向合并
	            DestImage = new BufferedImage(w1, h1 + h2,
	                    BufferedImage.TYPE_INT_RGB);
	            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
	            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // 设置下半部分的RGB
	        }

	        return DestImage;
	    }

//	    public static void main(String[] args) {
//	        // 读取待合并的文件
//	        BufferedImage bi1 = null;
//	        BufferedImage bi2 = null;
////	        try {
////	            bi1 = getBufferedImage("src/ImageProcessDemos/图像合并/垂直合并1.jpg");
////	            bi2 = getBufferedImage("src/ImageProcessDemos/图像合并/垂直合并2.jpg");
////	        } catch (IOException e) {
////	            e.printStackTrace();
////	        }
//	//
//	        // 调用mergeImage方法获得合并后的图像
//	        BufferedImage destImg = null;
////	        try {
////	            destImg = mergeImage(bi1, bi2, false);
////	        } catch (IOException e) {
////	            e.printStackTrace();
////	        }
//	//
////	        // saveImage(BufferedImage savedImg, String saveDir, String fileName,
////	        // String format)
////	        // 保存图像
////	        saveImage(destImg, "src/ImageProcessDemos/图像合并/", "垂直合并图像.png", "png");
////	        System.out.println("垂直合并完毕!");
//
//	        System.out.println("下面是水平合并的情况：");
//
//	        try {
//	            bi1 = getBufferedImage("/Users/acang/Downloads/ac0663cf55fbbc6edf2c4b17dfee6688.jpg");
//	            bi2 = getBufferedImage("/Users/acang/Downloads/ac0663cf55fbbc6edf2c4b17dfee6688.jpg");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	        // 调用mergeImage方法获得合并后的图像
//	        try {
//	            destImg = mergeImage(bi1, bi2, true);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	        // 保存图像
//	        saveImage(destImg, "/Users/acang/Downloads/", "luguo.png", "png");
//	        System.out.println("水平合并完毕!");
//
//	    }

	
	    
	    public static final void overlapImage(String bigPath, String smallPath) {
	    	try {
	    		BufferedImage big = ImageIO.read(new File(bigPath));
	    		BufferedImage small = ImageIO.read(new File(smallPath));
	    		Graphics2D g = big.createGraphics();
	    		int x = (big.getWidth() - small.getWidth()) / 2;
	    		int y = (big.getHeight() - small.getHeight()) / 2;
	    		g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
	    		g.dispose();
	    		ImageIO.write(big, "jpg", new File("/Users/acang/Downloads/BigSmall.jpg"));
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
    
    public static void createImage(int width, int height) throws IOException{
    	
        BufferedImage src = ImageIO.read(new File("/Users/acang/Downloads/982e521d-5297-46fb-97ed-82064f7ce5da.png"));
        
        BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);  
        //绘制放大后的图片
        tag.getGraphics().drawImage(src, 0, 0, src.getWidth(), src.getHeight(), null);  
        
//        tag.getGraphics().drawImage(src.getScaledInstance(440, (int) 100,  Image.SCALE_SMOOTH), 0, 0,  null);  
        
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = it.next();
        File f = new File("/Users/acang/Downloads/test02.jpg");
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);
        
        writer.write(tag);
    }
    
//    public static void ZoomTheImage(String fileUrl, String newUrl, int width,  
//            int height) {  
//        java.io.File file = new java.io.File(fileUrl); // 读入刚才上传的文件  
//        Image src = null;  
//        try {  
//            src = javax.imageio.ImageIO.read(file);  
//            // 构造Image对象  
//            BufferedImage tag = new BufferedImage(width, height,  
//                    BufferedImage.TYPE_INT_RGB);  
//            // tag.getGraphics().drawImage(src, 0, 0, width, height, null); //  
//            // 绘制缩小后的图  
//            tag.getGraphics().drawImage(  
//                    src.getScaledInstance(width, height, Image.SCALE_SMOOTH),  
//                    0, 0, null);  
//            FileOutputStream newimage = new FileOutputStream(newUrl); // 输出到文件流  
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);  
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);  
//            jep.setQuality((float) 1.0, true);  
//            encoder.encode(tag, jep);  
//            newimage.close();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }  
     
    public static void main(String[] args) {
		
    	
    	try {
			ImgUtils.createImage(100, 100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
	}
    
      /** the main method */
//      public static final void main(String[] args) {
//        overlapImage("/Users/acang/Downloads/2531170_150741007350_2.jpg", "/Users/acang/Downloads/100.jpeg");
//      }
	
}
