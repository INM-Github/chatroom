package com.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.tree.TreePath;

import com.pack.PubValue;
import com.qq.Group;
import com.qq.User;
import com.usertree.IconTreeNode;
import com.usertree.UserTree;

public class MainUi extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	public Dimension sc = toolkit.getScreenSize();
	private User user;
	private UserTree userTree;
	private UserTree groupTree;
	private JScrollPane userTreeJSP;
	private MessageUI messageUI;
	private static MainUi mainUI;
	private JButton sysMessageBtn = new JButton("ϵͳ��Ϣ");
	private JLabel name = new JLabel();
	private JPanel PanelFour;
	CardLayout card=new CardLayout();
	int xOld = 0;
	int yOld = 0;
	public void setName(String name) {
		this.name.setText(name);
	}
	public static MainUi getInstance() {
		if (mainUI == null) {
			mainUI = new MainUi();
		}
		return mainUI;
	}
	public static MainUi getInstance(User user) {
		if (mainUI == null) {
			mainUI = new MainUi(user);
		}
		return mainUI;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.name.setText(user.getName());
		
	}
	private MainUi() {
		// TODO Auto-generated constructor stub
		super("QQ");
		setLayout(null);
		ImageIcon im1 = new ImageIcon("image/qqfont.png");
		Image image1 = im1.getImage();
		setIconImage(image1);
		setSize(384, 720);
		setLocation(sc.width / 3, sc.height / 4);
		setUndecorated(true);
		addBackgroundImage(this);
		add(creatPanelOne());
		add(creatPanelTwo());
		add(creatPanelThree());
		add(creatPanelFour());
		setResizable(false); // false��ʾ���ɸı䴰�ڴ�С
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// ��¼��갴��ʱ������
				yOld = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				setLocation(xx, yy);// ������ק�󣬴��ڵ�λ��
			}
		});
	}
	private MainUi(User user1) {
		// TODO Auto-generated constructor stub
		super("QQ");
		setUser(user1);
		setLayout(null);
		ImageIcon im1 = new ImageIcon("image/qqfont.png");
		Image image1 = im1.getImage();
		setIconImage(image1);
		setSize(384, 720);
		setLocation(sc.width / 3, sc.height / 4);
		setUndecorated(true);
		addBackgroundImage(this);
		add(creatPanelOne());
		add(creatPanelTwo());
		add(creatPanelThree());
		add(creatPanelFour());
		setResizable(false); // false��ʾ���ɸı䴰�ڴ�С
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// ��¼��갴��ʱ������
				yOld = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				setLocation(xx, yy);// ������ק�󣬴��ڵ�λ��
			}
		});
	}

	public void addBackgroundImage(JFrame jf) {

		ImageIcon image = new ImageIcon("image/QQ7.jpg");
		JLabel background = new JLabel(image);
		// ���ñ�ǩ��ʾ��λ�úʹ�С
		background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		// ����ǩ��ӵ�����ĵڶ��������
		jf.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		// ��ȡ����ĵ�һ������
		JPanel contentPanel = (JPanel) jf.getContentPane();
		// ���õ�һ�����Ϊ͸��
		contentPanel.setOpaque(false);

	}

	public JPanel creatPanelOne() {
		
		ImageIcon im1 = new ImageIcon("image/QQ9.PNG");
		Image image1 = im1.getImage();
		JPanel PanelOne = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 390, 45, null);

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
			}
		};
		PanelOne.setLayout(null);
		JButton jb = new JButton();
		jb.setBounds(348, 0, 38, 32);
		jb.setOpaque(false);
		PanelOne.add(jb);
		JButton jb2 = new JButton();
		jb2.setBounds(308, 0, 35, 32);
		jb2.setOpaque(false);
		PanelOne.add(jb2);
		JButton jb3 = new JButton();
		jb3.setBounds(268, 0, 35, 32);
		jb3.setOpaque(false);
		PanelOne.add(jb3);
		PanelOne.setBounds(0, 0, 390, 50);
		ActionListener North = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				if (source == jb2) {
					mainUI.setExtendedState(Frame.ICONIFIED); // ��С��
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
		return PanelOne;
	}

	public JPanel creatPanelTwo() {
		ImageIcon im1 =GetImage.getBigHead(user.getIconId());
		Image image1 = im1.getImage();
		JPanel PanelTwo = new JPanel();
		PanelTwo.setLayout(null);
		PanelTwo.setOpaque(false);
		/*
		 * �����ʾͷ�������
		 */
		JPanel c1 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 85, 85, null);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		};
		c1.setBounds(10, 0, 85, 85);
		PanelTwo.add(c1);
		/*
		 * �����ʾ�����ı�ǩ
		 */
		name.setFont(new Font("����", 0, 20));
		name.setBounds(100, 0, 140, 55);
		PanelTwo.add(name);
		/*
		 * �����ʾ����ǩ���ı�ǩ
		 */
		JLabel jl2 = new JLabel(" ");
		jl2.setFont(new Font("����", 0, 16));
		jl2.setBounds(100, 30, 200, 50);
		PanelTwo.add(jl2);
		PanelTwo.setBounds(0, 50, 390, 100);
		return PanelTwo;
	}

	public JPanel creatPanelThree() {
		ImageIcon im1 = new ImageIcon("image/QQ11.PNG");
		Image image1 = im1.getImage();
		JPanel PanelThree = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g) {

				try {
					g.drawImage(image1, 0, 0, 385, 45, null);

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
			}
		};
		PanelThree.setLayout(null);
		/*
		 * ��Ϣ
		 */
		JButton jb2 = new JButton();
		jb2.setBounds(0, 0, 128, 45);
		jb2.setOpaque(false);
		PanelThree.add(jb2);
		JButton jb = new JButton();
		jb.setBounds(128, 0, 128, 45);
		jb.setOpaque(false);
		PanelThree.add(jb);
		JButton jb3 = new JButton();
		jb3.setBounds(256, 0, 128, 45);
		jb3.setOpaque(false);
		ActionListener Three = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				if (source == jb) {
					card.show(PanelFour,"Ⱥ��");
				} else if (source == jb2) {
					card.show(PanelFour,"��ϵ��");
				} else if (source == jb3) {
					
				}
			}
		};
		jb.addActionListener(Three);
		jb2.addActionListener(Three);
		jb3.addActionListener(Three);
		PanelThree.add(jb3);
		PanelThree.setBounds(0, 140, 384, 50);
		
		return PanelThree;
	}
	public JPanel creatPanelFour() {
		PanelFour = new JPanel();
		PanelFour.setLayout(card);
		PanelFour.add("��ϵ��",Four_One());
		PanelFour.add("Ⱥ��",Four_Two());
		PanelFour.setBounds(0, 190, 384, 480);
		return PanelFour;
	}

	public JPanel Four_One() {
		JPanel Four_One = new JPanel();
		Four_One.setLayout(new BorderLayout());
		this.userTree = new UserTree(new User());
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BorderLayout());
		Four_One.add(btnPanel, BorderLayout.NORTH);
		userTreeJSP = new JScrollPane(userTree);
		Four_One.add(userTreeJSP, BorderLayout.CENTER);
		LineBorder lin = new LineBorder(Color.WHITE, 3, true);
		userTree.putClientProperty("JTree.lineStyle", "Horizontal");
		// ����Ĵ�С����ʽ
		userTree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 18));
		// ���ڵ�ĸ߶�
		userTree.setRowHeight(45);
		// ����չ���ڵ�֮ǰ����굥����Ϊ1
		userTree.setToggleClickCount(1); 
		// ����һ������ѡ��һ���ڵ�
		userTreeJSP.setBorder(lin);
		Four_One.setBorder(lin);
		userTree.addMouseListener(new MainUIUserTreeListener(this));
		return Four_One;
	}
	public JPanel Four_Two() {
		JPanel Four_Two = new JPanel();
		Four_Two.setLayout(new BorderLayout());
		this.groupTree = new UserTree(new Group());
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BorderLayout());
		Four_Two.add(btnPanel, BorderLayout.NORTH);
		userTreeJSP = new JScrollPane(groupTree);
		Four_Two.add(userTreeJSP, BorderLayout.CENTER);
		LineBorder lin = new LineBorder(Color.WHITE, 3, true);
		groupTree.putClientProperty("JTree.lineStyle", "Horizontal");
		// ����Ĵ�С����ʽ
		groupTree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 18));
		// ���ڵ�ĸ߶�
		groupTree.setRowHeight(45);
		// ����չ���ڵ�֮ǰ����굥����Ϊ1
		groupTree.setToggleClickCount(1); 
		// ����һ������ѡ��һ���ڵ�
		userTreeJSP.setBorder(lin);
		groupTree.addMouseListener(new MainUIGroupTreeListener(this));
		return Four_Two;
	}
	/**
	 * ȡ���û���
	 * @return
	 */
	public UserTree getUserTree() {
		return this.userTree;
	}
	/**
	 * ȡ��Ⱥ����
	 * @return
	 */
	public UserTree getGroupTree() {
		return this.groupTree;
	}

	/**
	 * ���ϵͳ��Ϣ
	 * @param msg
	 */
	public void addMessage(String msg) {
		this.messageUI.addMessage(msg);
		sysMessageBtn.setText(  "ϵͳ��Ϣ��(��������Ϣ)");
	}

	/**
	 * ��ʾϵͳ��Ϣ����
	 *
	 */
	public void showMessage() {
		sysMessageBtn.setText("ϵͳ��Ϣ");
		this.messageUI.setVisible(true);
	}
	/**
	 * ����û���������
	 * @param mouseListener
	 */
	public void addUserTreeMouseListener(MouseListener mouseListener) {
		userTree.addMouseListener(mouseListener);
		groupTree.addMouseListener(mouseListener);
	}

}

