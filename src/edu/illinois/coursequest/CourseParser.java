package edu.illinois.coursequest;

//This class contains functions for string manipulation for CourseQuest...
//3 public functions toCourseList, toCourse, and safeStrings

public class CourseParser {
	private static CourseList sched = new CourseList(); // manipulates a

	// schedule like so

	// Notes No , or | are allowed in strings....else its considered broken...
	// @author Marcell Vazquez-Chanlatte
	// TODO make this not suck...
	// TODO seriously this code sucks....I'll clean it up when i need to....
	// TODO probably using String buffers and such....

	public static CourseList toCourseList(String s) {
		sched = new CourseList();
		// Get rid of bracket
		s = s.substring(1);
		int temp = 0;
		while (s.length() - 1 > temp) {// shrink string until its the last value
			// ]
			temp = findNextPos2(s.substring(0));
			String a = s.substring(0, temp);
			s = s.substring(temp + 2);
			sched.addCourse(toCourse(a));
		}
		sched.addCourse(toCourse(s.substring(0, findNextPos3(s))));

		return sched;
	}

	/**
	 * Takes a String and makes it a course.... passes info to toInfo and then
	 * uses ID to and type to make the course
	 * 
	 * @param s
	 * @return
	 */
	public static Course toCourse(String s) {
		if (s == null)
			return null; // early return
		// System.out.println(s.substring(3,s.length()));
		return makeCourse(s.substring(3), toType(s));
	}

	/**
	 *Takes a string and makes sure there is no |'s
	 * 
	 */
	public static boolean safeString(String s) {
		return !s.contains("|");
	}

	/**
	 * Takes a string a makes a CourseInfo object...loops 9 times...trust me...
	 * 
	 * @param s
	 * @return
	 */
	private static CourseInfo toInfo(String s) {
		/*
		 * (String name, String description, String prof, DaySlot dayslot,
		 * String section, int crn, int startTime, int endTime, boolean silent)
		 */
		int pos1;
		int pos2 = 0;
		String[] p = new String[10];
		for (int i = 0; i < 9; i++) {
			// take substring
			pos1 = findNextPos(s);
			pos2 = findNextPos(s = s.substring(pos1 + 1));
			p[i] = s.substring(0, pos2);
			pos1 = pos2;
		}
		// make CourseInfo object
		return new CourseInfo(p[0], p[1], p[2], toDaySlot(p[3]), p[4], Integer
				.parseInt(p[5]), Integer.parseInt(p[6]),
				Integer.parseInt(p[7]), Boolean.parseBoolean(p[8]));
	}

	private static int findNextPos(String s) {
		return s.indexOf('|');
	}

	private static int findNextPos2(String s) {
		return s.indexOf("|, ") + 1;
	}

	private static int findNextPos3(String s) {
		return s.indexOf("|]") + 1;
	}

	private static DaySlot toDaySlot(String s) {
		boolean[] a = new boolean[5];
		for (int i = 0; i < 5; i++) {
			a[i] = toBoolean(s, i);
		}
		return new DaySlot(a[0], a[1], a[2], a[3], a[4]);
	}

	private static boolean toBoolean(String s, int i) {
		return s.charAt(i) == '0' + i;
	}

	private static int toID(String s, int i) {
		return Integer.parseInt((String) s.subSequence(0, i));
	}

	private static char toType(String s) {

		return (s.charAt(1));
	}

	private static Course makeCourse(String s, char type) {
		int pos = findNextPos(s); // get the range of CourseID
		int id = toID(s, pos);
		CourseInfo info = toInfo(s.substring(pos));
		Course c;
		switch (type) {
		case 'L':
			c = new Lecture(info);
			c.setCourseID(id);
			break;
		case 'D':
			c = new Discussion(info, sched.findLecture(info));
			c.setCourseID(id);
			break;
		case 'l':
			c = new Lab(info, sched.findLecture(info));
			c.setCourseID(id);
			break;
		default:
			return null; // returns null if Type is bad
		}

		return c;
	}

	/*
	 * This Code was to test the Parser... TODO write J-Unit test...
	 */
	// public static void main(String args[]) {
	// [|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|,
	// |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|,
	// |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|,
	// |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|,
	// |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|,
	// |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|,
	// |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|]
	// System.out
	// .print("[|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|]"
	// .equals("[|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|]"));
	// System.out.print(toCourse("|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|"));
	// String s =
	// "[|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|]";
	// String b = toCourseList(s).toString();
	// System.out.println(b.equals(s));
	// System.out
	// .print(toCourseList("[|l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |l|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |L|0|test|testy|proftest|0 2 4|section|1111|12|1|false|, |D|0|test|testy|proftest|0 2 4|section|1111|12|1|false|]"));
	// }
}
