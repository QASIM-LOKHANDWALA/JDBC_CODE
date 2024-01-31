package in.qasim.DateApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.qasim.util.JdbcUtil;

public class insertDate {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		java.sql.Date sqlDob = null;
		
		String name = null;
		String dob = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) {
				String sqlInsertQuery = "insert into employee(`name`,`dob`) values (?,?)";
				stmt = connection.prepareStatement(sqlInsertQuery);
			}
			if(stmt != null) {
				sc = new Scanner(System.in);
				if(sc != null) {
					System.out.print("Enter name : ");
					name = sc.next();
					System.out.print("Enter DOB(MM-dd-yyyy) : ");
					dob = sc.next();
					
					if(dob != null) {
						SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
						java.util.Date uDate = s.parse(dob);
						
						long value = uDate.getTime();
						sqlDob = new java.sql.Date(value);
					}
				}
				stmt.setString(1, name);
				stmt.setDate(2, sqlDob);
				
				int row = stmt.executeUpdate();
				System.out.println("Rows Affected : "+row);
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} catch(Exception e) {
			 e.printStackTrace();
		} finally {
			sc.close();
			try {
				JdbcUtil.cleanUp(connection, stmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
