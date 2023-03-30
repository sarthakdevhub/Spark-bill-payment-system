package ui;

import java.util.Scanner;

import dao.ComplaintDAO;
import dao.ComplaintDAOImpl;

public class ComplaintUI {
	private ComplaintDAO complaintDAO;
	private Scanner scanner;
	
	public ComplaintUI(Scanner scanner){
		complaintDAO = new ComplaintDAOImpl();
		this.scanner = scanner;
	}
}
