package com.serverui.logui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.qqServer.PubToolkit;
import com.qqServer.User;

/**
 * ��־������
 * @author Administrator
 */
public class LogOper {
	/**
	 * ���õ�һģʽ
	 */
	private static LogOper logOper;

	/**
	 * ��־Ĭ�ϱ����ļ���
	 */	
	private String logFileName = "server.log";

	/**
	 * �ܱ�������־�๹�캯��
	 *
	 */
	private LogOper() {
	}

	/**
	 * ��һģʽȡ��ʵ���ľ�̬����
	 * @return LogOper
	 */
	public static LogOper getInstance() {
		if (logOper == null)
			logOper = new LogOper();
		return logOper;
	}

	/**
	 * ���ĳ�û�������Ϣ
	 * @param user
	 * @return
	 */
	public String insertOnLineLog(User user) {
		String str = "[" + PubToolkit.getDateTime() + "]" + "ID:"
				+ user.getId() + "�û�:" + user.getName() + " ����";
		this.saveLog(str);
		return str;
	}

	/**
	 * ���ĳ�û�������Ϣ
	 * @param user
	 * @return
	 */
	public String insertDownLineLog(User user) {
		String str = "[" + PubToolkit.getDateTime() + "]" + "ID:"
				+ user.getId() + "�û�:" + user.getName() + " ����";
		this.saveLog(str);
		return str;
	}

	/**
	 * ���ĳ�û���ǿ��������Ϣ
	 * @param user
	 * @return
	 */
	public String insertForceDownLineLog(User user) {
		String str = "[" + PubToolkit.getDateTime() + "]" + "ID:"
				+ user.getId() + "�û�:" + user.getName() + " ��ǿ������";
		this.saveLog(str);
		return str;
	}

	/**
	 * �����Ϣ������������Ϣ
	 * @return
	 */
	public String insertStartServerLog() {
		String str = "[" + PubToolkit.getDateTime() + "]" + " ��Ϣ�����������ɹ�.";
		this.saveLog(str);
		return str;
	}

	/**
	 * �����Ϣ�������ر���Ϣ
	 * @return
	 */
	public String insertStopServerLog() {
		String str = "[" + PubToolkit.getDateTime() + "]" + " ��Ϣ�������رճɹ�.";
		this.saveLog(str);
		return str;
	}

	/**
	 * �����Ϣ����������ʧ����Ϣ
	 * @return
	 */
	public String insertStartServerErrorLog() {
		String str = "[" + PubToolkit.getDateTime() + "]" + " ��Ϣ����������ʧ��.";
		this.saveLog(str);
		return str;
	}

	/**
	 * �����Ϣ�������ر�ʧ����Ϣ
	 * @return
	 */
	public String insertStopServerErrorLog() {
		String str = "[" + PubToolkit.getDateTime() + "]" + " ��Ϣ�������ر�ʧ��.";
		this.saveLog(str);
		return str;
	}

	/**
	 * ��ѯ��־
	 * @param data ����
	 * @param id   ĳ�û�ID,Ϊ�����ѯ"DATA"��ָ���ڵ����м�¼
	 * @return
	 */
	public StringBuffer getLog(String data, String id) {
		StringBuffer strBuf = new StringBuffer();
		FileReader reader = null;
		BufferedReader bufReader = null;
		String temp = null;
		if(id == null)
			id = "";
		if(data == null)
			return strBuf;
		try {
			reader = new FileReader(this.logFileName);
			bufReader = new BufferedReader(reader);
			while ((temp = bufReader.readLine()) != null) {
				if (temp.indexOf(data) >= 0 && temp.indexOf(id) >= 0) {
					strBuf.append(temp + "\n");					
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
				}
				bufReader = null;
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
				reader = null;
			}
		}
		return strBuf;
	}

	/**
	 * ������־
	 * @param log
	 * @return
	 */
	public boolean saveLog(String log) {
		FileWriter writer = null;
		BufferedWriter bufWriter = null;

		try {
			writer = new FileWriter(logFileName, true);
			bufWriter = new BufferedWriter(writer);
			bufWriter.write(log + "\n");
			bufWriter.flush();
			return true;
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			if (bufWriter != null) {
				try {
					bufWriter.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
				bufWriter = null;
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// e.printStackTrace();
				}
				writer = null;
			}
		}
		return false;
	}

	/**
	 * ���ñ�����־���ļ�
	 * @param logFileName
	 */
	public void setLogFileName(String logFileName) {
		File file = new File(logFileName);
		if (file.isFile()) {
			this.logFileName = logFileName;
		}
	}

}
