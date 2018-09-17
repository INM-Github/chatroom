package com.ui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;

import javax.swing.JLabel;

public class HyperLinkFLabel extends JLabel implements MouseListener{
	
	private static final long serialVersionUID = -2654237969531133307L;
	
	private String url;//�����ӵ�ַ
	
	private Boolean isMouseIn;//����Ƿ�����ǩ
	
	public HyperLinkFLabel(){
		super();
		initParameters();
	}
	
	/**
	 * ͨ����ʾ�ı��������ӵ�ַ������Label
	 * 
	 * @param test
	 * 			��ʾ�ı�
	 * @param url
	 * 			�����ӵ�ַ
	 */
	
	public HyperLinkFLabel(String text, String url){
		super(text);
		this.url = url;
		isMouseIn = false;
		setFont(new Font("����",0,16));
		
	}
	
	/**
	 * ������ʼ��
	 */
	private void initParameters(){
		url = "";
		isMouseIn = false;
	}
	
	public void paint(Graphics g){
		if(isMouseIn){
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			g.drawLine(0, getHeight()-15, getWidth()-12, getHeight()-15);		
		}else{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		super.paint(g);
	}
	
	public void mouseClicked(MouseEvent e){
		
		if(url.trim().length()==0){
			return;	
		}
		try {
			open(url);
		} catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	
	public void mouseEntered(MouseEvent e){
		isMouseIn = true;
		this.repaint();
	}
	
	public void mouseExited(MouseEvent e){
		isMouseIn = false;
		this.repaint();
	}
	
	public void mousePressed(MouseEvent e){
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	
	private static void open(String url) throws Exception {
	
		// ��ȡ����ϵͳ������
				String osName = System.getProperty("os.name", "");
				if (osName.startsWith("Mac OS")) {
					// ƻ���Ĵ򿪷�ʽ
					Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
					Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
					openURL.invoke(null, new Object[] { url });
				} else if (osName.startsWith("Windows")) {
					// windows�Ĵ򿪷�ʽ��
					Runtime.getRuntime().exec(
							"rundll32 url.dll,FileProtocolHandler " + url);
				} else {
					// Unix or Linux�Ĵ򿪷�ʽ
					String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
							"mozilla", "netscape" };
					String browser = null;
					for (int count = 0; count < browsers.length && browser == null; count++)
						// ִ�д��룬��brower��ֵ��������
						// ������������̴����ɹ��ˣ�==0�Ǳ�ʾ����������
						if (Runtime.getRuntime()
								.exec(new String[] { "which", browsers[count] })
								.waitFor() == 0)
							browser = browsers[count];
					if (browser == null)
						throw new Exception("Could not find web browser");
					else
						// ���ֵ�������Ѿ��ɹ��ĵõ���һ�����̡�
						Runtime.getRuntime().exec(new String[] { browser, url });
				
		
				}

	
	

	}

}