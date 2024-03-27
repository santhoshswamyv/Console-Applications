package com.santhosh.librarymanagement.model;

public class User {
	private int id;
	private static int idCount;
	private String name;
	private String emailId;
	private String phoneNo;
	private String address;

	private int bookTaken;
	private static int due = 10;

	public void setId() {
		this.id = ++idCount;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDue() {
		return due;
	}

	public int getbookTaken() {
		return bookTaken;
	}

	public void setbookTaken(int bookTaken) {
		this.bookTaken = bookTaken;
		;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", address="
				+ address + ", due=" + due + "]";
	}

}
