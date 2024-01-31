package in.qasim.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private JdbcUtil() {
		
	}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getJdbcConnection() throws SQLException {
	    String url="jdbc:mysql:///practice";
	    String username="root";
	    String password="SQLjourney";
		Connection connection = DriverManager.getConnection(url,username,password);
		System.out.println("CONNECTION OBJECT CREATED...");
		return connection;
	}
	public static void cleanUp(Connection con,Statement st,ResultSet rs) throws SQLException {
		if(con != null) {
			con.close();
		}
		if(st != null) {
			st.close();
		}
		if(rs != null) {
			rs.close();
		}
	}
}
