import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class OrderPanel extends JPanel{
	
	private JLabel titleLabel = new JLabel("주문내역");
	private JPanel bigPanel = new JPanel();
	
	private JPanel orderPanel = new JPanel(new BorderLayout(20,20));
	private JTextArea orderArea = new JTextArea();
	
	private JPanel rightPanel = new JPanel(new GridLayout(7,1,10,5));
	private JLabel customerLabel = new JLabel("고객명");
	private JTextField cNameField = new JTextField();
	private JLabel tableLabel = new JLabel("테이블명");
	private JComboBox<Integer> tNameBox = new JComboBox<Integer>();
	private JButton orderButton = new JButton("주문");
	private JButton cancelButton = new JButton("취소");
	private JButton payButton = new JButton("결제");
	
	OrderPanel(Event e){
		setLayout(new BorderLayout(10,10));
		bigPanel.setLayout(null);
		
		orderArea.setBorder(new LineBorder(Color.black,2));
		orderArea.setEditable(false);
		
		rightPanel.add(customerLabel);
		rightPanel.add(cNameField);
		
		rightPanel.add(tableLabel);
		for(int i=1;i<=20;i++) tNameBox.addItem(i);
		rightPanel.add(tNameBox);
		tNameBox.addActionListener(e);
		
		rightPanel.add(orderButton);
		rightPanel.add(cancelButton);
		rightPanel.add(payButton);
		orderButton.addActionListener(e);
		cancelButton.addActionListener(e);
		payButton.addActionListener(e);
		
		rightPanel.setPreferredSize(new Dimension(130,0));
		
		orderPanel.setBounds(10, 10, 345, 200);
		orderPanel.add("Center", orderArea);
		orderPanel.add("East", rightPanel);
		add("North",titleLabel);
		
		bigPanel.add(orderPanel);
		add("Center",bigPanel);
		
		
		
		
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JPanel getBigPanel() {
		return bigPanel;
	}

	public void setBigPanel(JPanel bigPanel) {
		this.bigPanel = bigPanel;
	}

	public JPanel getOrderPanel() {
		return orderPanel;
	}

	public void setOrderPanel(JPanel orderPanel) {
		this.orderPanel = orderPanel;
	}

	public JTextArea getOrderArea() {
		return orderArea;
	}

	public void setOrderArea(JTextArea orderArea) {
		this.orderArea = orderArea;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public JLabel getCustomerLabel() {
		return customerLabel;
	}

	public void setCustomerLabel(JLabel customerLabel) {
		this.customerLabel = customerLabel;
	}

	public JTextField getcNameField() {
		return cNameField;
	}

	public void setcNameField(JTextField cNameField) {
		this.cNameField = cNameField;
	}

	public JLabel getTableLabel() {
		return tableLabel;
	}

	public void setTableLabel(JLabel tableLabel) {
		this.tableLabel = tableLabel;
	}

	public JComboBox<Integer> gettNameBox() {
		return tNameBox;
	}

	public void settNameBox(JComboBox<Integer> tNameBox) {
		this.tNameBox = tNameBox;
	}

	public JButton getOrderButton() {
		return orderButton;
	}

	public void setOrderButton(JButton orderButton) {
		this.orderButton = orderButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JButton getPayButton() {
		return payButton;
	}

	public void setPayButton(JButton payButton) {
		this.payButton = payButton;
	}
	
	
	
	
	
	
	

}
