import java.awt.GridLayout;

import javax.swing.*;

public class MenuJoinView extends JFrame {
private JPanel bigPanel = new JPanel(new GridLayout(4,2,10,30));
	
	private JLabel mNameLabel = new JLabel("메뉴명");
	private JTextField mNameField = new JTextField();
	private JLabel mPriceLabel = new JLabel("가격");
	private JTextField mPriceField = new JTextField();
	
	private JButton mJoinButton = new JButton("등록");
	private JButton mCancelButton = new JButton("취소");
	
	
	MenuJoinView(Event e){
		setLayout(null);
		bigPanel.setBounds(30, 30, 220, 180);
		bigPanel.add(mNameLabel);
		bigPanel.add(mNameField);
		mNameField.addActionListener(e);
		
		bigPanel.add(mPriceLabel);
		bigPanel.add(mPriceField);
		mPriceField.addActionListener(e);
		
		bigPanel.add(mJoinButton);
		mJoinButton.addActionListener(e);
		bigPanel.add(mCancelButton);
		mCancelButton.addActionListener(e);
		
		add(bigPanel);
		setVisible(false);
		setSize(300,225);
	}

	
	public void clear(){
		mNameField.setText("");
		mPriceField.setText("");
	}
	
	public JPanel getBigPanel() {
		return bigPanel;
	}


	public void setBigPanel(JPanel bigPanel) {
		this.bigPanel = bigPanel;
	}


	public JLabel getmNameLabel() {
		return mNameLabel;
	}


	public void setmNameLabel(JLabel mNameLabel) {
		this.mNameLabel = mNameLabel;
	}


	public JTextField getmNameField() {
		return mNameField;
	}


	public void setmNameField(JTextField mNameField) {
		this.mNameField = mNameField;
	}


	public JLabel getmPriceLabel() {
		return mPriceLabel;
	}


	public void setmPriceLabel(JLabel mPriceLabel) {
		this.mPriceLabel = mPriceLabel;
	}


	public JTextField getmPriceField() {
		return mPriceField;
	}


	public void setmPriceField(JTextField mPriceField) {
		this.mPriceField = mPriceField;
	}


	public JButton getmJoinButton() {
		return mJoinButton;
	}


	public void setmJoinButton(JButton mJoinButton) {
		this.mJoinButton = mJoinButton;
	}


	public JButton getmCancelButton() {
		return mCancelButton;
	}


	public void setmCancelButton(JButton mCancelButton) {
		this.mCancelButton = mCancelButton;
	}
	
	

}
