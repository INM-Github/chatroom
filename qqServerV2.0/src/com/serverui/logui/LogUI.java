package com.serverui.logui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.qqServer.PubToolkit;


/**
 * ��־���
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class LogUI extends JPanel {

	/**
	 * <code>serialVersionUID</code> ��ע��
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ������
	 */
	//private JFrame mainFrame;

	/**
	 * ��ʷ��¼��ѯ���
	 */
	private LogHistoryUI logHistoryUI = new LogHistoryUI();

	/**
	 * ��ǰ��־���
	 */
	private TodayLogUI todayLogUI = new TodayLogUI();

	/**
	 * ��־��幹�캯��
	 * @param mainFrame
	 */
	public LogUI(JFrame mainFrame) {
		//this.mainFrame = mainFrame;
		init();
	}

	/**
	 * ��ʼ��
	 *
	 */
	public void init() {
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				todayLogUI, logHistoryUI);
		jsp.setOneTouchExpandable(true);
		jsp.setDividerLocation(390);
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.addButtonActionListener(new LogUIButtonActionListener(this));
		this.addLog(LogOper.getInstance().getLog(PubToolkit.getDate(), null).toString());
	}

	/**
	 * ��Ӱ�ť����
	 * @param a
	 */
	public void addButtonActionListener(ActionListener a) {
		logHistoryUI.addButtonActionListener(a);
	}

	/**
	 * ������ʷ��ѯ��¼
	 * @param logs
	 */
	public void setLogText(String logs) {
		this.logHistoryUI.setLogText(logs);
	}

	/**
	 * ȡ�õ������־
	 * @return
	 */
	public String getLog() {
		return todayLogUI.getTodayLog();
	}
	
	/**
	 * ��ӵ������־
	 * @param log
	 */
	public void addLog(String log) {
		todayLogUI.addTodayLog(log+"\n");
	}

	/**
	 * ȡ�ò�ѯ������
	 * @return
	 */
	public String getDate() {
		return logHistoryUI.getDate();
	}

	/**
	 * �����־
	 *
	 */
	public void clearLog() {
		todayLogUI.clearLog();
	}

	/**
	 * ȡ�ò�ѯ���û�ID
	 * @return
	 */
	public String getChangeUserId(){
		return this.logHistoryUI.getChangeUserId();
	}
}
/**
 * ��־���İ�ť������
 * @author Administrator
 *
 */
class LogUIButtonActionListener implements ActionListener {

	private LogUI logUI;

	public LogUIButtonActionListener(LogUI logUI) {
		this.logUI = logUI;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String command = e.getActionCommand();

			/*if (command.equals(LogUIConfig.SAVEBTN)) {
				if (LogOper.getInstance().saveLog(logUI.getLog())) {
					logUI.clearLog();
					PubToolkit.showInformation("��ϲ,������־�ɹ�!");
				} else {
					PubToolkit.showInformation("�ⲻ��,������־ʧ��!");
				}
				return;
			} else*/ if (command.equals(LogUIConfig.SEARCHBTN)) {
				logUI.setLogText("");
				StringBuffer logs = LogOper.getInstance().getLog(
						logUI.getDate(), logUI.getChangeUserId());
				if(logs.length()<=0){
					logUI.setLogText("�Բ���,û���ҵ���ؼ�¼!");
					return;
				}
				logUI.setLogText(logs.toString());
			} else if (command.equals(LogUIConfig.CHANGEBTN)) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(logUI);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					LogOper.getInstance().setLogFileName(chooser.getSelectedFile().getPath());					
				}

			}
		}
	}
}
