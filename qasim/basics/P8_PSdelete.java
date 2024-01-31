package in.qasim.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class P8_PSdelete {
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
        	String sqlDeleteQuery = "delete from player where sid=?";
        	if(connection != null) {
        		Pstatement = connection.prepareStatement(sqlDeleteQuery);
        	}
        	if(Pstatement != null) {
        		System.out.print("ENTER ID TO DELETE : ");
        		int id = sc.nextInt();
        	
        		Pstatement.setInt(1,id);
        		
        		int rows = Pstatement.executeUpdate();
        		if(rows==0) {
        			System.out.println("NO RECORD FOUND");
        		}else {
        			System.out.println("NUMBER OF ROWS AFFECTED : "+rows);
        		}
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
