package com.santhosh.interviewpanel.login;

import java.util.List;
import java.util.regex.Pattern;

import com.santhosh.interviewpanel.datalayer.DetailsDatabase;
import com.santhosh.interviewpanel.model.HR;

public class LoginModel {
	private LoginView loginView;
	private static String tempEmail = "admin@gmail.com";
	private static String tempPass = "admin@123";

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

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

	public void reValidateUser(String emailId, String password) throws Exception {
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

	void validateCredentials(String id, String name, String emailId, String password) throws Exception {

		String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
		String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		if (!Pattern.matches(namePattern, name)) {
			loginView.showAlert("\nName contains Invalid Characters..!");
			loginView.onSuccess();
			return;
		}

		if (!Pattern.matches(emailPattern, emailId)) {
			loginView.showAlert("Invalid Email Id \n(Email should match standard email format)");
			loginView.onSuccess();
			return;
		}

		if (!Pattern.matches(passwordPattern, password)) {
			loginView.showAlert(
					"Invalid password \n(Password should contain at least one digit, one lowercase and one uppercase letter, one special character, and have a minimum length of 8 characters)");
			loginView.onSuccess();
			return;
		}

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

	void viewHr() {
		List<HR> hrList = DetailsDatabase.getInstance().getHrs();
		if (hrList.isEmpty()) {
			loginView.showAlert("\nNo HR Found..!");
		} else {
			loginView.listHr(hrList);
		}
	}

}
