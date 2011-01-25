package edu.illinois.coursequest;

public abstract class Course {
	/**
	 * @author vazque16 mills16
	 * */
	private int courseID;
	private static int count;
	private int color;
	private CourseInfo info;

	protected static int[] primColors = new int[] {
			// purple,pink,blue,green,orange,yellow,red,teal
			0xB40097, 0xEA0037, 0x5A0DAC, 0xC0F400, 0xFF8C00, 0xFFC700,
			0xFF1300, 0x0969A2 };
	protected static int[] secColors = new int[] { 0xD936C0, 0xF53D68,
			0x8942D6, 0xD2FA3E, 0xFFA940, 0xFFD540, 0xFF4E40, 0x3D9AD1 };
	protected static int[] thirdColors = new int[] { 0xD962C7, 0xF56E8D,
			0x9D69D6, 0xDCFA70, 0xFFC073, 0xFFE073, 0xFF7D73, 0x64A8D1 };

	/**
	 * This will create a course object that holds information about the course
	 * 
	 * @precondition ??
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

	public Course(CourseInfo info) {
		courseID = count++;
		this.info = info;
	}

	public Course(Course a) {
		courseID = count++;
		this.info = a.info;
	}

	public String toString() {
		return getInfo().getName() + "   " + getFormattedTime();
	}
	
	public String getMiniString(){
		return getInfo().getName() + "   " + getFormattedTime();
	}

	public boolean isClassAt(int day, int hour) {
		if (day < 0 || day > 4)
			return false;
		boolean[] dayArray = getInfo().getDayslot().getDays();
		return (dayArray[day] && getInfo().getStartTime() == hour);
	}

	public String getFormattedTime() {
		// This code retrieves the time then converts it out of army time
		String times = "";
		int sTime = this.info.getStartTime();
		if (sTime > 12) {
			times += (sTime - 12) + ":00 PM - ";
		} else {
			times += sTime + ":00 AM - ";
		}

		int eTime = this.info.getEndTime();
		if (eTime > 12) {
			times += (eTime - 12) + ":00 PM";
		} else {
			times += eTime + ":00 AM";
		}
		return times;
	}

	protected int pickColor() {
		return 0;
	}

	protected static void decrementID() {
		count--;
		if (count < 0)
			count = 0;
	}

	/*
	 * Getters and setters
	 */

	public CourseInfo getInfo() {
		return info;
	}

	public void setInfo(CourseInfo info) {
		this.info = info;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Course.count = count;
	}

	public String getType() {
		return "";
	}

}