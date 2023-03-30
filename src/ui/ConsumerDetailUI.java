package ui;

import java.util.Scanner;

import dao.ConsumerDetailDAO;
import dao.ConsumerDetailDAOImpl;

public class ConsumerDetailUI {
	
	private ConsumerDetailDAO consumerDetailDAO;
	private Scanner scanner;
	
	public ConsumerDetailUI(Scanner scanner){
		consumerDetailDAO = new ConsumerDetailDAOImpl();
		this.scanner = scanner;
	}
}
