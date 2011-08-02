package edu.illinois.coursequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//TODO write better safeInput method....and implement it....
public class AddOrEditCourse extends Activity {
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
		if (saveState()) {
			setResult(Activity.RESULT_OK);
			finish();
		}
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
			if (crnText.equals(getString(R.string.CRN))) // Default assign 0
				crn = 0;
			else
				crn = Integer.parseInt(crnText);
			if (crn < 0)
				return -1;
			return crn;
		} catch (NumberFormatException nfe) {
			// Don't Add Course
			AlertDialog.Builder about = new AlertDialog.Builder(this);
			about.setMessage(getString(R.string.no_crn));
			about.show();
			return -1;
		}
	}

	private boolean saveState() {
		String type = getType();
		CourseInfo info = getInfo();
		if (info == null) {
			Toast.makeText(getApplicationContext(), ("Bad Input, Remove '|'"),
					Toast.LENGTH_SHORT).show();
			return false; // Early Return
		} else if (courseToEdit == null) { // new course
			CourseQuest.getSched().addCourse(info, type);
		} else { // edit course
			CourseQuest.getSched().editCourse(courseToEdit, info);
		}
		return true;
	}

	private CourseInfo getInfo() {
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
		// TODO add description
		if (safeStrings(course, "", professor, section)) {
			CourseInfo info = new CourseInfo(course, "", professor, dayslot,
					section, crn, startTime, endTime, false);
			return info;
		}
		return null;
	}

	private String getType() {
		Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
		int typeIndex = typeSpinner.getSelectedItemPosition();
		String[] typeArray = getResources().getStringArray(R.array.type_array);
		String type = typeArray[typeIndex];
		return type;
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

	private boolean safeStrings(String a, String b, String c, String d) {
		return CourseParser.safeString(a + b + c + d);
	}

	private void populateFields() {
		if (mCourseId != null) {
			courseToEdit = CourseQuest.getSched().getCourse(
					mCourseId.intValue());
			CourseInfo info = courseToEdit.getInfo();
			boolean[] days = info.getDayslot().getDays();
			((CheckBox) findViewById(R.id.mon)).setChecked(days[0]);
			((CheckBox) findViewById(R.id.tue)).setChecked(days[1]);
			((CheckBox) findViewById(R.id.wed)).setChecked(days[2]);
			((CheckBox) findViewById(R.id.thr)).setChecked(days[3]);
			((CheckBox) findViewById(R.id.fri)).setChecked(days[4]);

			((TextView) findViewById(R.id.course)).setText(info.getName());
			((TextView) findViewById(R.id.professor)).setText(info.getProf());
			((TextView) findViewById(R.id.section)).setText(info.getSection());
			((TextView) findViewById(R.id.crn)).setText("" + info.getCrn());
		}
	}
}