package userinterface;

import java.util.ArrayList;
import java.util.Scanner;

import model.Lift;

public class UserInterfaceView {

	static Scanner sc = new Scanner(System.in);

	private UserInterfaceModel userInterfaceModel;

	public UserInterfaceView() {
		userInterfaceModel = new UserInterfaceModel(this);
	}

	public void userInterface() {
		initiatePopulate();
		System.out.println("Lift Operations :-\n");
		System.out.println("1) Display the Lifts\n2) Choose Lift\n3) Management Options");
		String option = sc.next();
		switch (option) {
		case "1":
			displayLifts();
			break;
		case "2":
			chooseLift();
			break;
		case "3":
			manageLifts();
			break;
		default:
			System.err.println("Choose a Valid Option");
			userInterface();
			return;
		}
		userInterface();
		return;
	}

	public void initiatePopulate() {
		userInterfaceModel.populate();

	}

	private void displayLifts() {
		ArrayList<Lift> lifts = userInterfaceModel.getLifts();

		System.out.println("Lifts Position :-\n");
		for (Lift lift : lifts) {
			System.out.println("Lift - " + lift.getLiftName() + "   Floor - " + lift.getCurrentPosition());
		}
		System.out.println();
	}

	private void chooseLift() {
		System.out.print("Current Floor : ");
		String currentFloor = sc.next();
		System.out.print("Destination Floor : ");
		String destinationFloor = sc.next();

		Lift lift = userInterfaceModel.liftChooser(Integer.parseInt(currentFloor), Integer.parseInt(destinationFloor));

		if (lift == null) {
			showAlert("Enter Valid Floor");
			chooseLift();
			return;
		}

		showAlert(lift.getLiftName() + " is Assigned");
		displayLifts();
	}

	private void manageLifts() {
		System.out.println("Management Options");
		System.out.println("1) Put Under Maintenance\n2) Restrict the Access\n3) User Interface");
		String option = sc.next();
		switch (option) {
		case "1":
			makeMaintenance();
			return;
		case "2":
			restrictAccess();
			return;
		case "3":
			userInterface();
			return;
		default:
			System.err.println("Choose a Valid Option");
			manageLifts();
			return;
		}
	}

	private void restrictAccess() {
		Lift lift = getLift();
		System.out.println("Number of Floors to be Restricted");
		String no = sc.next();
		String restricted = "";
		for (int i = 0; i < Integer.parseInt(no); i++) {
			restricted += sc.next() + " ";
		}

		restricted = restricted.trim();

		userInterfaceModel.restrictAccess(lift, restricted);
	}

	private void makeMaintenance() {
		Lift lift = getLift();
		if (lift == null) {
			showAlert("Lift not found");
			manageLifts();
			return;
		}
		boolean response = userInterfaceModel.putMaintenance(lift);
		if (response) {
			showAlert(lift.getLiftName() + " is Under Maintenance");
		}
		manageLifts();
		return;

	}

	private Lift getLift() {
		System.out.print("Enter the Lift Name :-");
		String name = sc.next();
		return userInterfaceModel.searchLift(name);
	}

	void showAlert(String message) {
		System.err.println("\n" + message + "\n");
	}

}