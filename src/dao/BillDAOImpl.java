package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BillDTO;
import dto.BillDTOImpl;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public class BillDAOImpl implements BillDAO {
	private List<BillDTO> getUserListFromResultSet(ResultSet rs) throws SQLException{
		List<BillDTO> list = new ArrayList<>();
		while(rs.next()) {
			//Create an object of Employee
			BillDTO con = new BillDTOImpl();
			con.setid(rs.getInt(1));
			con.setcID(rs.getString(2));
			con.setUnit(rs.getDouble(3));
			con.setStart(rs.getDate(4).toLocalDate());
			con.setEnd(rs.getDate(5).toLocalDate());
			con.setFcharge(rs.getDouble(6));
			con.setTax(rs.getDouble(7));
			con.setTotalAmount(rs.getDouble(8));
			con.setAmountDue(rs.getDouble(9));
			con.setAmountPaid(rs.getDouble(10));
			con.setDueDate(rs.getDate(11).toLocalDate());
			con.setPaymentDate(rs.getDate(12).toLocalDate());
			con.setIsPaid(rs.getInt(13));
			list.add(con);
		}
		return list;
	}
	@Override
	public List<BillDTO> getBillofConsumer(String cID) throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		List<BillDTO> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select * from bill where consumer_id = ?";
//			String SELECT_QUERY = "select * from consumer";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, cID);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Bill Found");
			}
			
			list = getUserListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
//			sqlEx.printStackTrace();
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
	public List<BillDTO> getAllBills() throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		List<BillDTO> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select * from bill";
//			String SELECT_QUERY = "select * from consumer";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Bills Found");
			}
			
			list = getUserListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
//			sqlEx.printStackTrace();
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
	public List<BillDTO> getAllPendingBills() throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		List<BillDTO> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select * from bill where isPaid = 0";
//			String SELECT_QUERY = "select * from consumer";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Bills Found");
			}
			
			list = getUserListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
//			sqlEx.printStackTrace();
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
	public List<BillDTO> getAllPaidBills() throws SomeThingWrongException, NoRecordFoundException{
		Connection connection = null;
		List<BillDTO> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select * from bill where isPaid = 1";
//			String SELECT_QUERY = "select * from consumer";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Bills Found");
			}
			
			list = getUserListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
//			sqlEx.printStackTrace();
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
}
