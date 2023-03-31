package dao;

import java.util.List;

import dto.ConsumerDTO;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public interface ConsumerDAO {
	public List<ConsumerDTO> getAllConsumerList() throws SomeThingWrongException, NoRecordFoundException;
	public void deleteConsumer(String categoryId) throws SomeThingWrongException, NoRecordFoundException;
	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException;
	public void logout();
}
