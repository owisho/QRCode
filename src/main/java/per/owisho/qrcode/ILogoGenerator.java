package per.owisho.qrcode;

import java.io.OutputStream;

public interface ILogoGenerator {
	
	/**
	 * 生成logo并将内容放入流中
	 * @param os 包含logo内容的流
	 */
	void generate(OutputStream os);
	
	/**
	 * 设置logo的大小
	 * @param width
	 * @param height
	 */
//	void setScale(int width,int height);
	
	/**
	 * 字符串格式的内容来源
	 * @param content
	 * @param font
	 */
//	void setSource(String content,Font font);
	
	/**
	 * 图片格式的内容来源
	 * @param in
	 */
//	void setSource(InputStream in);
	
}
