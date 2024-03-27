package com.santhosh.interviewpanel.manageinterview;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;

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

	// Retrieving the ID if Present or Default value
	int confirmId() {
		DetailsDatabase database = DetailsDatabase.getInstance();
		int id = database.getId();
		if (id == 1) {
			database.retrieveId();
			id = database.getId();
		}
		return id;
	}

	// Adding the Candidate in the Queue
	void createCandidate(String id, String name, String collegeName, String emailId, String phoneNo, String location,
			String status, String result) throws Exception {
		if (DetailsDatabase.getInstance().addCandidate(id, name, collegeName, emailId, phoneNo, location, status,
				result)) {
			manageInterviewView.showAlert("\n" + " (" + id + ") " + name + " added to the Queue..!");
			DetailsDatabase.getInstance().id++;
			manageInterviewView.confirmation();
		} else {
			manageInterviewView.showAlert("\nUnexpected Error Occured Try Again..!");
			manageInterviewView.addCandidate();
		}
	}

	// Retrieving the Old Data
	void retrieveData() {
		DetailsDatabase.getInstance().retrieveHr();
		DetailsDatabase.getInstance().retrieveCandidates();
	}

	// Starting the Interview for Candidate
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

	// Method to Sort the Candidates if Emergency Student Collapsed the Queue
	void sortCandidates() {
		checkForCandidates();
		Iterator<Candidate> iterator = candidates.iterator();
		if (candidates.getFirst().getStatus().equals("Completed")
				&& candidates.getLast().getStatus().equals("Completed")) {
			while (iterator.hasNext()) {
				Candidate candidate = iterator.next();
				if (candidate.getStatus().equals("Completed")) {
					iterator.remove();
					candidates.addLast(candidate);
				}
			}
		} else {
			iterator = null;
			iterator = candidates.iterator();
			while (iterator.hasNext()) {
				Candidate candidate = iterator.next();
				if (candidate.getStatus().equals("Completed")) {
					iterator.remove();
					candidates.addLast(candidate);
				}
			}
		}
	}

	// Ending the Interview for Candidate
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

	// Method to Shift the Student to top of the Queue
	void emergencyCandidate(String cId) {
		checkForCandidates();
		if (!candidates.isEmpty()) {
			Candidate cnd = null;
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

	// Method to Transfer the Candidates
	void viewCandidates() {
		candidates = DetailsDatabase.getInstance().getCandidates();
		if (candidates.isEmpty()) {
			manageInterviewView.showAlert("\nCandidates Not Registered..!");
		} else {
			manageInterviewView.listCandidates(candidates);
		}
	}

	// Checking the candidates completed the Interview
	boolean checkForCandidates() {
		candidates = DetailsDatabase.getInstance().getCandidates();
		if (candidates.isEmpty()) {
			DetailsDatabase.getInstance().saveAll();
			return true;
		} else {
			if (candidates.getFirst().getStatus().equals("Completed")
					&& candidates.getLast().getStatus().equals("Completed")) {
				DetailsDatabase.getInstance().saveAll();
				return true;
			}
		}
		return false;
	}

}
