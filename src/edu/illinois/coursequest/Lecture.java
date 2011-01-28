package edu.illinois.coursequest;

public class Lecture extends Course {
	private char type = 'L';

	/**
	 * Same as Course but assigns proper Color
	 * 
	 * @param info
	 */
	public Lecture(CourseInfo info) {
		super(info);
		super.setColor(pickColor());
	}

	public Lecture(Course c) {
		super(c);
		super.setColor(pickColor());
	}

	public String toString() {
		return "|" + "L" + super.toString();
	}

	public String getCourseType() {
		return "Lecture";
	}

	public char getType() {
		return type;
	}

	protected int pickColor() {
		return primColors[super.getCourseID() % 8];
	}
}
