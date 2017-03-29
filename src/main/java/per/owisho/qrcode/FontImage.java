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
        /*createImage("��A1003��3�Ŵ���", new Font("����", Font.BOLD, 30), new File(  
                "e:/a.png"), 4096, 64);  
        createImage("��A1002��2�Ŵ���", new Font("����", Font.BOLD, 35), new File(  
                "e:/a1.png"), 4096, 64);  
        createImage("��A1001��1�Ŵ���", new Font("����", Font.PLAIN, 40), new File(  
                "e:/a2.png"), 4096, 64);  */
		createImage("��ǰ",new Font("����",Font.BOLD,190),new File("C:\\Users\\Administrator\\Desktop\\��ǰ.png"),600,600);
		createImage("�ʺ���",new Font("����",Font.BOLD,190),new File("C:\\Users\\Administrator\\Desktop\\�ʺ���.png"),600,600);
    } 
	
	// ����str,font����ʽ�Լ�����ļ�Ŀ¼  
    public static void createImage(String str, Font font, File outFile,  
            Integer width, Integer height) throws Exception {  
        // ����ͼƬ  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();  
        g.setClip(0, 0, width, height);  
        g.setColor(Color.black);  
        g.fillRect(0, 0, width, height);// ���ú�ɫ�������ͼƬ,Ҳ���Ǳ���  
        g.setColor(Color.white);// �ڻ��ɺ�ɫ  
        g.setFont(font);// ���û�������  
        /** ���ڻ�ô�ֱ����y */  
        Rectangle clip = g.getClipBounds();  
        FontMetrics fm = g.getFontMetrics(font);  
        int ascent = fm.getAscent();  
        int descent = fm.getDescent();  
        int y = (clip.height - (ascent + descent)) / 2 + ascent;  
        int strLen = fm.stringWidth(str);
        int x = (width - strLen)/2;
        g.drawString(str, x , y);// �����ַ���  
          
        g.dispose();  
        ImageIO.write(image, "png", outFile);// ���pngͼƬ  
    }  
	
}
