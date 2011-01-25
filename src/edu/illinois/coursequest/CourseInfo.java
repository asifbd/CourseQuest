package edu.illinois.coursequest;

public class CourseInfo {
	private String name, description, prof, section;
	private int crn, startTime, endTime;
	private boolean silent;
	private DaySlot dayslot;

	public CourseInfo(String name, String description, String prof,
			DaySlot dayslot, String section, int crn, int startTime,
			int endTime, boolean silent) {
		this.name = name;
		this.description = description;
		this.prof = prof;
		this.dayslot = dayslot;
		this.section = section;
		this.crn = crn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.silent = silent;
	}

	public String toString() {
		return name + "|" + description + "|" + prof + "|" + dayslot.toString()
				+ section + "|" + crn + "|" + startTime + "|" + endTime + "|"
				+ silent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getCrn() {
		return crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public boolean isSilent() {
		return silent;
	}

	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	public DaySlot getDayslot() {
		return dayslot;
	}

	public void setDayslot(DaySlot dayslot) {
		this.dayslot = dayslot;
	}

}