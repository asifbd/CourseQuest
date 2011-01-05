package edu.illinois.coursequest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class DayView extends Activity {
	private Course currentCourse;
	private int currentDay = 0;
	private String[] days = new String[] { "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday" };

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
			int color = 0;
			String courseID = "";
			currentCourse = CourseList.slotPos[i][currentDay];
			if (currentCourse != null) {
				courseName = currentCourse.getName();
				classTime = CourseList.getFormattedTime(currentCourse);
				courseID ="	ID:"+ currentCourse.getCourseID();
				int courseColor = currentCourse.getColor();
				color = Color.rgb(Color.red(courseColor), Color
						.green(courseColor), Color.blue(courseColor));// course.getColor();
			}
			addCourseText(i, courseName, classTime, color, courseID);
		}
	}

	private void addDay() {
		TextView valueTV = ((TextView) findViewById(R.id.day));
		valueTV.setText(days[currentDay]);
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

	private void addCourseText(int i, String courseName, String times, int color, String courseID) {
		ViewGroup innerRootView = (ViewGroup) findViewById(R.id.widget68);
		// Add TextView Dynamically
		TextView valueTV = (TextView) innerRootView.getChildAt(i+1);
		if(valueTV == null){
		valueTV = new TextView(this);
		valueTV.setText(i + 8 + "\t|	" + courseName + "\n\t	" + times + courseID);
		valueTV.setTextSize(17);
		valueTV.setId(i);
		valueTV.setClickable(true);
		//TODO implement
//		this.registerForContextMenu(valueTV); 
		valueTV.setBackgroundColor(color);
		valueTV.setPadding(0, 5, 0, 5);
		innerRootView.addView(valueTV);

		valueTV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO switch to context menu
				//Finds Course Based on Text
				String text = (String) ((TextView) v).getText();
				currentCourse = CourseList.findCourse(text);
				goMoreView(null);
			}
		});
		return; //Eary Return
		}
		valueTV.setText(i + 8 + "\t|	" + courseName + "\n\t	" + times + courseID);
		valueTV.setBackgroundColor(color);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 1, 0, "Edit");
	}
	
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		switch (item.getItemId()) {
		case 1:
			
//			Intent goToEditInfo = new Intent(getApplicationContext(),
//					AddCourse.class);
//			startActivityForResult(goToEditInfo, 0);
//			// Brings up Edit Info
			return true;
		case 2:
//			CourseList.deleteCourse((int)info.id);
			return true;
	}
		return false;
}
	
	public void infoUpdate(View view) {
		// TODO fix currentCourse to actually be currentCoure
		// Course currentCourse = CourseList.currentCourse;

		if (currentCourse != null) {
			String times = CourseList.getFormattedTime(currentCourse);
			((TextView) findViewById(R.id.timename)).setText(times);

			((TextView) findViewById(R.id.coursename)).setText(currentCourse
					.getName());
			((TextView) findViewById(R.id.profname)).setText(currentCourse
					.getProf());
			((TextView) findViewById(R.id.sectionname)).setText(currentCourse
					.getSection());
			((TextView) findViewById(R.id.crnname)).setText("CRN: "
					+ currentCourse.getCrn());
			String classType;
			if (currentCourse instanceof Lecture)
				classType = "Lecture";
			else if (currentCourse instanceof Lab)
				classType = "Lab";
			else
				classType = "Discussion";
			((TextView) findViewById(R.id.lecturename)).setText(classType);
			return; //early return
		}
		goDayView(null);
		Toast.makeText(getApplicationContext(),
				("No Class There!"), Toast.LENGTH_SHORT).show();
	}

	public void goMoreView(View view) {
		setContentView(R.layout.moreinfo);
		infoUpdate(null);
	}
	
	public void back(View view){
		goDayView(null);
	}

}
