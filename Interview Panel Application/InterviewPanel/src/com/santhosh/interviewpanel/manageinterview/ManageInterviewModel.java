package com.santhosh.interviewpanel.manageinterview;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.santhosh.interviewpanel.datalayer.DetailsDatabase;
import com.santhosh.interviewpanel.model.Candidate;
import com.santhosh.interviewpanel.model.HR;

public class ManageInterviewModel {

	private ManageInterviewView manageInterviewView;
	private ArrayDeque<Candidate> candidates;
	private List<HR> hrList;

	ManageInterviewModel(ManageInterviewView manageInterviewView) {
		this.manageInterviewView = manageInterviewView;
	}

	void validateCredentials(String id, String name, String collegeName, String emailId, String phoneNo,
			String location, String status, String result) throws Exception {
		String namePattern = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
		String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String phonePattern = "^\\d{10}$";

		if (Pattern.matches(namePattern, name)) {
			if (Pattern.matches(emailPattern, emailId)) {
				if (Pattern.matches(phonePattern, phoneNo)) {
					if (DetailsDatabase.getInstance().addCandidate(id, name, collegeName, emailId, phoneNo, location,
							status, result)) {
						manageInterviewView.showAlert("\n" + " ( " + id + " ) " + name + " added to the Queue..!");
						ManageInterviewView.id++;
						manageInterviewView.confirmation();
					}
				} else {
					manageInterviewView.showAlert("\nInvalid Phone Number\n(Phone No should contains 10 digits only)");
					manageInterviewView.addCandidate();
				}
			} else {
				manageInterviewView.showAlert("Invalid Email Id \n(Email should match standard email format)");
				manageInterviewView.addCandidate();
			}
		} else {
			manageInterviewView.showAlert("\nName contains Invalid Characters..!");
			manageInterviewView.addCandidate();
		}
	}

	void startInterview() {
		checkForCandidates();
		if (!candidates.isEmpty()) {
			ManageInterviewView.isStarted = true;
			hrList = DetailsDatabase.getInstance().getHrs();
			if (hrList.isEmpty()) {
				manageInterviewView.showAlert("\nHR's Currently Unavailable..!");
			} else {
				for (HR hr : hrList) {
					if (hr.isAvailable()) {
						hr.setAvailable(false);
						sortCandidates();
						Candidate cnd = candidates.getFirst();
						if (cnd.getStatus().equals("Completed")) {
							hr.setAvailable(true);
							manageInterviewView.showAlert("\nAll Candidates Interviewed..!");
							return;
						} else {
							cnd.setStatus("Ongoing");
							manageInterviewView.showAlert("Interview started for " + cnd.getId());
						}
						break;
					}
				}
			}
		}
		hrList = null;
		candidates = null;
	}

	void sortCandidates() {
		checkForCandidates();
		if (candidates.getFirst().getStatus().equals("Completed")) {
			Iterator<Candidate> iterator = candidates.iterator();
			while (iterator.hasNext()) {
				Candidate candidate = iterator.next();
				if (candidate.getStatus().equals("Waiting")) {
					iterator.remove();
					candidates.addFirst(candidate);
				}
			}
		}
	}

	void endInterview(String result) {
		checkForCandidates();
		if (!candidates.isEmpty()) {
			ManageInterviewView.isStarted = false;
			hrList = DetailsDatabase.getInstance().getHrs();
			if (hrList.isEmpty()) {
				manageInterviewView.showAlert("/n Interview has not begun..!");
			} else {
				for (HR hr : hrList) {
					if (!hr.isAvailable()) {
						hr.setAvailable(true);
						Candidate cnd = candidates.getFirst();
						cnd.setStatus("Completed");
						cnd.setResult(result);
						manageInterviewView.showAlert("Interview over for " + cnd.getId());
						candidates.remove(cnd);
						candidates.addLast(cnd);
					}
				}
			}
		}
		hrList = null;
		candidates = null;
	}

	void emergencyCandidate(String cId) {
		checkForCandidates();
		if (!candidates.isEmpty()) {
			Candidate cnd = candidates.getFirst();
			candidates.remove(cnd);
			candidates.addLast(cnd);
			cnd = null;
			for (Candidate candidate : candidates) {
				if (candidate.getId().equals(cId)) {
					cnd = candidate;
					if (cnd.getStatus().equals("Completed")) {
						manageInterviewView.showAlert("\nCandidate Completed his/her Interview..!");
						return;
					}
					break;
				}
			}
			candidates.remove(cnd);
			if (cnd != null) {
				candidates.addFirst(cnd);
				manageInterviewView.showAlert("\nNext Candidate is " + cId + "..!");
			} else {
				manageInterviewView.showAlert("\nCandidate Not Found..!");
			}
		}
		hrList = null;
		candidates = null;
	}

	void viewCandidates() {
		candidates = DetailsDatabase.getInstance().getCandidates();
		if (candidates.isEmpty()) {
			manageInterviewView.showAlert("\nCandidates Not Registered..!");
		} else {
			manageInterviewView.listCandidates(candidates);
		}
	}

	boolean checkForCandidates() {
		candidates = DetailsDatabase.getInstance().getCandidates();
		if (candidates.isEmpty()) {
			manageInterviewView.showAlert("\nCandidates Not Registered..!");
			return false;
		} else {
			if (candidates.getFirst().getStatus().equals("Completed")
					&& candidates.getLast().getStatus().equals("Completed")) {
				return true;
			}
		}
		return false;
	}

}
