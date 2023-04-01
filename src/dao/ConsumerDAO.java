package dao;

import java.util.List;

import dto.BilldetailDTO;
import dto.ConsumerDTO;
import dto.ConsumerSignUpDTO;
import exception.InvalidUserNameException;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public interface ConsumerDAO {
	public List<ConsumerDTO> getAllConsumerList() throws SomeThingWrongException, NoRecordFoundException;
	public void deleteConsumer(String categoryId) throws SomeThingWrongException, NoRecordFoundException;
	public void signUp(ConsumerSignUpDTO con) throws SomeThingWrongException, NoRecordFoundException, InvalidUserNameException;
	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException;
	public void logout();
	public double payBill(int id) throws SomeThingWrongException, NoRecordFoundException;
	List<BilldetailDTO> billHistory(int id) throws SomeThingWrongException, NoRecordFoundException;
}
