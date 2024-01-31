package in.qasim.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class P6_PreparedStatementApp {
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
        Connection connection = null;
        PreparedStatement Pstatement = null;
        String url="jdbc:mysql:///practice";
        String username="root";
        String password="SQLjourney";
        try {
        	connection = DriverManager.getConnection(url,username,password);
        	System.out.println("ESTABLISHED THE CONNECTION!");
        	String sqlInsertQuery = "insert into player values (?,?,?,?)";
        	if(connection != null) {
        		Pstatement = connection.prepareStatement(sqlInsertQuery);
        	}
        	if(Pstatement != null) {
        		Pstatement.setInt(1,5);
        		Pstatement.setString(2,"JADEJA");
        		Pstatement.setInt(3,38);
        		Pstatement.setString(4,"CSK");
        		
        		int rows = Pstatement.executeUpdate();
        		System.out.println("NUMBER OF ROWS AFFECTED : "+rows);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
        		Pstatement.close();
        		connection.close();
        		System.out.println("CLOSED THE RESOURCES!");
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        }
    }

}
