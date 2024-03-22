package com.santhosh.interviewpanel.model;

public class Candidate {

	private String id;
	private String name;
	private String collegeName;
	private String emailId;
	private String phoneNo;
	private String location;
	private String status;
	private String result;

	@SuppressWarnings("unused")
	private Candidate() {

	}

	public Candidate(String id, String name, String collegeName, String emailId, String phoneNo, String location,
			String status, String result) {
		this.id = id;
		this.name = name;
		this.collegeName = collegeName;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.location = location;
		this.status = status;
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
