package com.ui;

/**
 * ��¼����������Ϣ
 * 
 * @author Administrator
 */

public class Config {

	/**
	 * ������IP
	 */
	private String serverIP = "";

	/**
	 * �������˿�
	 */
	private int port = -1;

	/**
	 * @return ���� port��
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            Ҫ���õ� port��
	 */
	public void setPort(int port) {
		this.port =  -1;
		if (port >= 0 && port <= 65535)
			this.port = port;
		
	}

	/**
	 * @return ���� serverIP��
	 */
	public String getServerIP() {
		return serverIP;
	}

	/**
	 * @param serverIP
	 *            Ҫ���õ� serverIP��
	 */
	public void setServerIP(String serverIP) {
		if (serverIP == null) {
			this.serverIP = "";
			return;
		}
		String ip[] = serverIP.split("\\.");
		boolean isOK = true;
		if (ip.length == 4) {
			int temp = -1;
			for (int i = 0; i < ip.length; i++) {
				try {
					temp = Integer.parseInt(ip[i]);
					if (!(temp >= 0 && temp < 256)) {
						isOK = false;
						break;
					}
				} catch (Exception e) {
					isOK = false;
					break;
				}
			}		
			if(isOK){
				this.serverIP = serverIP;
			}
		}					
	}
}