package com.santhosh.interviewpanel;

import com.santhosh.interviewpanel.login.LoginView;

public class InterviewPanel {

	private static InterviewPanel interviewPanel;

	private String appName = "Interview Panel";

	private String appVersion = "0.0.2";

	// InterviewPanel Constructor is private so no other class can create a Instance
	private InterviewPanel() {

	}

	private void create() throws Exception {
		// Creating a Instance to access the View Starting Point
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static InterviewPanel getInstance() {
		if (interviewPanel == null) {
			// Creating a Instance of InterviewPanel
			interviewPanel = new InterviewPanel();
		}
		return interviewPanel;
	}

	public static void main(String[] args) throws Exception {
		// Entry point
		InterviewPanel.getInstance().create();
	}
}
