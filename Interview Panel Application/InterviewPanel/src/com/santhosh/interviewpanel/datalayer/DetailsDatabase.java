package com.santhosh.interviewpanel.datalayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.santhosh.interviewpanel.model.HR;
import com.santhosh.interviewpanel.model.Receptionist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.santhosh.interviewpanel.model.Candidate;

public class DetailsDatabase {
	private static DetailsDatabase detailsDatabase;

	// Base path of all Json files
	private static final String BASE_PATH = System.getProperty("user.dir")
			+ "/src/com/santhosh/interviewpanel/datalayer/data/";

	// Gson object used to serialize it as Json and Deserialize
	private static Gson gson = new Gson();

	// Data to be stored
	public int id = 1;
	private Receptionist receptionist;
	private List<HR> hrList = new ArrayList<HR>();
	private ArrayDeque<Candidate> candidates = new ArrayDeque<Candidate>();

	// File Names
	private final String idFile = "id.json";
	private final String receptionistFile = "receptionist.json";
	private final String hrFile = "hr.json";
	private final String candidatesFile = "candidates.json";

	// Saving Methods after finishing the Update
	public void saveId() {
		toJson(id, idFile);
	}

	public void saveReceptionist() {
		toJson(receptionist, receptionistFile);
	}

	public void saveHr() {
		toJson(hrList, hrFile);
	}

	public void saveCandidates() {
		toJson(candidates, candidatesFile);
	}

	public void saveAll() {
		saveId();
		saveReceptionist();
		saveHr();
		saveCandidates();
	}

	// Retrieving Methods while need
	public void retrieveId() {
		File file = new File(BASE_PATH + idFile);
		if (file.length() == 0) {
			return;
		}
		id = fromJson(idFile, Integer.class);
	}

	public void retrieveReceptionist() {
		File file = new File(BASE_PATH + receptionistFile);
		if (file.length() == 0) {
			return;
		}
		receptionist = fromJson(receptionistFile, Receptionist.class);
	}

	public void retrieveHr() {
		File file = new File(BASE_PATH + hrFile);
		if (file.length() == 0) {
			return;
		}
		hrList = fromJson(hrFile, new TypeToken<List<HR>>() {
		}.getType());
	}

	public void retrieveCandidates() {
		File file = new File(BASE_PATH + candidatesFile);
		if (file.length() == 0) {
			return;
		}
		candidates = fromJson(candidatesFile, new TypeToken<ArrayDeque<Candidate>>() {
		}.getType());
	}

	// Getter methods
	public Receptionist getReceptionist() {
		return receptionist;
	}

	public int getId() {
		return id;
	}

	public List<HR> getHrs() {
		return hrList;
	}

	public ArrayDeque<Candidate> getCandidates() {
		return candidates;
	}

	// Serializing as Json Object
	public void toJson(Object object, String fileName) {
		String json = gson.toJson(object);
		File file = new File(BASE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(BASE_PATH + fileName);
			fileOutputStream.write(json.getBytes());
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Deserializing the Json Object
	public <T> T fromJson(String fileName, Type type) {
		try (FileInputStream fileInputStream = new FileInputStream(BASE_PATH + fileName)) {
			byte[] bytes = fileInputStream.readAllBytes();
			String jsonString = new String(bytes);
			return gson.fromJson(jsonString, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Resetting the Application
	public void reset() {
		id = 0;
		receptionist = null;
		hrList = null;
		candidates = null;
		File folder = new File(BASE_PATH);
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
					file.delete();
				}
			}
		}
	}

	public static DetailsDatabase getInstance() {
		if (detailsDatabase == null) {
			// Creating a Instance of DetailsDatabase
			detailsDatabase = new DetailsDatabase();
		}
		return detailsDatabase;
	}

	// Method to Create an Instance of Receptionist
	public boolean setReceptionist(String id, String name, String emailId, String password) {
		if (receptionist == null) {
			this.receptionist = new Receptionist(id, name, emailId, password);
			saveReceptionist();
			DetailsDatabase.getInstance().generateHr();
			return true;
		}
		return false;
	}

	// Credentials Validation of Receptionist
	public boolean getReceptionist(String emailId, String password) {
		if (receptionist != null) {
			if (emailId.equals(this.receptionist.getEmailId()) && password.equals(this.receptionist.getPassword())) {
				return true;
			}
		}
		return false;
	}

	// Method to Create an Instance of Candidate
	public boolean addCandidate(String id, String name, String collegeName, String emailId, String phoneNo,
			String location, String status, String result) {
		Candidate cnd = new Candidate(id, name, collegeName, emailId, phoneNo, location, status, result);
		return candidates.add(cnd);
	}

	// Method to Implement More HR (Not yet Completed)
	private void generateHr() {
		int empId = 1000;
		hrList.add(new HR(empId, 8754120190l, "Sandy", "sandy@gmail.com"));
//		hrList.add(new HR(empId + 1, 9840806840l, "Suresh", "suresh@gmail.com"));
	}
}
