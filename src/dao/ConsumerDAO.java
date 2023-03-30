package dao;

import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public interface ConsumerDAO {
	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException;
	public void logout();
}
