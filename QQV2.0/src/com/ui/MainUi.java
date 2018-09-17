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
	private JButton sysMessageBtn = new JButton("系统消息");
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
		setResizable(false); // false表示不可改变窗口大小
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// 记录鼠标按下时的坐标
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
				setLocation(xx, yy);// 设置拖拽后，窗口的位置
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
		setResizable(false); // false表示不可改变窗口大小
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();// 记录鼠标按下时的坐标
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
				setLocation(xx, yy);// 设置拖拽后，窗口的位置
			}
		});
	}

	public void addBackgroundImage(JFrame jf) {

		ImageIcon image = new ImageIcon("image/QQ7.jpg");
		JLabel background = new JLabel(image);
		// 设置标签显示的位置和大小
		background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		// 将标签添加到窗体的第二层面板上
		jf.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		// 获取窗体的第一层板对象
		JPanel contentPanel = (JPanel) jf.getContentPane();
		// 设置第一层面板为透明
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
					mainUI.setExtendedState(Frame.ICONIFIED); // 最小化
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
		return PanelOne;
	}

	public JPanel creatPanelTwo() {
		ImageIcon im1 =GetImage.getBigHead(user.getIconId());
		Image image1 = im1.getImage();
		JPanel PanelTwo = new JPanel();
		PanelTwo.setLayout(null);
		PanelTwo.setOpaque(false);
		/*
		 * 添加显示头像的容器
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
		 * 添加显示网名的标签
		 */
		name.setFont(new Font("黑体", 0, 20));
		name.setBounds(100, 0, 140, 55);
		PanelTwo.add(name);
		/*
		 * 添加显示个性签名的标签
		 */
		JLabel jl2 = new JLabel(" ");
		jl2.setFont(new Font("黑体", 0, 16));
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
		 * 消息
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
					card.show(PanelFour,"群组");
				} else if (source == jb2) {
					card.show(PanelFour,"联系人");
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
		PanelFour.add("联系人",Four_One());
		PanelFour.add("群组",Four_Two());
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
		// 字体的大小，样式
		userTree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 18));
		// 树节点的高度
		userTree.setRowHeight(45);
		// 设置展开节点之前的鼠标单击数为1
		userTree.setToggleClickCount(1); 
		// 设置一次自能选中一个节点
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
		// 字体的大小，样式
		groupTree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 18));
		// 树节点的高度
		groupTree.setRowHeight(45);
		// 设置展开节点之前的鼠标单击数为1
		groupTree.setToggleClickCount(1); 
		// 设置一次自能选中一个节点
		userTreeJSP.setBorder(lin);
		groupTree.addMouseListener(new MainUIGroupTreeListener(this));
		return Four_Two;
	}
	/**
	 * 取得用户树
	 * @return
	 */
	public UserTree getUserTree() {
		return this.userTree;
	}
	/**
	 * 取得群聊树
	 * @return
	 */
	public UserTree getGroupTree() {
		return this.groupTree;
	}

	/**
	 * 添加系统消息
	 * @param msg
	 */
	public void addMessage(String msg) {
		this.messageUI.addMessage(msg);
		sysMessageBtn.setText(  "系统消息：(你有新消息)");
	}

	/**
	 * 显示系统消息窗口
	 *
	 */
	public void showMessage() {
		sysMessageBtn.setText("系统消息");
		this.messageUI.setVisible(true);
	}
	/**
	 * 添加用户树鼠标监听
	 * @param mouseListener
	 */
	public void addUserTreeMouseListener(MouseListener mouseListener) {
		userTree.addMouseListener(mouseListener);
		groupTree.addMouseListener(mouseListener);
	}

}

/**
 * 对主窗口的员工树的操作
 * 
 * @author Administrator
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
class MainUIUserTreeListener extends MouseAdapter {

	private MainUi mainUI;

	public MainUIUserTreeListener(MainUi mainUI) {
		this.mainUI = mainUI;
	}

	public void mousePressed(MouseEvent e) {
		// 如果双击树节点
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
		// 如果双击树节点
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




