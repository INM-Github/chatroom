package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import com.pack.Message;
import com.pack.MyObjectInputStream;
import com.pack.MyObjectOutputStream;
import com.pack.PackOper;
import com.pack.PubToolkit;
import com.pack.PubValue;
import com.qq.User;
import com.thread.ClientThread;

public class Login {

	private static JButton jbd = null;
	public JFrame jf = new JFrame("QQ");
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	public Dimension sc = toolkit.getScreenSize();
	public JTextField jcoCenter = new JTextField();
	public JPasswordField jpaCenter = new JPasswordField();
	HyperLinkFLabel registerLabel;
	HyperLinkFLabel findPasswrodLabel;
	int xOld = 0;
	int yOld = 0;

	Login() {
		BorderLayout bl = new BorderLayout();
		jf.setLayout(bl);
		jf.setUndecorated(true); // 去除窗体边框
		ImageIcon im1 = new ImageIcon("image/qqfont.png");
		Image image1 = im1.getImage();
		jf.setIconImage(image1);
		jf.setSize(548, 415);
		jf.setLocation(sc.width / 3, sc.height / 4);
		jf.add(creatPanelNorth(), BorderLayout.NORTH);
		jf.add(creatPanelWest(), BorderLayout.WEST);
		jf.add(creatPanelCenter(), BorderLayout.CENTER);
		jf.add(creatPanelSouth(), BorderLayout.SOUTH);
		jf.add(creatPanelEast(), BorderLayout.EAST);
		jf.setResizable(false); // false表示不可改变窗口大小
		jf.setVisible(true); // true显示窗体
		jf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// 记录鼠标按下时的坐标
				yOld = e.getY();
			}
		});

		jf.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				jf.setLocation(xx, yy);// 设置拖拽后，窗口的位置
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

	public JPanel creatPanelNorth() {
		ImageIcon im1 = new ImageIcon("image/QQ1.png");
		Image image1 = im1.getImage();
		JPanel PanelNorth = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 550, 230, null);

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
			}
		};
		PanelNorth.setLayout(null);
		JButton jb = new JButton();
		jb.setBounds(510, 0, 38, 32);
		jb.setOpaque(false);
		PanelNorth.add(jb);
		JButton jb2 = new JButton();
		jb2.setBounds(472, 0, 38, 32);
		jb2.setOpaque(false);
		PanelNorth.add(jb2);

		JButton jb3 = new JButton();
		jb3.setBounds(434, 0, 38, 32);
		jb3.setOpaque(false);
		PanelNorth.add(jb3);
		PanelNorth.setPreferredSize(new Dimension(0, 240));
		ActionListener North = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				if (source == jb2) {
					jf.setExtendedState(jf.ICONIFIED); // 最小化
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
		return PanelNorth;
	}

	public JPanel creatPanelWest() {
		ImageIcon im1 = new ImageIcon("image/QQ2.png");
		;
		Image image1 = im1.getImage();
		JPanel PanelWest = new JPanel();
		PanelWest.setPreferredSize(new Dimension(150, 0));
		PanelWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JPanel c1 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 100, 100, null);

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		};
		c1.setPreferredSize(new Dimension(100, 100));
		PanelWest.add(c1);
		return PanelWest;
	}

	public JPanel creatPanelCenter() {
		JPanel PanelCenter = new JPanel();
		PanelCenter.setLayout(null);
		/*
		 * 输入QQ账号的下拉框
		 */
		PanelCenter.add(jcoCenter);
		jcoCenter.setBounds(10, 8, 240, 30);
		LineBorder lin = new LineBorder(Color.WHITE, 3, true);
		jcoCenter.setBorder(lin);
		jcoCenter.setFont(new Font("Calibri ", 0, 13));
		/*
		 * 获取密码的文本框
		 */
		jpaCenter.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		jpaCenter.setBorder(lin);
		jpaCenter.setBounds(10, 45, 240, 30);
		PanelCenter.add(jpaCenter);
		/*
		 * 添加记住密码与自动登录选项
		 */
		JCheckBox jch1 = new JCheckBox("记住密码");
		jch1.setFocusPainted(false); // 选中时没有边框
		jch1.setFont(new Font("宋体", 0, 15));// 字体
		jch1.setBounds(10, 90, 100, 18);
		PanelCenter.add(jch1);
		JCheckBox jch2 = new JCheckBox("自动登录");
		jch2.setFocusPainted(false);
		jch2.setFont(new Font("宋体", 0, 15));
		jch2.setBounds(170, 90, 100, 18);
		PanelCenter.add(jch2);
		return PanelCenter;
	}

	public JPanel creatPanelSouth() {
		JPanel PanelSouth = new JPanel();
		PanelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
		PanelSouth.setPreferredSize(new Dimension(0, 55));
		ImageIcon image = new ImageIcon("image/QQ4.png");
		jbd = new JButton(image);
		jbd.setPreferredSize(new Dimension(240, 35));
		PanelSouth.add(jbd);
		ActionListener South = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(e);
			}
		};
		jbd.addActionListener(South);
		return PanelSouth;
	}

	public JPanel creatPanelEast() {
		JPanel PanelEast = new JPanel();
		PanelEast.setLayout(null);
		PanelEast.setPreferredSize(new Dimension(140, 0));
		registerLabel = new HyperLinkFLabel("注册账号", "http://localhost:8080/qqWebServer/Register.jsp");
		registerLabel.addMouseListener(registerLabel);
		registerLabel.setForeground(Color.blue);
		registerLabel.setBounds(10, 0, 80, 55);
		findPasswrodLabel = new HyperLinkFLabel("找回密码", "https://aq.qq.com/v2/uv_aq/html/reset_pwd/pc_reset_pwd_input_account.html");
		findPasswrodLabel.addMouseListener(findPasswrodLabel);
		findPasswrodLabel.setForeground(Color.blue);
		findPasswrodLabel.setBounds(10, 35, 80, 55);
		PanelEast.add(registerLabel);
		PanelEast.add(findPasswrodLabel);
		return PanelEast;
	}

	protected void login(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();
		if (source == jbd) {
			if (jcoCenter.getText() == null || jcoCenter.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "对不起,请输入用户帐号.", "提示", JOptionPane.INFORMATION_MESSAGE);

				return;
			}
			if (jpaCenter.getPassword().equals("")) {
				JOptionPane.showMessageDialog(null, "对不起,请输入用户密码.", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Socket socket = null;
			MyObjectOutputStream myObjectOut = null;
			MyObjectInputStream myObjectIn = null;
			System.out.println("userId");
			try {
				socket = new Socket("127.0.0.1", 9000);
				myObjectOut = new MyObjectOutputStream(socket.getOutputStream());
				myObjectIn = new MyObjectInputStream(socket.getInputStream());
				User user = new User();
				user.setType(PackOper.LOGIN);
				user.setId((String) jcoCenter.getText());
				System.out.println("userId:" + user.getId());
				user.setPassword(new String(jpaCenter.getPassword()));
				System.out.println("passwrod:" + user.getPassword());
				myObjectOut.writeMessage(user);
				Message message = null;
				message = myObjectIn.readMessage();
				if (message instanceof User) {
					user = (User) message;
					System.out.println(user.getType());
					if (user.getType().equals(PackOper.LOGIN_SUCCEED)) {
						jf.setVisible(false);
						// 把登录窗口关掉
						MainUi.getInstance(user);
						PubValue.setUser(user);
						// 显示主窗口,并等待加树
						ClientThread clientThread = new ClientThread(socket);
						clientThread.start();
						PubValue.setClientThread(clientThread);
						// ConfigOper.saveConfig(this.loginUI.getConfig());
						////

					} else if (user.getType().equals(PackOper.LOGIN_DEFEATED)) {
						JOptionPane.showMessageDialog(null, "账户或密码错误.", "提示", JOptionPane.INFORMATION_MESSAGE);
					} else if (user.getType().equals(PackOper.LOGIN_ONLINED)) {
						JOptionPane.showMessageDialog(null, "该账号已经登录", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// 发送信息不正确,关闭连接
					if (myObjectIn != null) {
						try {
							myObjectIn.close();
						} catch (IOException e1) {
							// e1.printStackTrace();
						}
					}
					if (myObjectOut != null) {
						try {
							myObjectOut.close();
						} catch (IOException e1) {
							// e1.printStackTrace();
						}
					}
					if (socket != null) {
						try {
							socket.close();
						} catch (IOException e1) {
							// e1.printStackTrace();
						}
					}
				}

			} catch (UnknownHostException e1) {
				// e1.printStackTrace();
				 PubToolkit.showYes(jf, "对不起,连接服务器出错!");
			} catch (IOException e1) {
				// e1.printStackTrace();
				 PubToolkit.showYes(jf, "对不起,连接服务器出错!");
			}
		}
	}
}