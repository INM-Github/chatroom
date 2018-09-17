package com.qqServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.serverui.logui.LogUI;
import com.serverui.onlineui.OnLineUI;
import com.serverui.userui.UserUI;





public class ServerUi extends JFrame
{

    /**
     * @author CSJ
     */
    private static final long serialVersionUID = 1L;
//    private Map<String, ServerClientConnectionStream> userMap;
//    private Set<UserInformation> userSet;
    private ServerSocket serverSocket;
    private JButton start;
    private JLabel welcome;
	/**
	 * �û��������
	 */	
	private UserUI userUI = new UserUI(this);

	/**
	 * �����û��������
	 */
	private OnLineUI onlineUI = new OnLineUI();

	/**
	 * ��־�������
	 */
	private LogUI logUI = new LogUI(this);
	private JTabbedPane tabbedPane = new JTabbedPane();
    private static ServerUi serverUi;
    

    public ServerUi()
    {
        super();
//        userMap = new HashMap<String, ServerClientConnectionStream>();
//        userSet = new HashSet<UserInformation>();
        welcome = new JLabel("��������");
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		this.tabbedPane.add(onlineUI, "�����û�����");
		this.tabbedPane.add(userUI, "�û�����");
		this.tabbedPane.add(logUI, "��־����");

        createFrame();
        addEventHandler();
    }

    private void createFrame()
    {
        start = new JButton("����������");
        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);

        JPanel welcomePanel = new JPanel();
        welcomePanel.add(welcome);
        JPanel southPanel = new JPanel();
        southPanel.add(start);
        setSize(400, 600);
        setVisible(true);
        add(welcomePanel, BorderLayout.NORTH);
        add(jTabbedPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int height = getHeight();
        int width = getWidth();
        int screenWidth = screenSize.width / 2;
        int screenHeight = screenSize.height / 2;
        setLocation(screenWidth - width / 2, screenHeight - height / 2);
    }

    public void addEventHandler()
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int t = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���������", "ȷ���˳�", JOptionPane.OK_CANCEL_OPTION);
                if (t == JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

	public OnLineUI getOnLineUI() {
		return this.onlineUI;
	}

	/**
	 * ȡ����־�������
	 * @return
	 */
	public LogUI getLogUI() {
		return this.logUI;
	}
	/**
	 * ȡ���û��������
	 * @return ���� userUI��
	 */
	public UserUI getUserUI() {
		return userUI;
	}
	public static ServerUi getInstance() {
		if (serverUi == null)
			serverUi = new ServerUi();
		return serverUi;
	}
}