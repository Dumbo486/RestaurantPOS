import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id = "system";
		String password = "csa1004";
		Event e;
		DB_Connect db = new DB_Connect();
		if(db.connectDB(id,password)){
			JOptionPane.showMessageDialog(null,"데이터베이스가 연결되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
			e = new Event(db);
			
		}else{
			JOptionPane.showMessageDialog(null,"데이터베이스 연결에 실패하였습니다.", "", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} 

	}

}
