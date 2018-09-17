package com.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;


/**
 * @author Administrator
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class MessageUI extends JDialog implements ItemListener {

	/**
	 * <code>serialVersionUID</code> 的注释
	 */
	private static final long serialVersionUID = 1L;

	private List list = new ArrayList();

	private JComboBox changeMsg = new JComboBox();

	private JTextArea messageText = new JTextArea();

	public MessageUI(JFrame mainFrame) {
		super(mainFrame, true);
		init();
	}

	private void init() {
		this.setTitle("系统消息");
		this.getContentPane().add(changeMsg, BorderLayout.NORTH);
		this.getContentPane().add(messageText, BorderLayout.CENTER);
		this.setSize(new Dimension(400, 300));
		this.setLocationRelativeTo(null);
		changeMsg.addItemListener(this);
	}

	public void addMessage(String msg) {
		list.add(msg);
		changeMsg.addItem("NO " + this.list.size());
	}

	public void itemStateChanged(ItemEvent e) {
		messageText.setText((String) list.get(changeMsg.getSelectedIndex()));
		System.out.println(changeMsg.getSelectedIndex());
	}
}

