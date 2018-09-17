package com.Serverimage;

import java.net.URL;
import javax.swing.ImageIcon;
/**
 * ��ȡͼƬ������
 * @author Administrator
 *
 */
public class GetImage {

	public static ImageIcon getImage(String fileName) {
		URL url = GetImage.class.getResource(fileName);
		if (url != null)
			return new ImageIcon(url);
		return null;
	}

	public static ImageIcon getSkinImage(String fileName) {
		return GetImage.getImage(fileName);
	}

	public static ImageIcon getMinHead(int iconId) {
		return GetImage.getImage("head/" + iconId + "_m.gif");
	}

	public static ImageIcon getBigHead(int iconId) {
		return GetImage.getImage("head/" + iconId + ".gif");
	}
}
