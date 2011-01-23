package edu.illinois.coursequest;


public class Lecture extends Course {
	/**
	 * Same as Course but assigns proper Color
	 * @param info
	 */
	public Lecture(CourseInfo info){
		super(info);
		super.setColor(pickColor());
	}
	
	public Lecture(Course c){
		super(c);
		super.setColor(pickColor());
	}
	
	protected int pickColor(){
		return primColors[super.getCourseID()];
	}
	
	public String getType(){
		return "Lecture";
	}
}
