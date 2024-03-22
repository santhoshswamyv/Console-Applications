package com.santhosh.interviewpanel.model;

public class Receptionist {
	private String id;
	private String name;
	private String emailId;
	private String password;

	@SuppressWarnings("unused")
	private Receptionist() {

	}

	public Receptionist(String id, String name, String emailId, String password) {
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
	}

	public String getId() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
