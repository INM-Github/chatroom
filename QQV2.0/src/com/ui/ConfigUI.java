package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * @author Administrator
 * 
 * �������
 */
public class ConfigUI extends JPanel {

	/**
	 * <code>serialVersionUID</code> ��ע��
	 */
	private static final long serialVersionUID = 1L;

	private Box myBox = Box.createVerticalBox();

	private JLabel labelAddress = new JLabel("IP��ַ");

	private JLabel labelPort = new JLabel("�˿�");

	private JTextField tfAddress = new JTextField();

	private JTextField tfPort = new JTextField();

	/**
	 * ���캯��
	 * 
	 */
	public ConfigUI() {
		init();
	}

	/**
	 * ��ʼ������
	 * 
	 */
	public void init() {
		this.setOpaque(false);
		Dimension dim = new Dimension(318,100);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);

		this.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(new Color(140,186,213)), " �������� ", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION, Font.getFont("1"), Color.BLACK));

		this.setLayout(new BorderLayout());
		this.add(myBox, BorderLayout.CENTER);

		dim = new Dimension(40, 20);
		labelAddress.setPreferredSize(dim);
		labelAddress.setMaximumSize(dim);
		labelAddress.setMinimumSize(dim);

		labelPort.setPreferredSize(dim);
		labelPort.setMaximumSize(dim);
		labelPort.setMaximumSize(dim);

		dim = new Dimension(149, 20);
		tfAddress.setPreferredSize(dim);
		tfAddress.setMaximumSize(dim);
		tfAddress.setMinimumSize(dim);

		dim = new Dimension(50, 20);
		tfPort.setPreferredSize(dim);
		tfPort.setMaximumSize(dim);
		tfPort.setMinimumSize(dim);

		myBox.add(Box.createHorizontalStrut(5));

		JPanel tmpPanel = new JPanel();
		tmpPanel.setOpaque(false);
		tmpPanel.add(labelAddress);
		tmpPanel.add(tfAddress);
		tmpPanel.add(labelPort);
		tmpPanel.add(tfPort);
		myBox.add(tmpPanel);
	}

	public Config getConfig(){
		Config config = new Config();
		config.setServerIP(this.getAddress());
		config.setPort(this.getPort());
		System.out.println("Get Port:"+this.getPort());
		System.out.println("Config port:"+config.getPort());
		return config;
	}
	
	public void setConfig(Config config){
		this.setAddress(config.getServerIP());
		this.setPort(config.getPort());
	}
	
	/**
	 * ȡ�õ�ַ
	 * 
	 * @return
	 */
	public String getAddress() {
		String str[] = tfAddress.getText().split("\\.");
		int ip;
		System.out.println("length=" + str.length);
		if (str.length != 4) {
			return null;
		}
		for (int i = 0; i < str.length; i++) {
			try {
				ip = Integer.parseInt(str[i]);
				if (!(ip >= 0 && ip <= 255)) {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
		return tfAddress.getText();
	}

	/**
	 * ȡ�ö˿�
	 * 
	 * @return
	 */
	public int getPort() {
		try {
			return Integer.parseInt(tfPort.getText());
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return -1;
	}

	/**
	 * ���õ�ַ
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		String str[] = address.split("\\.");
		int ip;
		if (str.length != 4) {
			tfAddress.setText("");
			return;
		}
		for (int i = 0; i < str.length; i++) {
			try {
				ip = Integer.parseInt(str[i]);
				if (!(ip >= 0 && ip <= 255)) {
					return;
				}
			} catch (Exception e) {
				return;
			}
		}
		tfAddress.setText(address);
	}

	/**
	 * ���ö˿�
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		if (port > 0 && port < 65536) {
			tfPort.setText("" + port);
		}
	}

}