package in.qasim.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class P7_DynamicPS {
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
        Scanner sc = new Scanner(System.in);
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
        		// Take user Input
        		System.out.print("ENTER ID : ");
        		int id = sc.nextInt();
        		System.out.print("ENTER NAME : ");
        		String name = sc.next();
        		System.out.print("ENTER AGE : ");
        		int age = sc.nextInt();
        		System.out.print("ENTER ADDERSS : ");
        		String address = sc.next();
        		
        		// Use PreCompiled Query to set Values
        		Pstatement.setInt(1,id);
        		Pstatement.setString(2,name);
        		Pstatement.setInt(3,age);
        		Pstatement.setString(4,address);
        		
        		// Execute the Query
        		int rows = Pstatement.executeUpdate();
        		System.out.println("NUMBER OF ROWS AFFECTED : "+rows);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
        		sc.close();
        		Pstatement.close();
        		connection.close();
        		System.out.println("CLOSED THE RESOURCES!");
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        }
    }
}
