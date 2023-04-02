package ui;

import java.util.Scanner;
import dao.LoggedINUser;


public class MainUI {
	private static BillUI billUI;
	private static ComplaintUI complaintUI;
	private static ConsumerUI consumerUI;
	
	static void displayAdminMenu() {
		System.out.println("1. View all consumer");
		System.out.println("2. View the bill of the consumer");
		System.out.println("3. View all the bills");
		System.out.println("4. View all the pending bills");
		System.out.println("5. View all the paid bills");
		System.out.println("6. Delete consumer");
		System.out.println("0. for Exit");
	}
	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Admin Logged Out");
					break;
				case 1:
					consumerUI.viewAllConsumer();
					break;
				case 2:
					billUI.viewbillofConsumer();
					
					break;
				case 3:
					billUI.viewAllbills();
					break;
				case 4:
					billUI.viewAllPendingbills();
					break;
				case 5:
					billUI.viewAllPaidbills();
					break;
				case 6:
					consumerUI.deleteConsumer();
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
	}
	static void adminLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			System.out.println("Welcome Admin");
			adminMenu(sc);
		}else {
			System.out.println("Invalid Username and Password");
		}
	}
	static void displayCustomerMenu() {
		System.out.println("1. Pay Bill");
		System.out.println("2. View transaction History");
		System.out.println("3. Raise Complaint");
		System.out.println("0. Logout");
	}
	
	static void customerLogin(Scanner sc) {
		if(!consumerUI.login())
			return;

		int choice = 0;
		do {
			displayCustomerMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					consumerUI.payBill();
					break;
				case 2:
					consumerUI.history();
					break;
				case 3:
					complaintUI.query();
					break;
				case 0:
					consumerUI.logout();
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(LoggedINUser.loggedInUserId != 0);
	}
	
	
	static void customerSignUp(Scanner sc) {
		
		consumerUI.signUp();
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		billUI = new BillUI(sc);
		complaintUI = new ComplaintUI(sc);
		consumerUI = new ConsumerUI(sc);
		
		System.out.println("|-------------------------------------------------------|");
		System.out.println("|                                                       |");
		System.out.println("|                  Spark Power Limited                  |");
		System.out.println("|                                                       |");
		System.out.println("|-------------------------------------------------------|");
		System.out.println();
		
		int choice = 0;
		do {
			System.out.println("1. Admin Login\n2. Consumer Login\n3. Consumer SignUp\n0. Exit");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Thank you, Visit again");
					break;
				case 1:
					adminLogin(sc);
					break;
				case 2:
					customerLogin(sc);
					break;
				case 3:
					customerSignUp(sc);
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		sc.close();
	}
}
