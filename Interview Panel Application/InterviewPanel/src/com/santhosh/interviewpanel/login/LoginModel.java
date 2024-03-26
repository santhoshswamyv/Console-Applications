package com.santhosh.interviewpanel.login;

import java.util.List;

import com.santhosh.interviewpanel.datalayer.DetailsDatabase;
import com.santhosh.interviewpanel.model.HR;
import com.santhosh.interviewpanel.model.Receptionist;

public class LoginModel {
	private LoginView loginView;
	private static String tempEmail = "admin@gmail.com";
	private static String tempPass = "admin@123";

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	// Method to Validate Temp Credentials
	public void validateUser(String emailId, String password) throws Exception {
		if (isValidEmailId(emailId)) {
			if (isValidPassword(emailId, password)) {
				loginView.onSuccess();
			} else {
				loginView.onLoginFailed("Invalid password");
				loginView.login();
			}
		} else {
			loginView.onLoginFailed("Invalid Email Id");
			loginView.login();
		}
	}

	// Method to Retrieve or Check of Receptionist
	Receptionist confirmReceptionist() {
		Receptionist receptioninst = DetailsDatabase.getInstance().getReceptionist();
		if (receptioninst == null) {
			DetailsDatabase.getInstance().retrieveReceptionist();
		}
		return DetailsDatabase.getInstance().getReceptionist();
	}

	// Method to Relogin the User
	public void reLoginUser(String emailId, String password) throws Exception {
		if (DetailsDatabase.getInstance().getReceptionist(emailId, password)) {
			loginView.showAlert("\nLogin Successful..!");
			loginView.proceedInterface();
		} else {
			loginView.onLoginFailed("Incorrect Credentials..!");
			loginView.reLogin();
		}
	}

	private boolean isValidEmailId(String emailId) {
		return emailId.equals(tempEmail);
	}

	private boolean isValidPassword(String emailId, String password) {
		return emailId.equals(tempEmail) && password.equals(tempPass);
	}

	// Method where it is Invoking to Create a Receptionist
	void createReceptionsist(String id, String name, String emailId, String password) throws Exception {
		if (DetailsDatabase.getInstance().setReceptionist(id, name, emailId, password)) {
			loginView.showAlert("\nNew Account Created..!");
			Thread.sleep(1000);
			loginView.showAlert("Welcome, " + name + "..!");
			loginView.proceedInterface();
		} else {
			loginView.showAlert("\nAccount Creation Failed..! Check if there is already a Receptionist..!");
			loginView.reLogin();
		}
	}

	// Method to Invoke the reset()
	void resetFile() {
		DetailsDatabase.getInstance().reset();
	}

	// Method to Invoke the getHrs()
	void viewHr() {
		List<HR> hrList = DetailsDatabase.getInstance().getHrs();
		if (hrList.isEmpty()) {
			loginView.showAlert("\nNo HR Found..!");
		} else {
			loginView.listHr(hrList);
		}
	}

}
