package per.owisho.qrcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class LogoGenerator implements ILogoGenerator{

	private int width ;
	
	private int height;
	
	private boolean isPicture;
	
	private String content;
	
	private Font font;
	
	private InputStream inputStream;
	
	public LogoGenerator(int width, int height, String content, Font font) {
		super();
		this.isPicture = false;
		this.width = width;
		this.height = height;
		this.content = content;
		this.font = font;
	}

	public LogoGenerator(int width, int height, InputStream inputStream) {
		super();
		this.isPicture = true;
		this.width = width;
		this.height = height;
		this.inputStream = inputStream;
	}

	@Override
	public void generate(OutputStream os) {
		if(isPicture){
			try {
				int len = 0;
				while((len=inputStream.read())!=-1){
					os.write(len);
				}
			} catch (Exception e) {
			} finally {
				close();
			}
		}else{
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
	        int strLen = fm.stringWidth(content);
	        int x = (width - strLen)/2;
	        g.drawString(content, x , y);// �����ַ���  
	        g.dispose();  
	        // ���pngͼƬ  
	        try {
				ImageIO.write(image, "png", os);
			} catch (IOException e) {
			}
		}
        
	}

	public void setScale(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setSource(String content, Font font) {
		this.isPicture = false;
		this.content = content;
		this.font = font;
	}

	public void setSource(InputStream in) {
		this.isPicture = true;
		this.inputStream = in;
	}

	private void close() {
		if(inputStream!=null){
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
}
