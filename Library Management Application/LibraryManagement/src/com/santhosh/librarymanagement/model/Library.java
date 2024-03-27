package com.santhosh.librarymanagement.model;

public class Library {
	private String libraryName;
	private int libraryId;
	private String phoneNo;
	private String emailId;
	private String address;

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Library [libraryName=" + libraryName + ", libraryId=" + libraryId + ", phoneNo=" + phoneNo
				+ ", emailId=" + emailId + ", address=" + address + "]";
	}

}
