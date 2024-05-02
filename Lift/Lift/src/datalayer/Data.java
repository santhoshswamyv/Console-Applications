package datalayer;

import java.util.ArrayList;

import model.Lift;
import userinterface.UserInterfaceModel;

public class Data {
	private ArrayList<Lift> lifts;
	private UserInterfaceModel userInterfaceModel;

	public Data(UserInterfaceModel userInterfaceModel) {
		this.userInterfaceModel = userInterfaceModel;
	}

	public ArrayList<Lift> getLifts() {
		return lifts;
	}

	public void setLifts(ArrayList<Lift> lifts) {
		this.lifts = lifts;
	}
}
