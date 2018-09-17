package com.usertree;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
/**
 * 
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class MyTimerUpTree extends Thread {

	private int runTime;

	private boolean isStop = false;

	private boolean isCancel = false;

	private JTree tree;

	public MyTimerUpTree(int runTime, JTree tree) {
		this.runTime = runTime;
		this.tree = tree;
	}

	public void run() {
		while (!isStop) {
			try {
				Thread.sleep(runTime);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			if (!isCancel) {
				try{
					SwingUtilities.updateComponentTreeUI(tree);
				}catch(Exception e1){
					
				}
				setStop();
			}
			isCancel = false;
		}
	}

	public void setStop() {
		isStop = true;
	}

	public void setCancel() {
		isCancel = true;
	}
}
