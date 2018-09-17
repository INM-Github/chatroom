package com.usertree;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
/**
 * 
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
