package edu.illinois.coursequest;


public class Lab extends Discussion {

	private Lecture l;

	/**
	 * Same as Lecture but assigns proper color based on Lecture
	 * 
	 * @param info
	 */
	public Lab(CourseInfo info, Lecture l) {
		super(info, l);
	}

	public Lab(Course c, Lecture l) {
		super(c, l);
	}

	public int pickColor() {
		if (l != null)
			return secColors[l.getCourseID()];
		return secColors[this.getCourseID()];
	}
	
	public String getType(){
		return "Lab";
	}
}
