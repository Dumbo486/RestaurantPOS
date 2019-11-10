import javax.swing.JButton;

public class Menu extends JButton {
	private String menu;
	private int id;
	private int price;
	
	Menu(String menu){
		this.menu = menu;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
