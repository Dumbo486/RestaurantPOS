import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class DB_Connect {
	private String id = "system";
	private String password = "csa1004";
	private static Connection dbTest;
	private Event event;
	public void setEvent(Event e){
		this.event = e;
	}
	
	
	
	public boolean connectDB(String id, String password){
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:"+"@192.168.56.101:1521:XE",id,password);
			dbTest.commit();
			System.out.println("데이터 베이스에 연결 되었습니다.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 베이스 연결에 실패하였습니다.");
			System.out.println("SQLException:" + e);
			return false;
		} catch (Exception e){
			System.out.println("Exception:"+e);
			return false;
		}
	}
	
	public String loginCheck(String name, String id){
		try{
			String query = "select * from staff where name = '"+name+"' and staff_id = '"+id+"'";
			PreparedStatement ps = dbTest.prepareStatement(query); 
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				rs.close();
				ps.close();
				return null;
			}
			else{
				String pos = rs.getString("position");
				rs.close();
				ps.close();
				return pos;
			}
		}catch(SQLException sqlEx){
			return null;
		}
	}
	
	public ArrayList<String> getTermList(){
		try{
			ArrayList<String> termList = new ArrayList<String>();
			String query = "select term from payinfo group by term";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				termList.add(rs.getString("term"));
			}
			return termList;
		}catch(SQLException e){
			return null;
		}
		
	}
	
	void setVisiblePayInfo(String term){
		try{
			String msg = "일 매출 : ";
			String query = "select sum(price) from payinfo group by term having term = '"+term+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				msg += rs.getString("sum(price)");	
			}else{
				msg += "0\n";
			}
			rs.close();
			ps.close();
			
			
			
			msg += "\n-----------------------------------------------\n가장 많이 팔린 메뉴\n";
			query = "select name from payinfo join menu using (menu_id) group by (name)"+
					 "having count(*) >= all(select count(*) from payinfo group by menu_id)";
			ps = dbTest.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				do{
					msg += ": "+rs.getString("name")+"\n";
				}while(rs.next());
			}
			
			rs.close();
			ps.close();
			
			
			msg += "가장 적게 팔린 메뉴\n";
			query = "select name from menu minus (select name from payinfo join menu using (menu_id))";
			ps = dbTest.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				do{
					msg += ": "+rs.getString("name")+"\n";
				}while(rs.next());
			}else{
				rs.close();
				ps.close();
				query = "select name from payinfo join menu using (menu_id) group by (name)"+
						 "having count(*) <= all(select count(*) from payinfo group by menu_id)";
				ps = dbTest.prepareStatement(query);
				rs = ps.executeQuery();
				if(rs.next()){
					do{
						msg += ": "+rs.getString("name") +"\n";
					}while(rs.next());
				}
			}
			rs.close();
			ps.close();
			
			
			msg += "-----------------------------------------------\n누적매출 : ";
			query = "select sum(price) from payinfo where term <= '"+term+"'";
			ps = dbTest.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("sum(price)")== null){
					msg += "0\n";
				}else{
					msg += rs.getString("sum(price)")+"\n";
				}
			}else{
				msg += "0\n";
			}
			rs.close();
			ps.close();
			event.showPayInfo(msg);
		}catch (SQLException e){
			
		}
	}
	
	void setVisibleMenu(){
		try{
			String query = "select * from menu";
			PreparedStatement ps = dbTest.prepareStatement(query); 
			ResultSet rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next()){
				int menu_id = Integer.parseInt(rs.getString("menu_id"));
				String name = rs.getString("name");
				int price = Integer.parseInt(rs.getString("price"));
				
				Menu tempMenu = event.getMv().getMenuPanel().getMenus(count);
				
				tempMenu.setId(menu_id);
				tempMenu.setName(name);
				tempMenu.setPrice(price);
				tempMenu.setText(name);
				tempMenu.setEnabled(true);
				count ++;
			}
			//메뉴 추가 안된 것 
			for(int i = count ; i < 20 ; i ++){
				Menu tempMenu = event.getMv().getMenuPanel().getMenus(i);
				tempMenu.setText("");
				tempMenu.setEnabled(false);
				
			}
			rs.close();
			ps.close();
		}catch(SQLException e){
		}
	}
	
	public boolean executeQuery(String query){
		try{
			PreparedStatement ps = dbTest.prepareStatement(query); 
			ps.executeQuery();
			ps.close();
			dbTest.commit();
			return true;
		}catch(SQLException sqlEx){
			return false;
		}
	}
	
	public boolean checkCustomer(String name){
		try{
			String query = "select * from customer where name = '"+name+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				rs.close();
				ps.close();
				return false;
			}
			String customer_id = rs.getString("customer_id");
			String birth = rs.getString("birth");
			String phone = rs.getString("phone");
			String pos = rs.getString("position");
			String account = rs.getString("account");
			event.showCustomer(name, customer_id, birth, phone, pos, account);
			return true;
		}catch(SQLException sqlEx){
			return false;
		}
	}	
	
	public boolean checkStaff(String name){
		try{
			String query = "select * from staff where name = '"+name+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				rs.close();
				ps.close();
				return false;
			}
			String staff_id = rs.getString("staff_id");
			String pos = rs.getString("position");
			rs.close();
			ps.close();
			query = "select sum(price) from payinfo group by staff_id having staff_id = '"+staff_id+"'";
			ps = dbTest.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(!rs.next()){
				rs.close();
				ps.close();
				event.showStaff(name,pos,"0");
				return true;
			}
			String account = rs.getString("sum(price)");
			rs.close();
			ps.close();
			event.showStaff(name,pos,account);
			return true;
		}catch(SQLException sqlEx){
			return false;
		}
	}
	
	
	
	
	public boolean checkMenu(String menuName){
		try{
			String query = "select * from menu where name = '"+menuName+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				rs.close();
				ps.close();
				return false;
			}
			String price = rs.getString("price");
			rs.close();
			ps.close();
			event.showMenu(menuName,price);
			return true;
		}catch(SQLException sqlEx){
			return false;
		}
	}
	
	public boolean joinStaff(String name, String level){
		try{
			String query = "select count(*) from staff";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int staff_id = Integer.parseInt(rs.getString("Count(*)"))+1000;
			if(executeQuery("insert into staff values('"+String.valueOf(staff_id) +"','"+name+"','"+level+"')")){
				rs.close();
				ps.close();
				return true;
			}
			else{
				rs.close();
				ps.close();
				return false;
			}
		}catch(SQLException sqlEx){
			return false;
		}
	}
	
	public boolean joinCustomer(String name, String birth, String phone){
		try{
			String query = "select count(*) from customer";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int customer_id = Integer.parseInt(rs.getString("COUNT(*)"))+1000;
			if(executeQuery("insert into customer values('"+String.valueOf(customer_id)+"','"+name+"','"+birth+"','"+phone+"','Normal','0')")){
				rs.close();
				ps.close();
				return true;
			}else{
				rs.close();
				ps.close();
				return false;
			}
		}catch(SQLException sqlEx){
			return false;
		}catch(Exception ex){
			return false;
		}
	}
	
	public boolean joinMenu(String menuName, String price){
		try{
			String query = "select count(*) from menu";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int menu_id = Integer.parseInt(rs.getString("Count(*)"))+1000;
			if(executeQuery("insert into menu values('"+String.valueOf(menu_id) +"','"+menuName+"','"+price+"')")){
				rs.close();
				ps.close();
				return true;
			}else{
				rs.close();
				ps.close();
				return false;
			}
		}catch(SQLException e){
			return false;
		}
	}
	
	public boolean isExistMenu(String menuName){
		try{
			String query = "select * from menu where name = '"+menuName+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			boolean isexist = rs.next();
			rs.close();
			ps.close();
			return isexist;
		}catch(SQLException e){
			return false;
		}
	}
	
	public int insertPayInfo(String customerName, String staffId, String menuName){
		int membership = 0;
		
		try{
			int payId,price;
			String menuId;
			SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
		    String date = dateformat.format(new Date());
			String query = "select count(*) from payinfo";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			payId = Integer.parseInt(rs.getString("COUNT(*)")) + 1000;
			rs.close();
			ps.close();
			

			query = "select * from menu where name = '"+menuName+"'";
			ps = dbTest.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			price = Integer.parseInt(rs.getString("price"));
			if(membership == 1) price = price * 90 / 100;
			else if(membership == 2) price = price * 80 / 100;
			else if(membership == 3) price = price * 70 / 100;
			menuId = rs.getString("menu_id");
			rs.close();
			ps.close();
			
			if(customerName != null){
				query = "select * from customer where name = '"+customerName+"'";
				ps = dbTest.prepareStatement(query);
				rs = ps.executeQuery();
				rs.next();
				String customerId = rs.getString("customer_id");
				String cMembership = rs.getString("position");
				int account = Integer.parseInt(rs.getString("account"));
				if(cMembership.equals("Bronze")) price = price * 90 / 100;
				else if(cMembership.equals("Silver")) price = price * 80 / 100;
				else if(cMembership.equals("Gold")) price = price * 70 / 100;
				
				if(account < 1000000 && account + price >= 1000000) executeQuery("update customer set position = 'Gold'");
				else if(account < 500000 && account + price >= 500000) executeQuery("update customer set position = 'Silver'");
				else if(account < 300000 && account + price >= 300000) executeQuery("update customer set position = 'Bronze'");
				
				rs.close();
				ps.close();
				executeQuery("update customer set account = account + '"+price+"' where customer_id = '"+customerId+"'");
			}
			executeQuery("insert into payinfo values('"+payId+"','"+String.valueOf(price)+"','"+date+"','"+staffId+"','"+menuId+"')");
			return membership;
		}catch(SQLException e){
			return membership;
		}
	}
	
	
	
	
	
	public boolean isExistCustomer(String name){
		boolean result;
		try{
			String query = "select * from customer where name = '"+name+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next() == false) result = false;
			else result = true;
		}catch(SQLException sqlEx){
			result = false;
		}return result;
	}
	
	public boolean isExistStaff(String name){
		boolean result;
		try{
			String query = "select * from staff where name = '"+name+"'";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next() == false) result = false;
			else result = true;
		}catch(SQLException sqlEx){
			result = false;
		}return result;
	}
	
	public boolean isAddMenu(){ //20개 안 넘었는지
		try{
			String query = "select count(*) from menu";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(Integer.parseInt(rs.getString("count(*)")) <20) return true;
			else return false;
		}catch(SQLException sqlEx){
			return false;
		}
	}
	
	
	
	
	
	public boolean checkOKCustomerTable(){
		if(executeQuery("select customer_id from customer")
				&&executeQuery("select name from customer")
				&&executeQuery("select birth from customer")
				&&executeQuery("select phone from customer")
				&&executeQuery("select position from customer")
				&&executeQuery("select account from customer")){
			return true;
		}
		return false;
			
	}
	
	public boolean checkOKStaffTable(){
		try{
			String query = "select count(*) from staff";
			PreparedStatement ps = dbTest.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(Integer.parseInt(rs.getString("count(*)")) == 0){
				return false;
			}
			else{
				if( executeQuery("select staff_id from staff")
						&&executeQuery("select name from staff")
						&&executeQuery("select position from staff")){
					return true;
				}
				return false;
			}
		}catch(SQLException sqlEx){
			return false;
		}
	}
	
	public boolean checkOKMenuTable(){
		if(executeQuery("select menu_id from menu")
				&&executeQuery("select name from menu")
				&&executeQuery("select price from menu")){
			return true;
		}
		return false;
		
	}
	
	public boolean checkOKPayInfoTable(){
		if(executeQuery("select pay_id from payinfo")
				&&executeQuery("select price from payinfo")
				&&executeQuery("select term from payfinfo")
				&&executeQuery("select staff_id from payinfo")
				&&executeQuery("select menu_id from payinfo")){
			return true;
		}
		return false;
	}
	
	public boolean isValidTable(){
		if(checkOKCustomerTable() && checkOKStaffTable() && checkOKMenuTable() && checkOKPayInfoTable()){
			return true;
		}
		return false;
	}
	
	public void createTable(){
		createTableCustomer();
		createTableStaff();
		createTableMenu();
		createTablePayInfo();
	}
	
	public void createTableCustomer() {
		executeQuery("create table customer(customer_id number(4),"
				 + "name varchar2(20),"
				 + "birth number(4),"
				 + "phone number(4),"
				 + "position varchar2(20),"
				 + "account number(20), primary key (customer_id))");
	}
	public void createTableStaff() {
		executeQuery("create table staff(staff_id number(4),"
				 + "name varchar2(20),"
				 + "position varchar2(20), primary key (staff_id))");
	}
	public void createTableMenu(){
		executeQuery("create table menu(menu_id number(4),"
				 + "name varchar2(30),"
				 + "price number(20), primary key (menu_id))");
	}
	public void createTablePayInfo(){
		executeQuery("create table payinfo(pay_id	number(25),"
				+ "price number(10),"
				+ "term	varchar2(10),"
				+ "staff_id	number(4),"
				+ "menu_id number(4),"
				+ "primary key (pay_id),"
				+ "foreign key (staff_id) references staff,"
				+ "foreign key (menu_id) references menu)");
	}
	
	public void dropTable(String tableName){
		executeQuery("drop table "+tableName);
	}
	public void dropTable(){
		dropTable("payinfo");
		dropTable("customer");
		dropTable("staff");	
		dropTable("menu");	
	}
	
	

	
	



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public static Connection getDbTest() {
		return dbTest;
	}



	public static void setDbTest(Connection dbTest) {
		DB_Connect.dbTest = dbTest;
	}



	public Event getEvent() {
		return event;
	}
	
	
	

}