/**
 * �������ڵ�Ա�����Ĳ���
 * 
 * @author Administrator
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
class MainUIUserTreeListener extends MouseAdapter {

	private MainUi mainUI;

	public MainUIUserTreeListener(MainUi mainUI) {
		this.mainUI = mainUI;
	}

	public void mousePressed(MouseEvent e) {
		// ���˫�����ڵ�
		if (e.getClickCount() == 2) {
			TreePath treePath = mainUI.getUserTree().getClosestPathForLocation(e.getX(), e.getY());
			if (treePath != null) {
				IconTreeNode itn = (IconTreeNode) treePath.getLastPathComponent();
				User user = (User) itn.getUser();
				if (itn.isLeaf()) {
					if (user != null) {
						ChatUI chatUI = PubValue.getChatUIForObject(user);
						if (chatUI == null) {
							chatUI = new ChatUI(user);
							PubValue.addChatUI(chatUI);
						}
						chatUI.setLocationRelativeTo(null);
						chatUI.setVisible(true);
						chatUI.requestFocus();
						chatUI.toFront();
					}
				}
			}
		}

	}
}
class MainUIGroupTreeListener extends MouseAdapter {

	private MainUi mainUI;

	public MainUIGroupTreeListener(MainUi mainUI) {
		this.mainUI = mainUI;
	}

	public void mousePressed(MouseEvent e) {
		// ���˫�����ڵ�
		if (e.getClickCount() == 2) {
			TreePath treePath = mainUI.getGroupTree().getClosestPathForLocation(e.getX(), e.getY());
			if (treePath != null) {
				IconTreeNode itn = (IconTreeNode) treePath.getLastPathComponent();
				Group group = (Group) itn.getGroup();
				if (itn.isLeaf()) {
					if (group != null) {
						ChatUI chatUI = PubValue.getChatUIForObject(group);
						if (chatUI == null) {
							chatUI = new ChatUI(group);
							PubValue.addChatUI(chatUI);
						}
						chatUI.setLocationRelativeTo(null);
						chatUI.setVisible(true);
						chatUI.requestFocus();
						chatUI.toFront();
					}
				}
			}
		}

	}
}




