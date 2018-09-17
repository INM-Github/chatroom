package com.qqServer;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class ServerMain {

	private static final int serverPort = 9000;
	
	public static int getServerPort(){
		return serverPort;
	}
	
	/**
	 * ��������������
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
		}
		System.out.println(MySqlConn.testConnection());
		if (MySqlConn.testConnection()) {
			ServerUi serverUI = ServerUi.getInstance();
			serverUI.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "�Բ���,��������Դ����.", "����",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}