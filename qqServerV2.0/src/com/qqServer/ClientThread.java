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
 * 与客户端进行消息交换的线程类
 *
 */
public class ClientThread extends Thread {

	/**
	 * 相应的用户信息
	 */
	private User user;

	/**
	 * 与之相连的SOCKET
	 */
	private Socket client;

	/**
	 * 自定义的输入流
	 */
	private MyObjectInputStream myObjectIn;

	/**
	 * 自定义的输出流
	 */
	private MyObjectOutputStream myObjectOut;

	/**
	 * 是否停止线程运行
	 */
	private boolean isStop = false;

	/**
	 * 线程构造函数
	 * @param client 与客户端相连的SOCKET
	 */
	public ClientThread(Socket client) {
		this.client = client;
	}

	/**
	 * 取得本线程的用户信息
	 * @return
	 */	
	public User getUser() {
		return this.user;
	}

	/**
	 * 发送信息与此线程相连的用户
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
	 * 停止线程运行
	 *
	 */
	public void setStop() {
		isStop = true;
	}

	/**
	 * 与客户端交流
	 */
	public void run() {
		System.out.println("服务器启动对客户的一个线程");
		try {
			myObjectIn = new MyObjectInputStream(client.getInputStream());
			myObjectOut = new MyObjectOutputStream(client.getOutputStream());
			Message message = null;
			while (!isStop) {
				message = myObjectIn.readMessage();
				System.out.println("---------------------------------------------");
				System.out.println("收到客户端的一个包,类型:" + message.getType());
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
	 * 用来处理所有消息
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
	 * 私聊,发给具体的对方
	 */
	private void chatUser(Message message) {
		MessagePack msgPack = (MessagePack) message;
		ClientThread clientThread = PubValue.getUserThread(msgPack.getTo());
		clientThread.sendMessage(msgPack);
	}
	/**
	 * 群聊天,发给群里的所有人
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
	 * 用户下线操作
	 * @param message
	 * @throws IOException
	 */
	private void downLine(Message message) throws IOException {
		// 下线包
		// 先把这个用户从在线列表中删除,再通知所有在线用户,
		// 先删除是为了防止,在通知所有在线用户时,造成时间过长,从而影响其它上线用户的真实信息
		// 下线对界面放在finally语句中,这样可以防止,中间异常而没有刷新在线列表
		// 还要在finally语句中,添加从存储列表删除的语句,这样做的原因也是为了防止导致.
		// 还要刷新数据库的在线状态
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
	 * 处理登录信息
	 * 
	 * @param message
	 * @throws IOException
	 */
	private void login(Message message) throws IOException {
		if (message.getType().equals(PackOper.LOGIN)) {
			User user = (User) message;
			System.out.println("登录ID:" + user.getId());
			List list = MySQLUserDao.getInstance().selectId(user.getId());
			if (list.size() > 0) {
				User tempUser = (User) list.get(0);
				System.out.println("密码: 上传密码<" + user.getPassword() + ">--,数据库密码:<" + tempUser.getPassword() + ">");
				if (tempUser.getPassword().equals(user.getPassword())) {
					if (tempUser.getIsOnline() == Parameter.ONLINE) {
						message.setType(PackOper.LOGIN_ONLINED);
						myObjectOut.writeMessage(message);
						this.setStop();
						return;
					}
					ServerUi.getInstance().getLogUI().addLog(LogOper.getInstance().insertOnLineLog(tempUser));
					// 登录成功后
					// 先通知所有人
					// 再通知此用户
					// 再把自己加到线程组中
					// 接着更新数据库
					// 下载树给此用户
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
                    System.out.println("下载树开始");
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