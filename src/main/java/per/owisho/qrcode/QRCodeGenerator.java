package per.owisho.qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.junit.Assert;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

	/**
	 * 创建一个二维码（此二维码存储了地址信息以及logo图标）
	 * @param data
	 * @param width
	 * @param height
	 * @param os
	 * @param logoWidth
	 * @param logoHeight
	 * @param logoGenerator
	 */
	public static void createQRCode(String data,int width,int height,OutputStream os,int logoWidth,int logoHeight,ILogoGenerator logoGenerator){
		
		Assert.assertTrue("param data cannot empty.", data != null && data.trim().length() > 0);
		Assert.assertTrue("param width and height must gt 0.", width > 0 && height > 0);
		MultiFormatWriter formatWriter = new MultiFormatWriter();
		Hashtable<EncodeHintType, Object> param = new Hashtable<EncodeHintType, Object>();
		param.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
		param.put(EncodeHintType.CHARACTER_SET, "utf-8");
		param.put(EncodeHintType.MARGIN, 0);
		
		try {
			BitMatrix bitMatrix = formatWriter.encode(data, BarcodeFormat.QR_CODE, width, height, param);
			// 1.1去白边
			int[] rec = bitMatrix.getEnclosingRectangle();
			int resWidth = rec[2] + 1;
			int resHeight = rec[3] + 1;
			BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
			resMatrix.clear();
			for (int i = 0; i < resWidth; i++) {
				for (int j = 0; j < resHeight; j++) {
					if (bitMatrix.get(i + rec[0], j + rec[1])) {
						resMatrix.set(i, j);
					}
				}
			}
			int width1 = resMatrix.getWidth();
			int height1 = resMatrix.getHeight();
			BufferedImage qrcode = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width1; x++) {
				for (int y = 0; y < height1; y++) {
					qrcode.setRGB(x, y, resMatrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
				}
			}
			// 添加logo图片
			if(logoGenerator!=null){
				insertImage(width1, height1, qrcode, logoWidth, logoHeight, logoGenerator, true);
			}
			ImageIO.write(qrcode, "png", os);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
		
	} 
	
	private static void insertImage(int QRCodeWidth,int QRCodeHeight, BufferedImage source,int logoWidth,int logoHeight, ILogoGenerator logoGenerator, boolean needCompress)
			throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		logoGenerator.generate(os);
		Image src = ImageIO.read(new ByteArrayInputStream(os.toByteArray()));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > logoWidth) {
				width = logoWidth;
			}
			if (height > logoHeight) {
				height = logoHeight;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCodeWidth - width) / 2;
		int y = (QRCodeHeight - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		// Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		// graph.draw(shape); 去除图片白边
		graph.dispose();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\府前.png"));
//		Font font = new Font("宋体",Font.BOLD,190);
//		ILogoGenerator logoGenerator = new LogoGenerator(600, 600, "府前", font);
		ILogoGenerator logoGenerator = new LogoGeneratorWithPicBackground();
		createQRCode("http://www.baidu.com", 900, 900, os, 270, 270, logoGenerator);
	}
	
}
