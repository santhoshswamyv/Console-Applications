package com.santhosh.librarymanagement.librarysetup;

import java.util.Scanner;
import com.santhosh.librarymanagement.homepage.HomePage;
import com.santhosh.librarymanagement.model.Library;
import com.santhosh.librarymanagement.Validator;

public class LibrarySetupView {
	static Scanner sc = new Scanner(System.in);
	private LibrarySetupModel librarySetupModel;

	public LibrarySetupView() {
		librarySetupModel = new LibrarySetupModel(this);
	}

	public void init() {
		librarySetupModel.startSetup();
	}

	public void onSetupComplete(Library library) {
		System.out.println("\nLibrary setup completed\n");
		System.out.println("\nLibrary Details : \n");
		System.err.print("\nLibrary Name = ");
		showAlert(library.getLibraryName());
		System.err.print("\nLibrary Id = ");
		showAlert("" + library.getLibraryId());
		System.err.print("\nLibrary Email = ");
		showAlert(library.getEmailId());
		System.err.print("\nLibrary PhoneNo = ");
		showAlert(library.getPhoneNo());
		System.err.print("\nLibrary Address = ");
		showAlert(library.getAddress());
		System.out.println("");
		HomePage homePage = new HomePage();
		homePage.init();

	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}

	public void initiateSetup() {
		Library library = new Library();
		String name;
		String phoneNo;
		String mail;

		System.err.println("\nEnter Library Details : ");
		do {
			System.err.print("\nEnter Library Name : ");
			name = sc.nextLine();
		} while (!Validator.validateName(name));
		library.setLibraryName(name);
		do {
			System.err.print("\nEnter Phone No : ");
			phoneNo = sc.next();
		} while (!Validator.validatePhoneNo(phoneNo));

		library.setPhoneNo(phoneNo);
		do {
			System.err.print("\nEnter Library Email Id : ");
			mail = sc.next();
		} while (!Validator.validateEmail(mail));

		library.setEmailId(mail);

		sc.nextLine();

		System.err.print("\nEnter Library Address : ");
		library.setAddress(sc.nextLine());

		librarySetupModel.createLibrary(library);
	}

}
