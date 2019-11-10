import java.awt.GridLayout;

import javax.swing.*;


public class CustomerJoinView extends JFrame {
	private JPanel bigPanel = new JPanel(new GridLayout(4,2,10,30));
	
	private JLabel cNameLabel = new JLabel("고객명");
	private JTextField cNameField = new JTextField();
	private JLabel cBirthLabel = new JLabel("생일(4자리)");
	private JTextField cBirthField = new JTextField();
	private JLabel cPhoneLabel = new JLabel("연락처");
	private JTextField cPhoneField = new JTextField();
	
	private JButton cJoinButton = new JButton("가입신청");
	private JButton cCancelButton = new JButton("취소");
	
	CustomerJoinView(Event e){
		setLayout(null);
		bigPanel.setBounds(30,30,220,180);
		bigPanel.add(cNameLabel);
		bigPanel.add(cNameField);
		cNameField.addActionListener(e);
		bigPanel.add(cBirthLabel);
		bigPanel.add(cBirthField);
		cBirthField.addActionListener(e);
		bigPanel.add(cPhoneLabel);
		bigPanel.add(cPhoneField);
		cPhoneField.addActionListener(e);
		bigPanel.add(cJoinButton);
		bigPanel.add(cCancelButton);
		cJoinButton.addActionListener(e);
		cCancelButton.addActionListener(e);
		
		
		add(bigPanel);
		setVisible(false);
		setSize(300,270);
		
		
	}

	public void clear(){
		cNameField.setText("");
		cBirthField.setText("");
		cPhoneField.setText("");
	}
	
	public JPanel getBigPanel() {
		return bigPanel;
	}

	public void setBigPanel(JPanel bigPanel) {
		this.bigPanel = bigPanel;
	}

	public JLabel getcNameLabel() {
		return cNameLabel;
	}

	public void setcNameLabel(JLabel cNameLabel) {
		this.cNameLabel = cNameLabel;
	}

	public JTextField getcNameField() {
		return cNameField;
	}

	public void setcNameField(JTextField cNameField) {
		this.cNameField = cNameField;
	}

	public JLabel getcBirthLabel() {
		return cBirthLabel;
	}

	public void setcBirthLabel(JLabel cBirthLabel) {
		this.cBirthLabel = cBirthLabel;
	}

	public JTextField getcBirthField() {
		return cBirthField;
	}

	public void setcBirthField(JTextField cBirthField) {
		this.cBirthField = cBirthField;
	}

	public JLabel getcPhoneLabel() {
		return cPhoneLabel;
	}

	public void setcPhoneLabel(JLabel cPhoneLabel) {
		this.cPhoneLabel = cPhoneLabel;
	}

	public JTextField getcPhoneField() {
		return cPhoneField;
	}

	public void setcPhoneField(JTextField cPhoneField) {
		this.cPhoneField = cPhoneField;
	}

	public JButton getcJoinButton() {
		return cJoinButton;
	}

	public void setcJoinButton(JButton cJoinButton) {
		this.cJoinButton = cJoinButton;
	}

	public JButton getcCancelButton() {
		return cCancelButton;
	}

	public void setcCancelButton(JButton cCancelButton) {
		this.cCancelButton = cCancelButton;
	}

	
	
	
	
	
	
	
}
