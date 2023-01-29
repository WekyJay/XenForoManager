package cn.wekyjay.www.tools.xfmanager.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.wekyjay.www.tools.xfmanager.Config;
import cn.wekyjay.www.tools.xfmanager.Utils;
import cn.wekyjay.www.tools.xfmanager.manager.alerts.Alerts;
import cn.wekyjay.www.tools.xfmanager.manager.alerts.AlertsPrompt;
import cn.wekyjay.www.tools.xfmanager.manager.user.User;
import cn.wekyjay.www.tools.xfmanager.manager.user.UserAvatar;
import cn.wekyjay.www.tools.xfmanager.manager.user.Users;

public class MainGUI {
	JFrame frame = new MainFrame();
	public MainGUI() {
		frame = new MainFrame();
		frame.setLayout(new BorderLayout());
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,0));
		var textLabel = new JLabel("XenForoManager",JLabel.CENTER);
		textLabel.setIcon(new ImageIcon(Config.getConfig().getImage("/images/favicon.jpg")));
		textLabel.putClientProperty( "FlatLaf.style", "font: $h00.font" );
		textLabel.setForeground(Color.WHITE);
		
		var tipLabel = new JLabel("加载数据中...(第一次可能有点慢)",JLabel.CENTER);
		tipLabel.setForeground(Color.WHITE);
		tipLabel.putClientProperty( "FlatLaf.style", "font: $h4.font" );
		tipLabel.setOpaque(true);
		tipLabel.setBackground(Color.CYAN);
		
		frame.add(textLabel,BorderLayout.CENTER);
		frame.add(tipLabel,BorderLayout.SOUTH);
		frame.setVisible(true);

	}
	
	
	public void openGUI() {
		JFrame frame = new AlertsFrame();
		frame.setTitle("XenForo管理器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); 
		frame.setIconImage(Config.getConfig().getImage("/images/favicon.jpg"));
		
		
		frame.setVisible(true);
		if(frame.isVisible())this.frame.setVisible(false);
	}
	
}
class MainFrame extends JFrame{

	private static final long serialVersionUID = 3246291452599346868L;
	
	public MainFrame() {
		/** 设置窗口布局 **/
		setTitle("数据加载中...");
		setSize(380, 80);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Config.getConfig().getImage("/images/favicon.jpg"));
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
}

class AlertsFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	public AlertsFrame() {
		
		/** 设置窗口布局 **/
		var layout = new GridBagLayout();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLayout(layout);
		
