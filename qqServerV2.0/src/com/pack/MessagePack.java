package com.pack;
/**
 * 一般消息类
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class MessagePack extends Message {

	private String from;

	private String fromIP;
	
	private int fromPort;
	
	private String to;

	private String message;

	/**
	 * @return 返回 from。
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            要设置的 from。
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
	 * @return 返回 message。
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            要设置的 message。
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return 返回 to。
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            要设置的 to。
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return 返回 fromPort。
	 */
	public int getFromPort() {
		return fromPort;
	}

	/**
	 * @param fromPort 要设置的 fromPort。
	 */
	public void setFromPort(int fromPort) {
		this.fromPort = fromPort;
	}
}
