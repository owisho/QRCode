package per.owisho.qrcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FontImage {

	public static void main(String[] args) throws Exception {  
        /*createImage("请A1003到3号窗口", new Font("宋体", Font.BOLD, 30), new File(  
                "e:/a.png"), 4096, 64);  
        createImage("请A1002到2号窗口", new Font("黑体", Font.BOLD, 35), new File(  
                "e:/a1.png"), 4096, 64);  
        createImage("请A1001到1号窗口", new Font("黑体", Font.PLAIN, 40), new File(  
                "e:/a2.png"), 4096, 64);  */
		createImage("府前",new Font("宋体",Font.BOLD,190),new File("C:\\Users\\Administrator\\Desktop\\府前.png"),600,600);
		createImage("仁和堂",new Font("宋体",Font.BOLD,190),new File("C:\\Users\\Administrator\\Desktop\\仁和堂.png"),600,600);
    } 
	
	// 根据str,font的样式以及输出文件目录  
    public static void createImage(String str, Font font, File outFile,  
            Integer width, Integer height) throws Exception {  
        // 创建图片  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();  
        g.setClip(0, 0, width, height);  
        g.setColor(Color.black);  
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景  
        g.setColor(Color.white);// 在换成黑色  
        g.setFont(font);// 设置画笔字体  
        /** 用于获得垂直居中y */  
        Rectangle clip = g.getClipBounds();  
        FontMetrics fm = g.getFontMetrics(font);  
        int ascent = fm.getAscent();  
        int descent = fm.getDescent();  
        int y = (clip.height - (ascent + descent)) / 2 + ascent;  
        int strLen = fm.stringWidth(str);
        int x = (width - strLen)/2;
        g.drawString(str, x , y);// 画出字符串  
          
        g.dispose();  
        ImageIO.write(image, "png", outFile);// 输出png图片  
    }  
	
}
