package in.qasim.DateApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.qasim.util.JdbcUtil;

public class retieveDate {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		java.sql.Date sqlDob = null;
		
		String name = null;
		String dob = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) {
				String sqlSelectQuery = "select id,name,dob from employee";
				stmt = connection.prepareStatement(sqlSelectQuery);
			}
			if(stmt != null) {
				rs = stmt.executeQuery();
			}
			if(rs != null) {
				System.out.println("ID\tNAME\tDOB");
				while(rs.next()) {
					Integer getid = rs.getInt(1);
					String getname = rs.getString(2);
					java.sql.Date getdate = rs.getDate(3);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String date = sdf.format(getdate);
					System.out.println(getid+"\t"+getname+"\t"+date);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, stmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
