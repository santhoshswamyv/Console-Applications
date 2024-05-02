package model;

import java.util.Comparator;

public class LiftComparator implements Comparator<Lift> {

	@Override
	public int compare(Lift lift1, Lift lift2) {
		return Integer.compare(lift1.getCurrentPosition(), lift2.getCurrentPosition());
	}
}
