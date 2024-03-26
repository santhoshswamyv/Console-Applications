package com.santhosh.interviewpanel.login;

import java.util.List;
import java.util.Scanner;

import com.santhosh.interviewpanel.InterviewPanel;
import com.santhosh.interviewpanel.Validator;
import com.santhosh.interviewpanel.manageinterview.ManageInterviewView;
import com.santhosh.interviewpanel.model.HR;

public class LoginView {

	private LoginModel loginModel;
	private ManageInterviewView manageInterviewView;
	static Scanner sc = new Scanner(System.in);

	public LoginView() {
		loginModel = new LoginModel(this);
		manageInterviewView = new ManageInterviewView(this);

	}

	// Entry Method of LoginView
	public void init() throws Exception {
		System.err.print("\t\tHuman Resource Management\n\n");
		Thread.sleep(1000);
		System.err.println("\t\t" + InterviewPanel.getInstance().getAppName() + "\t"
				+ InterviewPanel.getInstance().getAppVersion() + " --v\n");
		// Checking if already there is a Receptionist
		if (loginModel.confirmReceptionist() != null) {
			System.out.println("\nWelcome, Login to Continue..!\n");
			reLogin();
			return;
		}
		System.out.println("\nPlease login with Temp Credentials and Reset..!");
		login();
	}

	// If Receptionist is Null
	void login() throws Exception {
		System.out.print("\nEnter the Temp Email Id : ");
		String emailId = sc.nextLine().trim();
		System.out.print("Enter the Temp Password : ");
		String password = sc.nextLine().trim();
		loginModel.validateUser(emailId, password);
	}

	// After validating Temp Credentials
	public void onSuccess() throws Exception {
		System.out.flush();
		System.err.println("\n\n\t\t" + InterviewPanel.getInstance().getAppName() + "\t"
				+ InterviewPanel.getInstance().getAppVersion() + " --v\n");
		System.out.println("Create a New Account..!\n");
		System.out.print("Your ID : ");
		String id = "" + (int) (Math.random() * 10000);
		Thread.sleep(1000);
		showAlert(id);
		String name;
		String emailId;
		String password;

		do {
			System.out.print("Enter your Name : ");
			name = sc.nextLine().trim();
		} while (!Validator.validateName(name));

		do {
			System.out.print("Enter your Email Id : ");
			emailId = sc.nextLine().trim();
		} while (!Validator.validateEmail(emailId));

		do {
			System.out.print("Enter your Password : ");
			password = sc.nextLine().trim();
		} while (!Validator.validatePassword(password));

		loginModel.createReceptionsist(id, name, emailId, password);
	}

	// If Receptionist is Present
	public void reLogin() throws Exception {
		String emailId;
		String password;
		do {
			System.out.print("Enter your Email Id : ");
			emailId = sc.nextLine().trim();
		} while (!Validator.validateEmail(emailId));

		do {
			System.out.print("Enter your Password : ");
			password = sc.nextLine().trim();
		} while (!Validator.validatePassword(password));

		loginModel.reLoginUser(emailId, password);
	}

	public void onLoginFailed(String alertText) {
		showAlert(alertText);
	}

	// Interface for Receptionist
	public void proceedInterface() throws Exception {
		while (true) {
			System.out.println(
					"\n1) Add Candidates \n2) Candidate's Status \n3) Manage Interview \n4) View HR's \n5) Log Out \n6) Reset Data \n7) Exit ");
			switch (sc.nextInt()) {
			case 1:
				System.out.println();
				manageInterviewView.addCandidate();
				break;
			case 2:
				manageInterviewView.listCandidates();
				break;
			case 3:
				manageInterviewView.manageInterview();
				break;
			case 4:
				loginModel.viewHr();
				break;
			case 5:
				showAlert("\nLogged Out..!");
				reLogin();
				return;
			case 6:
				loginModel.resetFile();
				sc.nextLine();
				login();
				break;
			case 7:
				interviewCompletion();
				break;
			default:
				showAlert("Please Choose a Valid Option..!");
				proceedInterface();
				break;
			}
		}
	}

	// Checking if interview is Over for All Candidates
	void interviewCompletion() throws Exception {
		boolean b = manageInterviewView.checkCandidates();
		if (b) {
			System.err.println("\n\n\t\t" + "Thanks for using " + InterviewPanel.getInstance().getAppName() + "   "
					+ InterviewPanel.getInstance().getAppVersion() + " --v..!\n");
			System.exit(0);
		} else {
			showAlert("\nInterview in going on you can't exit..!");
			proceedInterface();
		}
	}

	// Method to Show HR's
	public void listHr(List<HR> hrList) {
		System.err.printf("%-10s %-15s %-20s %-30s %-15s %n", "EmpID", "PhoneNo", "Name", "EmailID", "IsAvailable");
		for (HR hr : hrList) {
			System.out.printf("%-10s %-15s %-20s %-30s %-15s %n", hr.getEmpId(), hr.getPhoneNo(), hr.getName(),
					hr.getEmailId(), (hr.isAvailable() ? "Yes" : "No"));
		}
	}

	public void showAlert(String alert) {
		System.err.println(alert + "\n");
	}

}
