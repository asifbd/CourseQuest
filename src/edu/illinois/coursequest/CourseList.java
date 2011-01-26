package edu.illinois.coursequest;
import java.util.ArrayList;

//import scheduler.Course;

public class CourseList {
	private ArrayList<Course> sched = new ArrayList<Course>();
	private static final int COLS = 5;
	private static final int ROWS = 16;
	private Course[][] slotPos = new Course[ROWS][COLS]; // 2d representation of
	// CourseList
	// maybe some encapsalation is in order
	private ArrayList<String> courseNames = new ArrayList<String>();
	static final String KEY_COURSE = "course";
	static final String KEY_ID = "_id";

	// The time slot method is intended to give the schedule item a time slot in
	// the [y,x] coordinate plane to display
	// Its information. It must deal with multiple time positions and display
	// the same information in those slots.
	// It doesn't have to be new objects, just positions that look at the same
	// info.

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

	public void addCourse(Course tempCourse) {
		sched.add(tempCourse); // adds parsed Arraylist to the schedule
		updateSlotPos();
	}

	public void addCourse(CourseInfo info, String type) {
		addCourse(makeCourse(info, type, findLecture(info)));
	}

	private static Course makeCourse(CourseInfo info, String type, Lecture l) {
		if (info == null || type == null)
			return null; // sadness
		if (type.contains("Lecture")) {
			return new Lecture(info);
		}
		if (type.contains("Lab")) {
			return new Lab(info, l);
		}
		return new Discussion(info, l);
	}

	public void editCourse(Course c, CourseInfo newInfo) {
		c.setInfo(newInfo);
		updateSlotPos();
	}

	public void editCourse(CourseInfo newInfo, int pos) {
		if (pos < 0 || pos > sched.size() - 1)
			return;
		editCourse(sched.get(pos), newInfo);
	}

	// Deletes a course form the schedule
	public void deleteCourse(int pos) {
		if (pos > sched.size() - 1 || pos < 0)
			return; // EARLY RETURN
		sched.remove(pos);
		courseNames.remove(pos);
		updateSlotPos();
	}

	private void updateSlotPos() {
		// TODO Write better implementation for this...
		slotPos = new Course[ROWS][COLS]; // zeros slotPos
		courseNames.clear();
		for (int i = 0; i < sched.size(); i++) {
			Course tempCourse = sched.get(i);
			courseNames.add(tempCourse.getMiniString());
			DaySlot dayslot = tempCourse.getInfo().getDayslot();
			boolean[] days = dayslot.getDays();
			for (int j = 0; j < days.length; j++) {
				if (days[j] == true) // if the course is that day
					slotPos[tempCourse.getInfo().getStartTime() - 8][j] = tempCourse;
			}
		}
	}

	public Course findCourse(String name) {
		Course temp;
		for (int i = 0; i < sched.size(); i++) {
			temp = sched.get(i);
			if (name.contains(temp.getInfo().getName() + "\n\t	"
					+ temp.getFormattedTime() + "	ID:" + temp.getCourseID()))
				return temp;
		}
		return null;
	}

	public Lecture findLecture(CourseInfo info) {
		Course temp;
		for (int i = 0; i < sched.size(); i++) {
			temp = sched.get(i);
			if (temp.getInfo().getName().equals(info.getName())
					&& temp instanceof Lecture)
				return (Lecture) temp;
		}
		return null;
	}

	/**
	 * This returns the position for displaying position of Course in View/Gui
	 * 
	 * @param day
	 * @param hour
	 * @return
	 */
	public Course getCourseAt(int day, int hour) {
		return slotPos[day][hour];
	}

	public Course getCourse(int index) {
		return sched.get(index);
	}

	public String[] getCourseNames() {
		return courseNames.toArray(new String[sched.size()]);
	}

	public boolean isClassAt(int day, int hour) {
		Course temp;
		for (int i = 0; i < sched.size(); i++) {
			temp = sched.get(i);
			if (temp != null && temp.isClassAt(day, hour))
				return true;
		}
		return false;
	}

	public String getFormattedTime(int i) {
		// This code retrieves the time then converts it out of army time
		return getCourse(i).getFormattedTime();
	}

	public String toString() {
		return sched.toString();
	}

	public void printSchedule() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				Course temp = slotPos[i][j];
				if (temp != null)
					System.out.print(slotPos[i][j].getInfo().getName()
							+ slotPos[i][j].getColor());
				else
					System.out.print("-");
				System.out.print("		");
			}
			System.out.println();
		}
	}

	public static void main(String args[]) {
//
		CourseList sched = new CourseList();
		DaySlot dayslot = new DaySlot();
		dayslot.setDays(new boolean[] { true, false, true, false, true });
		CourseInfo info = new CourseInfo("test", "testy", "proftest", dayslot,
				"section", 1111, 12, 1, false);
		Lecture temp = new Lecture(info);
		Lab temp2 = new Lab(info, temp);
		Discussion temp3 = new Discussion(info, temp);
		sched.addCourse(temp2);
		sched.addCourse(temp);
		sched.addCourse(temp3);
		sched.deleteCourse(1);
		sched.deleteCourse(1);
		sched.deleteCourse(1);
		sched.deleteCourse(1);
		sched.deleteCourse(1);
		sched.addCourse(temp2);
		sched.addCourse(temp);
		sched.addCourse(temp3);
		sched.addCourse(temp2);
		sched.editCourse(info, 1);
		sched.editCourse(info, 1);
		sched.editCourse(info, 1);
		sched.editCourse(info, 1);
		sched.editCourse(info, 1);
		sched.editCourse(info, 1);
		sched.editCourse(info, 'a');
		sched.editCourse(info, 1);
		sched.addCourse(temp);
		sched.addCourse(temp3);
//		//	 
//		// sched.printSchedule();
//		// System.out.print(sched.isClassAt(2, 12));
//		System.out.println(sched);
//		String s = sched.toString();
//		System.out.print(CourseParser.toCourseList(s).toString().equals(s));
		//System.out.println(sched);
		String s = "[|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|]";
		String b = CourseParser.toCourseList(s).toString(); 
		System.out.println(b.equals(s));
		
		s = sched.toString();
		b = CourseParser.toCourseList(s).toString(); 
		System.out.println(b.equals(s));
	}
}