import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class TablePanel extends JPanel {
	private JLabel titleLabel = new JLabel("테이블 현황");
	
	private Table tables[] = new Table[20];
	private JPanel bigPanel = new JPanel();
	private JPanel tablesPanel = new JPanel();
	

	
	TablePanel(Event e){
		setLayout(new BorderLayout(10,10));
		bigPanel.setLayout(null);
		bigPanel.setBorder(new LineBorder(Color.BLACK,2));
		tablesPanel.setLayout(new GridLayout(4,5,10,10));
		
		for(int i=0;i<tables.length;i++){
			tables[i] = new Table(i+1);
			tables[i].setText(tables[i].getTableNumber());
			tables[i].setBackground(Color.WHITE);
			tables[i].addActionListener(e);
			tablesPanel.add(tables[i]);
			
//			tablesPanel.add(new JLabel(String.valueOf(i+1)));
//			
			
		}
		
		tablesPanel.setBounds(10,10,345,200);
		
		bigPanel.add(tablesPanel);
		add("North",titleLabel);
		add("Center",bigPanel);
		setVisible(true);
		
		
	}

	public Table getTables(int index) {
		return tables[index];
	}

	public void setTables(Table[] tables) {
		this.tables = tables;
	}

	public JPanel getBigPanel() {
		return bigPanel;
	}

	public void setBigPanel(JPanel bigPanel) {
		this.bigPanel = bigPanel;
	}

	public JPanel getTablesPanel() {
		return tablesPanel;
	}

	public void setTablesPanel(JPanel tablesPanel) {
		this.tablesPanel = tablesPanel;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	
	
}
