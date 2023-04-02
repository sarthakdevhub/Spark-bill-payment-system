package dao;

import java.sql.Date;

import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public interface ComplaintDAO {

	void raiseQuery(int id, Date date, String query) throws SomeThingWrongException, NoRecordFoundException;

}
