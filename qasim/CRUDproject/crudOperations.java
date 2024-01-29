package in.qasim.CRUDproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class crudOperations {
	Scanner sc = new Scanner(System.in);
    String url="jdbc:mysql:///crudproject";
    String username="root";
    String password="SQLjourney";
    Connection connection;
    
    crudOperations(){
    	connect();
    	showMenu();
    }
    void connect() {
    	try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    void showMenu() {
    	System.out.println("\nWELCOME\n");
    	int choice = 0;
    	while(choice!=5) {
        	System.out.print("ENTER 1 : TO SHOW\nENTER 2 : TO INSERT\nENTER 3 : TO DELETE\nENTER 4 : TO UPDATE\nENTER 5 : TO EXIT\nENTER HERE : ");
    		choice = sc.nextInt();
    		switch(choice) {
	    		case 1:
	    			selectQuery();
				break;
	    		case 2:
	    			insertQuery();
				break;
	    		case 3:
	    			deleteQuery();
				break;
	    		case 4:
	    			updateQuery();
				break;
	    		case 5:
    			return;
	    		default:
	    			System.out.println("\nINVALID CHOICE\n");
				break;
    		}
    	}
    }
    void selectQuery() {
    	PreparedStatement select;
		try {
			select = connection.prepareStatement("SELECT * FROM users");
	    	ResultSet rs = select.executeQuery();
	    	System.out.println("ID\tNAME\\CITY\tAGE");
	        //Process the resultSet
	        while (rs.next()) {
	            Integer id = rs.getInt(1);
	            String name = rs.getString(2);
	            String age = rs.getString(3);
	            Integer city = rs.getInt(4);

	            System.out.println(id + "\t" + name + "\t" + city + "\t" + age);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    void insertQuery() {
    	PreparedStatement insert;
    	int rows = 0;
		try {
			insert = connection.prepareStatement("INSERT INTO users(`firstname`,`city`,`age`) VALUES(?,?,?)");
	    	System.out.print("ENTER NAME : ");
	    	String name = sc.next();
	    	System.out.print("ENTER CITY : ");
	    	String city = sc.next();
	    	System.out.print("ENTER AGE : ");
	    	int age = sc.nextInt();
	    	
	    	insert.setString(1, name);
	    	insert.setString(2, city);
	    	insert.setInt(3, age);
	    	
	    	rows = insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	if(rows!=0) {
			System.out.println("INSERTED SUCCESSFULLY");
		}else {
			System.out.println("OPERATION FAILED");
		}
    }
    void deleteQuery() {
    	PreparedStatement delete = null;
    	int rows = 0;
		try {
			delete = connection.prepareStatement("DELETE FROM users WHERE id=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.print("ENTER ID TO DELETE : ");
    	int id = sc.nextInt();
    	
    	try {
			delete.setInt(1, id);
	    	rows = delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(rows!=0) {
			System.out.println("DELETED SUCCESSFULLY");
		}else {
			System.out.println("OPERATION FAILED");
		}
    }
    void updateQuery() {
    	System.out.println("ENTER 1 : TO UPDATE NAME");
    	System.out.println("ENTER 2 : TO UPDATE CITY");
    	System.out.println("ENTER 3 : TO UPDATE AGE");
    	System.out.print("ENTER HERE : ");
    	int choice = sc.nextInt();
    	System.out.print("ENTER ID TO UPDATE : ");
    	int id = sc.nextInt();
    	switch(choice) {
	    	case 1:
	    		updateName(id);
			break;
	    	case 2:
	    		updateCity(id);
			break;
	    	case 3:
	    		updateAge(id);
			break;
			default:
				System.out.println("\nINVALID INPUT\n");
			break;
    	}
    }
    void updateName(int id) {
    	PreparedStatement uName = null;
    	int row = 0;
		try {
			uName = connection.prepareStatement("update users set firstname=? where id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.print("ENTER NEW NAME : ");
    	String name = sc.next();
    	
    	try {
			uName.setString(1, name);
	    	row = uName.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(row!=0) {
			System.out.println("UPDATED SUCCESSFULLY");
		}else {
			System.out.println("OPERATION FAILED");
		}
    }
    void updateCity(int id) {
    	PreparedStatement uCity = null;
    	int row = 0;
		try {
			uCity = connection.prepareStatement("update users set city=? where id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.print("ENTER NEW CITY : ");
    	String city = sc.next();
    	
    	try {
			uCity.setString(1, city);
	    	row = uCity.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(row!=0) {
			System.out.println("UPDATED SUCCESSFULLY");
		}else {
			System.out.println("OPERATION FAILED");
		}
    }
    void updateAge(int id) {
    	PreparedStatement uAge = null;
    	int row = 0;
		try {
			uAge = connection.prepareStatement("update users set age=? where id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.print("ENTER NEW AGE : ");
    	int age = sc.nextInt();
    	
    	try {
			uAge.setInt(1, age);
	    	row = uAge.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(row!=0) {
			System.out.println("UPDATED SUCCESSFULLY");
		}else {
			System.out.println("OPERATION FAILED");
		}
    }
}
