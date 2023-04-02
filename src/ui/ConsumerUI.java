package ui;

import java.util.List;
import java.util.Scanner;

import dao.ConsumerDAO;
import dao.ConsumerDAOImpl;
import dao.LoggedINUser;
import dto.BilldetailDTO;
import dto.ConsumerDTO;
import dto.ConsumerSignUpDTO;
import dto.ConsumerSignUpDTOImpl;
import exception.InvalidUserNameException;
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
			System.out.println("------------------------------------------------------");
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
	
	public void payBill() {
		int id = LoggedINUser.loggedInUserId;
		//code to take input category details
		try {
			System.out.println("Bill Paid\nAmount Due "+consumerDAO.payBill(id));
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void history() {
		int id = LoggedINUser.loggedInUserId;
		try {
			List<BilldetailDTO> listUsers = consumerDAO.billHistory(id);
			System.out.println("Payment Date  |   Due Date  |  Amount Paid  |  Unit consumed  ");
			listUsers.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
//			ex.printStackTrace();
			System.out.println(ex);
		}
	}
	
	
	public void signUp(){
		scanner = new Scanner(System.in);
		System.out.print("Enter your firstname: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter your lastname: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your mobile: ");
        String mobile = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
		System.out.println("Enter Security Question");
		String Sques = scanner.nextLine();
		System.out.println("Enter Security Answer");
		String Sans = scanner.nextLine();
		
		ConsumerSignUpDTO consign = new ConsumerSignUpDTOImpl(firstname, lastname, username, password, address, mobile, email, Sques, Sans);
		try {
			consumerDAO.signUp(consign);
			System.out.println("Sign Up Successful");
		} catch (SomeThingWrongException |NoRecordFoundException| InvalidUserNameException e) {
			System.out.println(e);
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
			System.out.println("Welcome "+username);
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
