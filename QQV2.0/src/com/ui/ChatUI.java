package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.pack.MessagePack;
import com.pack.PackOper;
import com.pack.PubToolkit;
import com.pack.PubValue;
import com.qq.Group;
import com.qq.User;

public class ChatUI extends JFrame implements ActionListener {

	/**
	 * <code>serialVersionUID</code> 的注释
	 */
	private static final long serialVersionUID = 1L;
	private static String messCompart = "\u0009";
	private JTextPane chatMessage = new JTextPane();

	private JTextPane sendMessage = new JTextPane();
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension sc = toolkit.getScreenSize();
	private User user;
	private Group group;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private JLabel sn;
	private JButton sendBtn;
	public ChatUI(User user) {
		this.setUser(user);
		setLayout(null);
		ImageIcon im1 =GetImage.getBigHead(user.getIconId());
		Image image1 = im1.getImage();
		setIconImage(image1);
		setSize(1024,768);    
		setLocation(sc.width/3,sc.height/4);    
		addBackgroundImage(this);
		add(creatPanelOneName());
		add(creatPanelOne());
		add(creatPanelTwo());
		add(creatPanelThree());
		add(creatPanelFour());
//		setResizable(false);           //false表示不可改变窗口大小
		setVisible(true);     
	}
	public ChatUI(Group group) {
		this.setGroup(group);
		setLayout(null);
		ImageIcon im1 = new ImageIcon("head/group1.jpg");
		Image image1 = im1.getImage();
		setIconImage(image1);
		setSize((int)(sc.width/1.86),sc.height*20/27);    
		setLocation(sc.width/3,sc.height/4);    
		addBackgroundImage(this);
		add(creatPanelOneName());
		add(creatPanelOne());
		add(creatPanelTwo());
		add(creatPanelThree());
		add(creatPanelFour());
		setResizable(false);           //false表示不可改变窗口大小
		setVisible(true);     
	}
	
