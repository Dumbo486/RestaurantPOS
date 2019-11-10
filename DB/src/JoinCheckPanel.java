import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class JoinCheckPanel extends JPanel {
	private JLabel titleLabel = new JLabel("등록/조회");
	private JLabel emptyLabel = new JLabel("");
	private JTabbedPane tab = new JTabbedPane();
	
	private JPanel customerBigPanel = new JPanel();
	private JPanel customerPanel = new JPanel(new BorderLayout(10,10));
	private JPanel customerTopPanel = new JPanel(new GridLayout(2,3,20,10));
	private JPanel customerCenterPanel = new JPanel(new BorderLayout(10,10));
	
	private JLabel customerNameLabel = new JLabel("고객명");
	private JTextField customerNameField = new JTextField();
	private	JButton customerJoinButton = new JButton("가입");
	private JButton customerCheckButton = new JButton("조회");
	private JTextArea customerInfoArea = new JTextArea();
	
	private JPanel salesBigPanel = new JPanel();
	private JPanel salesPanel = new JPanel(new BorderLayout(10,30));
	private JPanel termPanel = new JPanel();
	private JLabel termLabel = new JLabel("기간");
	private JComboBox<String> termBox = new JComboBox<String>();
	private JTextArea salesInfoArea = new JTextArea();
	
	
	private JPanel staffBigPanel = new JPanel();
	private JPanel staffPanel = new JPanel(new BorderLayout(10,10));
	private JPanel staffTopPanel = new JPanel(new GridLayout(2,3,20,10));
	private JPanel staffCenterPanel = new JPanel(new BorderLayout(10,10));
	
	private JLabel staffNameLabel = new JLabel("직원명");
	private JTextField staffNameField = new JTextField();
	private	JButton staffJoinButton = new JButton("직원등록");
	private JButton staffCheckButton = new JButton("조회");
	private JTextArea staffInfoArea = new JTextArea();
	
	private JPanel menuBigPanel = new JPanel();
	private JPanel menuPanel = new JPanel(new BorderLayout(10,10));
	private JPanel menuTopPanel = new JPanel(new GridLayout(2,3,20,10));
	private JPanel menuCenterPanel = new JPanel(new BorderLayout(10,10));
	
	private JLabel menuNameLabel = new JLabel("메뉴명");
	private JTextField menuNameField = new JTextField();
	private	JButton menuJoinButton = new JButton("메뉴등록");
	private JButton menuCheckButton = new JButton("조회");
	private JTextArea menuInfoArea = new JTextArea();
	

	JoinCheckPanel(Event e){
		setLayout(new BorderLayout(10,10));
		
		customerTopPanel.add(customerNameLabel);
		customerTopPanel.add(emptyLabel);
		customerTopPanel.add(emptyLabel);
		customerTopPanel.add(customerNameField);
		customerTopPanel.add(customerJoinButton);
		customerTopPanel.add(customerCheckButton);
		customerTopPanel.setPreferredSize(new Dimension(0,70));
		customerInfoArea.setBorder(new LineBorder(Color.BLACK,2));
		customerInfoArea.setEditable(false);
		customerCenterPanel.add("Center", customerInfoArea);
		customerJoinButton.addActionListener(e);
		customerCheckButton.addActionListener(e);
		customerPanel.setBounds(10,10,340,330);
		customerPanel.add("North", customerTopPanel);
		customerPanel.add("Center", customerCenterPanel);
		
		customerBigPanel.setLayout(null);
		customerBigPanel.add(customerPanel);
		
		
		termPanel.add(termLabel);
		termPanel.add(termBox);
		termBox.addActionListener(e);
		termPanel.add(emptyLabel);
		
		salesInfoArea.setBorder(new LineBorder(Color.BLACK,2));
		salesInfoArea.setEditable(false);
		
		salesPanel.setBounds(10, 40, 340, 300);
		salesPanel.add("North",termPanel);
		salesPanel.add("Center",salesInfoArea);
		
		salesBigPanel.setLayout(null);
		salesBigPanel.add(salesPanel);
		
		
		staffTopPanel.setPreferredSize(new Dimension(0,70));
		staffTopPanel.add(staffNameLabel);
		staffTopPanel.add(emptyLabel);
		staffTopPanel.add(emptyLabel);
		staffTopPanel.add(staffNameField);
		staffTopPanel.add(staffJoinButton);
		staffTopPanel.add(staffCheckButton);
		staffJoinButton.addActionListener(e);
		staffCheckButton.addActionListener(e);
		
		staffInfoArea.setBorder(new LineBorder(Color.BLACK,2));
		staffInfoArea.setEditable(false);
		staffCenterPanel.add("Center",staffInfoArea);
		
		staffPanel.setBounds(10,10,340,330);
		staffPanel.add("North", staffTopPanel);
		staffPanel.add("Center", staffCenterPanel);
		
		staffBigPanel.setLayout(null);
		staffBigPanel.add(staffPanel);
		
		
		menuTopPanel.setPreferredSize(new Dimension(0,70));
		menuTopPanel.add(menuNameLabel);
		menuTopPanel.add(emptyLabel);
		menuTopPanel.add(emptyLabel);
		menuTopPanel.add(menuNameField);
		menuTopPanel.add(menuJoinButton);
		menuTopPanel.add(menuCheckButton);
		menuJoinButton.addActionListener(e);
		menuCheckButton.addActionListener(e);
		
		menuInfoArea.setBorder(new LineBorder(Color.black,2));
		menuInfoArea.setEditable(false);
		menuCenterPanel.add("Center",menuInfoArea);
		
		menuPanel.setBounds(10,10,340,330);
		menuPanel.add("North",menuTopPanel);
		menuPanel.add("Center",menuCenterPanel);
		
		menuBigPanel.setLayout(null);
		menuBigPanel.add(menuPanel);
		
		tab.setBorder(new LineBorder(Color.BLACK,2));
		tab.add("고객",customerBigPanel);
		tab.add("매출",salesBigPanel);
		tab.add("직원",staffBigPanel);
		tab.add("메뉴",menuBigPanel);
		
		add("North",titleLabel);
		add("Center",tab);
		
	}


	public JLabel getTitleLabel() {
		return titleLabel;
	}


	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}


	public JTabbedPane getTab() {
		return tab;
	}


	public void setTab(JTabbedPane tab) {
		this.tab = tab;
	}


	public JPanel getCustomerBigPanel() {
		return customerBigPanel;
	}


	public void setCustomerBigPanel(JPanel customerBigPanel) {
		this.customerBigPanel = customerBigPanel;
	}


	public JPanel getCustomerPanel() {
		return customerPanel;
	}


	public void setCustomerPanel(JPanel customerPanel) {
		this.customerPanel = customerPanel;
	}


	public JPanel getCustomerTopPanel() {
		return customerTopPanel;
	}


	public void setCustomerTopPanel(JPanel customerTopPanel) {
		this.customerTopPanel = customerTopPanel;
	}


	public JPanel getCustomerCenterPanel() {
		return customerCenterPanel;
	}


	public void setCustomerCenterPanel(JPanel customerCenterPanel) {
		this.customerCenterPanel = customerCenterPanel;
	}


	public JLabel getCustomerNameLabel() {
		return customerNameLabel;
	}


	public void setCustomerNameLabel(JLabel customerNameLabel) {
		this.customerNameLabel = customerNameLabel;
	}


	public JTextField getCustomerNameField() {
		return customerNameField;
	}


	public void setCustomerNameField(JTextField customerNameField) {
		this.customerNameField = customerNameField;
	}


	public JButton getCustomerJoinButton() {
		return customerJoinButton;
	}


	public void setCustomerJoinButton(JButton customerJoinButton) {
		this.customerJoinButton = customerJoinButton;
	}


	public JButton getCustomerCheckButton() {
		return customerCheckButton;
	}


	public void setCustomerCheckButton(JButton customerCheckButton) {
		this.customerCheckButton = customerCheckButton;
	}


	public JTextArea getCustomerInfoArea() {
		return customerInfoArea;
	}


	public void setCustomerInfoArea(JTextArea customerInfoArea) {
		this.customerInfoArea = customerInfoArea;
	}


	public JPanel getSalesBigPanel() {
		return salesBigPanel;
	}


	public void setSalesBigPanel(JPanel salesBigPanel) {
		this.salesBigPanel = salesBigPanel;
	}


	public JPanel getSalesPanel() {
		return salesPanel;
	}


	public void setSalesPanel(JPanel salesPanel) {
		this.salesPanel = salesPanel;
	}


	public JPanel getTermPanel() {
		return termPanel;
	}


	public void setTermPanel(JPanel termPanel) {
		this.termPanel = termPanel;
	}


	public JLabel getTermLabel() {
		return termLabel;
	}


	public void setTermLabel(JLabel termLabel) {
		this.termLabel = termLabel;
	}


	public JComboBox<String> getTermBox() {
		return termBox;
	}


	public void setTermBox(JComboBox<String> termBox) {
		this.termBox = termBox;
	}


	public JTextArea getSalesInfoArea() {
		return salesInfoArea;
	}


	public void setSalesInfoArea(JTextArea salesInfoArea) {
		this.salesInfoArea = salesInfoArea;
	}


	public JPanel getStaffBigPanel() {
		return staffBigPanel;
	}


	public void setStaffBigPanel(JPanel staffBigPanel) {
		this.staffBigPanel = staffBigPanel;
	}


	public JPanel getStaffPanel() {
		return staffPanel;
	}


	public void setStaffPanel(JPanel staffPanel) {
		this.staffPanel = staffPanel;
	}


	public JPanel getStaffTopPanel() {
		return staffTopPanel;
	}


	public void setStaffTopPanel(JPanel staffTopPanel) {
		this.staffTopPanel = staffTopPanel;
	}


	public JPanel getStaffCenterPanel() {
		return staffCenterPanel;
	}


	public void setStaffCenterPanel(JPanel staffCenterPanel) {
		this.staffCenterPanel = staffCenterPanel;
	}


	public JLabel getStaffNameLabel() {
		return staffNameLabel;
	}


	public void setStaffNameLabel(JLabel staffNameLabel) {
		this.staffNameLabel = staffNameLabel;
	}


	public JTextField getStaffNameField() {
		return staffNameField;
	}


	public void setStaffNameField(JTextField staffNameField) {
		this.staffNameField = staffNameField;
	}


	public JButton getStaffJoinButton() {
		return staffJoinButton;
	}


	public void setStaffJoinButton(JButton staffJoinButton) {
		this.staffJoinButton = staffJoinButton;
	}


	public JButton getStaffCheckButton() {
		return staffCheckButton;
	}


	public void setStaffCheckButton(JButton staffCheckButton) {
		this.staffCheckButton = staffCheckButton;
	}


	public JTextArea getStaffInfoArea() {
		return staffInfoArea;
	}


	public void setStaffInfoArea(JTextArea staffInfoArea) {
		this.staffInfoArea = staffInfoArea;
	}


	public JPanel getMenuBigPanel() {
		return menuBigPanel;
	}


	public void setMenuBigPanel(JPanel menuBigPanel) {
		this.menuBigPanel = menuBigPanel;
	}


	public JPanel getMenuPanel() {
		return menuPanel;
	}


	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}


	public JPanel getMenuTopPanel() {
		return menuTopPanel;
	}


	public void setMenuTopPanel(JPanel menuTopPanel) {
		this.menuTopPanel = menuTopPanel;
	}


	public JPanel getMenuCenterPanel() {
		return menuCenterPanel;
	}


	public void setMenuCenterPanel(JPanel menuCenterPanel) {
		this.menuCenterPanel = menuCenterPanel;
	}


	public JLabel getMenuNameLabel() {
		return menuNameLabel;
	}


	public void setMenuNameLabel(JLabel menuNameLabel) {
		this.menuNameLabel = menuNameLabel;
	}


	public JTextField getMenuNameField() {
		return menuNameField;
	}


	public void setMenuNameField(JTextField menuNameField) {
		this.menuNameField = menuNameField;
	}


	

	public JButton getMenuJoinButton() {
		return menuJoinButton;
	}


	public void setMenuJoinButton(JButton menuJoinButton) {
		this.menuJoinButton = menuJoinButton;
	}


	public JButton getMenuCheckButton() {
		return menuCheckButton;
	}


	public void setMenuCheckButton(JButton menuCheckButton) {
		this.menuCheckButton = menuCheckButton;
	}


	public JTextArea getMenuInfoArea() {
		return menuInfoArea;
	}


	public void setMenuInfoArea(JTextArea menuInfoArea) {
		this.menuInfoArea = menuInfoArea;
	}
	
	
	
}
