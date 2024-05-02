package model;

import java.util.List;

public class Lift {
	private String liftName;
	private int currentPosition;
	private boolean underMaintenance;
	private List<String> floorsRestricted;
	private int capacity;

	public Lift(String liftName, int currentPosition, boolean underMaintenance, int capacity) {
		this.liftName = liftName;
		this.currentPosition = currentPosition;
		this.underMaintenance = underMaintenance;
		this.capacity = capacity;
	}

	public String getLiftName() {
		return liftName;
	}

	public void setLiftName(String liftName) {
		this.liftName = liftName;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public boolean isUnderMaintenance() {
		return underMaintenance;
	}

	public void setUnderMaintenance(boolean underMaintenance) {
		this.underMaintenance = underMaintenance;
	}

	public List<String> getFloorsRestricted() {
		return floorsRestricted;
	}

	public void setFloorsRestricted(List<String> floorsRestricted) {
		this.floorsRestricted = floorsRestricted;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
