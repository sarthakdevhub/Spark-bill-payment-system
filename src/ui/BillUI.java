package ui;

import java.util.List;
import java.util.Scanner;

import dao.BillDAO;
import dao.BillDAOImpl;
import dto.BillDTO;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;


public class BillUI {
	private BillDAO billDAO;
	private Scanner sc;
	
	public BillUI(Scanner sc){
		billDAO = new BillDAOImpl();
		this.sc = sc;
	}
	
	public void viewbillofConsumer() {
		try {
			System.out.println("Enter ConsumerID");
			String cID = sc.next();
			List<BillDTO> listUsers = billDAO.getBillofConsumer(cID);
//			System.out.println("conId  |   name  |  username  |  password  |  isActive");
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	
	public void viewAllbills() {
		try {
			List<BillDTO> listUsers = billDAO.getAllBills();
//			System.out.println("conId  |   name  |  username  |  password  |  isActive");
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAllPendingbills() {
		try {
			List<BillDTO> listUsers = billDAO.getAllPendingBills();
//			System.out.println("conId  |   name  |  username  |  password  |  isActive");
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAllPaidbills() {
		try {
			List<BillDTO> listUsers = billDAO.getAllPaidBills();
//			System.out.println("conId  |   name  |  username  |  password  |  isActive");
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
}
