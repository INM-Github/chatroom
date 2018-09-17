package com.pack;

import java.io.UnsupportedEncodingException;

import com.qqServer.Group;
import com.qqServer.User;

/**
 * �����  
 * @author Administrator 
 */
public class PackOper implements PackInterface {

	/**
	 * ��¼����ʶ
	 */
	public static final String LOGIN = "10";

	/**
	 * ��¼�ɹ���ʶ
	 */
	public static final String LOGIN_SUCCEED = "11";

	/**
	 * ��¼ʧ�ܱ�ʶ
	 */ 
	public static final String LOGIN_DEFEATED = "12";

	/**
	 * �Ѿ����߱�ʶ
	 */
	public static final String LOGIN_ONLINED = "13";

	/**
	 *  ����û�����ʶ
	 */
	public static final String ADD_USER = "16";
	/**
	 *  ��ӷ������ʶ
	 */
	public static final String ADD_GROUP = "17";
	/**
	 *  �����û���Ϣ
	 */
	public static final String UPDATE_USER = "19";
	
	/*
	 * Ⱥ��
	 */
	public static final String  CHAT_GROUP= "20";
	
	
	/**
	 * ɾ���û�
	 */
	public static final String DELETE_USER = "21";	

	/**
	 * ˽��
	 */ 
	public static final String CHAT_USER = "22";
	/**
	 *  ���߰�
	 */
	public static final String DOWN_LINE = "29";

	/**
	 *  ǿ�����߰�
	 */
	public static final String FORCEDOWN_LINE = "30";

	/**
	 *  �޸�����
	 */
	public static final String UPPASSWORD = "31";

	/**
	 *  ԭ���벻��
	 */
	public static final String UPPASSWORD_DEFEATED = "32";

	/** 
	 * ������Ϊ��
	 */
	public static final String UPPASSWORD_NEW_NULL = "33";

	/**
	 * �޸�����ɹ�
	 */ 
	public static final String UPPASSWORD_SUCCEED = "34";

	/**
	 *  �������ر�
	 */
	public static final String SERVERCLOSE = "35";

	/**
	 * ���õ�һģʽ
	 */
	private static PackOper packOper;

	private PackOper() {
	}

	/**
	 * ���þ�̬����ȡ�ý�����ʵ��
	 * @return  PackOper
	 */
	public static PackOper getInstance() {
		if (packOper == null) {
			packOper = new PackOper();
		}
		return packOper;
	}

	/**
	 * ����һ����,����object����
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
	 * ������Ϣ��
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
	 * �����û���
	 * @param user
	 * @return
	 */
	private byte[] createUser(User user) {
		return createByte(STARTSEPARATOR + user.getType() + SEAS + user.getId()
				+ SEAS + user.getName() + SEAS + user.getPassword() + SEAS
				+ user.getIconId() + SEAS + user.getIsOnline()  + SEAS + ENDSEPARATOR);
	}
	/**
	 * ����Ⱥ�İ�
	 * @param user
	 * @return
	 */
	private byte[] createGroup(Group group) {
		return createByte(STARTSEPARATOR + group.getType()  + SEAS +group.getNo()+ SEAS +group.getName()  + SEAS
				+ group.getGroupdate()  + SEAS + ENDSEPARATOR);
	}
	/**
	 * ת�����ֽ�
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
	 * ���
	 * @param bytes
	 * @return Message
	 */
	public Message unbindPackage(byte[] bytes) {
		String[] strs = unbindByte(bytes);
		System.out.println("������:" + strs[0]);
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
	 * ���ֽ�ת�����ַ�����
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
	 * ��һ����Ϣ��
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
	 * ���û���
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
	 * ��Ⱥ�İ�
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