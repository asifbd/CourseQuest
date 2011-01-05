package edu.illinois.coursequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class AddCourse extends Activity {
	private Long mCourseId;
	private Course courseToEdit;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCourseId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState.getSerializable(CourseList.KEY_ID);
		if (mCourseId == null) {
			Bundle extras = getIntent().getExtras();
			mCourseId = extras != null ? extras.getLong(CourseList.KEY_ID)
					: null;
		}
		goEditView(null);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(CourseList.KEY_ID, mCourseId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	public void goEditView(View view) {

		setContentView(R.layout.editlecture);
		makeSpinners();
		populateFields();

	}

	public void makeSpinners() {
		// Initializes the spinners
		Spinner end = (Spinner) findViewById(R.id.endHoursSpinner);
		ArrayAdapter<CharSequence> endAdapter = ArrayAdapter
				.createFromResource(this, R.array.hours_array,
						android.R.layout.simple_spinner_item);
		endAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		end.setAdapter(endAdapter);

		Spinner start = (Spinner) findViewById(R.id.startHoursSpinner);
		ArrayAdapter<CharSequence> startAdapter = ArrayAdapter
				.createFromResource(this, R.array.hours_array,
						android.R.layout.simple_spinner_item);
		startAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		start.setAdapter(startAdapter);

		Spinner type = (Spinner) findViewById(R.id.typeSpinner);
		ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter
				.createFromResource(this, R.array.type_array,
						android.R.layout.simple_spinner_item);
		typeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type.setAdapter(typeAdapter);

	}

	/**
	 * @param view
	 */
	public void save(View view) {
		setResult(Activity.RESULT_OK);
		finish();
	}

	private Course addCourse(String course, String professor, String section,
			String type, int startTime, int endTime, int crn, DaySlot dayslot) {
		Course temp;
		CourseInfo info = new CourseInfo(course, "", professor, dayslot,
				section, crn, startTime, endTime, false);
		if (type.contains("Lecture")) {
			return new Lecture(info);
		}
		Lecture l = CourseList.findLecture(course); // attempt to autoselect
		// lecture
		if (type.contains("Lab")) {
			return new Lab(info, l);
		}
		temp = new Discussion(info, l);
		return temp;
	}

	/**
	 * This Part of the Code checks to see if the CRN is a good Value or
	 * not...Possible Extension would be to see if it exists in the DataBase
	 */
	private int processCrn() {
		int crn = -1;
		try {
			String crnText = ((TextView) findViewById(R.id.crn)).getText()
					.toString();
			if (crnText.equals("CRN")) // Default assign 0
				crn = 0;
			else
				crn = Integer.parseInt(crnText);
			if (crn < 0)
				return -1;
			return crn;
		} catch (NumberFormatException nfe) {
			// Don't Add Course
			AlertDialog.Builder about = new AlertDialog.Builder(this);
			about.setMessage("Error Invalid CRN\n"
					+ "Tip: If CRN exists leave field as CRN");
			about.show();
			return -1;
		}
	}

	private void populateFields() {
		if (mCourseId != null) {
			courseToEdit = CourseList.getCourse(mCourseId.intValue());
			boolean[] days = courseToEdit.getDayslot().getDays();
			((CheckBox) findViewById(R.id.mon)).setChecked(days[0]);
			((CheckBox) findViewById(R.id.tue)).setChecked(days[1]);
			((CheckBox) findViewById(R.id.wed)).setChecked(days[2]);
			((CheckBox) findViewById(R.id.thr)).setChecked(days[3]);
			((CheckBox) findViewById(R.id.fri)).setChecked(days[4]);

			((TextView) findViewById(R.id.course)).setText(courseToEdit
					.getName());
			((TextView) findViewById(R.id.professor)).setText(courseToEdit
					.getProf());
			((TextView) findViewById(R.id.section)).setText(courseToEdit
					.getSection());
			((TextView) findViewById(R.id.crn)).setText(""
					+ courseToEdit.getCrn());
		}
	}

	private DaySlot createDaySlot() {
		// This creates the dayslot based on the checkboxes
		DaySlot dayslot = new DaySlot();
		CheckBox mon = (CheckBox) findViewById(R.id.mon);
		CheckBox tue = (CheckBox) findViewById(R.id.tue);
		CheckBox wed = (CheckBox) findViewById(R.id.wed);
		CheckBox thr = (CheckBox) findViewById(R.id.thr);
		CheckBox fri = (CheckBox) findViewById(R.id.fri);
		dayslot.setDays(new boolean[] { mon.isChecked(), tue.isChecked(),
				wed.isChecked(), thr.isChecked(), fri.isChecked() });
		return dayslot;
	}

	private void saveState() {
		int crn = processCrn();
		if (crn == -1) {
			finish(); // early terminate
		}
		String course = ((TextView) findViewById(R.id.course)).getText()
				.toString();
		String professor = ((TextView) findViewById(R.id.professor)).getText()
				.toString();
		String section = ((TextView) findViewById(R.id.section)).getText()
				.toString();

		Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
		int typeIndex = typeSpinner.getSelectedItemPosition();
		String[] typeArray = getResources().getStringArray(R.array.type_array);
		String type = typeArray[typeIndex];

		Spinner start = (Spinner) findViewById(R.id.startHoursSpinner);
		Spinner end = (Spinner) findViewById(R.id.endHoursSpinner);
		int startTime = start.getSelectedItemPosition() + 8;
		int endTime = end.getSelectedItemPosition() + 8;

		// If the end time is less than or equal to the start time, make the
		// end
		// time be one hour after the start time
		if (endTime <= startTime)
			endTime = startTime + 1;

		DaySlot dayslot = createDaySlot();
		Course temp = addCourse(course, professor, section, type, startTime,
				endTime, crn, dayslot);
		if (courseToEdit == null) // new course
			CourseList.addCourse(temp);
		else { // edit course
			CourseList.editCourse(courseToEdit, temp, mCourseId.intValue());
		}
	}

}