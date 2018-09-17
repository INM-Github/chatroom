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
					System.out.println("群聊");
					Group group = (Group) message;
					if (message.getType().equals(PackOper.ADD_GROUP)) {
						// 不进行添加自己信息在树中
						MainUi.getInstance().getUserTree().addGroup(group);
					}
				}else if (message instanceof User) {
					User user = (User) message;
					if (message.getType().equals(PackOper.ADD_USER)) {
						// 不进行添加自己信息在树中
						 if(!user.getId().equals(MainUi.getInstance().getUser().getId())){
						MainUi.getInstance().getUserTree().addUser(user);
						 }
					}else if (message.getType().equals(PackOper.FORCEDOWN_LINE)) {
						this.setStop();
						this.client.close();
						MainUi.getInstance().setVisible(false);
						PubToolkit.showYes(MainUi.getInstance(), "你已经被服务器强制下线!");
						return;
					} 
				} else if (message instanceof MessagePack) {
					System.out.println(MainUi.getInstance().getUserTree().getGroupSet()+"s");
					MessagePack msgPack = (MessagePack) message;
					// 如果是系统消息
						ChatUI chatUI = null;
						// 首先找出是谁发的
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
							PubToolkit.showYes(MainUi.getInstance(), "服务器关闭!");
							return;
						} else if(msgPack.getType().equals(PackOper.CHAT_GROUP)) {
							System.out.println(MainUi.getInstance().getUserTree().getGroupSet());
							group = MainUi.getInstance().getGroupTree().findGroup(msgPack.getTo());
							if(group == null) {
								System.out.println("没有获取到");
							}
							chatUI = PubValue.getChatUIForMsg(PackOper.CHAT_GROUP,group.getNo());
						}
						// 如果消息有显示的窗口的把它显示到最前面
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
			PubToolkit.showInformation("与服务器断开连接");
		} catch (IOException e) {
			PubToolkit.showInformation("与服务器断开连接");
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
	 * 发送消息
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
	 * 停止消息服务
	 *
	 */
	public void setStop() {
		isStop = true;
	}
}