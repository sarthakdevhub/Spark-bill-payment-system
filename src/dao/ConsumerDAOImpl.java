package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masaischool.dto.User;
import com.masaischool.dto.UserImpl;

import exception.NoRecordFoundException;
import exception.SomeThingWrongException;


public class ConsumerDAOImpl implements ConsumerDAO {
	
	private List<User> getUserListFromResultSet(ResultSet resultSet) throws SQLException{
		List<User> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of Employee
			User user = new UserImpl();
			user.setUserId(resultSet.getInt("user_id"));
			user.setName(resultSet.getString("name"));
			user.setUsername(resultSet.getString("username"));
			user.setRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
			list.add(user);
		}
		return list;
	}
	@Override
	public List<User> getAllUsersList() throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		List<User> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM user";
			
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
