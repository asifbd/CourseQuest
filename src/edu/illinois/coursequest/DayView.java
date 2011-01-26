package edu.illinois.coursequest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DayView extends Activity {
	private Course currentCourse;
	private static int currentDay = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		goDayView(null);
	}

	public void goDayView(View view) {

		setContentView(R.layout.editcourses);
		dayUpdate(null);
	}

	private void dayUpdate(View view) {
		try {
			// This loop makes a row for each course with the course name and
			// time
			addDay();
			addCourses();
		} catch (IndexOutOfBoundsException iobe) {

		}
	}

	private void addCourses() {
		for (int i = 0; i < 12; i++) { // If it fails change
			// to length - 1
			String courseName = "";
			String classTime = "";
			String courseID = "";
			int color = 0;

			currentCourse = CourseQuest.getSched().getCourseAt(i, currentDay);
			if (currentCourse != null) {
				courseName = currentCourse.getInfo().getName();
				classTime = currentCourse.getFormattedTime();
				courseID = "	ID:" + currentCourse.getCourseID();
				int courseColor = currentCourse.getColor();
				color = Color.rgb(Color.red(courseColor), Color
						.green(courseColor), Color.blue(courseColor));// course.getColor();
			}
			addCourseText(i, courseName, classTime, color, courseID);
		}
	}

	private void addDay() {
		TextView valueTV = ((TextView) findViewById(R.id.day));
		ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter
				.createFromResource(this, R.array.days,
						android.R.layout.simple_spinner_item);
		valueTV.setText(dayAdapter.getItem(currentDay));
		valueTV.setTextSize(25);
		valueTV.setPadding(0, 5, 0, 5);
		valueTV.setClickable(true);
		valueTV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO switch to context menu
				currentDay = ++currentDay % 5;
				dayUpdate(null);
			}
		});
	}

	private void addCourseText(int i, String courseName, String times,
			int color, String courseID) {
		ViewGroup innerRootView = (ViewGroup) findViewById(R.id.widget68);
		// Add TextView Dynamically
		TextView valueTV = (TextView) innerRootView.getChildAt(i + 1);
		if (valueTV == null) {
			valueTV = new TextView(this);
			valueTV.setText(i + 8 + "\t|	" + courseName + "\n\t	" + times
					+ courseID);
			valueTV.setTextSize(17);
			valueTV.setId(i);
			valueTV.setClickable(true);
			// TODO implement
			// this.registerForContextMenu(valueTV);
			valueTV.setBackgroundColor(color);
			valueTV.setPadding(0, 5, 0, 5);
			innerRootView.addView(valueTV);

			valueTV.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO switch to context menu
					// Finds Course Based on Text
					String text = (String) ((TextView) v).getText();
					currentCourse = CourseQuest.getSched().findCourse(text);
					goMoreView(null);
				}
			});
			return; // Eary Return
		}
		valueTV.setText(i + 8 + "\t|	" + courseName + "\n\t	" + times
				+ courseID);
		valueTV.setBackgroundColor(color);
	}

	public void infoUpdate(View view) {
		// TODO fix currentCourse to actually be currentCoure
		// Course currentCourse = CourseList.currentCourse;

		if (currentCourse != null) {
			String times = currentCourse.getFormattedTime();
			((TextView) findViewById(R.id.timename)).setText(times);
			CourseInfo info = currentCourse.getInfo();
			((TextView) findViewById(R.id.coursename)).setText(info.getName());
			((TextView) findViewById(R.id.profname)).setText(info.getProf());
			((TextView) findViewById(R.id.sectionname)).setText(info
					.getSection());
			((TextView) findViewById(R.id.crnname))
					.setText(getString(R.string.CRN) + ": " + info.getCrn());
			String classType;
			if (currentCourse instanceof Lecture)
				classType = getString(R.string.lecture);
			else if (currentCourse instanceof Lab)
				classType = getString(R.string.lab);
			else
				classType = getString(R.string.discussion);
			((TextView) findViewById(R.id.lecturename)).setText(classType);
			return; // early return
		}
		goDayView(null);
		Toast.makeText(getApplicationContext(), (getString(R.string.no_class)),
				Toast.LENGTH_SHORT).show();
	}

	public void goMoreView(View view) {
		setContentView(R.layout.moreinfo);
		infoUpdate(null);
	}

	public void back(View view) {
		goDayView(null);
	}

}
