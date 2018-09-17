package com.serverui.userui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Serverimage.GetImage;
import com.dao.MySQLUserDao;
import com.pack.PackOper;
import com.qqServer.ClientThread;
import com.qqServer.PubToolkit;
import com.qqServer.PubValue;
import com.qqServer.ServerUi;
import com.qqServer.User;


/**
 * @author Administrator
 */
public class UserMakeDialog extends JDialog {

	/**
	 * <code>serialVersionUID</code> ��ע��
	 */
	private static final long serialVersionUID = 1L;

	private JLabel idLabel = new JLabel("�û���ţ�");

	private JLabel nameLabel = new JLabel("�û����ƣ�");

	private JLabel passwordLabel = new JLabel("��    �룺");

	private JLabel sexLabel = new JLabel("��    ��");

	private JLabel deptLabel = new JLabel("��    �ţ�");

	private JLabel ageLable = new JLabel("��    �䣺");

	private JLabel telLable = new JLabel("��    ����");

	private JLabel addressLabel = new JLabel("��    ַ��");

	private JLabel regtimeLabel = new JLabel("ע��ʱ�䣺");

	private JLabel isOnlineLabel = new JLabel("�Ƿ����ߣ�");

	private JTextField idText = new JTextField();

	private JTextField nameText = new JTextField();

	private JPasswordField passwordText = new JPasswordField("123456");

	private JTextField ageText = new JTextField();

	private JTextField telText = new JTextField();

	private JTextField addressText = new JTextField();

	private JTextField regtimeText = new JTextField();

	private JTextField isOnlineText = new JTextField();

	private JComboBox iconComb = new JComboBox();

	private JComboBox sexComb = new JComboBox();

	private JComboBox deptComb = new JComboBox();

	private JButton saveBtn = new JButton("������Ϣ");

	private JButton cancelBtn = new JButton("ȡ������");

	private JButton passwordResetBtn = new JButton("��������");

	private List depts;

	private User user;

	private String type;

	public UserMakeDialog(JFrame mainFrame, String title, User user, String type) {
		super(mainFrame, title, true);
		this.user = user;
		this.type = type;
		init();
		this.setState(type);
	}

	private void setState(String type){
		if(type.equals(UserUIConfig.ADDBTN)){
			passwordResetBtn.setVisible(false);
		}else if(type.equals(UserUIConfig.MAKEBTN)){
			passwordResetBtn.setVisible(true);
		}
	}
	
	public void init() {
		this.idText.setEditable(false);
		this.regtimeText.setEditable(false);
		this.passwordText.setEnabled(false);
		this.isOnlineText.setEnabled(false);

		Box mainBox = Box.createVerticalBox();

		Box tempBox1 = Box.createVerticalBox();

		Box tempBox2 = Box.createHorizontalBox();
		tempBox2.add(idLabel);
		tempBox2.add(idText);

		tempBox1.add(tempBox2);

		tempBox2 = Box.createHorizontalBox();
		tempBox2.add(nameLabel);
		tempBox2.add(nameText);
		tempBox1.add(Box.createVerticalStrut(15));
		tempBox1.add(tempBox2);

		tempBox2 = Box.createHorizontalBox();
		tempBox2.add(tempBox1);
		tempBox2.add(Box.createHorizontalStrut(20));
		tempBox2.add(iconComb);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(tempBox2);

		Box temp = Box.createHorizontalBox();
		temp.add(passwordLabel);
		temp.add(passwordText);
		temp.add(isOnlineLabel);
		temp.add(isOnlineText);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(temp);

		tempBox1 = Box.createHorizontalBox();
		tempBox1.add(sexLabel);
		tempBox1.add(sexComb);
		tempBox1.add(ageLable);
		tempBox1.add(ageText);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(tempBox1);

		tempBox1 = Box.createHorizontalBox();
		tempBox1.add(deptLabel);
		tempBox1.add(deptComb);
		tempBox1.add(telLable);
		tempBox1.add(telText);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(tempBox1);

		tempBox1 = Box.createHorizontalBox();
		tempBox1.add(addressLabel);
		tempBox1.add(addressText);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(tempBox1);

		tempBox1 = Box.createHorizontalBox();
		tempBox1.add(regtimeLabel);
		tempBox1.add(regtimeText);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(tempBox1);

		tempBox1 = Box.createHorizontalBox();
		tempBox1.add(saveBtn);
		tempBox1.add(Box.createHorizontalStrut(80));
		tempBox1.add(cancelBtn);
		tempBox1.add(Box.createHorizontalStrut(80));
		tempBox1.add(passwordResetBtn);

		mainBox.add(Box.createVerticalStrut(15));
		mainBox.add(tempBox1);

		Dimension size = new Dimension(80, 25);
		idLabel.setPreferredSize(size);
		nameLabel.setPreferredSize(size);
		sexLabel.setPreferredSize(size);
		deptLabel.setPreferredSize(size);
		addressLabel.setPreferredSize(size);
		regtimeLabel.setPreferredSize(size);
		isOnlineLabel.setPreferredSize(size);
		passwordLabel.setPreferredSize(size);
		ageLable.setPreferredSize(size);
		telLable.setPreferredSize(size);

		size = new Dimension(150, 25);
		idText.setPreferredSize(size);
		nameText.setPreferredSize(size);
		iconComb.setPreferredSize(size);
		passwordText.setPreferredSize(size);
		isOnlineText.setPreferredSize(size);

		sexComb.setPreferredSize(size);
		deptComb.setPreferredSize(size);
		addressText.setPreferredSize(size);
		regtimeText.setPreferredSize(size);

		ageText.setPreferredSize(size);
		telText.setPreferredSize(size);

		this.getContentPane().add(mainBox);
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createBevelBorder(20));
		panel.add(mainBox);
		this.getContentPane().add(panel);
		this.setResizable(false);
		this.setSize(500, 390);

