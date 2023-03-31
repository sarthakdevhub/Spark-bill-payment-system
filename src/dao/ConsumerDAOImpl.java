package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ConsumerDTO;
import dto.ConsumerDTOImpl;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;


public class ConsumerDAOImpl implements ConsumerDAO {
	
	private List<ConsumerDTO> getUserListFromResultSet(ResultSet resultSet) throws SQLException{
		List<ConsumerDTO> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of Employee
			ConsumerDTO con = new ConsumerDTOImpl();
			con.setConId(resultSet.getString(1));
			con.setName(resultSet.getString(2));
			con.setUsername(resultSet.getString(3));
			con.setPassword(resultSet.getString(4));
			con.setIsActive(resultSet.getInt(5));
			list.add(con);
		}
		return list;
	}
	@Override
	public List<ConsumerDTO> getAllConsumerList() throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		List<ConsumerDTO> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT c.consumer_id,cd.firstname,c.username,c.password,c.is_active FROM consumer c"
					+ " join consumerdetail cd "
					+ "on c.consumer_id = cd.consumer_id AND c.is_active = 1;";
//			String SELECT_QUERY = "select * from consumer";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No user Found");
			}
			
			list = getUserListFromResultSet(resultSet);
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
		return list;
	}
	
	@Override
	public void deleteConsumer(String categoryId) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String DELETE_QUERY = "Update Consumer set is_active = 0 where consumer_id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, categoryId);
			
			//execute query
			int x = ps.executeUpdate();
//			System.out.println(x);
			if(x==0) {
				throw new SomeThingWrongException();
			}
		}catch(SQLException sqlEx) {
			//code to log the error in the file
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
	
	@Override
	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String LOGIN_QUERY = "SELECT user_id FROM user WHERE username = ? AND password = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
			
			//stuff the data in the query
			ps.setString(1, username);
			ps.setString(2, password);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("Invalid Username and Password");
			}
			
			//you are here means username and password combination is correct
			resultSet.next();
			LoggedINUser.loggedInUserId = resultSet.getInt("user_id");
		}catch(SQLException sqlEx) {
			//code to log the error in the file
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
	
	@Override
	public void logout() {
		LoggedINUser.loggedInUserId = 0;
	}
}
