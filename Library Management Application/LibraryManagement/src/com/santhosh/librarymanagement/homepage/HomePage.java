package com.santhosh.librarymanagement.homepage;

import java.util.Scanner;

import com.santhosh.librarymanagement.LibraryManagement;
import com.santhosh.librarymanagement.issuebook.IssueBookView;
import com.santhosh.librarymanagement.login.LoginView;
import com.santhosh.librarymanagement.managebook.ManageBookView;
import com.santhosh.librarymanagement.manageusers.UserView;

public class HomePage {
	private static HomePage homePage;

	public static HomePage getInstance() {
		if (homePage == null) {
			homePage = new HomePage();
		}
		return homePage;
	}

	Scanner sc = new Scanner(System.in);

	public void init() {

		while (true) {
			System.out.println("+----------------------------------+\n" + "|---------  Main Menu  ------------|\n"
					+ "|                                  |\n" + "| 1. Book Management               |\n"
					+ "| 2. User Management               |\n" + "| 3. Issue Management              |\n"
					+ "| 4. Logout                        |\n" + "| 0. Exit                          |\n"
					+ "|					                |\n" + "+----------------------------------+");
			System.out.println("Enter The Your Choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				bookManagement();
				break;
			case 2:
				userManagement();
				break;
			case 3:
				issueManagement();
				break;

			case 4:
				new LoginView().init();
				return;
			case 0:
				System.out.println("\n-- Thanks for using " + LibraryManagement.getInstance().getAppName() + "---");
				System.exit(0);

			default:
				System.out.println("\nInValid Choice");

			}
		}

	}

	public void bookManagement() {

		while (true) {
			System.out.println("\n+---------------------- Sub Menu ----------------------+\n"
					+ "|                                                      |\n"
					+ "|  1. Add Book                                         |\n"
					+ "|  2. View all Book                                    |\n"
					+ "|  3. View Specific Book                               |\n"
					+ "|  4. Search Book By Name                              |\n"
					+ "|  5. Update Book Count                                |\n"
					+ "|  6. Remove Book                                      |\n"
					+ "|  7. Return to Main Menu                              |\n"
					+ "|                                                      |\n"
					+ "+------------------------------------------------------+");
			System.out.println("Enter The Your Choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				new ManageBookView().initAdd();
				break;
			case 2:
				new ManageBookView().viewBook();
				break;
			case 3:
				new ManageBookView().viewBookByID();
				break;
			case 4:
				new ManageBookView().fetchBooks();
				break;
			case 5:
				new ManageBookView().UpdateBookCount();
				break;
			case 6:
				new ManageBookView().removeBook();
				break;
			case 7:
				init();
				break;
			case 8:
				System.out.println("\n-- You are logged out successfully -- \n\n");
				new LoginView().init();
				return;
			default:
				System.err.println("\nPlease Enter valid choice\n");
				// choice=sc.nextInt();
			}
		}

	}

	public void userManagement() {
		while (true) {
			System.out.println("\n+---------------------- User Management ----------------------+\n"
					+ "|                                                              |\n"
					+ "|  1. Add User                                                 |\n"
					+ "|  2. View all User                                            |\n"
					+ "|  3. View User Issued Book                                    |\n"
					+ "|  4. Remove User                                              |\n"
					+ "|  5. Return To Main Menu                                      |\n"
					+ "|                                                              |\n"
					+ "+--------------------------------------------------------------|\n+");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				new UserView().init();
				break;
			case 2:
				new UserView().viewUser();
				break;
			case 3:
				new IssueBookView().viewUserIssuedBook();
				break;
			case 4:
				new UserView().removeUser();
				break;
			case 5:
				init();
				break;
			default:
				System.out.println("Invalid Choice:");

			}
		}

	}

	public void issueManagement() {
		while (true) {
			System.out.println("\n+---------------------- Issue Management ----------------------+\n"
					+ "|                                                              |\n"
					+ "|  1. Issue Book                                               |\n"
					+ "|  2. View all Issue Book                                      |\n"
					+ "|  3. View User Issued Book                                    |\n"
					+ "|  4. Return  Book                                             |\n"
					+ "|  0. Return To Main Menu                                      |\n"
					+ "|                                                              |\n"
					+ "+--------------------------------------------------------------|\n+");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				new IssueBookView().init();
				break;
			case 2:
				new IssueBookView().viewIssuedBook();
				break;
			case 3:
				new IssueBookView().viewUserIssuedBook();
				break;
			case 4:
				new IssueBookView().returnBook();
				break;
			case 0:
				init();
			default:
				System.out.println("Invalid Choice:");
			}
		}
	}
}
