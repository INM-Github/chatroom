package com.ui;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author Administrator
 * 
 * ר�Ż�ȡͼƬ�Ĺ�����
 */
public class GetImage {
	/**
	 * ����Сͷ��ͼƬ,���ݱ��
	 * @param iconId
	 * @return
	 */
	public static ImageIcon getMinHead(int iconId){
		return new ImageIcon("head/" + iconId + ".jpg");
	}
	
	/**
	 * ���ش�ͷ��ͼƬ,���ݱ��
	 * @param iconId
	 * @return
	 */
	public static ImageIcon getBigHead(int iconId){
		return new ImageIcon("head/" + iconId + ".png");
	}	
}