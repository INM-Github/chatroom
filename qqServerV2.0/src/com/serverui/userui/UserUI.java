package com.serverui.userui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dao.MySQLUserDao;
import com.pack.PackOper;
import com.qqServer.ClientThread;
import com.qqServer.PubToolkit;
import com.qqServer.PubValue;
import com.qqServer.User;



/**
 * �û��������
 * @author Administrator
 */
public class UserUI extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * ������ť���
	 */
	private ButtonPanel buttonPanel = new ButtonPanel();

	/**
	 * ̽�����
	 */
	private SearchPanel searchPanel = new SearchPanel();

	/**
	 * ������
	 */	
	private TablePanel tablePanel;

	/**
	 * ������
	 */	
	private JFrame mainFrame;
	
	/**
	 * ���캯��
	 * @param mainFrame
	 */
	public UserUI(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	/**
	 * ��ʼ��
	 *
	 */
	public void init() {
		searchPanel = new SearchPanel();
		

		buttonPanel = new ButtonPanel();

		tablePanel = new TablePanel(new UserTableModel(MySQLUserDao.getInstance().getColumnNames(), MySQLUserDao.getInstance().getUserModel(searchPanel.getSearch())));
		this.setLayout(new BorderLayout());
		this.add(searchPanel, BorderLayout.NORTH);
//		if(tablePanel == null) {
//			System.out.println("kong");
//		}
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.addButtonActionListener(new UserUIButtonActionListener(this));

	}

	/**
	 * ��Ӱ�ť����
	 * @param actionListener
	 */
	public void addButtonActionListener(ActionListener actionListener) {
		this.buttonPanel.addButtonActionListener(actionListener);
		this.searchPanel.addButtonActionListener(actionListener);
	}

	/**
	 * ȡ��������
	 * @return
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * ȡ��ѡ����û�ID
	 * @return
	 */
	public String getRowId() {
		return this.tablePanel.getRowId();
	}

	/**
	 * ȡ����������
	 * @return
	 */	
	public HashMap getSearch() {
		return this.searchPanel.getSearch();
	}

	/**
	 * �����û����
	 * @param hm
	 */
	public void update(HashMap hm) {
		this.tablePanel.update(hm);
	}
}
/**
 * �û�������尴ť������
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
class UserUIButtonActionListener implements ActionListener {

	private UserUI userUI;

	public UserUIButtonActionListener(UserUI userUI) {
		this.userUI = userUI;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String command = ((JButton) e.getSource()).getActionCommand();
			//	User user = userUI.getU
			if (command.equals(UserUIConfig.DELBTN)) {
				//����ѡ��,����ǽ���ɾ��,����ɾ����ʾɾ���ɹ�,ɾ�����ɹ�����ʾɾ�����ɹ�
				if (userUI.getRowId() == null) {
					PubToolkit.showInformation("�ⲻ��,��ѡ��Ҫɾ�����û�.");
					return;
				}
				if (PubToolkit.showYesNo(userUI.getMainFrame(), "���Ҫɾ����?")) {
					User tempUser = (User)MySQLUserDao.getInstance().selectId(userUI.getRowId()).get(0);
					if(tempUser.getIsOnline() == 1){
						PubToolkit.showYes(userUI.getMainFrame(), "�Բ���,���û���������״̬,����ǿ������,��ɾ��.");
						return;
					}
					if (MySQLUserDao.getInstance().delete(userUI.getRowId())) {
						userUI.update(userUI.getSearch());
						PubToolkit.showInformation("��ϲ,ɾ���ɹ�.");						
					
						//��ʾ�޸ĶԻ���
						Iterator it = PubValue.getOnLineUserThread();
						tempUser.setType(PackOper.DELETE_USER);
						while(it.hasNext()){
							((ClientThread) it.next()).sendMessage(tempUser);
						}
						
					}
				}
			} else if (command.equals(UserUIConfig.MAKEBTN)) {
				if (userUI.getRowId() == null) {
					PubToolkit.showInformation("�ⲻ��,��ѡ��Ҫ�޸ĵļ�¼.");
					return;
				} else {
					List list = MySQLUserDao.getInstance().selectId(userUI.getRowId());
					User user = (User)list.get(0);
					//��ʾ�޸ĶԻ���
					(new UserMakeDialog(userUI.getMainFrame(), "�޸��û�",user ,UserUIConfig.MAKEBTN)).setVisible(true);
				}
			} else if (command.equals(UserUIConfig.SEARCHBTN)) {
				//��ѯ
				userUI.update(userUI.getSearch());
			}
		}
	}
}