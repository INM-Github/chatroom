package com.serverui.userui;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class IconCombobox extends JLabel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			this.setBackground(list.getSelectionBackground());
			this.setForeground(list.getSelectionForeground());
		} else {
			this.setBackground(list.getBackground());
			this.setForeground(list.getForeground());
		}

		this.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 3));
		this.setOpaque(true);

		return this;
	}

}