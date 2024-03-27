package com.santhosh.librarymanagement.manageusers;

import java.util.Scanner;

import com.santhosh.librarymanagement.model.User;
import com.santhosh.librarymanagement.Validator;

public class UserView {
	static Scanner sc = new Scanner(System.in);

	private UserModel userModel;

	public UserView() {
		this.userModel = new UserModel(this);
	}

	public void init() {
		User user = new User();
		String name;
		System.out.println("\n\nEnter User details:");
		do {
			System.out.println("\nEnter User name:");
			name = sc.nextLine();
		} while (!Validator.validateName(name));
		user.setName(name);
		String phoneNo;
		do {
			System.out.println("\nEnter User PhoneNo:");
			phoneNo = sc.next();
		} while (!Validator.validatePhoneNo(phoneNo));

		user.setPhoneNo(phoneNo);
		String mail;
		do {
			System.out.println("\nEnter User Email:");
			;
			mail = sc.next();
		} while (!Validator.validateEmail(mail));
		user.setEmailId(mail);
		System.out.println("Enter The User Address");
		user.setAddress(sc.next());
		user.setId();
		userModel.addUser(user);
		System.out.println();

	}

	public void onUserAdded(User user) {
		System.out.println("\n------- User '" + user.getName() + "' added successfully ------- \n");
		checkForAddNewUser();

	}

	private void checkForAddNewUser() {
		System.out.println("\nDo you want to add more User? \nType Yes/No");
		String choice = sc.next();
		if (choice.equalsIgnoreCase("yes")) {
			init();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding User");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewUser();
		}
	}

	public void getAllUser() {
		userModel.getAllUserInfo();

	}

	public void onUserExits(User user) {
		System.out.println("\n------- User '" + user.getName() + "' already exist -------\n");
	}

	public void viewUser() {
		userModel.getAllUserInfo();

	}

	public void showAlert(Object alert) {
		System.out.println(alert);
	}

	public void removeUser() {
		System.out.println("Enter The User Id");
		int userId = sc.nextInt();
		userModel.getRemoveUser(userId);

	}

}
