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
		//�����ֵ�ͼƬ
		try {
			BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\logo��.png"));
			Graphics g = image.getGraphics();  
			g.setColor(Color.white);// �ڻ��ɺ�ɫ  
	        g.setFont(new Font("΢���ź�",Font.BOLD,300));// ���û�������
	        g.drawString("�ʺ���", 295 , 950);// �����ַ���  
	        g.dispose(); 
	        // ���pngͼƬ  
	        try {
				ImageIO.write(image, "png", os);
			} catch (IOException e) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�����ֵ�ͼƬ
		/*try {
			BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\logo��.png"));
			Graphics g = image.getGraphics();  
			g.setColor(Color.white);// �ڻ��ɺ�ɫ  
	        g.setFont(new Font("����",Font.BOLD,300));// ���û�������
	        g.drawString("��ǰ", 440 , 950);// �����ַ���  
	        g.dispose(); 
	        // ���pngͼƬ  
	        try {
				ImageIO.write(image, "png", os);
			} catch (IOException e) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
}