	public void addBackgroundImage(JFrame jf){  
        
	    ImageIcon image = new ImageIcon("image/QQ18.png");  
	    JLabel background = new JLabel(image);   
	    //设置标签显示的位置和大小  
	    background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());  
	    //将标签添加到窗体的第二层面板上  
	    jf.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));  
	    //获取窗体的第一层板对象  
	    JPanel contentPanel = (JPanel)jf.getContentPane();  
	    //设置第一层面板为透明  
	    contentPanel.setOpaque(false);  
	      
	}  
	public JPanel creatPanelOneName() {
		JPanel PanelOneName = new JPanel();
			PanelOneName.setLayout(null);  
			PanelOneName.setOpaque(false);
	        sn = new JLabel();
	        if(user == null) {
	        	sn.setText(group.getName());
	        }
	        if(group == null) {
	        	sn.setText(user.getName());
	        }
	        sn.setFont(new Font("黑体",0,20));
			sn.setBounds(430, 5, 140, 40);
			PanelOneName.add(sn);
			PanelOneName.setBounds(0, 0, 606, 50);
			return PanelOneName;
	}
	public JPanel creatPanelOne() {
		ImageIcon im1 = new ImageIcon("image/QQ21.png");
		Image image1 = im1.getImage();
		JPanel PanelOne = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 420, 50, null);

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
			}
		};
			PanelOne.setLayout(null);  
			JButton jb = new JButton();  
			jb.setBounds(988, 0, 45, 40);  //关闭按钮
			PanelOne.add(jb);
			JButton jb2 = new JButton();    // 最小化按钮
			jb2.setBounds(946, 0, 40, 40);
			jb2.setOpaque(false);
			PanelOne.add(jb2);
	        JButton jb3 = new JButton();  
	        jb3.setBounds(906, 0, 40, 40);   
	        jb3.setOpaque(false);
	        PanelOne.add(jb3);
	        JButton jb4 = new JButton();      
	        jb4.setBounds(866, 0, 40, 40);
	        jb4.setOpaque(false);
	        PanelOne.add(jb4);
		    ActionListener North = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton source = (JButton) e.getSource();
					if (source == jb2) {
						setExtendedState(JFrame.ICONIFIED); // 最小化
					} else if (source == jb) {
						System.exit(0);
					} else if (source == jb3) {
						// 设置按钮

					}
				}
			};
			jb.addActionListener(North);
			jb2.addActionListener(North);
			jb3.addActionListener(North);
			PanelOne.setBounds(606, 0, 420, 50);
			return PanelOne;
	}
	public JPanel creatPanelTwo() {
		JPanel PanelTwo = new JPanel();
		JScrollPane jsc =new JScrollPane(sendMessage);
		sendMessage.setFont(new Font("宋体",Font.BOLD,20));
		LineBorder lin = new LineBorder(Color.WHITE,1,true);
        jsc.setBorder(lin);
		jsc.setPreferredSize(new Dimension(852, 62));
		jsc.setLocation(0, 0);
		jsc.setOpaque(true);
		jsc.getViewport().setOpaque(false);  
		PanelTwo.add(jsc);
		PanelTwo.setBounds(0, 650, 852, 62);
		PanelTwo.setOpaque(false);
//		PanelTwo.setBounds(0, 110, 852, 500);
		return PanelTwo;
	}
	public JPanel creatPanelThree() {
		JPanel PanelThree = new JPanel();
		chatMessage.setEditable(false);
		JScrollPane jsc =new JScrollPane(chatMessage);
		chatMessage.setFont(new Font("宋体",Font.BOLD,20));
		LineBorder lin = new LineBorder(Color.WHITE,1,true);
        jsc.setBorder(lin);
		jsc.setPreferredSize(new Dimension(852, 510));
		jsc.setLocation(0, 0);
		jsc.setOpaque(true);
		jsc.getViewport().setOpaque(false);  
		PanelThree.add(jsc);
		PanelThree.setBounds(0, 110, 852, 500);
		PanelThree.setOpaque(false);
		return PanelThree;
	}
	public JPanel creatPanelFour() {
		ImageIcon im1 = new ImageIcon("image/QQ22.jpg");
		Image image1 = im1.getImage();
		JPanel PanelFour = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 1026, 60, null);

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
			}
		};
		PanelFour.setLayout(null);
		sendBtn = new JButton();
	    JButton closeBtn = new JButton();
	    sendBtn.setBounds(725, 8, 112, 35);
	    closeBtn.setBounds(611, 8, 100, 35);
	    sendBtn.setOpaque(false);
	    closeBtn.setOpaque(false);
	    ActionListener Four = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				 if (source == closeBtn) {
					setVisible(false);
				} 
			}
		};
		sendBtn.addActionListener(this);
		closeBtn.addActionListener(Four);
	    PanelFour.add(sendBtn);
	    PanelFour.add(closeBtn);
		PanelFour.setBounds(0, 710, 1026, 62);
		PanelFour.setOpaque(true);
		return PanelFour;
	}

	/**
	 * 添加消息
	 * @param msgPack
	 */
	public void addMessage(MessagePack msgPack) {
		String nowDateTime = PubToolkit.getDateTime();
		User fromUser;
		if(msgPack.getFrom()==MainUi.getInstance().getUser().getId()) {
			fromUser =MainUi.getInstance().getUser();
		}else {
			fromUser = MainUi.getInstance().getUserTree().findUser(msgPack.getFrom());
		}
		this.chatMessage.setEditable(true);
		Document chatDocument = chatMessage.getDocument();
		try {
			//插入消息进聊天面板
			chatDocument.insertString(chatDocument.getLength(), fromUser
					.getName()
					+ "("
					+ msgPack.getFrom()
					+ ")  "
					+ nowDateTime + "\n", null);
			unbindMessage(msgPack.getMessage());
			this.chatMessage.setEditable(false);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对消息进行解包
	 * @param str
	 */
	private void unbindMessage(String str) {
		Document chatDocument = chatMessage.getDocument();
		String[] mess = str.split(messCompart);
		int start = 0;
		StyledDocument styledDocument = this.chatMessage.getStyledDocument();
		try {
			styledDocument.insertString(chatDocument.getLength(), "  ", null);
		} catch (BadLocationException e1) {
			//e1.printStackTrace();
		}
		for (int i = 0; i < mess.length; i++) {
			this.chatMessage.setCaretPosition(styledDocument.getLength());
			if (mess[i].indexOf("[MESSAGE=") >= 0) {
				start = mess[i].indexOf("[MESSAGE=") + "[MESSAGE=".length();
				try {
					styledDocument.insertString(chatDocument.getLength(),
							mess[i].substring(start, mess[i]
									.indexOf("]", start)), null);
				} catch (BadLocationException e) {
					//e.printStackTrace();
				}
			} 
		}
	}

	/**
	 * 取得要发送的消息
	 * 
	 * @return
	 */
	public String getMessage() {		
		StringBuffer strBuf = new StringBuffer();
		this.getContent(this.sendMessage.getDocument().getDefaultRootElement(),
				strBuf);
		this.sendMessage.setText("");
		return strBuf.toString();
		
	}

	/**
	 * 拼装要发送的信息
	 * 
	 * @param e
	 * @param sb
	 */
	private void getContent(Element e, StringBuffer sb) {
		if (!e.isLeaf()) {
			for (int i = 0; i < e.getElementCount(); i++) {
				getContent(e.getElement(i), sb);
			}
		} else {
			if (e.getName().equals("content")) {
				int start = e.getStartOffset();
				int end = e.getEndOffset();
				String s;
				try {
					s = e.getDocument().getText(start, end - start);
					sb.append("[MESSAGE=" + s + "]" + messCompart);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			} 

		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();
		if (source == sendBtn) {
			MessagePack msgPack = new MessagePack();
			User user = getUser();
			msgPack.setMessage(getMessage());
			if(user != null) {
			msgPack.setFrom(MainUi.getInstance().getUser().getId());
			msgPack.setType(PackOper.CHAT_USER);
			msgPack.setTo(user.getId());
			System.out.println("---------------Chat---------------");
			System.out.println("Type: " + msgPack.getType());
			System.out.println("from :" + msgPack.getFrom());
			System.out.println("to :" + msgPack.getTo());
			System.out.println("message: " + msgPack.getMessage());
			System.out.println("---------------over---------------");
			msgPack.setFrom(PubValue.getUser().getId());
			}else {
				msgPack.setFrom(MainUi.getInstance().getUser().getId());
				msgPack.setType(PackOper.CHAT_GROUP);
				msgPack.setTo(group.getNo());
			}
			addMessage(msgPack);
			msgPack.setFrom(PubValue.getUser().getId());
			PubValue.getClientThread().sendMessage(msgPack);
		}
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}


}
