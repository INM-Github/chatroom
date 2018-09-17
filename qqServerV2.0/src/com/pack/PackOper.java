package com.pack;

import java.io.UnsupportedEncodingException;

import com.qqServer.Group;
import com.qqServer.User;

/**
 * 解包类  
 * @author Administrator 
 */
public class PackOper implements PackInterface {

	/**
	 * 登录包标识
	 */
	public static final String LOGIN = "10";

	/**
	 * 登录成功标识
	 */
	public static final String LOGIN_SUCCEED = "11";

	/**
	 * 登录失败标识
	 */ 
	public static final String LOGIN_DEFEATED = "12";

	/**
	 * 已经在线标识
	 */
	public static final String LOGIN_ONLINED = "13";

	/**
	 *  添加用户包标识
	 */
	public static final String ADD_USER = "16";
	/**
	 *  添加分组包标识
	 */
	public static final String ADD_GROUP = "17";
	/**
	 *  更新用户信息
	 */
	public static final String UPDATE_USER = "19";
	
	/*
	 * 群聊
	 */
	public static final String  CHAT_GROUP= "20";
	
	
	/**
	 * 删除用户
	 */
	public static final String DELETE_USER = "21";	

	/**
	 * 私聊
	 */ 
	public static final String CHAT_USER = "22";
	/**
	 *  下线包
	 */
	public static final String DOWN_LINE = "29";

	/**
	 *  强制下线包
	 */
	public static final String FORCEDOWN_LINE = "30";

	/**
	 *  修改密码
	 */
	public static final String UPPASSWORD = "31";

	/**
	 *  原密码不对
	 */
	public static final String UPPASSWORD_DEFEATED = "32";

	/** 
	 * 新密码为空
	 */
	public static final String UPPASSWORD_NEW_NULL = "33";

	/**
	 * 修改密码成功
	 */ 
	public static final String UPPASSWORD_SUCCEED = "34";

	/**
	 *  服务器关闭
	 */
	public static final String SERVERCLOSE = "35";

	/**
	 * 采用单一模式
	 */
	private static PackOper packOper;

	private PackOper() {
	}

	/**
	 * 采用静态方法取得解包类的实例
	 * @return  PackOper
	 */
	public static PackOper getInstance() {
		if (packOper == null) {
			packOper = new PackOper();
		}
		return packOper;
	}

	/**
	 * 创建一个包,根据object类型
	 * @param object
	 * @return
	 */
	public byte[] createPackage(Object object) {
		if (object == null) {
			return null;
		}

		if (object instanceof MessagePack) {
			return createMessagePack((MessagePack) object);
		}

		if (object instanceof User) {
			return createUser((User) object);
		}
		if (object instanceof Group) {
			return createGroup((Group) object);
		}
		return null;
	}

	/**
	 * 创建消息包
	 * @param pack
	 * @return
	 */
	private byte[] createMessagePack(MessagePack pack) {
		return createByte(STARTSEPARATOR + pack.getType() + SEAS
				+ pack.getFrom() + SEAS + pack.getFromIP() + SEAS
				+ pack.getFromPort() + SEAS + pack.getTo() + SEAS
				+ pack.getMessage() + ENDSEPARATOR);
	}

	/**
	 * 创建用户包
	 * @param user
	 * @return
	 */
	private byte[] createUser(User user) {
		return createByte(STARTSEPARATOR + user.getType() + SEAS + user.getId()
				+ SEAS + user.getName() + SEAS + user.getPassword() + SEAS
				+ user.getIconId() + SEAS + user.getIsOnline()  + SEAS + ENDSEPARATOR);
	}
	/**
	 * 创建群聊包
	 * @param user
	 * @return
	 */
	private byte[] createGroup(Group group) {
		return createByte(STARTSEPARATOR + group.getType()  + SEAS +group.getNo()+ SEAS +group.getName()  + SEAS
				+ group.getGroupdate()  + SEAS + ENDSEPARATOR);
	}
	/**
	 * 转换成字节
	 * @param str
	 * @return
	 */
	private byte[] createByte(String str) {
		try {
			return str.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解包
	 * @param bytes
	 * @return Message
	 */
	public Message unbindPackage(byte[] bytes) {
		String[] strs = unbindByte(bytes);
		System.out.println("包类型:" + strs[0]);
		if (strs != null) {
			if (strs[0].equals(PackOper.ADD_USER)
					|| strs[0].equals(PackOper.UPDATE_USER)
					|| strs[0].equals(PackOper.LOGIN)
					|| strs[0].equals(PackOper.LOGIN_DEFEATED)
					|| strs[0].equals(PackOper.LOGIN_SUCCEED)
					|| strs[0].equals(PackOper.LOGIN_ONLINED)
					|| strs[0].equals(PackOper.DOWN_LINE)
					|| strs[0].equals(PackOper.FORCEDOWN_LINE)
					|| strs[0].equals(PackOper.DELETE_USER)) {
				return unbindUser(strs);
			}
			if(strs[0].equals(PackOper.ADD_GROUP)){
				return unbindGroup(strs);
			}

			return unbindMessagePack(strs);
		}
		return null;
	}

	/**
	 * 把字节转换成字符数组
	 * @param bytes
	 * @return
	 */
	private String[] unbindByte(byte[] bytes) {
		try {
			StringBuffer str = new StringBuffer(new String(bytes, CHARSET));
			str.delete(0, STARTSEPARATOR.length());
			str.delete(str.length() - ENDSEPARATOR.length(), str.length());
			String tempStr = str.toString().replace(SEPARATORAS.charAt(0), ' ');
			String[] strs = tempStr.split(SEPARATOR);
			for (int i = 0; i < strs.length; i++) {
				strs[i] = strs[i].trim();
			}
			return strs;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解一般消息包
	 * @param strs
	 * @return
	 */
	private Message unbindMessagePack(String[] strs) {
		MessagePack messagePack = null;
		if (strs != null) {
			try {
				messagePack = new MessagePack();
				messagePack.setType(strs[0]);
				messagePack.setFrom(strs[1]);
				messagePack.setFromIP(strs[2]);
				messagePack.setFromPort(Integer.parseInt(strs[3]));
				messagePack.setTo(strs[4]);
				messagePack.setMessage(strs[5]);
			} catch (Exception e) {
				return null;
			}
		}
		return messagePack;
	}
	/**
	 * 解用户包
	 * @param strs
	 * @return
	 */
	private Message unbindUser(String[] strs) {
		User user = null;
		if (strs != null) {
			try {
				user = new User();
				user.setType(strs[0]);
				user.setId(strs[1]);
				user.setName(strs[2]);
				user.setPassword(strs[3]);
				user.setIconId(Integer.parseInt(strs[4]));
				user.setIsOnline(Integer.parseInt(strs[5]));
			} catch (Exception e) {

			}
		}
		return user;
	}
	/**
	 * 解群聊包
	 * @param strs
	 * @return
	 */
	private Message unbindGroup(String[] strs) {
		Group group = null;
		if (strs != null) {
			try {
				group = new Group();
				group.setType(strs[0]);
				group.setNo(strs[1]);
				group.setName(strs[2]);
				group.setGroupdate(strs[3]);
			} catch (Exception e) {

			}
		}
		return group;
	}
}