		/** 构建组件 **/
		// 用户列表
		JList<String> userlist = new JList<>(Users.getUsersName()) ;
		userlist.setSize(50, DEFAULT_HEIGHT);
		userlist.setCellRenderer(new UserListRender());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200,350));
		scrollPane.setViewportView(userlist);
		
		// 用户大头像
		JLabel imgLabel = new JLabel(new ImageIcon(Config.getConfig().getImage("/images/avatar.png")),Label.LEFT);
		
		
		// 标签组件
		var userNameLabel = new JLabel("用户名：");
		var userIDLabel = new JLabel("用户ID：");
		var userGroupLabel = new JLabel("用户组：");
		var isOnlineLabel = new JLabel("账号状态：");
		
		var senderNameLabel = new JLabel("发送人：");
		var urlLabel = new JLabel("链接URL：");
		var urlTitleLabel = new JLabel("链接标题：");
		var contentLabel = new JLabel("提醒内容：");

		// 分割线
		JSeparator s = new JSeparator();
		s.setPreferredSize(new Dimension(300, 5));
		
		// 选项及文本框
		JComboBox<String> senderNameCombo = new JComboBox<>(new String[] {"默认"});
		if(Config.getConfig().getSuperApikey() != null) {senderNameCombo.addItem("匿名");}
		
		var urlText = new JTextField();
		var urlTitle = new JTextField();
		
		// 文本域
		var alertsContent = new JTextArea();
		var alertsScroll = new JScrollPane(alertsContent);
		
		// 提交按钮
		var submit = new JButton("发送提醒");
		
		/** 添加事件 **/
		
		// 选择用户事件
		userlist.addMouseListener(new MouseAdapter() {
			@Override
				public void mousePressed(MouseEvent e) {
					List<String> namelist = userlist.getSelectedValuesList();
					String name = "";
					if(namelist.size() == 1) {
						name = namelist.get(0);
						User user = Users.getUserOfName(name);
						if(user != null) {
							imgLabel.setIcon(new ImageIcon(UserAvatar.getAvatar(name))); // 头像
							userNameLabel.setText("用户名：" + name);
							userIDLabel.setText("用户ID：" + user.getUser_id());
							if(user.is_admin())userGroupLabel.setText("用户组：管理员");
							else userGroupLabel.setText("用户组：普通用户");
							if(user.isActivity_visible()) isOnlineLabel.setText("账号状态：正常");
							else isOnlineLabel.setText("账号状态：不可见");
							
						}
					}else {
						imgLabel.setIcon(new ImageIcon(Config.getConfig().getImage("/images/avatar.png"))); // 头像
						userNameLabel.setText("用户名：多选");
						userIDLabel.setText("用户ID：多选");
						userGroupLabel.setText("用户组：多选");
						isOnlineLabel.setText("账号状态：多选");
					}

				}
		});
		
		// 发送提醒事件
		submit.addActionListener(new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userlist.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "请至少选择一个用户再发送噢~", "提醒", JOptionPane. ERROR_MESSAGE);
					return;
				}
				Integer sender = senderNameCombo.getSelectedIndex() == 0?null:0;
				String url = urlText.getText();
				String title = urlTitle.getText();
				String alert = alertsContent.getText();
				userlist.getSelectedValuesList().forEach(username->{
					Integer getter = Users.getUserOfName(username).getUser_id();
					var statu = Alerts.sendPrompt(new AlertsPrompt().setFromUserID(sender)
							.setAlert(alert)
							.setLinkUrl(url)
							.setLinkTitle(title)
							.setToUserID(getter)
							.build());
					if(statu)count++;
				});
				
				if(count == 1) JOptionPane.showMessageDialog(null, "发送成功!");
				else if(count > 1) JOptionPane.showMessageDialog(null, "成功发送" + count + "条提醒!");
				
			}
		});
		
		/** 添加组件 **/
		// 1
		add(imgLabel,new GBC(0,0,1,2).setAnchor(GBC.NORTHWEST).setInsets(10));
		add(userNameLabel,new GBC(1, 0).setAnchor(GBC.WEST).setWeight(50, 0).setInsets(10,5,0,0));
		add(userGroupLabel,new GBC(2, 0).setAnchor(GBC.WEST).setWeight(50, 0).setInsets(10,5,0,0));
		// 2
		add(userIDLabel,new GBC(1, 1).setAnchor(GBC.WEST).setInsets(10,5,0,0));
		add(isOnlineLabel,new GBC(2, 1).setAnchor(GBC.WEST).setInsets(10,5,0,0));
		// 3
		add(s,new GBC(0, 2,3,1).setAnchor(GBC.NORTH).setFill(GBC.HORIZONTAL).setInsets(10, 0, 0, 0));
		// 4
		add(senderNameLabel,new GBC(0, 3).setAnchor(GBC.NORTHEAST).setInsets(10, 0, 0, 0));
		add(senderNameCombo,new GBC(1, 3).setAnchor(GBC.NORTHWEST).setInsets(10, 0, 0, 0));
		// 5
		add(urlLabel,new GBC(0, 4).setAnchor(GBC.NORTHEAST).setInsets(10, 0, 0, 0));
		add(urlText,new GBC(1, 4,2,1).setFill(GBC.HORIZONTAL).setAnchor(GBC.NORTHWEST).setInsets(10, 0, 0, 5));
		// 6
		add(urlTitleLabel,new GBC(0, 5).setAnchor(GBC.NORTHEAST).setInsets(10, 0, 0, 0));
		add(urlTitle,new GBC(1, 5,2,1).setFill(GBC.HORIZONTAL).setAnchor(GBC.NORTHWEST).setInsets(10, 0, 0, 5));
		// 7
		add(contentLabel,new GBC(0, 6).setAnchor(GBC.NORTHEAST).setInsets(10, 0, 0, 0));
		add(alertsScroll,new GBC(1, 6,2,1).setFill(GBC.BOTH).setAnchor(GBC.NORTHWEST).setWeight(0, 30).setInsets(10, 0, 0, 5));
		
		// 8
		add(submit,new GBC(0, 7, 3, 1).setFill(GBC.NORTH).setWeight(10, 10).setInsets(10, 0, 0, 0));
		
		// 独占列
		add(scrollPane,new GBC(3, 0,1,8).setFill(GBC.BOTH).setWeight(80, 0).setInsets(0, 0, 0, 5));
		
		

		

		
	}
	
}