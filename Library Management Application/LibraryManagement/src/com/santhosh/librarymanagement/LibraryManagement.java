package com.santhosh.librarymanagement;

import com.santhosh.librarymanagement.datalayer.LibraryDatabase;
import com.santhosh.librarymanagement.login.LoginView;

public class LibraryManagement {

	private static LibraryManagement libraryManagement;

	private String appName = "Library Management System";

	private String appVersion = "0.0.1";

	private LibraryManagement() {

	}

	private void create() {
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public static LibraryManagement getInstance() {
		if (libraryManagement == null) {
			libraryManagement = new LibraryManagement();
		}
		return libraryManagement;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static void main(String[] args) {
		LibraryDatabase.getInstance().getFromDB();
		LibraryManagement.getInstance().create();
	}
}
