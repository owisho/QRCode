package per.owisho.qrcode;

import java.io.OutputStream;

public interface ILogoGenerator {
	
	/**
	 * ����logo�������ݷ�������
	 * @param os ����logo���ݵ���
	 */
	void generate(OutputStream os);
	
	/**
	 * ����logo�Ĵ�С
	 * @param width
	 * @param height
	 */
//	void setScale(int width,int height);
	
	/**
	 * �ַ�����ʽ��������Դ
	 * @param content
	 * @param font
	 */
//	void setSource(String content,Font font);
	
	/**
	 * ͼƬ��ʽ��������Դ
	 * @param in
	 */
//	void setSource(InputStream in);
	
}
