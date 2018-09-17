package com.qqServer;

import java.util.HashMap;
import java.util.List;

public interface UserIFC {
	/**
	 * ����ĳ���û�������
	 * 
	 * @param user
	 * @param isOnline
	 * @return
	 */
	public boolean setOnline(String id, int isOnline);

	/**
	 * ɾ���û�
	 * 
	 * @param dpt
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * �����û�
	 * 
	 * @param dpt
	 * @return
	 */
	public boolean update(User user);

	/**
	 * ����û�
	 * 
	 * @param dpt
	 * @return boolean
	 */
	public boolean add(User user);

	/**
	 * ��ѯ�û�
	 * 
	 * @param dpt
	 * @return
	 */
	public List selectId(String id);

	/**
	 * ��ѯĳ���������û�
	 * 
	 * @param id
	 * @return
	 */
	public List select(HashMap hm);

	public List selectGroup(String id) ;

	public List selectOnLine();

	/**
	 * ȡ���û��б��������Ϣ
	 * 
	 * @return
	 */
	//����Ҫ���в�ѯ��������:
	//Ա�����,���Ϊ��ֵ�����ǿ��ַ���,�Ͳ����ɲ�ѯ����,����ͬ
	//Ա������
	//Ա������
	public List getUserModel(HashMap hm);

	/**
	 * �����б���
	 * 
	 * @return
	 */
	public List getColumnNames();

	/**
	 * ��������
	 * 
	 * @param id
	 * @param password 
	 * @return
	 */
	public boolean resetPassword(String id, String password);
}