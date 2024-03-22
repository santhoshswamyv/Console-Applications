package com.santhosh.interviewpanel;

import com.santhosh.interviewpanel.login.LoginView;

public class InterviewPanel {

	private static InterviewPanel interviewPanel;

	private String appName = "Interview Panel";

	private String appVersion = "0.0.1";

	private InterviewPanel() {

	}

	private void create() throws Exception {
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
			interviewPanel = new InterviewPanel();
		}
		return interviewPanel;
	}

	public static void main(String[] args) throws Exception {
		InterviewPanel.getInstance().create();
	}
}
