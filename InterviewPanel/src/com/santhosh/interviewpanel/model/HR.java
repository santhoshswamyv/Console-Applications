package com.santhosh.interviewpanel.model;

public class HR {

	private int empId;
	private long phoneNo;
	private String name;
	private String emailId;
	private boolean isAvailable;

	@SuppressWarnings("unused")
	private HR() {

	}

	public HR(int empId, long phoneNo, String name, String emailId) {
		this.empId = empId;
		this.phoneNo = phoneNo;
		this.name = name;
		this.emailId = emailId;
		this.isAvailable = true;
	}

	public int getEmpId() {
		return empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
