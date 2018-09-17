package com.usertree;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.qq.Group;
import com.qq.User;
import com.ui.GetImage;

public class IconTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = -8044774991176225332L;

	protected Icon icon;

	protected User user;
	
	protected Group group;
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public IconTreeNode(String fname) {// 包装对象为User实例的结点的构造方法
		super(fname, true);
		this.icon = null;
	}

	public IconTreeNode(User user) {// 包装对象为User实例的结点的构造方法

		this(user, false);
	}
	public IconTreeNode(Group group) {// 包装对象为User实例的结点的构造方法

		this(group, false);
	}
	public IconTreeNode(Group group, boolean allowsChildren) {// allowsChildren也就是准不准许有孩子节点

		super(group, allowsChildren);
		this.group = group;
		ImageIcon im1 = new ImageIcon("head/group1.jpg");
		setIcon(im1);
	}
	public IconTreeNode(User user, boolean allowsChildren) {// allowsChildren也就是准不准许有孩子节点

		super(user, allowsChildren);
		System.out.println(getIcon()+"ss");
		this.user = user;
		ImageIcon im1 = GetImage.getMinHead(user.getIconId());
		setIcon(im1);
	}

	public void setIcon(Icon icon) {

		this.icon = icon;

	}

	public Icon getIcon() {

		return icon;

	}

	public User getUser() {

		return user;

	}

	public void setUser(User user) {

		this.user = user;
	}


}
