package edu.illinois.coursequest;

import java.util.ArrayList;

//import scheduler.Course;

public class CourseList {
	public static ArrayList<Course> sched = new ArrayList<Course>();
	public static final int COLS = 5;
	public static final int ROWS = 16;
	public static Course[][] slotPos = new Course[ROWS][COLS];
	public static ArrayList<String> courseNames = new ArrayList<String>();
	int POSITIONMULTIPLER = 10;
	public static int length = 0;
	public static final String KEY_COURSE = "course";
	public static final String KEY_ID = "_id";
	public static Course currentCourse;
	public static int currentIndex;

	// The time slot method is intended to give the schedule item a time slot in
	// the [y,x] coordinate plane to display
	// Its information. It must deal with multiple time positions and display
	// the same information in those slots.
	// It doesn't have to be new objects, just positions that look at the same
	// info.

	public static void updateSlotPos() {
		slotPos = new Course[ROWS][COLS]; // zeros slotPos
		for (int i = 0; i < length; i++) {
			Course tempCourse = sched.get(i);
			DaySlot dayslot = tempCourse.getDayslot();
			boolean[] days = dayslot.getDays();
			for (int j = 0; j < days.length; j++) {
				if (days[j] == true) // if the course is that day
					slotPos[tempCourse.getStartTime() - 8][j] = tempCourse;
			}
		}
	}

	/**
	 * This adds the course to the schedule via manual input
	 * 
	 * @param name
	 * @param description
	 * @param prof
	 * @param days
	 * @param section
	 * @param classType
	 * @param crn
	 * @param startTime
	 * @param endTime
	 */
	public static void addCourse(String name, String description, String prof,
			DaySlot dayslot, String section, String classType, int crn,
			int startTime, int endTime, boolean silent, Lecture l) {

		Course newCourse = makeCourse(name, description, prof, dayslot,
				section, crn, startTime, endTime, silent, classType, l);
		addCourse(newCourse);
	}

	private static Course makeCourse(String name, String description,
			String prof, DaySlot dayslot, String section, int crn,
			int startTime, int endTime, boolean silent, String classType,
			Lecture l) {
		CourseInfo newInfo = new CourseInfo(name, description, prof, dayslot,
				section, crn, startTime, endTime, silent);
		if (classType.contains("Lecture")) {
			return new Lecture(newInfo);
		}
		if (classType.contains("Lab")) {
			return new Lab(newInfo, l);
		}
		return new Discussion(newInfo, l);
	}

	public static void addCourse(Course tempCourse) {
		sched.add(tempCourse); // adds parsed Arraylist to the schedule
		courseNames.add(tempCourse.getName() + "   "
				+ getFormattedTime(tempCourse)); // adds to string array
		length++;
		updateSlotPos();
	}

	public static void editCourse(Course oldCourse, Course newCourse, int pos) {
		newCourse.setCourseID(oldCourse.getCourseID());
		newCourse.setColor(oldCourse.getColor());
		courseNames.set(pos, newCourse.getName() + "   "
				+ getFormattedTime(newCourse));
		sched.set(pos, newCourse);
		updateSlotPos();

	}

	// Deletes a course form the schedule
	public static void deleteCourse(int pos) {
		sched.remove(pos);
		courseNames.remove(pos);
		length--;
		updateSlotPos();
	}

	public static Course getCourse(int index) {
		return sched.get(index);
	}

	public static Course findCourse(String name) {
		Course temp;
		for (int i = 0; i < length; i++) {
			temp = sched.get(i);
			if (name.contains(temp.getName() + "\n\t	" + getFormattedTime(temp)
					+ "	ID:" + temp.getCourseID()))
				return temp;
		}
		return null;
	}

	public static Lecture findLecture(String name) {
		Course temp;
		for (int i = 0; i < length; i++) {
			temp = sched.get(i);
			if (temp.getName().equals(name) && temp instanceof Lecture)
				return (Lecture) temp;
		}
		return null;
	}

	/**
	 * This returns the position for displaying position of Course in View/Gui
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static Course getSlotPos(int i, int j) {
		return slotPos[i][j];
	}

	public static boolean isClassAt(int day, int hour) {
		for (int i = 0; i < sched.size(); i++) {
			Course temp = sched.get(i);
			if (temp != null) {
				boolean[] dayArray = temp.getDayslot().getDays();
				if (dayArray[day] && temp.getStartTime() == hour)
					return true;
			}
		}
		return false;
	}

	public static String getFormattedTime(int i) {
		// This code retrieves the time then converts it out of army time
		String times = "";
		int sTime = getCourse(i).getStartTime();
		if (sTime > 12) {
			times += (sTime - 12) + ":00 PM - ";
		} else {
			times += sTime + ":00 AM - ";
		}

		int eTime = getCourse(i).getEndTime();
		if (eTime > 12) {
			times += (eTime - 12) + ":00 PM";
		} else {
			times += eTime + ":00 AM";
		}
		return times;
	}

	public static String getFormattedTime(Course c) {
		// This code retrieves the time then converts it out of army time
		String times = "";
		int sTime = c.getStartTime();
		if (sTime > 12) {
			times += (sTime - 12) + ":00 PM - ";
		} else {
			times += sTime + ":00 AM - ";
		}

		int eTime = c.getEndTime();
		if (eTime > 12) {
			times += (eTime - 12) + ":00 PM";
		} else {
			times += eTime + ":00 AM";
		}
		return times;
	}

	public void printSchedule() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				Course temp = slotPos[i][j];
				if (temp != null)
					System.out.print(slotPos[i][j].getName()
							+ slotPos[i][j].getColor());
				else
					System.out.print("-");
				System.out.print("		");
			}
			System.out.println();
		}
	}

	// public static void main(String args[]) {
	//
	// CourseList sched = new CourseList();
	// DaySlot dayslot = new DaySlot();
	// dayslot.setDays(new boolean[] { true, false, true, false, true });
	// CourseInfo info = new CourseInfo("test", "testy", "proftest", dayslot,
	// "section", 1111, 12, 1, false);
	// Lecture temp = new Lecture(info);
	// Lab temp2 = new Lab(info, temp);
	// Discussion temp3 = new Discussion(info, temp);
	// sched.addCourse(temp2);
	// sched.addCourse(temp);
	// sched.addCourse(temp3);
	// sched.printSchedule();
	// System.out.print(sched.isClassAt(2, 12));
	// }
}