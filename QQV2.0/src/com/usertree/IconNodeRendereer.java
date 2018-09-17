package com.usertree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class IconNodeRendereer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = -3494723715969483744L;

	// �������������д���Լ��鿴API������������˼

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		// IconTreeNode����д�ļ̳�DefaultMutableTreeNode�Ĵ�ͼƬ�Ľڵ�
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		if (((IconTreeNode) node).getIcon() != null) {
			Icon icon = ((IconTreeNode) node).getIcon();
			setIcon(icon);
		} else {
			setLeafIcon(new ImageIcon("image/QQ13.PNG"));
			setClosedIcon(new ImageIcon("image/QQ13.PNG"));
			// //����tree��Ҷ�ӽڵ�չ��ʱ��ͼƬ
			setOpenIcon(new ImageIcon("image/QQ14.PNG"));
		}

		String stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);

		setText(stringValue);

		return this;

	}

}