		String sexs[] = { "��", "Ů" };
		this.setSex(sexs);
		this.setLocationRelativeTo(this.getOwner());
		this.initIconCom();
		this.iconComb.setRenderer(new IconCombobox());

		this.setUser(this.user);

		this.addButtonActionListener(new UserMakeButtonActionListener(this));
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(PubToolkit.showYesNo(UserMakeDialog.this, "���Ҫȡ����")){
					UserMakeDialog.this.dispose();
				}
			}
		});
		
	}


	public void setSex(String[] sexs) {
		if (sexs != null) {
			for (int i = 0; i < sexs.length; i++) {
				sexComb.addItem(sexs[i]);
			}
		}
	}

	private void initIconCom() {
		for (int i = 0; i < 150; i++) {
			ImageIcon imageIcon = GetImage.getBigHead(i);
			if (imageIcon != null) {
				iconComb.addItem("" + i);
			}
		}
	}

	public void setDept(String[] deptNames) {
		if (depts != null) {
			for (int i = 0; i < deptNames.length; i++) {
				deptComb.addItem(deptNames[i]);
			}
		}
	}

	public void addButtonActionListener(ActionListener a) {
		this.saveBtn.addActionListener(a);
		this.cancelBtn.addActionListener(a);
		this.passwordResetBtn.addActionListener(a);
		this.saveBtn.setActionCommand(UserUIConfig.SAVEBTN);
		this.cancelBtn.setActionCommand(UserUIConfig.CANCELBTN);
		this.passwordResetBtn.setActionCommand(UserUIConfig.PASSWORDRESTBTN);
	}

	public User getUser() {

		User user = new User();
		user.setId(idText.getText());
		user.setName(nameText.getText());
		user.setIconId(Integer.parseInt((String) iconComb.getSelectedItem()));
		user.setPassword("123456");
		return user;
	}
	public void setUser(User user) {
		idText.setText(user.getId());
		nameText.setText(user.getName());
		for (int i = 0; i < iconComb.getItemCount(); i++) {
			if (iconComb.getItemAt(i).toString().equals("" + user.getIconId())) {
				iconComb.setSelectedIndex(i);
				break;
			}
		}
		isOnlineText.setText(user.getIsOnline() == 1 ? "����" : "����");
	}
}

class UserMakeButtonActionListener implements ActionListener {

	private UserMakeDialog userMakeDialog;

	public UserMakeButtonActionListener(UserMakeDialog userMakeDialog) {
		this.userMakeDialog = userMakeDialog;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			User user = userMakeDialog.getUser();
			String actionString = ((JButton) e.getSource()).getActionCommand();
			if (actionString.equals(UserUIConfig.SAVEBTN)) {
				if (user.getName() == null || user.getName().equals("")) {
					PubToolkit.showError("�ⲻ��,�������û�����.");
					return;
				}

				if (userMakeDialog.getType().equals(UserUIConfig.MAKEBTN)) {
					if (MySQLUserDao.getInstance().update(user)) {
						PubToolkit.showInformation("��ϲ,�޸ĳɹ�!");
						((ServerUi) userMakeDialog.getOwner()).getUserUI().update(( (ServerUi) userMakeDialog.getOwner()).getUserUI().getSearch());
						
						Iterator it = PubValue.getOnLineUserThread();
						user.setType(PackOper.UPDATE_USER);
						while(it.hasNext()){
							((ClientThread) it.next()).sendMessage(user);
						}
						
						userMakeDialog.setVisible(false);
						userMakeDialog.dispose();
					} else {
						PubToolkit.showInformation("�ⲻ��,�޸�ʧ��!");
					}
				} else if (userMakeDialog.getType().equals(UserUIConfig.ADDBTN)) {
					if (MySQLUserDao.getInstance().add(user)) {
						((ServerUi) userMakeDialog.getOwner()).getUserUI()
								.update(
										((ServerUi) userMakeDialog.getOwner())
												.getUserUI().getSearch());
						PubToolkit.showInformation("��ϲ,��ӳɹ�!");
						
						Iterator it = PubValue.getOnLineUserThread();
						user.setType(PackOper.ADD_USER);
						while(it.hasNext()){
							((ClientThread)it.next()).sendMessage(user);
						}
						
						userMakeDialog.setVisible(false);
						userMakeDialog.dispose();
					} else {
						PubToolkit.showInformation("�ⲻ��,���ʧ��!");
					}
				}
			} else if (actionString.equals(UserUIConfig.CANCELBTN)) {
				if (PubToolkit.showYesNo(userMakeDialog, "���Ҫȡ����?")) {
					userMakeDialog.setVisible(false);
					userMakeDialog.dispose();
				}
			} else if (actionString.equals(UserUIConfig.PASSWORDRESTBTN)) {
				if (PubToolkit.showYesNo(userMakeDialog, "���Ҫ����������?")) {
					if (MySQLUserDao.getInstance().resetPassword(user.getId(),"123456")) {
						PubToolkit.showInformation("��ϲ,��������ɹ�!");
					} else {
						PubToolkit.showInformation("�ⲻ��,��������ʧ��!");
					}
				}
			}
		}
	}

}