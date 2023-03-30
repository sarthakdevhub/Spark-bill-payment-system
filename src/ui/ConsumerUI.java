package ui;

import java.util.List;
import java.util.Scanner;
import dao.ConsumerDAO;
import dao.ConsumerDAOImpl;
import exception.NoRecordFoundException;
import exception.SomeThingWrongException;


public class ConsumerUI {
	private ConsumerDAO consumerDAO;
	private Scanner scanner;
	
	public ConsumerUI(Scanner scanner){
		consumerDAO = new ConsumerDAOImpl();
		this.scanner = scanner;
	}
	
	
	public void viewAllConsumer() {
		try {
			List<User> listUsers = consumerDAO.getAllUsersList();
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public boolean login() {
		boolean loginSuccessful = false;
		System.out.print("Enter username ");
		String username = scanner.next();
		
		System.out.print("Enter password ");
		String password = scanner.next();
		
		try {
			consumerDAO.Login(username, password);
			loginSuccessful = true;
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
		return loginSuccessful;
	}
	
	public void logout() {
		consumerDAO.logout();
	}
}
