package edu.illinois.coursequest;

public class Lab extends Course {

	private Lecture l;

	/**
	 * Same as Lecture but assigns proper color based on Lecture
	 * 
	 * @param info
	 */
	public Lab(CourseInfo info, Lecture l) {
		super(info);
		this.l = l;
		super.setColor(pickColor());
		if (l != null) {
			super.setCourseID(l.getCourseID());
		}
	}

	public Lab(Course c, Lecture l) {
		super(c);
		this.l = l;
		super.setColor(pickColor());
		if (l != null) {
			super.setCourseID(l.getCourseID());
		}
	}

	public int pickColor() {
		if (l != null)
			return secColors[l.getCourseID()];
		return secColors[this.getCourseID()];
	}

	public Lecture getL() {
		return l;
	}

	public void setL(Lecture l) {
		this.l = l;
		super.setColor(pickColor());
		super.setCourseID(l.getCourseID());
		super.decrementID();
	}
}
