package com.serverui.onlineui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class OnLineTableUI extends JPanel{

	/**
	 * <code>serialVersionUID</code> ��ע��
	 */
	private static final long serialVersionUID = 1L;
	private JTable onLineTable = new JTable();
	
	public OnLineTableUI(){
		init();
	}

	public void init(){
		this.setLayout(new BorderLayout());
		this.add(onLineTable, BorderLayout.CENTER);		
	}
}
