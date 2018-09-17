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
		jf.setUndecorated(true); // ȥ������߿�
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
		jf.setResizable(false); // false��ʾ���ɸı䴰�ڴ�С
		jf.setVisible(true); // true��ʾ����
		jf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// ��¼��갴��ʱ������
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
				jf.setLocation(xx, yy);// ������ק�󣬴��ڵ�λ��
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
					jf.setExtendedState(jf.ICONIFIED); // ��С��
				} else if (source == jb) {
					System.exit(0);
				} else if (source == jb3) {
					// ���ð�ť

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
		 * ����QQ�˺ŵ�������
		 */
		PanelCenter.add(jcoCenter);
		jcoCenter.setBounds(10, 8, 240, 30);
		LineBorder lin = new LineBorder(Color.WHITE, 3, true);
		jcoCenter.setBorder(lin);
		jcoCenter.setFont(new Font("Calibri ", 0, 13));
		/*
		 * ��ȡ������ı���
		 */
		jpaCenter.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		jpaCenter.setBorder(lin);
		jpaCenter.setBounds(10, 45, 240, 30);
		PanelCenter.add(jpaCenter);
		/*
		 * ��Ӽ�ס�������Զ���¼ѡ��
		 */
		JCheckBox jch1 = new JCheckBox("��ס����");
		jch1.setFocusPainted(false); // ѡ��ʱû�б߿�
		jch1.setFont(new Font("����", 0, 15));// ����
		jch1.setBounds(10, 90, 100, 18);
		PanelCenter.add(jch1);
		JCheckBox jch2 = new JCheckBox("�Զ���¼");
		jch2.setFocusPainted(false);
		jch2.setFont(new Font("����", 0, 15));
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
		registerLabel = new HyperLinkFLabel("ע���˺�", "http://localhost:8080/qqWebServer/Register.jsp");
		registerLabel.addMouseListener(registerLabel);
		registerLabel.setForeground(Color.blue);
		registerLabel.setBounds(10, 0, 80, 55);
		findPasswrodLabel = new HyperLinkFLabel("�һ�����", "https://aq.qq.com/v2/uv_aq/html/reset_pwd/pc_reset_pwd_input_account.html");
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
				JOptionPane.showMessageDialog(null, "�Բ���,�������û��ʺ�.", "��ʾ", JOptionPane.INFORMATION_MESSAGE);

				return;
			}
			if (jpaCenter.getPassword().equals("")) {
				JOptionPane.showMessageDialog(null, "�Բ���,�������û�����.", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
						// �ѵ�¼���ڹص�
						MainUi.getInstance(user);
						PubValue.setUser(user);
						// ��ʾ������,���ȴ�����
						ClientThread clientThread = new ClientThread(socket);
						clientThread.start();
						PubValue.setClientThread(clientThread);
						// ConfigOper.saveConfig(this.loginUI.getConfig());
						////

					} else if (user.getType().equals(PackOper.LOGIN_DEFEATED)) {
						JOptionPane.showMessageDialog(null, "�˻����������.", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					} else if (user.getType().equals(PackOper.LOGIN_ONLINED)) {
						JOptionPane.showMessageDialog(null, "���˺��Ѿ���¼", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// ������Ϣ����ȷ,�ر�����
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
				 PubToolkit.showYes(jf, "�Բ���,���ӷ���������!");
			} catch (IOException e1) {
				// e1.printStackTrace();
				 PubToolkit.showYes(jf, "�Բ���,���ӷ���������!");
			}
		}
	}
}