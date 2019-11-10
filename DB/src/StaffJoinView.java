import java.awt.GridLayout;

import javax.swing.*;

public class StaffJoinView extends JFrame {
	private JPanel bigPanel = new JPanel(new GridLayout(4,2,10,30));
	private JLabel sNameLabel = new JLabel("직원명");
	private JTextField sNameField = new JTextField();
	private JLabel sPositionLabel = new JLabel("직급");
	private JComboBox<String> sPositionBox = new JComboBox<String>();
	private JButton sJoinButton = new JButton("등록");
	private JButton sCancelButton = new JButton("취소");
		
	StaffJoinView(Event e){
		setLayout(null);
		bigPanel.setBounds(30, 30, 220, 180);
		bigPanel.add(sNameLabel);
		bigPanel.add(sNameField);
		sNameField.addActionListener(e);
		
		bigPanel.add(sPositionLabel);
		sPositionBox.addItem("Supervisor");
		sPositionBox.addItem("Staff");
		bigPanel.add(sPositionBox);
		sPositionBox.addActionListener(e);
		
		bigPanel.add(sJoinButton);
		sJoinButton.addActionListener(e);
		bigPanel.add(sCancelButton);
		sCancelButton.addActionListener(e);
		
		add(bigPanel);
		setVisible(false);
		setSize(300,225);
		
	}
	
	public void clear(){
		sNameField.setText("");
		sPositionBox.setSelectedIndex(0);
	}

	public JPanel getBigPanel() {
		return bigPanel;
	}

	public void setBigPanel(JPanel bigPanel) {
		this.bigPanel = bigPanel;
	}

	public JLabel getsNameLabel() {
		return sNameLabel;
	}

	public void setsNameLabel(JLabel sNameLabel) {
		this.sNameLabel = sNameLabel;
	}

	public JTextField getsNameField() {
		return sNameField;
	}

	public void setsNameField(JTextField sNameField) {
		this.sNameField = sNameField;
	}

	public JLabel getsPositionLabel() {
		return sPositionLabel;
	}

	public void setsPositionLabel(JLabel sPositionLabel) {
		this.sPositionLabel = sPositionLabel;
	}

	public JComboBox<String> getsPositionBox() {
		return sPositionBox;
	}

	public void setsPositionBox(JComboBox<String> sPositionBox) {
		this.sPositionBox = sPositionBox;
	}

	public JButton getsJoinButton() {
		return sJoinButton;
	}

	public void setsJoinButton(JButton sJoinButton) {
		this.sJoinButton = sJoinButton;
	}

	public JButton getsCancelButton() {
		return sCancelButton;
	}

	public void setsCancelButton(JButton sCancelButton) {
		this.sCancelButton = sCancelButton;
	}
	
	
}
