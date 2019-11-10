import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MenuPanel extends JPanel{
	private JLabel titleLabel = new JLabel("메뉴");
	private JPanel bigPanel = new JPanel();
	private Menu menus[] = new Menu[20];
	private JPanel menuPanel = new JPanel(new GridLayout(10,2,10,10));
	
	
	MenuPanel(Event e){
		bigPanel.setLayout(null);
		setLayout(new BorderLayout(10,10));
		for(int i=0;i<20;i++){
			menus[i] = new Menu("");
			menus[i].setId(0);
			menus[i].setPrice(0);
			menus[i].addActionListener(e);
			menuPanel.add(menus[i]);
		}
		menuPanel.setBounds(10,10,300,300);
		
		bigPanel.add(menuPanel);
		bigPanel.setBorder(new LineBorder(Color.BLACK,2));
		
		add("North",titleLabel);
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


	public Menu getMenus(int index) {
		return menus[index];
	}


	public void setMenus(Menu[] menus) {
		this.menus = menus;
	}


	public JPanel getMenuPanel() {
		return menuPanel;
	}


	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}
	
	
}