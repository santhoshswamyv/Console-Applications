package com.santhosh.librarymanagement.login;

public class LoginModel {
	private LoginView loginView;
	private static String tempEmail = "admin@gmail.com";
	private static String tempPass = "Admin@123";

	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	public void validateUser(String userName, String password) {
		if (isValidUserName(userName)) {
			if (isValidPassword(password)) {
				loginView.onSuccess();
			} else {
				loginView.showAlert("Invalid Password");
				retryOrNot();
			}
		} else {
			loginView.showAlert("Invalid User Name ");
			retryOrNot();
		}

	}

	public void retryOrNot() {
		if (loginView.retryOrNot().equalsIgnoreCase("yes")) {
			loginView.init();
		} else {
			System.out.println("\nThank You..!");
		}
	}

	private boolean isValidUserName(String userName) {
		return userName.equals(tempEmail);
	}

	private boolean isValidPassword(String password) {
		return password.equals(tempPass);
	}
}
