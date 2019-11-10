import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Table extends JButton {
	private String tableNumber;
	private int price;
	private ArrayList <Menu> orderlist;
	
	Table(int n){
		this.tableNumber = String.valueOf(n);
		this.price = 0;
		this.orderlist = new ArrayList<Menu>();
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setBorder(new LineBorder(Color.BLACK,2));
	}
	
	
	
	public String getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = String.valueOf(tableNumber);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ArrayList<Menu> getOrderList() {
		return orderlist;
	}
	public void setOrderList(ArrayList<Menu> orderList) {
		this.orderlist = orderList;
	}
	private ArrayList<Menu> orderList;
	
	
}
