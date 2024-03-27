package com.santhosh.librarymanagement.issuebook;

import java.util.Scanner;

public class IssueBookView {

	private IssueBookModel issueBookModel;

	public IssueBookView() {
		issueBookModel = new IssueBookModel(this);
	}

	Scanner sc = new Scanner(System.in);

	public void init() {
		System.out.print("Enter The User Id : ");
		int userId = sc.nextInt();
		System.out.print("Enter The Book Id : ");
		int bookId = sc.nextInt();
		issueBookModel.issueValidation(userId, bookId);

	}

	public void viewIssuedBook() {
		issueBookModel.viewIssuedBookInfo();
	}

	public void showAlert(Object string) {
		System.out.println(string);

	}

	public void returnBook() {
		System.out.print("Enter The UserId : ");
		int userId = sc.nextInt();
		issueBookModel.returnIsseBook(userId);

	}

	public void viewUserIssuedBook() {
		System.out.print("Enter The UserId : ");
		int userId = sc.nextInt();
		issueBookModel.viewUserIssuedBook(userId);

	}

	public int getIssueId() {
		System.out.print("Enter The IssueId : ");
		int issueId = sc.nextInt();
		return issueId;

	}

	public String retryOrNot() {
		System.out.println("Do You Want To Continue : Yes / No");
		String result = sc.next();
		return result;
	}

}
