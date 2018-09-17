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
	
	private String url;//超链接地址
	
	private Boolean isMouseIn;//鼠标是否进入标签
	
	public HyperLinkFLabel(){
		super();
		initParameters();
	}
	
	/**
	 * 通过显示文本，超链接地址来构建Label
	 * 
	 * @param test
	 * 			显示文本
	 * @param url
	 * 			超链接地址
	 */
	
	public HyperLinkFLabel(String text, String url){
		super(text);
		this.url = url;
		isMouseIn = false;
		setFont(new Font("宋体",0,16));
		
	}
	
	/**
	 * 参数初始化
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
	
		// 获取操作系统的名字
				String osName = System.getProperty("os.name", "");
				if (osName.startsWith("Mac OS")) {
					// 苹果的打开方式
					Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
					Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
					openURL.invoke(null, new Object[] { url });
				} else if (osName.startsWith("Windows")) {
					// windows的打开方式。
					Runtime.getRuntime().exec(
							"rundll32 url.dll,FileProtocolHandler " + url);
				} else {
					// Unix or Linux的打开方式
					String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
							"mozilla", "netscape" };
					String browser = null;
					for (int count = 0; count < browsers.length && browser == null; count++)
						// 执行代码，在brower有值后跳出，
						// 这里是如果进程创建成功了，==0是表示正常结束。
						if (Runtime.getRuntime()
								.exec(new String[] { "which", browsers[count] })
								.waitFor() == 0)
							browser = browsers[count];
					if (browser == null)
						throw new Exception("Could not find web browser");
					else
						// 这个值在上面已经成功的得到了一个进程。
						Runtime.getRuntime().exec(new String[] { browser, url });
				
		
				}

	
	

	}

}