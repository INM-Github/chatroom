package com.usertree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class IconNodeRendereer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = -3494723715969483744L;

	// 下面这个方法重写请自己查看API各个参数的意思

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		// IconTreeNode是重写的继承DefaultMutableTreeNode的带图片的节点
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		if (((IconTreeNode) node).getIcon() != null) {
			Icon icon = ((IconTreeNode) node).getIcon();
			setIcon(icon);
		} else {
			setLeafIcon(new ImageIcon("image/QQ13.PNG"));
			setClosedIcon(new ImageIcon("image/QQ13.PNG"));
			// //设置tree非叶子节点展开时的图片
			setOpenIcon(new ImageIcon("image/QQ14.PNG"));
		}

		String stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);

		setText(stringValue);

		return this;

	}

}