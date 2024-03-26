package com.santhosh.interviewpanel;

import java.util.regex.Pattern;

//Validator class to validation purpose
public class Validator {

	private static void showAlert(String alert) {
		System.err.println(alert + "\n");

	}

	public static boolean validateName(String name) {
		String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";

		if (Pattern.matches(namePattern, name)) {
			return true;
		}
		showAlert("Invalid name format. Please try again.");
		return false;
	}

	public static boolean validateEmail(String emailId) {
		String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		if (Pattern.matches(emailPattern, emailId)) {
			return true;
		}
		showAlert("Invalid email format. Please try again.");
		return false;
	}

	public static boolean validatePassword(String password) {
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		if (Pattern.matches(passwordPattern, password)) {
			return true;
		}
		showAlert("Invalid password format. Please try again.");
		return false;
	}

	public static boolean validatePhoneNo(String phoneNo) {
		String phoneNoPattern = "^[0-9]{10}$";

		if (Pattern.matches(phoneNoPattern, phoneNo)) {
			return true;
		}
		showAlert("Invalid phoneNo format. Please try again.");
		return false;
	}
}
