package com.pack;

import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;

import com.ui.MainUi;

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
	 * @param mainUi
	 * @param message
	 */
	public static void showYes(Component mainUi,String message){
		Object[] options = { "ȷ��"};
		JOptionPane.showOptionDialog(mainUi, message, "��ʾ", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		null, options, options[0]);
	}	
}