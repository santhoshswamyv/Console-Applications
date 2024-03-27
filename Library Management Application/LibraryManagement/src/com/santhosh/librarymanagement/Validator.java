package com.santhosh.librarymanagement;

import java.util.regex.Pattern;

public class Validator {

	public static boolean validateName(String name) {
		String pattern = "^[a-z]*$";
		Pattern p = Pattern.compile(pattern);
		if (name == null) {
			return false;
		}
		return p.matcher(name).matches();
	}

	public static boolean validateEmail(String emailId) {
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(pattern);
		if (emailId == null) {
			return false;
		}
		return p.matcher(emailId).matches();
	}

	public static boolean validatePassword(String password) {
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern p = Pattern.compile(pattern);
		if (password == null) {
			return false;
		}
		return p.matcher(password).matches();
	}

	public static boolean validatePhoneNo(String phoneNo) {
		String pattern = "^[0-9]{10}$";
		Pattern p = Pattern.compile(pattern);
		if (phoneNo == null) {
			return false;
		}
		return p.matcher(phoneNo).matches();
	}

}
