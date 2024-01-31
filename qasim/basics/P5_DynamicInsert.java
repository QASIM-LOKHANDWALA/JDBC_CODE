package in.qasim.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class P5_DynamicInsert {
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
        //Establish Connection
        String url="jdbc:mysql:///practice";
        //if downloaded using default paths then no need to write "localhost:3306"     
        String username="root";
        String password="SQLjourney";
        Connection connection = DriverManager.getConnection(url,username ,password);
        System.out.println("The implement class name is: " + connection.getClass().getName());

        //Take Input from User
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID : ");
        int id = sc.nextInt();
        System.out.print("Enter NAME : ");
        String name = sc.next();
        System.out.print("Enter AGE : ");
        int age = sc.nextInt();
        System.out.print("Enter ADDRESS : ");
        String address = sc.next();
        
        
        //Create the Statement Object and send Query
        String sqlInsertQuery = String.format("insert into player (`sid`,`sname`,`sage`,`saddress`) values (%d,'%s',%d,'%s')",id,name,age,address);
        Statement statement = connection.createStatement();
        System.out.println("The implementation class name is: "+statement.getClass().getName());
        
        //Execute the Query
        int rowAffected = statement.executeUpdate(sqlInsertQuery);
        System.out.println("NUMBER OF ROWS AFFECTED ARE : "+rowAffected);
        
        //Print table
        String sqlSelectQuery = "select * from player";
        ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
        
        System.out.println("ID\tNAME\tAGE\tADDRESS");
        while(resultSet.next()) {
        	Integer sid = resultSet.getInt(1);
            String sname = resultSet.getString(2);
            Integer sage = resultSet.getInt(3);
            String saddress = resultSet.getString(4);
        	System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
        }

        //Close the Resources
		sc.close();
        resultSet.close();
        statement.close();
        connection.close();
    }
}
