package com.santhosh.librarymanagement.managebook;

import java.util.List;
import java.util.Scanner;

import com.santhosh.librarymanagement.model.Book;

public class ManageBookView {
	private ManageBookModel manageBookModel;
	static Scanner sc = new Scanner(System.in);

	public ManageBookView() {
		manageBookModel = new ManageBookModel(this);
	}

	public void initAdd() {

		Book book = new Book();

		System.out.println("Enter the Book Name:");
		book.setName(sc.next());
		book.setId();
		System.out.println("Enter the Book Author:");
		book.setAuthor(sc.next());
		System.out.println("Enter the publication:");
		book.setPublication(sc.next());
		System.out.println("Enter the Book edition:");
		book.setEdition(sc.next());
		System.out.println("Enter the Book Journer:");
		book.setJourner(sc.next());
		System.out.println("Enter the availableCount of  Book:");
		book.setAvailableCount(sc.nextInt());
		System.out.println("Enter the volume of  Book:");
		book.setVolume(sc.nextInt());
		manageBookModel.addNewBook(book);

	}

	public void showLibraryName(String libraryName) {
		System.out.println("Current Library Name - " + libraryName);
	}

	public void onBookExist(Book book) {
		System.out.println("\n------- Book '" + book.getName() + "' already exist -------\n");
		checkForAddNewBook();

	}

	public void onBookAdded(Book book) {
		System.out.println("\n------- Book '" + book.getName() + "' added successfully ------- \n");
		checkForAddNewBook();

	}

	private void checkForAddNewBook() {
		System.out.println("\nDo you want to add more books? \nType Yes/No");
		String choice = sc.next();
		if (choice.equalsIgnoreCase("yes")) {
			initAdd();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding books");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewBook();
		}
	}

	public void viewBook() {
		List<Book> bookInfo = manageBookModel.getAllBooks();
		for (Book book : bookInfo) {
			System.out.println(book);
		}

	}

	public void viewBookByID() {
		System.out.println("Enter the Book Id");
		int id = sc.nextInt();
		manageBookModel.getBookById(id);
	}

	public void fetchBooks() {
		System.out.println("Enter The Book Name:");
		String name = sc.next();
		manageBookModel.getSearchBooks(name);
		List<Book> bookInfo = manageBookModel.getSearchBooks(name);
		if (bookInfo.size() == 0) {
			System.out.println("There Is No Book Available in " + name);
			manageBookModel.retryOrNotName();
		}
		for (Book book : bookInfo) {
			if (book.getName().contains(name)) {
				System.out.println(book);

			}
		}

	}

	public void UpdateBookCount() {
		System.out.println("Enter The Id:");
		int id = sc.nextInt();
		System.out.println("Enter The book count:");
		int count = sc.nextInt();
		manageBookModel.updateBookCount(id, count);

	}

	public void removeBook() {
		System.out.println("Enter The Id:");
		int id = sc.nextInt();
		manageBookModel.removeBook(id);

	}

	public void showAlert(Object string) {
		System.out.println(string);

	}

	public String retryOrNot() {
		System.out.println("Do You Want To Retry : Yes / No");
		String result = sc.next();
		return result;
	}

}
