package lift;

import userinterface.UserInterfaceView;

public class LiftDriver {
	private UserInterfaceView userInterfaceView;

	public UserInterfaceView getInstance() {
		return userInterfaceView;
	}

	public void init() {
		if (getInstance() == null) {
			userInterfaceView = new UserInterfaceView();
		}

		userInterfaceView.userInterface();
	}

	public static void main(String[] args) {
		LiftDriver liftDriver = new LiftDriver();
		liftDriver.init();
	}
}
