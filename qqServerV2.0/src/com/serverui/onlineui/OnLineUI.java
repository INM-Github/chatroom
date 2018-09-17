package com.serverui.onlineui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.dao.MySQLUserDao;
import com.pack.MessagePack;
import com.pack.PackOper;
import com.qqServer.ClientThread;
import com.qqServer.MessageServer;
import com.qqServer.Parameter;
import com.qqServer.PubToolkit;
import com.qqServer.PubValue;
import com.qqServer.ServerMain;
import com.qqServer.ServerUi;
import com.qqServer.User;
import com.serverui.logui.LogOper;
import com.serverui.userui.TablePanel;
import com.serverui.userui.UserTableModel;



/**
 * �����û����
 * 
 */
public class OnLineUI extends JPanel {

	/**
	 * <code>serialVersionUID</code> ��ע��
	 */
	private static final long serialVersionUID = 1L;

	private BottomPane bottomPane = new BottomPane();

	private TopPanel topPanel = new TopPanel();

	private TablePanel tablePanel;

	private UserTableModel userTableModel;

	public OnLineUI() {
		init();
	}

	public void init() {
		userTableModel = new UserTableModel(MySQLUserDao.getInstance().getColumnNames(), MySQLUserDao.getInstance().selectOnLine());
		tablePanel = new TablePanel(userTableModel);
		tablePanel.setBorder(BorderFactory.createTitledBorder("�����û�"));

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(bottomPane, BorderLayout.SOUTH);
		this.addButtonActionListener(new OnLineButtonActionListener(this));
	}

	/**
	 * ȡ�ñ�ѡ�е����������û�
	 * 
	 * @return
	 */
	public String[] getSelectId() {
		return this.tablePanel.getSelectId();
	}

	/**
	 * ��ť����
	 * 
	 * @param a
	 */
	public void addButtonActionListener(ActionListener a) {
		topPanel.addButtonActionListener(a);
		bottomPane.addButtonActionListener(a);
	}

	/**
	 * ���������û��б�
	 */
	public void updateOnLine() {
		HashMap hm = new HashMap();
		hm.put("U_isOnline", "" + 1);
		this.tablePanel.update(hm);
		this.updateUI();
	}

	/**
	 * ȡ��Ҫ���͵���Ϣ
	 * 
	 * @return
	 */
	public String getMessage() {
		return this.bottomPane.getMessage();
	}

	/**
	 * �������͵���Ϣ
	 *  
	 */
	public void clear() {
		this.bottomPane.clear();
	}

	/**
	 * ������Ϣ��������ť״̬
	 * 
	 * @param type
	 */
	public void setServerBtn(String type) {
		this.topPanel.setServerBtn(type);
	}

	/**
	 * ȡ����Ϣ���Ͷ���
	 * 
	 * @return
	 */
	public Object getSendObject() {
		return bottomPane.getSendObject();
	}

	/**
	 * ��ʼ�����Ͷ���
	 *  
	 */
	public void initSendCom() {
		this.bottomPane.initSendCom();
	}
}

/**
 * �����û���ť������
 * 
 * @author Administrator
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */

class OnLineButtonActionListener implements ActionListener {

	private OnLineUI onLineUI;

	public OnLineButtonActionListener(OnLineUI onLineUI) {
		this.onLineUI = onLineUI;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String command = e.getActionCommand();
			if (command.equals(OnLineConfig.STARTSERVERBTN)) {
				try {
					MessageServer messageServer = new MessageServer(ServerMain.getServerPort());
					messageServer.start();
					PubValue.setMessageServer(messageServer);
					this.onLineUI.setServerBtn(OnLineConfig.STOPSERVERBTN);
					System.out.println("�����������ɹ�");
					ServerUi.getInstance().getLogUI().addLog(
							LogOper.getInstance().insertStartServerLog());
				} catch (IOException e1) {
					ServerUi.getInstance().getLogUI().addLog(
							LogOper.getInstance().insertStartServerErrorLog());
					e1.printStackTrace();
					PubToolkit.showYes(ServerUi.getInstance(), "������Ϣ������ʧ��!");
				}
			} else if (command.equals(OnLineConfig.STOPSERVERBTN)) {
				Iterator it = PubValue.getOnLineUserThread();
				MessagePack msgPack = new MessagePack();
				msgPack.setType(PackOper.SERVERCLOSE);
				while (it.hasNext()) {
					ClientThread clientThread = (ClientThread) it.next();
					clientThread.sendMessage(msgPack);
				}
				PubValue.removeAll();
				PubValue.setAllUserDown();
				ServerUi.getInstance().getOnLineUI().updateOnLine();
				this.onLineUI.setServerBtn(OnLineConfig.STARTSERVERBTN);
				if (PubValue.getMessageServer() != null) {
					PubValue.getMessageServer().stopServer();
					PubValue.setMessageServer(null);
				}

				System.out.println("�رշ������ɹ�");
			} else if (command.equals(OnLineConfig.DOWNONLINEBTN)) {
				// ǿ������
				String[] downIds = onLineUI.getSelectId();
				if (!(downIds != null && downIds.length > 0)) {
					PubToolkit.showInformation("��ѡ��Ҫǿ�����ߵ��û�");
					return;
				}
				if (!PubToolkit
						.showYesNo(ServerUi.getInstance(), "���Ҫ����ǿ��������?")) {
					return;
				}
				for (int i = 0; i < downIds.length; i++) {

					// ȡ��Ҫ���ߵ��û��߳�
					ClientThread downClientThread = PubValue
							.getUserThread(downIds[i]);
					// ��֪ͨ����,ֹͣ�߳�
					if (downClientThread != null) {
						User downUser = downClientThread.getUser();

						downUser.setType(PackOper.FORCEDOWN_LINE);
						downUser.setIsOnline(Parameter.NOTONLINED);
						downClientThread.sendMessage(downUser);
						downClientThread.setStop();
						ServerUi.getInstance().getLogUI().addLog(
								LogOper.getInstance().insertForceDownLineLog(
										downUser));
						// ���߳�����ɾ��
						PubValue.deleteUserThread(downUser.getId());

						// ֪ͨ������
						Iterator it = PubValue.getOnLineUserThread();
						ClientThread clientThread;
						downUser.setType(PackOper.DOWN_LINE);
						while (it.hasNext()) {
							clientThread = (ClientThread) it.next();
							clientThread.sendMessage(downUser);
						}
						// �������ݿ�
						MySQLUserDao.getInstance().setOnline(downUser.getId(),
								Parameter.NOTONLINED);
					}
					// ���������û����
					ServerUi.getInstance().getOnLineUI().updateOnLine();
					System.out.println("ǿ������");
				}
			} 
		}
	}

}