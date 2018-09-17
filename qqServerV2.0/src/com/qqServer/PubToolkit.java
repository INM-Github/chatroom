package com.qqServer;

import java.awt.Font;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * ����������
 * @author Administrator
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class PubToolkit {

	/**
	 * ȡ�õ�ǰ����
	 * 
	 * @return
	 */
	public static String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	/**
	 * ȡ�õ�ǰʱ��
	 */
	public static String getTime() {
		return (new SimpleDateFormat("HH:mm:ss")).format(new Date());
	}

	/**
	 * ȡ��������ʱ��
	 * @return
	 */
	public static String getDateTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

	
	/**
	 * ���ý��������
	 * @param font
	 */
	public static void setUIFont(Font font) {

		FontUIResource fontRes = new FontUIResource(font);
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	/**
	 * ��ʾ��Ϣ��ʾ
	 * @param information
	 */
	public static void showInformation(String information) {
		JOptionPane.showMessageDialog(null, information, "��ʾ",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * ��ʾһ��������ʾ
	 * @param error
	 */
	public static void showError(String error) {
		JOptionPane.showMessageDialog(null, error, "��ʾ",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * ��ʾһ��ǿģʽ��ѡ��Ի���
	 * @param window
	 * @param message
	 * @return
	 */
	public static boolean showYesNo(Window window, String message) {
		if (JOptionPane.showConfirmDialog(window, message, "��ʾ",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
	/**
	 * ��ʾһ��ǿģʽ�ĶԻ���
	 * @param window
	 * @param message
	 */
	public static void showYes(Window window,String message){
		Object[] options = { "ȷ��"};
		JOptionPane.showOptionDialog(window, message, "��ʾ", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		null, options, options[0]);
	}	
}