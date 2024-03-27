package com.santhosh.librarymanagement.librarysetup;

import com.santhosh.librarymanagement.datalayer.LibraryDatabase;
import com.santhosh.librarymanagement.model.Library;

public class LibrarySetupModel {
	private LibrarySetupView librarySetupView;

	@SuppressWarnings("unused")
	private Library library;

	LibrarySetupModel(LibrarySetupView librarySetupView) {
		this.librarySetupView = librarySetupView;
		library = LibraryDatabase.getInstance().getLibrary();
	}

	public void startSetup() {
		librarySetupView.initiateSetup();
	}

	public void createLibrary(Library library) {
		if (library.getLibraryName().length() < 3 || library.getLibraryName().length() > 50) {
			librarySetupView.showAlert("Enter valid name");
			return;
		}
		this.library = LibraryDatabase.getInstance().insertLibrary(library);
		library.setLibraryId(1);
		librarySetupView.onSetupComplete(library);
	}
}
