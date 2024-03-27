package com.santhosh.librarymanagement.issuebook;

import java.util.List;

import com.santhosh.librarymanagement.datalayer.LibraryDatabase;
import com.santhosh.librarymanagement.homepage.HomePage;
import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.BookIssue;
import com.santhosh.librarymanagement.model.User;

public class IssueBookModel {
	private IssueBookView issueBookView;

	public IssueBookModel(IssueBookView issueBookView) {
		this.issueBookView = issueBookView;
	}

	public void issueBook(BookIssue bookissue) {

		LibraryDatabase.getInstance().addIssueData(bookissue);
		issueBookView.showAlert("Book Issued Successfully");

	}

	public void viewIssuedBookInfo() {
		List<BookIssue> issueData = LibraryDatabase.getInstance().getAllIssuedData();
		if (issueData != null) {
			for (BookIssue bookIssue : issueData) {
				issueBookView.showAlert(bookIssue);
			}
		} else {
			issueBookView.showAlert("IssuedBook List Is Empty");

		}

	}

	public void issueValidation(int userId, int bookId) {
		User user = LibraryDatabase.getInstance().getAllUserData(userId);
		if (user != null) {
			Book book = LibraryDatabase.getInstance().getAllBooksData(bookId);
			if (book != null) {
				if (book.getAvailableCount() > 0) {
					BookIssue bookIssue = new BookIssue(bookId, userId);
					LibraryDatabase.getInstance().bookUpdate(book.getId(), book.getAvailableCount() - 1);
					issueBook(bookIssue);
				} else {
					issueBookView.showAlert("Book Not Avalible Try Another Book:");
					issueBookView.init();

				}
			} else {
				issueBookView.showAlert("Invalid Book Id");
				retryOrNotId();
			}
		} else {
			issueBookView.showAlert("Invalid User");
			retryOrNotId();
		}

	}

	public void viewUserIssuedBook(int userId) {
		List<BookIssue> viewIssuedBook = LibraryDatabase.getInstance().getAllIssuedData(userId);
		if (viewIssuedBook.size() > 0) {
			for (BookIssue bookIssue : viewIssuedBook) {
				issueBookView.showAlert(bookIssue);
			}
		} else {
			issueBookView.showAlert("User Doesn't Take Any Book");

		}
	}

	public void returnIsseBook(int userId) {
		viewUserIssuedBook(userId);
		int issueId = issueBookView.getIssueId();
		LibraryDatabase.getInstance().getIssuedData(issueId);
	}

	public void retryOrNotId() {
		if (issueBookView.retryOrNot().equalsIgnoreCase("yes")) {
			issueBookView.init();
		} else {
			HomePage.getInstance().bookManagement();
		}
	}

}
