package in.qasim.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P2_InsertApp {
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
        //Establish Connection
        String url="jdbc:mysql:///practice";
        //if downloaded using default paths then no need to write "localhost:3306"     
        String username="root";
        String password="SQLjourney";
        Connection connection = DriverManager.getConnection(url,username ,password);
        System.out.println("The implement class name is: " + connection.getClass().getName());

        //Create the Statement Object and send Query
        String sqlInsertQuery = "insert into player values (3,'SACHIN',53,'MI')";
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
        	Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Integer age = resultSet.getInt(3);
            String address = resultSet.getString(4);
        	System.out.println(id+"\t"+name+"\t"+age+"\t"+address);
        }

        //Close the Resources
        resultSet.close();
        statement.close();
        connection.close();
    }
}
