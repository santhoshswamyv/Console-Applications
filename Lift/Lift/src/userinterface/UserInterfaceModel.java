package userinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datalayer.Data;
import model.Lift;

public class UserInterfaceModel {

	private UserInterfaceView userInterfaceView;
	private ArrayList<Lift> lifts = new ArrayList<>();
	private Data data;

	UserInterfaceModel(UserInterfaceView userInterfaceView) {
		this.userInterfaceView = userInterfaceView;
		this.data = new Data(this);
	}

	void populate() {
		if (!lifts.isEmpty()) {
			return;
		}

		Lift L1 = new Lift("L1", 0, false, 5);
		Lift L2 = new Lift("L2", 0, false, 5);
		Lift L3 = new Lift("L3", 0, false, 5);
		Lift L4 = new Lift("L4", 0, false, 5);
		Lift L5 = new Lift("L5", 0, false, 5);

		lifts.add(L1);
		lifts.add(L2);
		lifts.add(L3);
		lifts.add(L4);
		lifts.add(L5);

		data.setLifts(lifts);
	}

	public ArrayList<Lift> getLifts() {
		if (!lifts.isEmpty()) {
			return lifts;
		}
		return data.getLifts();
	}

	Lift liftChooser(int currentFloor, int destinationFloor) {
		if (currentFloor < 0 || currentFloor > 10) {
			return null;
		}

		if (lifts.isEmpty()) {
			lifts = data.getLifts();
		}

		Lift nearestLift = null;
		int distance = Integer.MAX_VALUE;

		for (Lift lift : lifts) {

			int currentPosition = lift.getCurrentPosition();

			if (lift.getFloorsRestricted() != null) {
				if (currentPosition == currentFloor && !lift.isUnderMaintenance()
						&& !lift.getFloorsRestricted().contains("" + currentFloor)) {
					lift.setCurrentPosition(destinationFloor);
					return lift;
				}
			} else {
				if (currentPosition == currentFloor && !lift.isUnderMaintenance()) {
					lift.setCurrentPosition(destinationFloor);
					return lift;
				}
			}
			int tempDistance = Math.abs(currentFloor - currentPosition);
			if (lift.getFloorsRestricted() != null) {
				if (tempDistance < distance && !lift.isUnderMaintenance()
						&& !lift.getFloorsRestricted().contains("" + currentFloor)) {
					distance = tempDistance;
					nearestLift = lift;
				}
			} else {
				if (tempDistance < distance && !lift.isUnderMaintenance()) {
					distance = tempDistance;
					nearestLift = lift;
				}
			}

		}

		nearestLift.setCurrentPosition(destinationFloor);

		return nearestLift;
	}

	Lift searchLift(String name) {
		if (!lifts.isEmpty()) {
			lifts = data.getLifts();
		}

		for (Lift lift : lifts) {
			if (lift.getLiftName().equals(name)) {
				return lift;
			}
		}

		return null;
	}

	public boolean putMaintenance(Lift lift) {
		lift.setUnderMaintenance(true);
		return true;
	}

	public void restrictAccess(Lift lift, String restricted) {
		List<String> floorsRestricted = Arrays.asList(restricted.split(" "));
		lift.setFloorsRestricted(floorsRestricted);
	}

}
