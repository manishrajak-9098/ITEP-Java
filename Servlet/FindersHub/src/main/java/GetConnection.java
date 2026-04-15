

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetConnection{
	public static Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Connected ");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itep16","root","root");
			if(con!=null) {
				System.out.println("Connection established successfully");
			}
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception : "+e);
		}
		return con;
	}
}