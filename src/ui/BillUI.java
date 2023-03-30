package ui;

import java.util.Scanner;

import dao.BillDAO;
import dao.BillDAOImpl;


public class BillUI {
	private BillDAO billDAO;
	private Scanner scanner;
	
	public BillUI(Scanner scanner){
		billDAO = new BillDAOImpl();
		this.scanner = scanner;
	}
}
