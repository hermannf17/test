package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBconnection {

public static Connection connect(String a) {
		
		if(a == null) {
			a = "jdbc:mysql://localhost:3306/DocumentManagementDB?useTimezone=true&serverTimezone=UTC";
		}
		
		Connection conn;
		
		try {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = a;
            String user = "root";
            String pw = "";
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println("Database connected");
			return conn;
			
			
		} catch (Exception e) {
            System.out.println("Database connection failed");
			e.printStackTrace();
			return null;
		}
		
			
	}



	
	
	
	
	
}
