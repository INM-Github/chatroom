package com.serverui.userui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.Serverimage.GetImage;



/**
 * @author Administrator
 */
public class UserTableIconCellReader extends JPanel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	private JLabel iconLabel = new JLabel();
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setVerticalAlignment(JLabel.CENTER);
		int iconId = 1;
		try {
			iconId = Integer.parseInt((String) value);
		} catch (Exception e) {

		}
		ImageIcon imageIcon = GetImage.getMinHead(iconId);
		if (imageIcon != null) {
			iconLabel.setIcon(imageIcon);
			iconLabel.setText(null);
		} else {
			iconLabel.setIcon(null);
			iconLabel.setText("û��ͷ��");
		}
		this.add(iconLabel);
		this.setBackground(isSelected?table.getSelectionBackground():table.getBackground());
		return this;
	}
}