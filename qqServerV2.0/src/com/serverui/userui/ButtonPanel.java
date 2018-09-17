package com.serverui.userui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * <code>serialVersionUID</code> µÄ×¢ÊÍ
	 */
	private static final long serialVersionUID = 1L;

	private JButton addBtn = new JButton("Ìí¼Ó");

	private JButton delBtn = new JButton("É¾³ý");

	private JButton makeBtn = new JButton("ÐÞ¸Ä");

	public ButtonPanel() {
		init();
	}

	public void init() {
		this.add(addBtn);
		this.add(delBtn);
		this.add(makeBtn);

		Dimension size = new Dimension(80, 25);
		addBtn.setPreferredSize(size);
		delBtn.setPreferredSize(size);
		makeBtn.setPreferredSize(size);
	}

	public void addButtonActionListener(ActionListener actionListener) {
		addBtn.addActionListener(actionListener);
		delBtn.addActionListener(actionListener);
		makeBtn.addActionListener(actionListener);

		addBtn.setActionCommand(UserUIConfig.ADDBTN);
		delBtn.setActionCommand(UserUIConfig.DELBTN);
		makeBtn.setActionCommand(UserUIConfig.MAKEBTN);
	}
}