package com.qq;

import com.pack.Message;

/**
 * 员工数据BEAN
 * 
 * @author Administrator
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class User extends Message implements Comparable {
	/**
	 * 编号
	 */
	private String id = "";

	/**
	 * 姓名
	 */
	private String name = "";

	/**
	 * 密码
	 */
	private String password = "";
	/**
	 * 头像编号
	 */
	private int iconId;
	/**
	 * 是否在线
	 */
	private int isOnline;

	/**
	 *  
	 */
	public User() {
	}


	/**
	 * @return 返回 id。
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id。
	 */
	public void setId(String id) {
		this.id = id.trim();
	}

	/**
	 * @return 返回 name。
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name。
	 */
	public void setName(String name) {
		if (name == null)
			name = "";
		this.name = name.trim();
	}

	/**
	 * @return 返回 password。
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的 password。
	 */
	public void setPassword(String password) {
		if (password == null)
			password = "";
		this.password = password;
	}


	/**
	 * @return 返回 icon。
	 */
	public int getIconId() {
		return iconId;
	}

	/**
	 * @param icon
	 *            要设置的 icon。
	 */
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	/**
	 * @return 返回 isOnline。
	 */
	public int getIsOnline() {
		return isOnline;
	}

	/**
	 * @param isOnline
	 *            要设置的 isOnline。
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