package com.ui;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author Administrator
 * 
 * 专门获取图片的工具类
 */
public class GetImage {
	/**
	 * 返回小头像图片,根据编号
	 * @param iconId
	 * @return
	 */
	public static ImageIcon getMinHead(int iconId){
		return new ImageIcon("head/" + iconId + ".jpg");
	}
	
	/**
	 * 返回大头像图片,根据编号
	 * @param iconId
	 * @return
	 */
	public static ImageIcon getBigHead(int iconId){
		return new ImageIcon("head/" + iconId + ".png");
	}	
}