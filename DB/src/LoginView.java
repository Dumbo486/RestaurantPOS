import javax.swing.*;

public class LoginView extends JFrame {
	private JPanel loginPanel = new JPanel();
	private JLabel nameLabel = new JLabel("이름");
	private JLabel idLabel = new JLabel("사원번호");
	private JTextField nameInput = new JTextField();
	private JTextField idInput = new JTextField();
    private JButton loginButton = new JButton("로그인");
	
	public LoginView(Event e){
		loginPanel.setLayout(null);
		
		nameLabel.setBounds(20, 10, 60, 30);
		idLabel.setBounds(20, 50, 60, 30);
		nameInput.setBounds(100,10,80,30);
		idInput.setBounds(100,50,80,30);
	    loginButton.setBounds(200, 25, 80, 35);
	    
	    nameInput.addActionListener(e);
	    idInput.addActionListener(e);
	    loginButton.addActionListener(e);
		
	    loginPanel.add(nameLabel);
	    loginPanel.add(idLabel);
	    loginPanel.add(nameInput);
	    loginPanel.add(idInput);
	    loginPanel.add(loginButton);
	    
	    add(loginPanel);
	    setTitle("로그인");
        setSize(300, 120);
        setResizable(false);
        setVisible(false);
	}

	public void clear(){
		nameInput.setText("");
		idInput.setText("");
	}

	

	public JPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}

	public JTextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(JTextField nameInput) {
		this.nameInput = nameInput;
	}

	public JTextField getIdInput() {
		return idInput;
	}

	public void setIdInput(JTextField idInput) {
		this.idInput = idInput;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
	
	

}
