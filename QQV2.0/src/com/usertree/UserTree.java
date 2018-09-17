package com.usertree;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTree;

import com.qq.Group;
import com.qq.User;
/**
 * �û�����
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class UserTree extends JTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>serialVersionUID</code> ��ע��
	 */
    //�߳�
	private MyTimerUpTree myTimerUpTree;
	private Set userSet = new TreeSet();
	private static Set groupSet = new TreeSet();
	public Set getGroupSet() {
		return groupSet;
	}
	public void setGroupSet(Set groupSet) {
		this.groupSet = groupSet;
	}

	static IconTreeNode Fgroup = new IconTreeNode("�ҵĺ���");
	static IconTreeNode Ggroup = new IconTreeNode("�ҵ�Ⱥ��");
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
	 * ���һ���û�
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
//	 * ɾ��һ���û�
//	 * @param user
//	 */	
//	public void deleteUser(User user) {
//		System.out.println("ִ���û�ɾ��");
//		updateTree();
//	}

	/**
	 * ˢ���û���
	 *
	 */
	public void updateTree() {
		
		if (myTimerUpTree == null || !myTimerUpTree.isAlive()) {   //�̵߳�isAlive()�����ж��߳��Ƿ����ڻ�Ծ״̬
			myTimerUpTree = new MyTimerUpTree(50, this);
			myTimerUpTree.setDaemon(true);
			myTimerUpTree.start();
		} else
			myTimerUpTree.setCancel();
	}
}