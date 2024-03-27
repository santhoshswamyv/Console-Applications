package com.santhosh.librarymanagement.login;

import java.util.Scanner;

import com.santhosh.librarymanagement.LibraryManagement;
import com.santhosh.librarymanagement.librarysetup.LibrarySetupView;

public class LoginView {
	Scanner sc = new Scanner(System.in);
	private LoginModel loginModel;

	public LoginView() {
		loginModel = new LoginModel(this);
	}

	public void init() {
		System.err.println("\t\t" + LibraryManagement.getInstance().getAppName() + "\t"
				+ LibraryManagement.getInstance().getAppVersion() + " --v\n");
		System.out.println("\nPlease login with Temp Credentials and Reset..!");
		System.out.print("\nEnter the Temp Email Id : ");
		String userName = sc.next();
		System.out.print("Enter the Temp Password : ");
		String password = sc.next();
		loginModel.validateUser(userName, password);

	}

	public void showAlert(String string) {
		System.out.println(string);
	}

	public void onSuccess() {
		System.out.flush();
		LibrarySetupView librarySetupView = new LibrarySetupView();
		librarySetupView.init();
	}

	public String retryOrNot() {
		System.out.println("Do You Want To Retry : Yes / No");
		String result = sc.next();
		return result;
	}

}
