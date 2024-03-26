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

	public String getCollegeName() {
		return collegeName;
	}

	public String getId() {
		return id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getLocation() {
		return location;
	}

	public String getStatus() {
		return status;
	}

	public String getResult() {
		return result;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Candidate{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", collegeName='" + collegeName + '\''
				+ ", emailId='" + emailId + '\'' + ", phoneNo='" + phoneNo + '\'' + ", location='" + location + '\''
				+ ", status='" + status + '\'' + ", result='" + result + '\'' + '}';
	}
}
