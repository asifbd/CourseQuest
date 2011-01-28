package edu.illinois.coursequest;
public class Lab extends Discussion {

	/**
	 * Same as Lecture but assigns proper color based on Lecture
	 * 
	 * @param info
	 */
	public Lab(CourseInfo info, Lecture l) {
		super(info, l);
		type = 'l'; //for parsing..../
	}

	public Lab(Course c, Lecture l) {
		super(c, l);
	}

	public String getCourseType(){
		return "Lab";
	}
	
	public int pickColor() {
		if (this.getL() != null)
			return secColors[this.getL().getCourseID() % 8];
		return secColors[this.getCourseID() % 8];
	}
}
