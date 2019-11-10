import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Event implements ActionListener {
	private int who;//0번 nobody 1번 staff 2번 supervisor
	private String name;//사원 이름 
	private String id;//사원 번호
	private ArrayList<Menu> menu;//
	private LoginView lv;
	private MainView mv;
	private DB_Connect db;
	private CustomerJoinView cjView;
	private StaffJoinView sjView;
	private MenuJoinView mjView;
	
	public Event(DB_Connect db){
		this.db = db;
		db.setEvent(this);
		this.menu = new ArrayList<Menu>();
		run();
		
	}
	
	public void run(){
		try{
			this.who = 0;
			this.lv = new LoginView(this);
			this.mv = new MainView(this);
			this.cjView = new CustomerJoinView(this);
			this.sjView = new StaffJoinView(this);
			this.mjView = new MenuJoinView(this);
			

		}
		catch (Exception e){}
			
	}
	
	void setVisibleTermBox(){
		
			mv.getJoinCheckPanel().getTermBox().removeAllItems();
			//ArrayList<String> dateList = db.getTermList();
			if(db.getTermList() == null) return;
			else{
				for(int i = 0 ; i < db.getTermList().size(); i ++){
					String term = db.getTermList().get(i).toString();
					mv.getJoinCheckPanel().getTermBox().addItem(term);
				}
			}
	}
	
	void completeLogin(){
		String text ="\n---------------------------\n총 합계\t0";
		mv.getOrderPanel().getOrderArea().setText(text);
		showMsg("사원 로그인이 되었습니다.");
		lv.setVisible(false);
	}
	
	void initJoinCheckScreen(){
		mv.getJoinCheckPanel().getTermBox().removeAllItems();
		mv.getJoinCheckPanel().getSalesInfoArea().setText("");
		mv.getJoinCheckPanel().getMenuInfoArea().setText("");
		mv.getJoinCheckPanel().getCustomerInfoArea().setText("");
		mv.getJoinCheckPanel().getStaffInfoArea().setText("");
	}
	
	void initTableOrderScreen(int tableIndex){
		mv.getOrderPanel().getcNameField().setText("");
		mv.getTablePanel().getTables(tableIndex).getOrderList().clear();
		mv.getTablePanel().getTables(tableIndex).setBackground(Color.WHITE);
		mv.getOrderPanel().getOrderArea().setText("\n---------------------------\n총 합계\t0");
	}
	
	void showMsg(String msg){
		JOptionPane.showMessageDialog(null,msg, "", JOptionPane.WARNING_MESSAGE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		try{
			Object action = ae.getSource();
			//메뉴 3가지 눌렀을 때
			if(action == mv.getMenuOpen()){
				File file = fileChoose();
				if(file != null){
					fileRead(file);
					for(int i = 0; i < 20 ; i ++){
						mv.getTablePanel().getTables(i).getOrderList().clear();
						
					}
					menu.clear();
					mv.getOrderPanel().getOrderArea().setText("\n---------------------------\n주문내역이 없습니다.");
					db.setVisibleMenu();
				}
				else{
					showMsg("파일을 불러올 수 없습니다.");
				}
			}
			else if(action == mv.getMenuLogin()){
				if(who == 0){
					lv.clear();
					lv.setVisible(true);
				}
				else{
					showMsg("이미 로그인 한 상태입니다.\n로그아웃을 해주세요.");
				}
			}
			else if(action == lv.getLoginButton()){
				login();
			}
			else if(action == mv.getMenuLogout()){
				logout();
			}
			//로그인 안하고 버튼 눌렀을 때
			else if(who == 0 && action != mv.getOrderPanel().gettNameBox()){ 
				showMsg("로그인이 필요합니다.");
				lv.clear();
				lv.setVisible(true);
			
			//로그인 하고 버튼 눌렀을 때
			}
			else{
				// staff, supervisor 둘 다 가능
				if(who >= 1){
					
					//각각의 메뉴를 누를 때 OrderArea에 정보 띄우기
					for(int i = 0 ; i < 20 ; i ++){
						if(action == mv.getMenuPanel().getMenus(i)){
							Menu m =  mv.getMenuPanel().getMenus(i);
							menu.add(m);
							
							String str = "";
							int sum =0;
							
							for(int j = 0 ; j < menu.size(); j ++){	
								str+= menu.get(j).getName() + "\t" + String.valueOf(menu.get(j).getPrice())+"\n";
								sum += menu.get(j).getPrice();
							}
							str+="\n---------------------------\n총 합계\t"+String.valueOf(sum);
							mv.getOrderPanel().getOrderArea().setText(str);
							return ;
							
						}
					}
					
					//각각의 테이블을 누를 때 orderArea에 정보 띄우기
					for(int i=0; i<20;i++){
						if(action == mv.getTablePanel().getTables(i)){
							menu.clear();
							String str = "";
							int sum = 0;
					
							mv.getOrderPanel().gettNameBox().setSelectedIndex(i);
							for(int j = 0 ; j < mv.getTablePanel().getTables(i).getOrderList().size() ; j ++){
								Menu m =  mv.getTablePanel().getTables(i).getOrderList().get(j);
								menu.add(m);
								str += m.getName() +"\t"+String.valueOf(m.getPrice()) + "\n";
								sum += m.getPrice();
							}
							str+="\n---------------------------\n총 합계\t"+String.valueOf(sum);
							mv.getOrderPanel().getOrderArea().setText(str);
							return;
						}
					}
					
					
					// 테이블 넘버 콤보박스 바꿀 때
					if(action == mv.getOrderPanel().gettNameBox()){
						String str = "";
						int sum = 0;
						int tableIndex = mv.getOrderPanel().gettNameBox().getSelectedIndex(); 
						menu.clear();
					 
						for(int j = 0 ; j < mv.getTablePanel().getTables(tableIndex).getOrderList().size() ; j ++){
							Menu curM =  mv.getTablePanel().getTables(tableIndex).getOrderList().get(j);
							menu.add(curM);
							str += curM.getName() +"\t"+String.valueOf(curM.getPrice()) + "\n";
							sum += curM.getPrice();
						}
						str+="\n---------------------------\n총 합계\t"+String.valueOf(sum);
						
						mv.getOrderPanel().getOrderArea().setText(str);
						mv.getOrderPanel().getcNameField().setText("");
						return;
						
					}
					//주문 버튼 눌렀을 때
					else if(action == mv.getOrderPanel().getOrderButton()){
						//테이블 정보 set
						int tableIndex = mv.getOrderPanel().gettNameBox().getSelectedIndex(); 
						int price = 0;
						Table tempTable = mv.getTablePanel().getTables(tableIndex);
						
						if(menu.size() == 0){
							showMsg("주문할 메뉴가 없습니다.");
							return ;
						}
						tempTable.getOrderList().clear();
						for(int i = 0 ; i <menu.size() ; i ++){
							tempTable.getOrderList().add(menu.get(i));
							price += menu.get(i).getPrice();
						}
						tempTable.setPrice(price);
						tempTable.setBackground(Color.YELLOW);
						menu.clear();
						
						
						String str = "";
						int sum = 0;
						menu.clear();
						for(int j = 0 ; j < mv.getTablePanel().getTables(tableIndex).getOrderList().size() ; j ++){
							Menu m =  mv.getTablePanel().getTables(tableIndex).getOrderList().get(j);
							menu.add(m);
							str += m.getName() +"\t"+String.valueOf(m.getPrice()) + "\n";
							sum += m.getPrice();
						}
						str+="\n---------------------------\n총 합계\t"+String.valueOf(sum);
						mv.getOrderPanel().getOrderArea().setText(str);
						showMsg("주문이 완료되었습니다.");
						return ;
						
					}
					//주문 취소 버튼 눌렀을 때
					else if(action == mv.getOrderPanel().getCancelButton()){
						int tableIndex = mv.getOrderPanel().gettNameBox().getSelectedIndex();
						initTableOrderScreen(tableIndex);
						menu.clear();
						showMsg("주문이 취소되었습니다.");
						return ;
					}
					//결제 버튼 눌렀을 때
					else if(action == mv.getOrderPanel().getPayButton()){
						int tableIndex = mv.getOrderPanel().gettNameBox().getSelectedIndex();
						ArrayList<Menu> orderList = mv.getTablePanel().getTables(tableIndex).getOrderList();
						//해당 테이블이 주문한 내역이 없을 때
						if(orderList.size() == 0){ 
							showMsg("결제할 내역이 없습니다.");
							return;
						}
						//해당 테이블 주문 내역이 있을 때
						else{
							String customerName = mv.getOrderPanel().getcNameField().getText().trim();
							//고객명이 빈칸일 때 - 결제 
							if(customerName.equals("")){
								for(int i = 0 ; i < orderList.size(); i ++){
									db.insertPayInfo(null,id,orderList.get(i).getName());
									
								}
							}
							//고객명이 데이터에 있는지 확인 - 결제
							else if(db.isExistCustomer(customerName)){
								for(int i = 0 ; i < orderList.size(); i ++){
									db.insertPayInfo(customerName,id,orderList.get(i).getName());
								}
							}
							//고객명이 데이터에 없을 때
							else{ 
								showMsg("등록되지 않은 고객 이름입니다.");
								return;
							}
						}
						
						//결제하고나서
						initTableOrderScreen(tableIndex);
						menu.clear();
						if(who == 2) setVisibleTermBox();//supervisor는 결제 버튼 누르면 매출 탭에 기간이 뜸
						showMsg("결제가 완료되었습니다.");
						return ;
					}
					//고객 조회 버튼
					else if(action == mv.getJoinCheckPanel().getCustomerCheckButton()){
						findCustomer();
					}
					//직원 조회 버튼
					else if(action == mv.getJoinCheckPanel().getStaffCheckButton()){
						findStaff();
					}
					//메뉴 조회 버튼
					else if(action == mv.getJoinCheckPanel().getMenuCheckButton()){
						findMenu();
					}
					
					
					
				}
				//Staff만 할 수 있는 것
				if(who == 1){
					// 각자 가입 시키기, 기간 콤보박스 보이는 거 막아놓기
					Object cAction = mv.getJoinCheckPanel().getCustomerJoinButton();
					Object sAction = mv.getJoinCheckPanel().getStaffJoinButton();
					Object mAction = mv.getJoinCheckPanel().getMenuJoinButton();
					Object tAction = mv.getJoinCheckPanel().getTermBox();
					
					if(action == cAction || action == sAction || action == mAction || action == tAction)
						showMsg("Supervisor만 접근할 수 있습니다.");
					
				}
				//Supervisor만 할 수 있는 것
				else if(who== 2){
					//고객 가입 버튼 눌렀을 때
					if(action == mv.getJoinCheckPanel().getCustomerJoinButton()){
						cjView.clear();
						cjView.setVisible(true);
					}
					// 직원 가입 버튼 눌렀을 때 
					else if(action == mv.getJoinCheckPanel().getStaffJoinButton()){
						sjView.clear();
						sjView.setVisible(true);
					}
					//메뉴 등록 버튼 눌렀을 때
					else if(action == mv.getJoinCheckPanel().getMenuJoinButton()){
						mjView.clear();
						mjView.setVisible(true);
						
					}
					//고객 가입 - 가입 버튼 눌렀을 때
					else if(action == cjView.getcJoinButton()){
						EJoinCustomer();
					}
					// 직원 가입-가입 버튼 눌렀을 때
					else if(action == sjView.getsJoinButton()){
						EJoinStaff();
					}
					//메뉴 등록 - 등록 버튼 눌렀을 때
					else if(action == mjView.getmJoinButton()){
						EJoinMenu();
					}
					//고객 가입 - 취소 버튼 눌렀을 때
					else if(action == cjView.getcCancelButton()){
						cjView.clear();
						cjView.setVisible(false);
					}
					
					// 직원 가입-취소 버튼 눌렀을 때
					else if(action == sjView.getsCancelButton()){
						sjView.clear();
						sjView.setVisible(false);
					}
					
					//메뉴 등록 - 취소 버튼 눌렀을 때
					else if(action == mjView.getmCancelButton()){
						mjView.clear();
						mjView.setVisible(false);
					}
					// 매출 탭에서 기간 설정하면 매출 정보 보여주기
					else if(action == mv.getJoinCheckPanel().getTermBox()){
						String term = (String)mv.getJoinCheckPanel().getTermBox().getSelectedItem();
						db.setVisiblePayInfo(term);
					}
				}
			}
		}catch(Exception e){}
	}
	
	
	public void showCustomer(String cName, String customer_id, String birth, String phone, String pos, String account){
		String text = "고객명 : "+cName +"\n고객ID : "+customer_id+"\n생일 : "+birth+"\n전화번호 : "+phone+"\n고객등급 : "+pos+"\n총 구매금액 : "+account;
		mv.getJoinCheckPanel().getCustomerInfoArea().setText(text);
	}
	
	public void showStaff(String sName, String pos, String account){
		String text = "직원명 : "+sName+"\n직급 : "+pos+"\n총 실적 : "+account;
		mv.getJoinCheckPanel().getStaffInfoArea().setText(text);
	}
	
	public void showMenu(String mName, String price){
		String text = "메뉴명 : "+mName+"\n가격 : "+price;
		mv.getJoinCheckPanel().getMenuInfoArea().setText(text);
	}
	
	
	public void showPayInfo(String text){
		mv.getJoinCheckPanel().getSalesInfoArea().setText(text);
	}
	
	
	public void findCustomer(){
		String customerName = mv.getJoinCheckPanel().getCustomerNameField().getText().trim();
		if(customerName.equals("")){ 
			showMsg("고객명을 입력해주세요.");
			mv.getJoinCheckPanel().getCustomerNameField().requestFocus();
			return ;
		}
		else if(!db.checkCustomer(customerName)){ 
			showMsg("등록되지 않은 고객입니다.");
		}
	}

	
	public void findStaff(){
		String staffName = mv.getJoinCheckPanel().getStaffNameField().getText().trim();
		
		if(staffName.equals("")){ 
			showMsg("직원명을 입력해주세요.");
			mv.getJoinCheckPanel().getStaffNameField().requestFocus();
			return ;
		}else if(!db.checkStaff(staffName)){ 
			showMsg("등록되지 않은 직원입니다.");
		}
	}
	
	public void findMenu(){
		String menuName = mv.getJoinCheckPanel().getMenuNameField().getText().trim();
		if(menuName.equals("")){ 
			showMsg("메뉴를 입력해주세요.");
			mv.getJoinCheckPanel().getMenuNameField().requestFocus();
			return ;
		}
		else if(!db.checkMenu(menuName)){ 
			showMsg("등록되지 않은 메뉴입니다.");
		}
	}
	
	
	public void EJoinCustomer(){
		String name = cjView.getcNameField().getText().trim();
		String sBirth = "";
		String sPhone = "";
		int birth = 0;
		int phone = 0;
		if(name.equals("")){ 
			showMsg("고객명을 입력해주세요.");
			return;	
		}
		else if(db.isExistCustomer(name)){ 
			showMsg("이미 등록된 고객입니다.");
			return;
		}
		//생년월일 입력 예외처리
		try{
			sBirth = cjView.getcBirthField().getText().trim();
			birth = Integer.parseInt(sBirth);
			
		}catch(NumberFormatException nfe){ 
			showMsg("4자리 숫자의 생년월일을 입력해주세요.");
			return ;
		}
		
		//휴대폰 뒷자리 입력 예외처리
		try{
			sPhone = cjView.getcPhoneField().getText().trim();
			phone = Integer.parseInt(sPhone);
			
			if(cjView.getcPhoneField().getText().length() != 4){
				showMsg("4자리 숫자의 휴대폰 뒷번호를 입력해주세요.");
				return ;
			}
		}catch(NumberFormatException nfe){ 
			showMsg("4자리 숫자의 휴대폰 뒷번호를 입력해주세요.");
			return ;
		}
		
	
		if(db.joinCustomer(name,sBirth,sPhone)){
			showMsg("고객으로 등록되었습니다.");
			cjView.setVisible(false);
		}else{
			showMsg("고객등록에 실패하였습니다.");
			cjView.setVisible(false);		
		}
	}
	
	public void EJoinStaff(){
		String staffName = sjView.getsNameField().getText().trim();
		if(staffName.equals("")){
			showMsg("직원명을 입력해주세요.");
			return ;
		}else if(db.isExistStaff(staffName)){ 
			showMsg("이미 등록된 직원입니다.");
			return;
		}
		String pos = (String)sjView.getsPositionBox().getSelectedItem();
		db.joinStaff(staffName,pos);
		showMsg("직원으로 등록되었습니다.");
		sjView.setVisible(false);
		
		
	}
	
	public void EJoinMenu(){
		String menuName = mjView.getmNameField().getText().trim();
		String price = mjView.getmPriceField().getText().trim();
		if(menuName.equals("")){ 
			showMsg("메뉴명을 입력해주세요.");
			mjView.getmNameField().requestFocus();
			return;
		}
		else{
			try{
				int p = Integer.parseInt(price);
				if(db.isExistMenu(menuName)){
					showMsg("이미 등록된 메뉴입니다.");
					return;
				}
				else{
					if(db.isAddMenu()){
						if(db.joinMenu(menuName,price)){
							showMsg("메뉴로 등록되었습니다.");
							db.setVisibleMenu();
							mjView.setVisible(false);
						}
					}else{
						showMsg("메뉴가 초과되어 등록할 수 없습니다.");
					}
				}
			}catch(NumberFormatException nfe){ 
				showMsg("올바른 형식의 가격을 입력해주세요.");
			}
		}
	}
	public void login(){
		String name = lv.getNameInput().getText().trim();
		String id = lv.getIdInput().getText().trim();
		
		if(name.equals(""))
			showMsg("이름을 입력해주세요.");
		else if(id.equals(""))
			showMsg("사원번호를 입력해주세요.");
		else{
			try{
				Integer.parseInt(id.trim());
				String pos = db.loginCheck(name, id);
				
				if(pos == null)
					showMsg("사원이 아닙니다. 로그인할 수 없습니다.");
				
				else if(pos.equals("Staff")){
					setWho(1);
					setName(name);
					setId(id);
					
					initJoinCheckScreen();
					completeLogin();
				}
				else if(pos.equals("Supervisor")){
					setWho(2);
					setName(name);
					setId(id);
					
					setVisibleTermBox();
					completeLogin();
				}
				
			}catch(NumberFormatException ex){ 
				showMsg("올바른 형식의 사원번호를 입력해주세요.");
			}
		}
	}
	
	
	
	public void logout(){
		setWho(0);
		setName(null);
		setId(null);
	
		
		for(int i=0; i<20; i++){
			mv.getTablePanel().getTables(i).getOrderList().clear();
			mv.getTablePanel().getTables(i).setPrice(0);
			mv.getTablePanel().getTables(i).setBackground(Color.WHITE);
			mv.getTablePanel().getTables(i).setOpaque(true);
			
		}
		
		mv.getOrderPanel().getOrderArea().setText("\n---------------------------\n주문내역이 없습니다");
		mv.getOrderPanel().gettNameBox().setSelectedIndex(0);
	
		initJoinCheckScreen();
		showMsg("로그아웃 되었습니다.");
		

	}
	public void fileRead(File file){
		try{
			db.dropTable();
			db.createTable();
			mv.getMenuPanel();
			StringBuilder sb = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new FileReader(file));){
				String line = null;
				while ((line = br.readLine()) != null){
					sb.append(line);
					sb.append("\n");
				}
			} catch (IOException e) {}
			String data = sb.toString();
			StringTokenizer token = new StringTokenizer(data, "\t\n");
			int size = Integer.parseInt(token.nextToken());
		
			for(int id = 1000; id < 1000 + size; id++){
				String query = "insert into customer values('"+id+"','"+token.nextToken() + "','"+token.nextToken() + "','"+token.nextToken()+"','"; 
				String customerPos = token.nextToken();
				if(customerPos.equals("Normal")){
					query +=  "Normal" + "', '0')";
				} 
				else if (customerPos.equals("Bronze")){
					query += "Bronze" + "', '300000')";
				} 
				else if (customerPos.equals("Silver")){
					query += "Silver" + "', '500000')";
				} 
				else if (customerPos.equals("Gold")){
					query += "Gold" + "', '1000000')";
				}
				db.executeQuery(query);
			}
	
			size = Integer.parseInt(token.nextToken());
			for(int id = 1000; id < size + 1000; id++){
				String query = "insert into staff values('"+id+ "','"+token.nextToken()+"','"+token.nextToken()+"')";
				db.executeQuery(query);
			}
		
			size = Integer.parseInt(token.nextToken());
			for(int id = 1000; id < size + 1000; id++){
				String query = "INSERT INTO menu values('"+id+"','"+token.nextToken()+"','"+token.nextToken()+"')";
				db.executeQuery(query);
			}
			db.setVisibleMenu();
			setVisibleTermBox();
	

		}catch(Exception ex){ 
			showMsg("파일을 읽을 수 없습니다.");
			file = fileChoose();
			if(file != null){
				fileRead(file);
			}else{
				showMsg("파일을 불러올 수 없습니다.");
			}
		}	
	}
	public File fileChoose(){
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 파일 형식", "txt", "txt"); 
		fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	file = fileChooser.getSelectedFile();
        }
		return file;
	}
	

	public int getWho() {
		return who;
	}

	public void setWho(int who) {
		this.who = who;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Menu> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Menu> menu) {
		this.menu = menu;
	}

	public LoginView getLv() {
		return lv;
	}

	public void setLv(LoginView lv) {
		this.lv = lv;
	}

	public MainView getMv() {
		return mv;
	}

	public void setMv(MainView mv) {
		this.mv = mv;
	}

	public DB_Connect getDb() {
		return db;
	}

	public void setDb(DB_Connect db) {
		this.db = db;
	}

	public CustomerJoinView getCjView() {
		return cjView;
	}

	public void setCjView(CustomerJoinView cjView) {
		this.cjView = cjView;
	}

	public StaffJoinView getSjView() {
		return sjView;
	}

	public void setSjView(StaffJoinView sjView) {
		this.sjView = sjView;
	}

	public MenuJoinView getMjView() {
		return mjView;
	}

	public void setMjView(MenuJoinView mjView) {
		this.mjView = mjView;
	}
	
	
	
	
	
	
	
	
}
