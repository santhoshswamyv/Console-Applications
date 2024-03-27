package com.santhosh.librarymanagement.managebook;

import java.util.ArrayList;
import java.util.List;

import com.santhosh.librarymanagement.datalayer.LibraryDatabase;
import com.santhosh.librarymanagement.homepage.HomePage;
import com.santhosh.librarymanagement.model.Book;

public class ManageBookModel {
	private ManageBookView manageBookView;

	ManageBookModel(ManageBookView manageBookView) {
		this.manageBookView = manageBookView;
	}

	public void addNewBook(Book book) {
		if (LibraryDatabase.getInstance().insertBook(book)) {
			manageBookView.onBookAdded(book);
		} else {
			manageBookView.onBookExist(book);
		}

	}

	public List<Book> getAllBooks() {
		return LibraryDatabase.getInstance().getAllBooksData();
	}

	public void updateBookCount(int id, int count) {
		Book book = LibraryDatabase.getInstance().getAllBooksData(id);
		if (LibraryDatabase.getInstance().bookUpdate(id, book.getAvailableCount() + count)) {
			manageBookView.showAlert("Book Update Successfully");
		} else {
			manageBookView.showAlert("There Is No Book In This " + id);

		}

	}

	public List<Book> getSearchBooks(String name) {
		List<Book> bookInfo = LibraryDatabase.getInstance().getAllBooksData();
		List<Book> fetchBooksByName = new ArrayList<>();
		for (Book book : bookInfo) {
			if (book.getName().contains(name)) {
				fetchBooksByName.add(book);
			}
		}

		return fetchBooksByName;

	}

	public void removeBook(int id) {
		String removeBook = LibraryDatabase.getInstance().removeBookById(id);
		if (removeBook.length() != 0) {
			manageBookView.showAlert("Book " + removeBook + " is Removed Succesful");
		} else {
			manageBookView.showAlert("Invalid Id");
			retryOrNotRemove();
		}

	}

	public void getBookById(int id) {
		Book book = LibraryDatabase.getInstance().getAllBooksData(id);
		if (book != null) {
			manageBookView.showAlert(book);
		} else {
			manageBookView.showAlert("Invalid Book Id");
			retryOrNotId();

		}

	}

	public void retryOrNotId() {
		if (manageBookView.retryOrNot().equalsIgnoreCase("yes")) {
			manageBookView.viewBookByID();
		} else {
			HomePage.getInstance().bookManagement();
		}
	}

	public void retryOrNotName() {
		if (manageBookView.retryOrNot().equalsIgnoreCase("yes")) {
			manageBookView.fetchBooks();
		} else {
			HomePage.getInstance().bookManagement();
		}
	}

	public void retryOrNotRemove() {
		if (manageBookView.retryOrNot().equalsIgnoreCase("yes")) {
			manageBookView.removeBook();
		} else {
			HomePage.getInstance().bookManagement();
		}
	}

}
