package com.qqServer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.MySQLUserDao;


/**
 * ���񹫹���Ϣ��
 */
public class PubValue {
	/**
	 * ��¼���������û��������߳�
	 */
	private static Map onLineUser = new HashMap();
	
	/**
	 * ��Ϣ������
	 */
	private static MessageServer messageServer;
	
	/**
	 * ȡ����Ϣ������������
	 * @return
	 */
	public static MessageServer getMessageServer(){
		return messageServer;
	}
	
	/**
	 * ������Ϣ������
	 * @param messageServer
	 */
	public static void setMessageServer(MessageServer messageServer){
		PubValue.messageServer = messageServer; 
	}
	
	/**
	 * ȡ�����������û����̵߳�����
	 * @return
	 */
	public static Iterator getOnLineUserThread(){
		return onLineUser.values().iterator();
	}
	
	/**
	 * ����ĳ���û���ID,ȡ����Ӧ���߳�
	 * @param id
	 * @return
	 */
	public static ClientThread getUserThread(String id){
		ClientThread clientThread = (ClientThread)onLineUser.get(id);
		return clientThread;
	}
	/**
	 * �����û�ID,����Ӧ���߳�,��ӵ������ü��߳��б���
	 * @param id
	 * @param thread
	 */	
	public static void addUserThread(String id, Thread thread){
		onLineUser.put(id,thread);
	}

	/**
	 * �����û�ID,�������û��б���ɾ����Ӧ���߳�
	 * @param id
	 */
	public static void deleteUserThread(String id){
		onLineUser.remove(id);
	}
	
	/**
	 * ������������û����߳��б�
	 *
	 */
	public static void removeAll(){
		onLineUser.clear();
	}
	
	/**
	 * �����������û�����
	 *
	 */
	public static void setAllUserDown(){
		HashMap hm = new HashMap();
		hm.put("U_isOnline", "" + 1);
		List list = MySQLUserDao.getInstance().select(hm);
		Iterator it = list.iterator();
		while(it.hasNext()){
			User user = (User)it.next();
			MySQLUserDao.getInstance().setOnline(user.getId(), Parameter.NOTONLINED);
		}		
	}
	
}
