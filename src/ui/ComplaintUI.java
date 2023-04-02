package ui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import dao.ComplaintDAO;
import dao.ComplaintDAOImpl;
import dao.LoggedINUser;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;

public class ComplaintUI {
	private ComplaintDAO complaintDAO;
	private Scanner scanner;
	
	public ComplaintUI(Scanner scanner){
		complaintDAO = new ComplaintDAOImpl();
		this.scanner = scanner;
	}
	
	
	public void query() {
		scanner = new Scanner(System.in);
		int id = LoggedINUser.loggedInUserId;
		Date date = Date.valueOf(LocalDate.now());
		System.out.println("Enter Complaint");
		String query = scanner.nextLine();
//		sc.close();
//		scanner.nextLine();
//		System.out.println(query);
		try {
			complaintDAO.raiseQuery(id,date,query);
			System.out.println("Complaint raised successfully");
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}	
	}
}
