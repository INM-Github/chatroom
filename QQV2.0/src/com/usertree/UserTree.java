package com.usertree;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTree;

import com.qq.Group;
import com.qq.User;
/**
 * 用户树类
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class UserTree extends JTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>serialVersionUID</code> 的注释
	 */
    //线程
	private MyTimerUpTree myTimerUpTree;
	private Set userSet = new TreeSet();
	private static Set groupSet = new TreeSet();
	public Set getGroupSet() {
		return groupSet;
	}
	public void setGroupSet(Set groupSet) {
		this.groupSet = groupSet;
	}

	static IconTreeNode Fgroup = new IconTreeNode("我的好友");
	static IconTreeNode Ggroup = new IconTreeNode("我的群聊");
	IconTreeNode node;
	public UserTree(User user){
		super(Fgroup);
		this.setCellRenderer(new IconNodeRendereer());
	}
	public UserTree(Group group){
		super(Ggroup);
		this.setCellRenderer(new IconNodeRendereer());
	}
	/**
	 * 添加一个用户
	 */	
	public void addUser(User user) {
		node =  new IconTreeNode(user);
		Fgroup.add(node);
		userSet.add(user);
		updateTree();
	}
	public User findUser(String id){
		Iterator it = userSet.iterator();
		System.out.println(userSet);
		while(it.hasNext()){
			User user = (User)it.next();
			if(user.getId().equals(id)){
				return user;
			}
		}
		return null;
	}	
	public void addGroup(Group group) {
		node =  new IconTreeNode(group);
		Ggroup.add(node);
		groupSet.add(group);
		updateTree();
	}
	public Group findGroup(String no){
		Iterator it = groupSet.iterator();
		System.out.println(groupSet);
		while(it.hasNext()){
			Group group = (Group)it.next();
			if(group.getNo().equals(no)){
				return group;
			}
		}
		return null;
	}
//	/**
//	 * 删除一个用户
//	 * @param user
//	 */	
//	public void deleteUser(User user) {
//		System.out.println("执行用户删除");
//		updateTree();
//	}

	/**
	 * 刷新用户树
	 *
	 */
	public void updateTree() {
		
		if (myTimerUpTree == null || !myTimerUpTree.isAlive()) {   //线程的isAlive()方法判断线程是否是在活跃状态
			myTimerUpTree = new MyTimerUpTree(50, this);
			myTimerUpTree.setDaemon(true);
			myTimerUpTree.start();
		} else
			myTimerUpTree.setCancel();
	}
}