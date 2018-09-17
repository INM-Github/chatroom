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
 * 公共工具类
 * @author Administrator
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class PubToolkit {

	/**
	 * 取得当前日期
	 * 
	 * @return
	 */
	public static String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	/**
	 * 取得当前时间
	 */
	public static String getTime() {
		return (new SimpleDateFormat("HH:mm:ss")).format(new Date());
	}

	/**
	 * 取得日期与时间
	 * @return
	 */
	public static String getDateTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

	
	/**
	 * 设置界面的字体
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
	 * 显示信息提示
	 * @param information
	 */
	public static void showInformation(String information) {
		JOptionPane.showMessageDialog(null, information, "提示",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 显示一个警告提示
	 * @param error
	 */
	public static void showError(String error) {
		JOptionPane.showMessageDialog(null, error, "提示",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 显示一个强模式的选择对话框
	 * @param window
	 * @param message
	 * @return
	 */
	public static boolean showYesNo(Window window, String message) {
		if (JOptionPane.showConfirmDialog(window, message, "提示",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
	/**
	 * 显示一个强模式的对话框
	 * @param window
	 * @param message
	 */
	public static void showYes(Window window,String message){
		Object[] options = { "确定"};
		JOptionPane.showOptionDialog(window, message, "提示", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		null, options, options[0]);
	}	
}