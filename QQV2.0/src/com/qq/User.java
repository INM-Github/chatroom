package com.qq;

import com.pack.Message;

/**
 * Ա������BEAN
 * 
 * @author Administrator
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class User extends Message implements Comparable {
	/**
	 * ���
	 */
	private String id = "";

	/**
	 * ����
	 */
	private String name = "";

	/**
	 * ����
	 */
	private String password = "";
	/**
	 * ͷ����
	 */
	private int iconId;
	/**
	 * �Ƿ�����
	 */
	private int isOnline;

	/**
	 *  
	 */
	public User() {
	}


	/**
	 * @return ���� id��
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            Ҫ���õ� id��
	 */
	public void setId(String id) {
		this.id = id.trim();
	}

	/**
	 * @return ���� name��
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            Ҫ���õ� name��
	 */
	public void setName(String name) {
		if (name == null)
			name = "";
		this.name = name.trim();
	}

	/**
	 * @return ���� password��
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            Ҫ���õ� password��
	 */
	public void setPassword(String password) {
		if (password == null)
			password = "";
		this.password = password;
	}


	/**
	 * @return ���� icon��
	 */
	public int getIconId() {
		return iconId;
	}

	/**
	 * @param icon
	 *            Ҫ���õ� icon��
	 */
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	/**
	 * @return ���� isOnline��
	 */
	public int getIsOnline() {
		return isOnline;
	}

	/**
	 * @param isOnline
	 *            Ҫ���õ� isOnline��
	 */
	public void setIsOnline(int isOnline) {
		if (isOnline == 1)
			this.isOnline = 1;
		else
			this.isOnline = 0;
	}

	public int hashCode() {
		return this.id.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof User) {
			return object.hashCode() == this.hashCode();
		}
		return false;
	}

	public String toString() {
		return this.getName();
	}

	public int compareTo(Object arg0) {
		return this.id.compareTo(arg0.toString());
	}

}