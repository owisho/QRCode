package per.owisho.qrcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class LogoGeneratorForYBT implements ILogoGenerator{

	@Override
	public void generate(OutputStream os) {
		//三个字的图片
		try {
			BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\logo底.png"));
			Graphics g = image.getGraphics();  
			g.setColor(Color.white);// 在换成黑色  
	        g.setFont(new Font("微软雅黑",Font.BOLD,300));// 设置画笔字体
	        g.drawString("仁和堂", 295 , 950);// 画出字符串  
	        g.dispose(); 
	        // 输出png图片  
	        try {
				ImageIO.write(image, "png", os);
			} catch (IOException e) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//两个字的图片
		/*try {
			BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\logo底.png"));
			Graphics g = image.getGraphics();  
			g.setColor(Color.white);// 在换成黑色  
	        g.setFont(new Font("宋体",Font.BOLD,300));// 设置画笔字体
	        g.drawString("府前", 440 , 950);// 画出字符串  
	        g.dispose(); 
	        // 输出png图片  
	        try {
				ImageIO.write(image, "png", os);
			} catch (IOException e) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
}
