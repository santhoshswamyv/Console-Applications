package com.santhosh.interviewpanel.login;

import java.util.List;
import java.util.Scanner;

import com.santhosh.interviewpanel.InterviewPanel;
import com.santhosh.interviewpanel.manageinterview.ManageInterviewView;
import com.santhosh.interviewpanel.model.HR;

public class LoginView {

	private LoginModel loginModel;
	private ManageInterviewView manageInterviewView;
	static Scanner sc = new Scanner(System.in);
//	Console console = System.console();

	public LoginView() {
		loginModel = new LoginModel(this);
		manageInterviewView = new ManageInterviewView(this);
	}

	public void init() throws Exception {
		System.err.print("\t\tHuman Resource Management\n\n");
		Thread.sleep(1000);
		System.err.println("\t\t" + InterviewPanel.getInstance().getAppName() + "\t"
				+ InterviewPanel.getInstance().getAppVersion() + " --v\n");
		System.out.println("\nPlease login with Temp Credentials and Reset..!");
		login();
	}

	void login() throws Exception {
		System.out.print("\nEnter the Temp Email Id : ");
		String emailId = sc.nextLine().trim();
		System.out.print("Enter the Temp Password : ");
		String password = sc.nextLine().trim();
		loginModel.validateUser(emailId, password);
	}

	public void onSuccess() throws Exception {
		System.out.flush();
		System.err.println("\n\n\t\t" + InterviewPanel.getInstance().getAppName() + "\t"
				+ InterviewPanel.getInstance().getAppVersion() + " --v\n");
		System.out.println("Create a New Account..!\n");
		System.out.print("Your ID : ");
		String id = "" + (int) (Math.random() * 10000);
		Thread.sleep(1000);
		showAlert(id);
		System.out.print("Enter your Name : ");
		String name = sc.nextLine().trim();
		System.out.print("Enter your Email Id : ");
		String emailId = sc.nextLine().trim();
		System.out.print("Enter yout Password : ");
		String password = sc.nextLine().trim();
		loginModel.validateCredentials(id, name, emailId, password);
	}

	public void reLogin() throws Exception {
		System.out.print("Enter your Email Id : ");
		String emailId = sc.next();
		System.out.print("Enter your Password : ");
		String password = sc.next();
		loginModel.reValidateUser(emailId, password);
	}

	public void onLoginFailed(String alertText) {
		showAlert(alertText);
	}

	public void proceedInterface() throws Exception {
		while (true) {
			System.out.println(
					"\n1) Add Candidates \n2) Candidate's Status \n3) Manage Interview \n4) View HR's \n5) Log Out \n6) Exit ");
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
				interviewCompletion();
				break;
			default:
				showAlert("Please Choose a Valid Option..!");
				proceedInterface();
				break;
			}
		}
	}

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
