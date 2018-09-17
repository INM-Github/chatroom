package com.thread;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import com.pack.Message;
import com.pack.MessagePack;
import com.pack.MyObjectInputStream;
import com.pack.MyObjectOutputStream;
import com.pack.PackOper;
import com.pack.PubToolkit;
import com.pack.PubValue;
import com.qq.Group;
import com.qq.User;
import com.ui.ChatUI;
import com.ui.MainUi;

public class ClientThread extends Thread {

	private Socket client;

	private MyObjectInputStream myObjectIn;

	private MyObjectOutputStream myObjectOut;

	private boolean isStop = false;

	public ClientThread(Socket client) {
		this.client = client;
	}
	public void run() {
		try {
			myObjectIn = new MyObjectInputStream(this.client.getInputStream());
			myObjectOut = new MyObjectOutputStream(this.client
					.getOutputStream());
			Message message = null;
			while (!isStop) {
				message = myObjectIn.readMessage();
				
				if(message instanceof Group) {
					System.out.println("Ⱥ��");
					Group group = (Group) message;
					if (message.getType().equals(PackOper.ADD_GROUP)) {
						// ����������Լ���Ϣ������
						MainUi.getInstance().getUserTree().addGroup(group);
					}
				}else if (message instanceof User) {
					User user = (User) message;
					if (message.getType().equals(PackOper.ADD_USER)) {
						// ����������Լ���Ϣ������
						 if(!user.getId().equals(MainUi.getInstance().getUser().getId())){
						MainUi.getInstance().getUserTree().addUser(user);
						 }
					}else if (message.getType().equals(PackOper.FORCEDOWN_LINE)) {
						this.setStop();
						this.client.close();
						MainUi.getInstance().setVisible(false);
						PubToolkit.showYes(MainUi.getInstance(), "���Ѿ���������ǿ������!");
						return;
					} 
				} else if (message instanceof MessagePack) {
					System.out.println(MainUi.getInstance().getUserTree().getGroupSet()+"s");
					MessagePack msgPack = (MessagePack) message;
					// �����ϵͳ��Ϣ
						ChatUI chatUI = null;
						// �����ҳ���˭����
						User user = null;
						Group group = null ;
						System.out.println(MainUi.getInstance().getUserTree().getGroupSet()+"m");
							if (msgPack.getType().equals(PackOper.CHAT_USER)) {
							user = MainUi.getInstance().getUserTree()
									.findUser(msgPack.getFrom());
							chatUI = PubValue.getChatUIForMsg(PackOper.CHAT_USER,user.getId());
						} else if (message.getType().equals(PackOper.SERVERCLOSE)) {
							this.setStop();
							this.client.close();
							PubToolkit.showYes(MainUi.getInstance(), "�������ر�!");
							return;
						} else if(msgPack.getType().equals(PackOper.CHAT_GROUP)) {
							System.out.println(MainUi.getInstance().getUserTree().getGroupSet());
							group = MainUi.getInstance().getGroupTree().findGroup(msgPack.getTo());
							if(group == null) {
								System.out.println("û�л�ȡ��");
							}
							chatUI = PubValue.getChatUIForMsg(PackOper.CHAT_GROUP,group.getNo());
						}
						// �����Ϣ����ʾ�Ĵ��ڵİ�����ʾ����ǰ��
						if (chatUI == null && user != null) {
							chatUI = new ChatUI(user);
						}
						if (chatUI == null && group != null) {
							chatUI = new ChatUI(group);
						}
						if (chatUI != null) {
							chatUI.addMessage(msgPack);
							PubValue.addChatUI(chatUI);							
							chatUI.setVisible(true);
							chatUI.toFront();
						}
				}
			}
		} catch (UnsupportedEncodingException e) {
			PubToolkit.showInformation("��������Ͽ�����");
		} catch (IOException e) {
			PubToolkit.showInformation("��������Ͽ�����");
		} finally {
			if (myObjectIn != null) {
				try {
					myObjectIn.close();
					myObjectIn = null;
				} catch (IOException e1) {
				}
			}
			if (myObjectOut != null) {
				try {
					myObjectOut.close();
					myObjectOut = null;
				} catch (IOException e1) {
				}
			}
			if (this.client != null) {
				try {
					this.client.close();
				} catch (IOException e) {
				}
			}
		}

	}
	/**
	 * ������Ϣ
	 */
	public boolean sendMessage(Object object) {
		try {
			if (myObjectOut != null) {
				myObjectOut.writeMessage(object);
			}
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * ֹͣ��Ϣ����
	 *
	 */
	public void setStop() {
		isStop = true;
	}
}