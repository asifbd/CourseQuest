package edu.illinois.coursequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MoreInfo extends Activity {
	private static Course currentCourse;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.moreinfo);
		TextView tv = new TextView(null);
		tv.setText("Hello");
		setContentView(tv);
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
			return; // early return
		}
		finish();
		Toast.makeText(getApplicationContext(), ("No Class There!"),
				Toast.LENGTH_SHORT).show();
	}

	public void goMoreView(View view) {
		setContentView(R.layout.moreinfo);
		infoUpdate(null);
	}

	public void back(View view) {
		finish();
	}
}
