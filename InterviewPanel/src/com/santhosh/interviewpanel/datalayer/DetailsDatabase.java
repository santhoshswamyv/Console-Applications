package com.santhosh.interviewpanel.datalayer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.santhosh.interviewpanel.model.HR;
import com.santhosh.interviewpanel.model.Receptionist;
import com.santhosh.interviewpanel.model.Candidate;

public class DetailsDatabase {
	private static DetailsDatabase detailsDatabase;

	private Receptionist receptionist;
	private List<HR> hrList = new ArrayList<HR>();
	private ArrayDeque<Candidate> candidates = new ArrayDeque<Candidate>();

	public static DetailsDatabase getInstance() {
		if (detailsDatabase == null) {
			detailsDatabase = new DetailsDatabase();
		}
		return detailsDatabase;
	}

	public boolean setReceptionist(String id, String name, String emailId, String password) {
		if (receptionist == null) {
			this.receptionist = new Receptionist(id, name, emailId, password);
			DetailsDatabase.getInstance().generateHr();
			return true;
		}
		return false;
	}

	public boolean getReceptionist(String emailId, String password) {
		if (receptionist != null) {
			if (emailId.equals(this.receptionist.getEmailId()) && password.equals(this.receptionist.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public boolean addCandidate(String id, String name, String collegeName, String emailId, String phoneNo,
			String location, String status, String result) {
		Candidate cnd = new Candidate(id, name, collegeName, emailId, phoneNo, location, status, result);
		return candidates.add(cnd);
	}

	public List<HR> getHrs() {
		return hrList;
	}

	public ArrayDeque<Candidate> getCandidates() {
		return candidates;
	}

	private void generateHr() {
		int empId = 1000;
		hrList.add(new HR(empId, 8754120190l, "Sandy", "sandy@gmail.com"));
//		hrList.add(new HR(empId + 1, 9840806840l, "Suresh", "suresh@gmail.com"));
	}
}
