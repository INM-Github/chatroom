package com.serverui.onlineui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class BottomPane extends JPanel{

	private static final long serialVersionUID = 1L;

	private JTextArea afficheText = new JTextArea();

	private JButton sendBtn = new JButton("����");
	
	private JComboBox sendCom = new JComboBox();
	
	private List sendList = null;

	public BottomPane(){
		init();
	}
	
	public void init(){		
		JScrollPane jsp = new JScrollPane(afficheText);
		jsp.setPreferredSize(new Dimension(0, 100));
		JPanel sendPanel = new JPanel();
		sendPanel.setLayout(new BorderLayout());
		sendPanel.add(sendBtn);
		sendPanel.add(sendCom, BorderLayout.NORTH);

		//this.sendBtn.setEnabled(false);
		this.sendBtn.setActionCommand(OnLineConfig.SENDMESSAGEBTN);		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(sendPanel, BorderLayout.EAST);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ϵͳ����"));
		this.initSendCom();
	}
	
	public void addButtonActionListener(ActionListener actionListener){
		this.sendBtn.addActionListener(actionListener);
		this.sendBtn.setActionCommand(OnLineConfig.SENDMESSAGEBTN);
	}
	
	public String getMessage(){
		return afficheText.getText();
	}
	
	public void clear(){
		this.afficheText.setText("");
	}
	
	public void initSendCom(){
//		sendCom.removeAllItems();
//		sendList = new ArrayList();
//		List tempList = DepartmentDaoFactory.getDepartmentDao().select(null);
//		Iterator it = tempList.iterator();
//		while(it.hasNext()){
//			sendList.add(it.next());
//		}
//		Department department = null;
//		it = tempList.iterator();
//		while(it.hasNext()){
//			department = (Department)it.next();
//			sendCom.addItem(department.getName());
//		}
	}
	
	public Object getSendObject(){
		return sendList.get(sendCom.getSelectedIndex());
	}
}
