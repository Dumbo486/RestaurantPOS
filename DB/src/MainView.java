import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuBar;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainView extends JFrame {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Menu");
	private JMenuItem mOpen = new JMenuItem("Open");
	private JMenuItem mLogin = new JMenuItem("Login");
	private JMenuItem mLogout = new JMenuItem("Logout");
	
	private JLabel titleLabel = new JLabel("식당 주문관리");
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel centerPanel = new JPanel(new GridLayout(1,2,10,10));
	private JPanel bottomPanel = new JPanel(new GridLayout(1,2,10,10));
	
	private TablePanel tablePanel;
	private OrderPanel orderPanel;
	private MenuPanel menuPanel;
	private JoinCheckPanel joinCheckPanel;
	
	MainView(Event e){
		setLayout(new BorderLayout(20,20));
		
		
		
		mOpen.addActionListener(e);
		mLogin.addActionListener(e);
		mLogout.addActionListener(e);
		menu.add(mOpen);
		menu.add(mLogin);
		menu.add(mLogout);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		
		tablePanel = new TablePanel(e);
		orderPanel = new OrderPanel(e);
		menuPanel = new MenuPanel(e);
		joinCheckPanel = new JoinCheckPanel(e);
		
		topPanel.setBorder(new LineBorder(Color.BLACK, 2));
		topPanel.setPreferredSize(new Dimension(0,110));
		titleLabel.setFont(new Font("돋움",Font.PLAIN,15));
		topPanel.add("Center",titleLabel);
		
		centerPanel.add(tablePanel);
		centerPanel.add(orderPanel);
		
		bottomPanel.setPreferredSize(new Dimension(0,420));
		bottomPanel.add(menuPanel);
		bottomPanel.add(joinCheckPanel);
		
		setJMenuBar(menuBar);
		add("North",topPanel);
		add("Center",centerPanel);
		add("South",bottomPanel);
		
		setSize(750,870);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public JMenu getMenu() {
		return menu;
	}

	public JMenuItem getMenuOpen() {
		return mOpen;
	}

	public JMenuItem getMenuLogin() {
		return mLogin;
	}

	public JMenuItem getMenuLogout() {
		return mLogout;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public JPanel getNorthPanel() {
		return topPanel;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public JPanel getSouthPanel() {
		return bottomPanel;
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}

	public OrderPanel getOrderPanel() {
		return orderPanel;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public JoinCheckPanel getJoinCheckPanel() {
		return joinCheckPanel;
	}
	
	
	
}
