package com.pack;
/**
 * һ����Ϣ��
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class MessagePack extends Message {

	private String from;

	private String fromIP;
	
	private int fromPort;
	
	private String to;

	private String message;

	/**
	 * @return ���� from��
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            Ҫ���õ� from��
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getFromIP(){
		return fromIP;
	}

	public void setFromIP(String fromIP) {
		this.fromIP = fromIP;
	}

	/**
	 * @return ���� message��
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            Ҫ���õ� message��
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return ���� to��
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            Ҫ���õ� to��
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return ���� fromPort��
	 */
	public int getFromPort() {
		return fromPort;
	}

	/**
	 * @param fromPort Ҫ���õ� fromPort��
	 */
	public void setFromPort(int fromPort) {
		this.fromPort = fromPort;
	}
}
