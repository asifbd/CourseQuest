package edu.illinois.coursequest;

public class Course {
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

	public CourseInfo getInfo() {
		return info;
	}

	
	public void setInfo(CourseInfo info) {
		this.info = info;
	}

	public Course(Course c) {
		courseID = count;
		count++;
		this.info = c.info;
	}

	protected int pickColor() {
		return 0;
	}

	public String getName() {
		return info.getName();
	}

	public String getDescription() {
		return info.getDescription();
	}

	public String getProf() {
		return info.getProf();
	}

	public String getSection() {
		return info.getSection();
	}

	public int getCrn() {
		return info.getCrn();
	}

	public int getStartTime() {
		return info.getStartTime();
	}

	public int getEndTime() {
		return info.getEndTime();
	}

	public boolean isSilent() {
		return info.isSilent();
	}

	public DaySlot getDayslot() {
		return info.getDayslot();
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void decrementID(){
		count--;
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

}