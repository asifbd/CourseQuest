package edu.illinois.coursequest;


public class Discussion extends Course {
	private Lecture l;
	protected char type = 'D';

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
			return thirdColors[l.getCourseID() % 8];
		}
		return thirdColors[super.getCourseID() % 8];
	}
	
	public String toString(){
		return "|" + type + super.toString();
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
