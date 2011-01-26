package edu.illinois.coursequest;


public class DaySlot {
	boolean[] days = new boolean[5];

	public DaySlot() {
	}

	public DaySlot(boolean[] daySlot) {
		this.days = daySlot;
	}

	/**
	 * @param monday
	 * @param tuesday
	 * @param wednesday
	 * @param thursday
	 * @param friday
	 */
	public DaySlot(boolean m, boolean t, boolean w, boolean r, boolean f) {
		this.days[0] = m;
		this.days[1] = t;
		this.days[2] = w;
		this.days[3] = r;
		this.days[4] = f;
	}

	// Puts the number representation of turned on days i.e mtw = 012 mwf = 024
	public String toString() {
		String temp = "";
		for (int i = 0; i < 5; i++)
			if (days[i])
				temp += i;
			else
				temp += " ";
		return temp;
	}

	public boolean[] getDays() {
		return days;
	}

	public void setDays(boolean[] daySlot) {
		this.days = daySlot;
	}
}
