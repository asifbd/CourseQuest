package edu.illinois.coursequest;


public class Discussion extends Course {
	private Lecture l;

	public Discussion(CourseInfo info, Lecture l) {
		super(info);
		init(l);
	}

	public Discussion(Course c, Lecture l) {
		super(c);
		init(l);
	}

	private void init(Lecture l) {
		this.l = l;
		super.setColor(pickColor());
		if (l != null) {
			super.setCourseID(l.getCourseID());
			Course.decrementID();
		}
	}
	protected int pickColor() {
		if (l != null) {
			return thirdColors[l.getCourseID()];
		}
		return thirdColors[super.getCourseID()];
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
	
	public String getType(){
		return "Discussion";
	}
}
