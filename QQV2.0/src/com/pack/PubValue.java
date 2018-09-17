package com.pack;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.qq.Group;
import com.qq.User;
import com.thread.ClientThread;
import com.ui.ChatUI;

/**
 * ����������
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class PubValue {
	
	/**
	 * ���촰���б�
	 */
	private static ArrayList chatUIList = new ArrayList();

	/**
	 * �ͻ���Ϣ�����߳�
	 */
	private static ClientThread clientThread;

	/**
	 * �û���Ϣ
	 */
	private static User user;
	
	/**
	 * �����û���Ϣ
	 * @param user
	 */
	public static void setUser(User user) {
		PubValue.user = user;
		createUserDir(user.getId());
	}
	
	/**
	 * �����û�Ŀ¼
	 * @param dir
	 * @return
	 */
	private static boolean createUserDir(String dir){
		File file = new File(dir);
		System.out.println("Dir="+file.getAbsolutePath());
		if(!file.exists()){
			file.mkdir();
			return true;
		}
		return false;
	}

	/**
	 * ȡ���û���Ϣ
	 * @return User
	 */
	public static User getUser() {
		return PubValue.user;
	}

	/**
	 * ȡ����Ϣ�����߳�
	 * @return
	 */
	public static ClientThread getClientThread() {
		return clientThread;
	}

	/**
	 * ������Ϣ�����߳�
	 * @param clientThread
	 */
	public static void setClientThread(ClientThread clientThread) {
		PubValue.clientThread = clientThread;
	}

	/**
	 * ������촰������
	 * @param chatUI
	 */
	public static void addChatUI(ChatUI chatUI) {
		chatUIList.add(chatUI);
	}

	/**
	 * ɾ�����촰������
	 * @param chatUI
	 */
	public static void removeChatUI(ChatUI chatUI) {
		Iterator it = chatUIList.iterator();
		while (it.hasNext()) {
			if (it.next() == chatUI) {
				it.remove();
				return;
			}
		}
	}

	// ͨ����������ҵ���Ӧ�Ի���,Ҳ����˫�������ڵ�,�������ڵ�Ĵ����Ѿ��򿪾Ͳ����¿�һ��,���������������ǰ
	/**
	 * ���������������ȡ�����촰������
	 */
	public static ChatUI getChatUIForObject(User user) {
		Iterator it = chatUIList.iterator();
		while (it.hasNext()) {
			ChatUI chatUI = (ChatUI) it.next();
			if (chatUI.getUser().equals(user)
					&& user.getClass().getName().equals(
							chatUI.getUser().getClass().getName())) {
				return chatUI;
			}
		}
		return null;
	}
	public static ChatUI getChatUIForObject(Group group) {
		Iterator it = chatUIList.iterator();
		while (it.hasNext()) {
			ChatUI chatUI = (ChatUI) it.next();
			if (group.equals(chatUI.getGroup())) {
				return chatUI;
			}
		}
		return null;
	}
	// ͨ����Ϣ�ҵ���Ӧ�ĶԻ���
	/**
	 * ��������:��˾,����,�û� ����Ӧ��ID��,ȡ�ô�������
	 */	
	public static ChatUI getChatUIForMsg(String e, String id) {
		if (e.equals(PackOper.CHAT_USER)) {
			Iterator it = chatUIList.iterator();
			while (it.hasNext()) {
				ChatUI chatUI = (ChatUI) it.next();
				Object object = chatUI.getUser();
				if (object instanceof User) {
					if (((User) object).getId().equals(id))
						return chatUI;
				}
			}
		}else if (e.equals(PackOper.CHAT_GROUP)) {
			Iterator it = chatUIList.iterator();
			while (it.hasNext()) {
				ChatUI chatUI = (ChatUI) it.next();
				Object object = chatUI.getGroup();
				if (object instanceof Group) {
					if (((Group) object).getNo().equals(id))
						return chatUI;
				}
			}
		}
		return null;
	}

	

	/**
	 * �ر����е����촰��
	 *
	 */
	public static void closeAllChatUI() {
		Iterator it = chatUIList.iterator();
		while (it.hasNext()) {
			ChatUI chatUI = (ChatUI) it.next();
			chatUI.setVisible(false);
			chatUI.dispose();
		}
	}

}
