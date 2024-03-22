package com.santhosh.interviewpanel.manageinterview;

import java.util.ArrayDeque;
import java.util.Scanner;

import com.santhosh.interviewpanel.login.LoginView;
import com.santhosh.interviewpanel.model.Candidate;

public class ManageInterviewView {

	static Scanner sc = new Scanner(System.in);
	private ManageInterviewModel manageInterviewModel;
	private LoginView loginView;
	static int id = 100;
	static boolean isStarted;

	@SuppressWarnings("unused")
	private ManageInterviewView() {

	}

	public ManageInterviewView(LoginView loginView) {
		manageInterviewModel = new ManageInterviewModel(this);
		this.loginView = loginView;
	}

	public void confirmation() throws Exception {
		System.out.println("Do you want to add another Candidate ? (Yes / No)");
		String confirmation = sc.nextLine().trim().trim();
		if (confirmation.toLowerCase().equals("yes")) {
			addCandidate();
		} else if (confirmation.toLowerCase().equals("no")) {
			loginView.proceedInterface();
		} else {
			loginView.showAlert("\nIncorrect Option..!");
			confirmation();
			return;
		}
	}

	public void addCandidate() throws Exception {
		System.out.print("\nCandidate's ID : ");
		Thread.sleep(1000);
		String cId = "CAND" + id;
		loginView.showAlert(cId);
		System.out.print("Enter Candidate's Name : ");
		String name = sc.nextLine().trim();
		System.out.print("");
		System.out.print("Enter Candidate's College Name : ");
		String collegeName = sc.nextLine().trim();
		System.out.print("Enter Candidate's Email ID : ");
		String emailId = sc.nextLine().trim();
		System.out.print("Enter Candidate's Phone No : ");
		String phoneNo = sc.nextLine().trim();
		System.out.print("Enter Candidate's Location : ");
		String location = sc.nextLine().trim();
		String status = "Waiting";
		String result = "Awaiting";
		manageInterviewModel.validateCredentials(cId, name, collegeName, emailId, phoneNo, location, status, result);
	}

	public void manageInterview() throws Exception {
		System.out.println("\n1) Start Interview \n2) End Interview \n3) Emergency Candidate \n4) Main Menu");
		switch (sc.nextInt()) {
		case 1:
			manageInterviewModel.startInterview();
			break;
		case 2:
			if (isStarted) {
				manageInterviewModel.endInterview(reConfirmation());
			} else {
				showAlert("\nInterview not yet Started..!");
			}
			break;
		case 3:
			if (!isStarted) {
				System.out.print("Enter the Candidate ID : ");
				sc.nextLine();
				String cId = sc.nextLine().trim();
				manageInterviewModel.emergencyCandidate(cId);
			} else {
				manageInterviewModel.checkForCandidates();
			}
			return;
		case 4:
			loginView.proceedInterface();
			return;
		default:
			showAlert("Please Choose a Valid Option..!");
			manageInterview();
			break;
		}
	}

	String reConfirmation() {
		boolean b = true;
		String result = "Not Declared";
		while (b) {
			System.out.println("Result of the Candidate : ");
			System.out.println("1) Selected \n2) Rejected");
			int res = sc.nextInt();
			if (res == 1) {
				result = "Selected";
				b = false;
			} else if (res == 2) {
				b = false;
				result = "Rejected";
			} else {
				showAlert("\nPlease Choose a Valid Option..!");
			}
		}
		return result;
	}

	public void listCandidates() {
		manageInterviewModel.viewCandidates();
	}

	public void listCandidates(ArrayDeque<Candidate> candidates) {
		System.out.printf("%-10s %-15s %-20s %-30s %-15s %-20s %-15s %-10s %n", "ID", "Name", "CollegeName", "EmailID",
				"PhoneNo", "Location", "Status", "Result");
		for (Candidate candidate : candidates) {
			if (candidate.getResult().equals("Selected")) {
				System.out.printf("%-10s %-15s %-20s %-30s %-15s %-20s %-15s %-10s %n", candidate.getId(),
						candidate.getName(), candidate.getCollegeName(), candidate.getEmailId(), candidate.getPhoneNo(),
						candidate.getLocation(), candidate.getStatus(), candidate.getResult());
			} else {
				System.err.printf("%-10s %-15s %-20s %-30s %-15s %-20s %-15s %-10s %n", candidate.getId(),
						candidate.getName(), candidate.getCollegeName(), candidate.getEmailId(), candidate.getPhoneNo(),
						candidate.getLocation(), candidate.getStatus(), candidate.getResult());
			}
		}
	}

	public void showAlert(String alert) {
		System.err.println(alert + "\n");
	}

	public boolean checkCandidates() {
		return manageInterviewModel.checkForCandidates();
	}

}
