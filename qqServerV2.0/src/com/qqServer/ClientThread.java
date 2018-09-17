package com.qqServer;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.dao.MySQLUserDao;
import com.pack.Message;
import com.pack.MessagePack;
import com.pack.MyObjectInputStream;
import com.pack.MyObjectOutputStream;
import com.pack.PackOper;
import com.serverui.logui.LogOper;
/**
 * ��ͻ��˽�����Ϣ�������߳���
 *
 */
public class ClientThread extends Thread {

	/**
	 * ��Ӧ���û���Ϣ
	 */
	private User user;

	/**
	 * ��֮������SOCKET
	 */
	private Socket client;

	/**
	 * �Զ����������
	 */
	private MyObjectInputStream myObjectIn;

	/**
	 * �Զ���������
	 */
	private MyObjectOutputStream myObjectOut;

	/**
	 * �Ƿ�ֹͣ�߳�����
	 */
	private boolean isStop = false;

	/**
	 * �̹߳��캯��
	 * @param client ��ͻ���������SOCKET
	 */
	public ClientThread(Socket client) {
		this.client = client;
	}

	/**
	 * ȡ�ñ��̵߳��û���Ϣ
	 * @return
	 */	
	public User getUser() {
		return this.user;
	}

	/**
	 * ������Ϣ����߳��������û�
	 * @param message
	 */
	public void sendMessage(Message message) {
		try {
			myObjectOut.writeMessage(message);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * ֹͣ�߳�����
	 *
	 */
	public void setStop() {
		isStop = true;
	}

	/**
	 * ��ͻ��˽���
	 */
	public void run() {
		System.out.println("�����������Կͻ���һ���߳�");
		try {
			myObjectIn = new MyObjectInputStream(client.getInputStream());
			myObjectOut = new MyObjectOutputStream(client.getOutputStream());
			Message message = null;
			while (!isStop) {
				message = myObjectIn.readMessage();
				System.out.println("---------------------------------------------");
				System.out.println("�յ��ͻ��˵�һ����,����:" + message.getType());
				if (this.user != null) {
					messageDispose(message);
				} else {
					login(message);
				}
				System.out.println("---------------------------------------------");
			}
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			setStop();
		} catch (IOException e) {
			// e.printStackTrace();
			setStop();
		} finally {
			if (myObjectIn != null) {
				try {
					myObjectIn.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
			if (myObjectOut != null) {
				try {
					myObjectOut.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
			if (this.user != null) {
				ServerUi.getInstance().getLogUI().addLog(
						LogOper.getInstance().insertDownLineLog(this.user));
				MySQLUserDao.getInstance().setOnline(this.user.getId(),
						Parameter.NOTONLINED);

				ServerUi.getInstance().getOnLineUI().updateOnLine();
				PubValue.deleteUserThread(this.user.getId());

				this.user.setIsOnline(Parameter.NOTONLINED);
				this.user.setType(PackOper.DOWN_LINE);
				Iterator it = PubValue.getOnLineUserThread();
				while (it.hasNext()) {
					ClientThread clientThread = (ClientThread) it.next();
					clientThread.sendMessage(this.user);
				}
			}
		}
	}

	/**
	 * ��������������Ϣ
	 * @param message
	 * @throws IOException
	 */
	private void messageDispose(Message message) throws IOException {
	if (message.getType().equals(PackOper.CHAT_USER)) {
			chatUser(message);
		} else if (message.getType().equals(PackOper.DOWN_LINE)) {
			downLine(message);
		} else if(message.getType().equals(PackOper.CHAT_GROUP)) {
			chatGroup(message);
		}
	}
	/**
	 * ˽��,��������ĶԷ�
	 */
	private void chatUser(Message message) {
		MessagePack msgPack = (MessagePack) message;
		ClientThread clientThread = PubValue.getUserThread(msgPack.getTo());
		clientThread.sendMessage(msgPack);
	}
	/**
	 * Ⱥ����,����Ⱥ���������
	 */ 
	private void chatGroup(Message message) {
		Iterator it = PubValue.getOnLineUserThread();
		String noSendId = null;
		if (message instanceof MessagePack) {
			noSendId = ((MessagePack) message).getFrom();
		}
		if (noSendId == null)
			noSendId = "";
		while (it.hasNext()) {
			ClientThread clientThread = (ClientThread) it.next();
			if (!clientThread.getUser().getId().equals(noSendId))
				clientThread.sendMessage(message);
		}
	}

	/**
	 * �û����߲���
	 * @param message
	 * @throws IOException
	 */
	private void downLine(Message message) throws IOException {
		// ���߰�
		// �Ȱ�����û��������б���ɾ��,��֪ͨ���������û�,
		// ��ɾ����Ϊ�˷�ֹ,��֪ͨ���������û�ʱ,���ʱ�����,�Ӷ�Ӱ�����������û�����ʵ��Ϣ
		// ���߶Խ������finally�����,�������Է�ֹ,�м��쳣��û��ˢ�������б�
		// ��Ҫ��finally�����,��ӴӴ洢�б�ɾ�������,��������ԭ��Ҳ��Ϊ�˷�ֹ����.
		// ��Ҫˢ�����ݿ������״̬
		this.setStop();
		this.client.close();
				PubValue.deleteUserThread(this.user.getId());
				Iterator it = PubValue.getOnLineUserThread();
				ClientThread clientThread;
				while (it.hasNext()) {
					clientThread = (ClientThread) it.next();
					clientThread.sendMessage(message);
				}
				MySQLUserDao.getInstance().setOnline(this.user.getId(),
		 Parameter.NOTONLINED);
				ServerUi.getInstance().getOnLineUI().updateOnLine();
				ServerUi.getInstance().getLogUI().addLog(LogOper.getInstance().insertDownLineLog(this.user));
	}

	/**
	 * �����¼��Ϣ
	 * 
	 * @param message
	 * @throws IOException
	 */
	private void login(Message message) throws IOException {
		if (message.getType().equals(PackOper.LOGIN)) {
			User user = (User) message;
			System.out.println("��¼ID:" + user.getId());
			List list = MySQLUserDao.getInstance().selectId(user.getId());
			if (list.size() > 0) {
				User tempUser = (User) list.get(0);
				System.out.println("����: �ϴ�����<" + user.getPassword() + ">--,���ݿ�����:<" + tempUser.getPassword() + ">");
				if (tempUser.getPassword().equals(user.getPassword())) {
					if (tempUser.getIsOnline() == Parameter.ONLINE) {
						message.setType(PackOper.LOGIN_ONLINED);
						myObjectOut.writeMessage(message);
						this.setStop();
						return;
					}
					ServerUi.getInstance().getLogUI().addLog(LogOper.getInstance().insertOnLineLog(tempUser));
					// ��¼�ɹ���
					// ��֪ͨ������
					// ��֪ͨ���û�
					// �ٰ��Լ��ӵ��߳�����
					// ���Ÿ������ݿ�
					// �����������û�
					tempUser.setType(PackOper.LOGIN_SUCCEED);
					tempUser.setIsOnline(Parameter.ONLINE);
					this.user = tempUser;
					this.myObjectOut.writeMessage(tempUser);
					PubValue.addUserThread(this.user.getId(), this);
					MySQLUserDao.getInstance().setOnline(tempUser.getId(), Parameter.ONLINE);
					ServerUi.getInstance().getOnLineUI().updateOnLine();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						// e1.pntriStackTrace();
					}
					list = MySQLUserDao.getInstance().select(null);
                    System.out.println("��������ʼ");
					Iterator it = list.iterator();
					while (it.hasNext()) {
						message = (Message) it.next();
						message.setType(PackOper.ADD_USER);
						myObjectOut.writeMessage(message);
					}
					list = MySQLUserDao.getInstance().selectGroup(user.getId());
					it = list.iterator();
					while (it.hasNext()) {
						message = (Message) it.next();
						message.setType(PackOper.ADD_GROUP);
						myObjectOut.writeMessage(message);
					}
					return;
				}
			}
			message.setType(PackOper.LOGIN_DEFEATED);
			myObjectOut.writeMessage(message);
			this.setStop();
		}
	}
}