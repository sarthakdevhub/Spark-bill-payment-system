package dao;

import java.util.List;

import dto.BillDTO;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public interface BillDAO {
	public List<BillDTO> getBillofConsumer(String cID) throws SomeThingWrongException, NoRecordFoundException;
	public List<BillDTO> getAllBills() throws SomeThingWrongException, NoRecordFoundException;
	public List<BillDTO> getAllPendingBills() throws SomeThingWrongException, NoRecordFoundException;
	public List<BillDTO> getAllPaidBills() throws SomeThingWrongException, NoRecordFoundException;
}
