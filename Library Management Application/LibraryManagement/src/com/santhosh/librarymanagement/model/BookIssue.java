package com.santhosh.librarymanagement.model;

import java.util.Date;

import com.santhosh.librarymanagement.model.BookIssue;

public class BookIssue {
	private static int issueCount = 1;
	private int issueId;
	private int bookId;
	private int userId;
	private Date issueDate;
	private Date returnDate;

	public BookIssue(int bookId, int userId) {
		super();
		this.issueId = issueCount++;
		this.bookId = bookId;
		this.userId = userId;
		this.issueDate = new Date();
		this.returnDate = null;
	}

	public static int getIssueCount() {
		return issueCount;
	}

	public static void setIssueCount(int issueCount) {
		BookIssue.issueCount = issueCount;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "BookIssue [issueId=" + issueId + ", bookId=" + bookId + ", userId=" + userId + ", issueDate="
				+ issueDate + ", returnDate=" + returnDate + "]";
	}

}
