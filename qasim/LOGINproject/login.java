package in.qasim.LOGINproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class login {
	Scanner sc = new Scanner(System.in);
    String url="jdbc:mysql:///loginproject";
    String username="root";
    String password="SQLjourney";
	Connection connection;
	login(){
		connect();
	}
	private void connect() {
    	try {
			connection = DriverManager.getConnection(url,username,password);
			start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	private void start() {
		int n = 0;
		System.out.println("WELCOME");
		do {
			System.out.println("\nENTER 1 : TO LOGIN\nENTER 2 : TO REGISTER\nENTER 3 : TO EXIT");
			System.out.print("ENTER YOUR CHOICE : ");
			n = sc.nextInt();
			switch(n) {
				case 1:
					checkDetails();
				break;
				case 2:
					register();
				break;
				case 3:
					System.out.println("THANK YOU");
				default:
					System.out.println("INVALID INPUT");
				break;
			}
		}while(n!=3);
		
	}
	private void register() {
		PreparedStatement stmt;
		String p = "";
		String u = "";
		String sqlInsertQuery = "insert into credentials (`username`,`pin`) values (?,?)";
		System.out.println("PIN MUST BE 4 CHARCACTER LONG");
		do {
			System.out.print("ENTER USERNAME : ");
			u = sc.next();
			System.out.print("ENTER PIN : ");
			p = sc.next();
			if(p.length()!=4) {
				System.out.println("INVALID PIN\nTRY AGAIN\n");
			}
		}while(p.length()!=4);
		
		
		try {
			stmt = connection.prepareStatement(sqlInsertQuery);
			stmt.setString(1,u);
			stmt.setString(2,p);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void checkDetails() {
		PreparedStatement stmt;
		String sqlSelectQuery = "select * from credentials";
		System.out.print("ENTER USERNAME : ");
		String u = sc.next();
		System.out.print("ENTER PIN : ");
		String p = sc.next();
		
		try {
			stmt = connection.prepareStatement(sqlSelectQuery);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String checkUN = rs.getString(1);
				String checkPIN = rs.getString(2);
				if(checkUN.equals(u)) {
					if(checkPIN.equals(p)) {
						System.out.println("LOGIN SUCCESSFUL");
						System.exit(0);
					}else {
						System.out.println("INVALID PASSWORD");
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
