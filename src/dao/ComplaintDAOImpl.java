package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public class ComplaintDAOImpl implements ComplaintDAO {
	
	@Override
	public void raiseQuery(int id ,Date date, String query) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String QUERY = "select consumer_id from consumerdetail where id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(QUERY);
			
			//stuff the data in the query
			ps.setInt(1, id);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			String cID = resultSet.getString(1);
//			System.out.println("Current Bill -"+cID);
			
			String QUERY1 = "insert into complaint"
					+ " (consumer_id,qdate,query)"
					+ " values"
					+ " (?,?,?)";
			PreparedStatement ps1 = connection.prepareStatement(QUERY1);
			ps1.setString(1, cID);
			ps1.setDate(2, date);
			ps1.setString(3, query);
			ps1.executeUpdate();
		
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			sqlEx.printStackTrace();
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
	}
}
