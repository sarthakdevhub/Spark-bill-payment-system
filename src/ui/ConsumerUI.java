package ui;

import java.util.List;
import java.util.Scanner;

import dao.ConsumerDAO;
import dao.ConsumerDAOImpl;
import dto.ConsumerDTO;
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
			List<ConsumerDTO> listUsers = consumerDAO.getAllConsumerList();
			System.out.println("conId  |   name  |  username  |  password  |  isActive");
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
//			ex.printStackTrace();
			System.out.println(ex);
		}
	}
	public void deleteConsumer() {
		//code to take input category details
		System.out.print("Enter Consumer id ");
		String catId = scanner.next();
		
		try {
			consumerDAO.deleteConsumer(catId);
			System.out.println("Consumer deleted successfully");
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
