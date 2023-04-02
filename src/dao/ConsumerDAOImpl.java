package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.BilldetailDTO;
import dto.BilldetailDTOImpl;
import dto.ConsumerDTO;
import dto.ConsumerDTOImpl;
import dto.ConsumerSignUpDTO;
import exception.InvalidUserNameException;
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
			String SELECT_QUERY = "SELECT consumer_id,firstname,username,password,is_active FROM consumerdetail"
					+ " where is_active = 1;";
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
			String DELETE_QUERY = "Update consumerdetail set is_active = 0 where consumer_id = ?";
			
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
	public double payBill(int id) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		double due;
		double paid;
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
			
			String QUERY1 = "select amountDue,amountPaid from bill where consumer_id = ? AND isPaid = 0";
			PreparedStatement ps1 = connection.prepareStatement(QUERY1);
			ps1.setString(1, cID);
			ResultSet rs = ps1.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No Bill Found");
			}
			rs.next();
			due = rs.getDouble(1);
			paid = rs.getDouble(2);
			System.out.println("Amount Due-> "+due);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Pay Bill\nEnter Amount");
			double money = sc.nextDouble();
			String QUERY2 = "Update bill set amountDue = ?,amountPaid = ?,isPaid = ?,payment_date=? where consumer_id = ?";
			
			//get the prepared statement object
			PreparedStatement ps2 = connection.prepareStatement(QUERY2);
			due = due-money;
			int p = 0;
			if(due==0) p=1;
			//stuff the data in the query
			Date date = Date.valueOf(LocalDate.now());
			ps2.setDouble(1, due);
			ps2.setDouble(2, money+paid);
			ps2.setInt(3, p);
			ps2.setDate(4, date);
			ps2.setString(5, cID);
			
			//execute query
			int x = ps2.executeUpdate();
//			System.out.println(x);
			
		
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
		return due;
	}
	
	private List<BilldetailDTO> getBillListFromResultSet(ResultSet resultSet) throws SQLException{
		List<BilldetailDTO> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of Employee
			BilldetailDTO con = new BilldetailDTOImpl();
			con.setPaymentDate(resultSet.getDate(1).toLocalDate());
			con.setDueDate(resultSet.getDate(2).toLocalDate());
			con.setAmountPaid(resultSet.getDouble(3));
			con.setUnit(resultSet.getDouble(4));
			list.add(con);
		}
		return list;
	}
	@Override
	public List<BilldetailDTO> billHistory(int id) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<BilldetailDTO> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String QUERY1 = "select consumer_id from consumerdetail where id = ?";
			
			//get the prepared statement object
			PreparedStatement ps1 = connection.prepareStatement(QUERY1);
			
			//stuff the data in the query
			ps1.setInt(1, id);
			
			//execute query
			ResultSet resultSet1 = ps1.executeQuery();
			resultSet1.next();
			String cID = resultSet1.getString(1);
			
			String SELECT_QUERY = "SELECT payment_date,due_date,amountPaid,unit_consumed FROM bill where consumer_id = ? AND isPaid = 1 ;";
//			String SELECT_QUERY = "select * from consumer";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, cID);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Transaction Found");
			}
			
			list = getBillListFromResultSet(resultSet);
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
	
	static boolean checkUsername(String Username) throws SomeThingWrongException {
		Connection connection = null;
		int conID;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String QUERY = "select username from consumerdetail where username = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(QUERY);
			//execute query
			ps.setString(1, Username);
			ResultSet resultSet = ps.executeQuery();
			return resultSet.next();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
			throw new SomeThingWrongException();
		}finally {
			try {
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
	}
	
	static int getConsumerID() throws SomeThingWrongException {
		Connection connection = null;
		int conID;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String QUERY = "select id from consumerdetail"
					+ " order by id desc"
					+ " limit 1;";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(QUERY);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			conID = resultSet.getInt("id");
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
			throw new SomeThingWrongException();
		}finally {
			try {
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
		return conID;
	}
	
	
	
	@Override
	public void signUp(ConsumerSignUpDTO con) throws SomeThingWrongException, NoRecordFoundException, InvalidUserNameException{
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String userID = "C"+getConsumerID();
			if(checkUsername(con.getUsername())==true) {
				throw new InvalidUserNameException();
			}

			String SignIN_QUERY = "insert into consumerdetail"
							+ " (firstname,lastname,username,password,address,mobile,email,securityques,"
							+ "securityans,consumer_id)"
							+ " Values"
							+ " (?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(SignIN_QUERY);
			ps.setString(1,con.getFname());
			ps.setString(2,con.getLname());
			ps.setString(3,con.getUsername());
			ps.setString(4,con.getPassword());
			ps.setString(5,con.getAddress());
			ps.setString(6,con.getMobile());
			ps.setString(7,con.getEmail());
			ps.setString(8,con.getSques());
			ps.setString(9,con.getSans());
			ps.setString(10,userID);
			
			ps.executeUpdate();
			
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
	
	@Override
	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String LOGIN_QUERY = "SELECT id FROM consumerdetail WHERE username = ? AND password = ? AND is_active = 1";
			
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
			LoggedINUser.loggedInUserId = resultSet.getInt("id");
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
	
	@Override
	public void logout() {
		LoggedINUser.loggedInUserId = 0;
	}
}
